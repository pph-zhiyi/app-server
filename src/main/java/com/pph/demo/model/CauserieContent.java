package com.pph.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class CauserieContent {
    private Long id;

    private String user;

    private String name;

    private Date gmtCreate;

    private Date gmtModify;

    private String content;
}