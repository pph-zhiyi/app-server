package com.pph.demo.mapper;

import com.pph.demo.model.LoginLogInfo;
import com.pph.demo.vo.request.login.queryUserLoginCountReq;
import com.pph.demo.vo.response.login.queryUserLoginCountRes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface LoginLogInfoMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(LoginLogInfo record);

    int insertSelective(LoginLogInfo record);

    LoginLogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLogInfo record);

    int updateByPrimaryKey(LoginLogInfo record);

    /**
     * 插入
     *
     * @param login 入参
     * @return 结果
     */
    Integer save(LoginLogInfo login);

    /**
     * 查询用户登录记录
     *
     * @param user 用户名
     * @return 结果
     */
    List<LoginLogInfo> queryLoginLogByUser(String user);

    /**
     * 查询用户登录次数
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<queryUserLoginCountRes> queryUserLoginCount(@Param(value = "startTime") Date startTime,
                                                     @Param(value = "endTime") Date endTime);
}