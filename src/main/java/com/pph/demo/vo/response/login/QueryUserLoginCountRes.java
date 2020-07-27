package com.pph.demo.vo.response.login;

import lombok.Data;

/**
 * @author pph
 * @date 2019/10/24 19:12
 * @description
 */
@Data
public class QueryUserLoginCountRes {

    private String user;

    private Long loginCount;
}
