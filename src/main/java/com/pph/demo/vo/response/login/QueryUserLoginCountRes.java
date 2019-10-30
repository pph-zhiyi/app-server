package com.pph.demo.vo.response.login;

import lombok.Data;

/**
 * @Author: pph
 * @Date: 2019/10/24 19:12
 * @Description:
 */
@Data
public class QueryUserLoginCountRes {

    private String user;

    private Long loginCount;
}
