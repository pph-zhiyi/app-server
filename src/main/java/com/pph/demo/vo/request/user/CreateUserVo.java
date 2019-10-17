package com.pph.demo.vo.request.user;

import com.pph.demo.utils.oval.CheckBeanUtil;
import lombok.Data;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import java.util.Date;

/**
 * @Author: PPH
 * @Date: 2019-05-24 10:10
 * @Description:
 */
@Data
public class CreateUserVo {

    private Integer id;

    @NotNull(message = "user can not be null!")
    @NotBlank(message = "user can not be blank!")
    @Length(min = 2, max = 15, message = "user length must in (2 ~ 15)")
    private String user;

    @NotNull(message = "password can not be null!")
    @NotBlank(message = "password can not be blank!")
    private String password;

    private String name;

    @CheckWith(value = CheckBeanUtil.CheckSex.class, message = "sex must be (男 | 女)")
    private String sex;

    private Date birthday;

    private String address;

    private String description;
}
