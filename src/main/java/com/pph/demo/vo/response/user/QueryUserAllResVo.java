package com.pph.demo.vo.response.user;

import lombok.Data;

import java.util.Date;

/**
 * @Author: PPH
 * @Date: 2019-05-24 11:06
 * @Description:
 */
@Data
public class QueryUserAllResVo {

    private Integer id;

    private String name;

    private String sex;

    private Date gmt_create;

    private Date gmt_modify;
}
