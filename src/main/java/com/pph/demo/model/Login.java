package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Login {

    private Integer id;

    private String user;

    private String password;

    private Date gmtCreate;

    private Date gmtModify;
}