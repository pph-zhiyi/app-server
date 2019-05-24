package com.pph.demo.vo.request.user;

import com.pph.demo.utils.oval.CheckBeanUtil;
import lombok.Data;
import net.sf.oval.constraint.CheckWith;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: PPH
 * @Date: 2019-05-24 10:10
 * @Description:
 */
@Data
public class CreateUserVo {

    @NotNull(message = "name can not be null!")
    @NotBlank(message = "name can not be blank!")
    @Length(min = 2, max = 15, message = "name length must in (2 ~ 15)")
    private String name;

    @CheckWith(value = CheckBeanUtil.CheckSex.class, message = "sex must be (男 | 女)")
    private String sex;

    @CheckWith(value = CheckBeanUtil.CheckAge.class, message = "age must in (18 ~ 85)")
    private Integer age;
}
