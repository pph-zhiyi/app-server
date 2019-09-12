package com.pph.demo.service;

import com.pph.demo.model.Login;
import com.pph.demo.vo.request.login.LoginVo;

import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-05-26 19:00
 * @Description:
 */
public interface LoginService {
    /**
     * 登陆查询
     *
     * @param user
     * @param password
     * @return
     */
    Boolean isExists(String user, String password);

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
}
