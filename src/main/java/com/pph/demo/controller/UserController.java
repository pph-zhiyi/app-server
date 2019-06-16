package com.pph.demo.controller;

import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.oval.OvalVerifyUtil;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.vo.response.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-22 13:46
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 参数校验
     */
    @Autowired
    private OvalVerifyUtil ovalVerifyUtil;

    /**
     * 用户相关操作
     */
    @Autowired
    private UserService userService;

    /**
     * 根据条件过滤用户
     *
     * @param filter
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageResult queryUser(@RequestBody Map<String, Object> filter) {
        LOGGER.info("^^^queryUser filter: {}", filter);
        List<User> users = userService.queryUserByTerms(filter);
        Integer total = userService.queryCountByTerms(filter);
        return new PageResult(users, total, filter);
    }

    /**
     * 新增用户
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "create/user", method = RequestMethod.POST)
    public Object createUser(@RequestBody CreateUserVo req) {
        LOGGER.info("^^^createUser request: {}", req.toString());
        ovalVerifyUtil.verifyObj(req);
        return userService.createUser(req);
    }
}
