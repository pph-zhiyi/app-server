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
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户相关操作
     */
    private final UserService userService;

    /**
     * 根据条件过滤用户
     *
     * @param filter 入参
     * @return 结果
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageResult<User> queryUser(@RequestBody Map<String, Object> filter) {
        List<User> users = userService.queryUserByTerms(filter);
        Integer total = userService.queryCountByTerms(filter);
        return PageResult.make(users, total, filter);
    }

    /**
     * 新增用户
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Result<Integer> createUser(@RequestBody CreateUserVo req) {
        OvalVerify.verifyObj(req);
        return ApiResult.success(userService.createUser(req));
    }

    /**
     * 根据 id 修改用户
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Integer updateUserById(@RequestBody UpdateUserVo req) {
        OvalVerify.verifyObj(req);
        return userService.updateUserById(req);
    }

    /**
     * 根据 id 删除用户
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Integer deleteUserById(@RequestBody DeleteUserVo req) {
        OvalVerify.verifyObj(req);
        return userService.deleteUserById(req);
    }

    /**
     * 获取所有用户名
     *
     * @return 结果
     */
    @RequestMapping(value = "/query/users", method = RequestMethod.GET)
    public List<String> queryUsers() {
        return userService.queryUsers();
    }

}
