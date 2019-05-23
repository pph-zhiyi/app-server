package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private Date gmtCreater;

    private Date gmtModefy;

    public User(Integer id, String name, String sex, Integer age, Date gmtCreater, Date gmtModefy) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.gmtCreater = gmtCreater;
        this.gmtModefy = gmtModefy;
    }
}