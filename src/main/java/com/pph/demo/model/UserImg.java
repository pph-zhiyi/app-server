package com.pph.demo.model;

import java.util.Date;

public class UserImg {
    private Long id;

    private Long userId;

    private String imgUrl;

    private Date gmtCreate;

    private Date gmtModify;

    public UserImg(Long id, Long userId, String imgUrl, Date gmtCreate, Date gmtModify) {
        this.id = id;
        this.userId = userId;
        this.imgUrl = imgUrl;
        this.gmtCreate = gmtCreate;
        this.gmtModify = gmtModify;
    }

    public UserImg() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}