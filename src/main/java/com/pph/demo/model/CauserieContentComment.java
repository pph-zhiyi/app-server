package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class CauserieContentComment {

    private Long id;

    private Long contentId;

    private String authorUser;

    private String authorName;

    private String commentUser;

    private String commentName;

    private Boolean isContentAuthor;

    private String oldContent;

    private String commentContent;

    private Date gmtCreate;

    private Date gmtModify;
}