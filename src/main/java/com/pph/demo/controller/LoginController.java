package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.model.LoginLogInfo;
import com.pph.demo.service.LoginService;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.login.*;
import com.pph.demo.vo.response.login.QueryLatelyLoginRes;
import com.pph.demo.vo.response.login.QueryUserLoginCountRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author PPH
 * @date 2019-05-26 18:55
 * @Description:
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    private final LoginService loginService;

    /**
     * 登陆
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> login(@RequestBody LoginVo req) {
        OvalVerify.verifyObj(req);
        return loginService.login(req.getUser(), req.getPassword());
    }

    /**
     * 注册
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Integer register(@RequestBody LoginVo req) {
        OvalVerify.verifyObj(req);
        return loginService.register(req);
    }

    /**
     * 列表
     *
     * @param req 入参
     * @return 结果
     */
    @SkipToken
    @RequestMapping(value = "/query/login/log/by/user", method = RequestMethod.POST)
    public List<LoginLogInfo> queryLoginLog(@RequestBody LoginLogQueryVo req) {
        OvalVerify.verifyObj(req);
        return loginService.queryLoginLogByUser(req.getUser());
    }

    /**
     * 测试 redis
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping("/redis/string")
    public String redisString(@RequestBody RedisStringVo req) {
        OvalVerify.verifyObj(req);
        switch (req.getType()) {
            case "get":
                return loginService.getRedisString(req.getKey());
            case "set":
                loginService.setRedisString(req.getKey(), req.getVal());
                return "OK";
            default:
                throw new IllegalArgumentException("type is must [get or set]");
        }
    }

    /**
     * 用户登录次数
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/query/user/login/count", method = RequestMethod.POST)
    public List<QueryUserLoginCountRes> queryUserLoginCount(@RequestBody QueryUserLoginCountReq req) {
        OvalVerify.verifyObj(req);
        return loginService.queryUserLoginCount(req);
    }

    /**
     * 查询用户最近一次登录记录
     *
     * @param req 入参
     * @return 几个
     */
    @RequestMapping(value = "/query/lately/login", method = RequestMethod.POST)
    public QueryLatelyLoginRes queryLatelyLogin(@RequestBody QueryLatelyLoginReq req) {
        OvalVerify.verifyObj(req);
        return loginService.queryLatelyLogin(req);
    }
}
