package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    /**
     * 主键-ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 住址
     */
    private String address;
    /**
     * 描述
     */
    private String description;
    /**
     * 记录创建时间
     */
    private Date gmtCreate;
    /**
     * 记录修改时间
     */
    private Date gmtModify;
}