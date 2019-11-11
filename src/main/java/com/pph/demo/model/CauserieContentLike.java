package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class CauserieContentLike {

    private Long id;

    private Long contentId;

    private String likeUser;

    private String likeName;

    private Integer isLike;

    private Date gmtCreate;

    private Date gmtModify;
}