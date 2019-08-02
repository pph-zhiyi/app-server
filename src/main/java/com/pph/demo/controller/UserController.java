package com.pph.demo.controller;

import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.utils.PageResult;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;
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
    public PageResult<User> queryUser(@RequestBody Map<String, Object> filter) {
        LOGGER.info("^^^queryUser filter: {}", filter);
        List<User> users = userService.queryUserByTerms(filter);
        Integer total = userService.queryCountByTerms(filter);
        return new PageResult<>(users, total, filter);
    }

    /**
     * 新增用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public Integer createUser(@RequestBody CreateUserVo request) {
        LOGGER.info("^^^createUser request: {}", request);
        OvalVerify.verifyObj(request);
        return userService.createUser(request);
    }

    /**
     * 根据 id 修改用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public Integer updateUserById(@RequestBody UpdateUserVo request) {
        LOGGER.info("^^^updateUserById request: {}", request);
        OvalVerify.verifyObj(request);
        return userService.updateUserById(request);
    }

    /**
     * 根据 id 删除用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/delete/user", method = RequestMethod.POST)
    public Integer deleteUserById(@RequestBody DeleteUserVo user) {
        LOGGER.info("^^^deleteUserById user: {}", user);
        OvalVerify.verifyObj(user);
        return userService.deleteUserById(user);
    }
}
