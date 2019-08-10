package com.pph.demo.controller;

import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.common.ApiResult;
import com.pph.demo.utils.common.Result;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.utils.PageResult;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;
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
    public Object queryUser(@RequestBody Map<String, Object> filter) {
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
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<Integer> createUser(@RequestBody CreateUserVo request) {
        OvalVerify.verifyObj(request);
        return ApiResult.success(userService.createUser(request));
    }

    /**
     * 根据 id 修改用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer updateUserById(@RequestBody UpdateUserVo request) {
        OvalVerify.verifyObj(request);
        return userService.updateUserById(request);
    }

    /**
     * 根据 id 删除用户
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer deleteUserById(@RequestBody DeleteUserVo user) {
        OvalVerify.verifyObj(user);
        return userService.deleteUserById(user);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public Object test(@RequestBody CreateUserVo request) {
        int i = 1 / 0;
        return "hello";
    }
}
