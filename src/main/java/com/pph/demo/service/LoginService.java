package com.pph.demo.service;

import com.pph.demo.vo.request.login.LoginVo;

/**
 * @Author: PPH
 * @Date: 2019-05-26 19:00
 * @Description:
 */
public interface LoginService {

    /**
     * 登陆查询
     *
     * @param r
     * @return
     */
    Boolean isExists(LoginVo r);

    /**
     * 注册
     *
     * @param r
     * @return
     */
    Integer register(LoginVo r);
}
