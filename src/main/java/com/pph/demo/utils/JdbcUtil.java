package com.pph.demo.utils;

import com.alibaba.fastjson.JSON;
import com.pph.demo.model.User;
import com.pph.demo.utils.common.Params;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.NumberUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * @Author: PPH
 * @Date: 2019-07-30 10:46
 * @Description:
 */
public final class JdbcUtil {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcUtil.class);

    private JdbcUtil() {

    }

    /**
     * 获取 Connection
     *
     * @param info
     * @return
     * @throws SQLException
     */
    public static Connection getConnection(Map<CP, String> info) throws SQLException {
        return getConnection(info.get(CP.HOST), info.get(CP.USER), info.get(CP.PWD));
    }

    /**
     * 获取 Connection
     *
     * @param host
     * @param user
     * @param pwd
     * @return
     */
    public static Connection getConnection(String host, String user, String pwd) throws SQLException {
        LOGGER.info("^^^ getConnection host: {}, user: {}, pwd: {}", host, user, pwd);

        if (StringUtils.isEmpty(host) || StringUtils.isEmpty(user) || StringUtils.isEmpty(pwd))
            throw new IllegalArgumentException("db info error!");

        return DriverManager.getConnection(host, user, pwd);
    }

    /**
     * 执行查询 SQL 并返回结果集，默认: List<Map<String, Object>>
     *
     * @param info
     * @param sql
     * @param <T>
     * @return
     */
    public static <T extends Map<String, Object>> List<T> executeQuery(Map<CP, String> info, String sql) {
        return executeQuery(info, sql, null);
    }

    /**
     * 执行查询 SQL 并返回结果集
     *
     * @param info
     * @param sql
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> executeQuery(Map<CP, String> info, String sql, Class<T> clz) {
        LOGGER.info("^^^ executeQuery info: {} \n sql: {}", info, sql);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection(info);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            return Objects.isNull(clz) ? rsToMap(rs) : rsToObj(rs, clz);
        } catch (Exception e) {
            throw new RuntimeException("executeQuery error: ".concat(e.getMessage()));
        } finally {
            close(conn, ps, rs);
        }
    }

    /**
     * 将 ResultSet 格式化成 Map 集合
     *
     * @param rs
     * @param <T>
     * @return
     * @throws SQLException
     */
    @SuppressWarnings("unchecked")
    private static <T> List<T> rsToMap(ResultSet rs) throws SQLException {
        List<T> result = new ArrayList<>();

        while (rs.next()) {
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();

            Map<String, Object> map = new HashMap<String, Object>(columnCount) {
                {
                    for (int i = 1; i <= columnCount; i++)
                        put(md.getColumnLabel(i), rs.getObject(i));
                }
            };

            result.add((T) map);
        }

        return result;
    }

    /**
     * 将 ResultSet 格式化成指定对象类型的集合
     *
     * @param rs
     * @param clz
     * @param <E>
     * @return
     * @throws Exception
     */
    private static <E> List<E> rsToObj(ResultSet rs, Class<E> clz) throws Exception {
        List<E> result = new ArrayList<>();

        while (rs.next()) {
            E obj = clz.newInstance();
            ResultSetMetaData md = rs.getMetaData();

            for (int index = 1; index <= md.getColumnCount(); index++) {
                switch (md.getColumnType(index)) {
                    case Types.VARCHAR:
                    case Types.CHAR:
                        invoke(rs, clz, obj, index, md, String.class);
                        break;
                    case Types.INTEGER:
                    case Types.SMALLINT:
                        invoke(rs, clz, obj, index, md, Integer.class);
                        break;
                    case Types.BIGINT:
                        invoke(rs, clz, obj, index, md, Long.class);
                        break;
                    case Types.DATE:
                    case Types.TIMESTAMP:
                        try {
                            invoke(rs, clz, obj, index, md, Date.class);
                        } catch (Exception e) {
                            invoke(rs, clz, obj, index, md, String.class);
                        }
                        break;
                    case Types.DECIMAL:
                        invoke(rs, clz, obj, index, md, BigDecimal.class);
                        break;
                    case Types.DOUBLE:
                    case Types.NUMERIC:
                        invoke(rs, clz, obj, index, md, Double.class);
                        break;
                    case Types.BIT:
                        invoke(rs, clz, obj, index, md, Boolean.class);
                        break;
                    default:
                        break;
                }
            }
            result.add(obj);
        }

        return result;
    }

    /**
     * 通过反射给对象赋值
     *
     * @param rs
     * @param clz
     * @param obj
     * @param index
     * @param md
     * @param type
     * @param <E>
     * @throws Exception
     */
    private static <E> void invoke(ResultSet rs, Class<E> clz, E obj, int index, ResultSetMetaData md,
                                   Class<?> type) throws Exception {
        Method method;
        method = clz.getMethod("set".concat(Params.makeColumnName(md.getColumnName(index))), type);

        if (Objects.nonNull(method))
            method.invoke(obj, getResultSetValue(rs, index, type));
    }

    /**
     * 关闭资源
     *
     * @param conn
     * @param st
     * @param rs
     */
    public static void close(Connection conn, Statement st, ResultSet rs) {
        /*
         * 关闭 ResultSet
         */
        closeResultSet(rs);
        /*
         * 关闭 Statement
         */
        closeStatement(st);
        /*
         * 关闭 Connection
         */
        closeConnection(conn);
    }

    /**
     * 关闭 Connection
     *
     * @param conn
     */
    private static void closeConnection(Connection conn) {
        if (Objects.nonNull(conn)) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("Could not close JDBC Connection", e);
            }
        }
        conn = null;
    }

    /**
     * 关闭 Statement
     *
     * @param st
     */
    private static void closeStatement(Statement st) {
        if (Objects.nonNull(st)) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.error("Could not close JDBC Statement", e);
            }
        }
        st = null;
    }

    /**
     * 关闭 ResultSet
     *
     * @param rs
     */
    private static void closeResultSet(ResultSet rs) {
        if (Objects.nonNull(rs)) {
            try {
                rs.close();
            } catch (SQLException e) {
                LOGGER.trace("Could not close JDBC ResultSet", e);
            }
        }
        rs = null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getResultSetValue(ResultSet rs, int index, Class<T> requiredType) throws SQLException {
        if (requiredType == null) {
            return (T) getResultSetValue(rs, index, Object.class);
        } else if (String.class == requiredType) {
            return (T) rs.getString(index);
        } else {
            Object value;
            if (Boolean.TYPE != requiredType && Boolean.class != requiredType) {
                if (Byte.TYPE != requiredType && Byte.class != requiredType) {
                    if (Short.TYPE != requiredType && Short.class != requiredType) {
                        if (Integer.TYPE != requiredType && Integer.class != requiredType) {
                            if (Long.TYPE != requiredType && Long.class != requiredType) {
                                if (Float.TYPE != requiredType && Float.class != requiredType) {
                                    if (Double.TYPE != requiredType && Double.class != requiredType && Number.class != requiredType) {
                                        if (BigDecimal.class == requiredType) {
                                            return (T) rs.getBigDecimal(index);
                                        }

                                        if (Date.class == requiredType) {
                                            return (T) rs.getDate(index);
                                        }

                                        if (Time.class == requiredType) {
                                            return (T) rs.getTime(index);
                                        }

                                        if (Timestamp.class != requiredType) {
                                            if (byte[].class == requiredType) {
                                                return (T) rs.getBytes(index);
                                            }

                                            if (Blob.class == requiredType) {
                                                return (T) rs.getBlob(index);
                                            }

                                            if (Clob.class == requiredType) {
                                                return (T) rs.getClob(index);
                                            }

                                            if (requiredType.isEnum()) {
                                                Object obj = rs.getObject(index);
                                                if (obj instanceof String) {
                                                    return (T) obj;
                                                }

                                                if (obj instanceof Number) {
                                                    return (T) NumberUtils.convertNumberToTargetClass((Number) obj, Integer.class);
                                                }

                                                return (T) rs.getString(index);
                                            }

                                            try {
                                                return rs.getObject(index, requiredType);
                                            } catch (AbstractMethodError var5) {
                                                LOGGER.debug("JDBC driver does not implement JDBC 4.1 'getObject(int, Class)' method", var5);
                                            } catch (SQLFeatureNotSupportedException var6) {
                                                LOGGER.debug("JDBC driver does not support JDBC 4.1 'getObject(int, Class)' method", var6);
                                            } catch (SQLException var7) {
                                                LOGGER.debug("JDBC driver has limited support for JDBC 4.1 'getObject(int, Class)' method", var7);
                                            }

                                            String typeName = requiredType.getSimpleName();
                                            if ("LocalDate".equals(typeName)) {
                                                return (T) rs.getDate(index);
                                            }

                                            if ("LocalTime".equals(typeName)) {
                                                return (T) rs.getTime(index);
                                            }

                                            if ("LocalDateTime".equals(typeName)) {
                                                return (T) rs.getTimestamp(index);
                                            }

                                            return (T) getResultSetValue(rs, index, Object.class);
                                        }

                                        return (T) rs.getTimestamp(index);
                                    }

                                    value = rs.getDouble(index);
                                } else {
                                    value = rs.getFloat(index);
                                }
                            } else {
                                value = rs.getLong(index);
                            }
                        } else {
                            value = rs.getInt(index);
                        }
                    } else {
                        value = rs.getShort(index);
                    }
                } else {
                    value = rs.getByte(index);
                }
            } else {
                value = rs.getBoolean(index);
            }

            return rs.wasNull() ? null : (T) value;
        }
    }

    /**
     * 连接池属性
     */
    public enum CP {
        /**
         * 主机地址
         */
        HOST,
        /**
         * 用户名
         */
        USER,
        /**
         * 密码
         */
        PWD;
    }

    public static void main(String[] args) throws Exception {

        HashMap<CP, String> info = new HashMap<CP, String>(4) {
            {
                put(CP.HOST, "jdbc:mysql://localhost:3306/pph");
                put(CP.USER, "root");
                put(CP.PWD, "pph123");
            }
        };
        String sql = "SELECT * FROM `user`";

        List<Map<String, Object>> lists = executeQuery(info, sql);
        System.out.println(JSON.toJSONString(lists));

        List<User> users = executeQuery(info, sql, User.class);
        System.out.println(JSON.toJSONString(users));
    }
}
