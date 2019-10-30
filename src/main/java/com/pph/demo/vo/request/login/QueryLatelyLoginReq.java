package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: pph
 * @Date: 2019/10/30 14:34
 * @Description:
 */
@Data
public class QueryLatelyLoginReq {

    @NotNull(message = "user can not be null!")
    private String user;
}
