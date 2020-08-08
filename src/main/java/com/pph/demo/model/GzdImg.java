package com.pph.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GzdImg {

    private Long id;

    private String name;

    private String group;

    private Integer index;

    private String url;

    private String description;

    private Date gmtCreate;

    private Date gmtModify;
}