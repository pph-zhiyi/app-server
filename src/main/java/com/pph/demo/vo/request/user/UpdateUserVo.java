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
 * @Date: 2019-08-02 10:31
 * @Description:
 */
@Data
public class UpdateUserVo {
    @NotNull(message = "id can not be null!")
    private Integer id;

//    private String user;

    private String password;

    private String name;

    @CheckWith(value = CheckBeanUtil.CheckSex.class, message = "sex must be (男 | 女)")
    private String sex;

    private Date birthday;

    private String address;

    private String description;
}
