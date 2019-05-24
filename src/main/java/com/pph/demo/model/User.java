package com.pph.demo.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private Date gmtCreater;

    private Date gmtModify;

    public User(Integer id, String name, String sex, Integer age, Date gmtCreater, Date gmtModify) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.gmtCreater = gmtCreater;
        this.gmtModify = gmtModify;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getGmtCreater() {
        return gmtCreater;
    }

    public void setGmtCreater(Date gmtCreater) {
        this.gmtCreater = gmtCreater;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}