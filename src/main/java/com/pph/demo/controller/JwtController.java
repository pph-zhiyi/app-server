package com.pph.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.pph.demo.annotation.SkipToken;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author pph
 * @date 2019/9/19 19:15
 * @description
 */
@RestController
@RequestMapping("/jwt")
public class JwtController {
    @Autowired
    public JwtController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @RequestMapping("/test")
    public String test() {
        return "hello jwt";
    }

    @SkipToken
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@RequestBody User user) {
        String name = user.getUser();
        String password = user.getPassword();
        User userInfo = userService.queryUserByUserPwd(name, password);
        if (Objects.isNull(userInfo)) {
            throw new IllegalArgumentException("账户密码不匹配");
        }
        //登录成功获得token
        String token = JwtUtil.encode(userInfo.getUser());
        JSONObject result = new JSONObject();
        result.put("token", token);

        return result;
    }
}
