package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author pph
 * @date 2019/10/21 11:30
 * @description
 */
@Data
public class LoginLogQueryVo {

    @NotNull(message = "user can not be null!")
    @NotBlank(message = "user can not be blank!")
    private String user;
}
