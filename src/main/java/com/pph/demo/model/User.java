package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;

    private String name;

    private String sex;

    private Integer age;

    private Date gmt_create;

    private Date gmt_modify;
}