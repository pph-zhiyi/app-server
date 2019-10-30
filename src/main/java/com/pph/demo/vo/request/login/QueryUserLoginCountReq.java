package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: pph
 * @Date: 2019/10/24 19:07
 * @Description:
 */
@Data
public class QueryUserLoginCountReq {

    @NotNull(message = "startTime can not be null!")
    private Long startTime;

    @NotNull(message = "endTime can not be null!")
    private Long endTime;
}
