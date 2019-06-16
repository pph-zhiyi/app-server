package com.pph.demo.redis;

import lombok.Data;

/**
 * @Author: PPH
 * @Date: 2019-06-15 19:20
 * @Description:
 */
@Data
public class UserVo {
    public static final String T_USER = "t_user";

    private String name;
    private String address;
    private Integer age;
}
