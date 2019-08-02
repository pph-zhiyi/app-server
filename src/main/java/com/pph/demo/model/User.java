package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private String sex;

    private Date birthday;

    private Integer age;

    private String address;

    private String description;

    private Date gmtCreate;

    private Date gmtModify;
}