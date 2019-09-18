package com.pph.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginLogInfo implements Serializable {
    private Integer id;

    private String user;

    private String password;

    private String userInfo;

    private Date entryTime;

    private Integer isLogin;

    private Date gmtCreate;

    private Date gmtModify;
}