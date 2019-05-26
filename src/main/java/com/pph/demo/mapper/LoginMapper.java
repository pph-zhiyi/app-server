package com.pph.demo.mapper;

import com.pph.demo.model.Login;
import com.pph.demo.vo.request.login.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {

    /**
     * 根据用户与密码查询
     *
     * @param user
     * @param password
     * @return
     */
    Login queryLoginByUserAndPwd(@Param(value = "user") String user,
                                 @Param(value = "password") String password);

    /**
     * 注册
     *
     * @param r
     * @return
     */
    Integer createLogin(LoginVo r);
}