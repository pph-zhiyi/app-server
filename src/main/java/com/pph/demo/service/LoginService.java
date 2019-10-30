package com.pph.demo.service;

import com.pph.demo.model.LoginLogInfo;
import com.pph.demo.vo.request.login.LoginVo;
import com.pph.demo.vo.request.login.QueryLatelyLoginReq;
import com.pph.demo.vo.request.login.QueryUserLoginCountReq;
import com.pph.demo.vo.response.login.QueryLatelyLoginRes;
import com.pph.demo.vo.response.login.QueryUserLoginCountRes;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-26 19:00
 * @Description:
 */
public interface LoginService {
    /**
     * 登陆
     *
     * @param user
     * @param password
     * @return
     */
    Map<String, Object> login(String user, String password);

    /**
     * 注册
     *
     * @param r
     * @return
     */
    Integer register(LoginVo r);

    /**
     * 查询所有用户名
     *
     * @return
     */
    List<String> queryUserNameAll();

    /**
     * 通过 key 获取 redis 一条 String 类型的缓存数据
     *
     * @param key
     * @return
     */
    String getRedisString(String key);

    /**
     * 往 redis 插入一条 String 类型的数据
     *
     * @param key
     * @param val
     */
    void setRedisString(String key, String val);

    /**
     * 查询登录记录
     *
     * @param user
     * @return
     */
    List<LoginLogInfo> queryLoginLogByUser(String user);

    /**
     * 查询用户登录次数
     *
     * @param req
     * @return
     */
    List<QueryUserLoginCountRes> queryUserLoginCount(QueryUserLoginCountReq req);

    /**
     * 查询用户最近一次登录记录
     *
     * @param req
     * @return
     */
    QueryLatelyLoginRes queryLatelyLogin(QueryLatelyLoginReq req);
}
