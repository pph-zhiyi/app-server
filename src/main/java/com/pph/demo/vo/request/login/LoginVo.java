package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: PPH
 * @Date: 2019-05-26 18:52
 * @Description:
 */
@Data
public class LoginVo {

    @NotNull(message = "user can not be null!")
    @NotBlank(message = "user can not be blank!")
    private String user;

    @NotNull(message = "password can not be null!")
    @NotBlank(message = "password can not be blank!")
    private String password;
}
