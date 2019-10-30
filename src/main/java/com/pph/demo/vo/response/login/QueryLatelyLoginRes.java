package com.pph.demo.vo.response.login;

import lombok.Data;

import java.util.Date;

/**
 * @Author: pph
 * @Date: 2019/10/30 14:30
 * @Description:
 */
@Data
public class QueryLatelyLoginRes {

    private String user;

    private String password;

    private String userInfo;

    private Date entryTime;

    private Integer isLogin;
}
