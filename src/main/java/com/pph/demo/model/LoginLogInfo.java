package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: pph
 * @Date: 2019/9/6 17:50
 * @Description:
 */
@Data
public class LoginLogInfo {
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
     * 用户详细信息
     */
    private String userInfo;
    /**
     * 登录时间
     */
    private Date entryTime;
    /**
     * 是否登录成功
     */
    private Boolean isLogin;
    /**
     * 记录创建时间
     */
    private Date gmtCreate;
    /**
     * 记录修改时间
     */
    private Date gmtModify;
}
