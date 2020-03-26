package com.pph.demo.service;

import com.pph.demo.model.User;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 11:15
 * @Description:
 */
public interface UserService {
    /**
     * 根据条件过滤查询
     *
     * @param filter 过滤条件
     * @return 结果
     */
    List<User> queryUserByTerms(Map<String, Object> filter);

    /**
     * 总条数
     *
     * @param filter 过滤条件
     * @return 结果
     */
    Integer queryCountByTerms(Map<String, Object> filter);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Integer createUser(CreateUserVo user);

    /**
     * 根据 id 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    Integer updateUserById(UpdateUserVo user);

    /**
     * 根据 id 删除用户
     *
     * @param user 用户
     * @return 结果
     */
    Integer deleteUserById(DeleteUserVo user);

    /**
     * 根据用户名与密码查询用户
     *
     * @param user     用户
     * @param password 密码
     * @return 结果
     */
    User queryUserByUserPwd(String user, String password);

    /**
     * 注册
     *
     * @param user     用户
     * @param password 密码
     * @return 结果
     */
    Integer register(String user, String password);

    /**
     * 获取所有用户名
     *
     * @return 结果
     */
    List<String> queryUsers();

    /**
     * 异步测试
     * @return
     */
    List<String> asyncTest();
}
