package com.pph.demo.mapper;

import com.pph.demo.model.User;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
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
     * @param user 用户
     * @return 结果
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
    User queryUserByUserPwd(@Param(value = "user") String user,
                            @Param(value = "password") String password);

    /**
     * 简单注册
     *
     * @param user     用户
     * @param password 密码
     * @return 结果
     */
    Integer register(@Param(value = "user") String user,
                     @Param(value = "password") String password);

    /**
     * 根据用户名查询用户
     *
     * @param user 用户
     * @return 结果
     */
    @Select("SELECT * FROM `user` WHERE `user` = #{user}")
    User queryUserByUser(@Param(value = "user") String user);

    @Select("SELECT DISTINCT `user` FROM `user`")
    List<String> queryUsers();
}