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

    @NotNull(message = "name can not be null!")
    @NotBlank(message = "name can not be blank!")
    @Length(min = 2, max = 15, message = "name length must in (2 ~ 15)")
    private String name;

    @CheckWith(value = CheckBeanUtil.CheckSex.class, message = "sex must be (男 | 女)")
    private String sex;

    private Date birthday;

    @CheckWith(value = CheckBeanUtil.CheckAge.class, message = "age must in (18 ~ 85)")
    private Integer age;

    private String address;

    private String description;
}
