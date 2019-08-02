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
     * @param filter
     * @return
     */
    List<User> queryUserByTerms(Map<String, Object> filter);

    /**
     * 总条数
     *
     * @param filter
     * @return
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
     * @param user
     * @return
     */
    Integer updateUserById(UpdateUserVo user);

    /**
     * 根据 id 删除用户
     *
     * @param user
     * @return
     */
    Integer deleteUserById(DeleteUserVo user);
}
