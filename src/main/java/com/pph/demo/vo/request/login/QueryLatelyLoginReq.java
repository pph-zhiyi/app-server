package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author pph
 * @date 2019/10/30 14:34
 * @description
 */
@Data
public class QueryLatelyLoginReq {

    @NotNull(message = "user can not be null!")
    private String user;
}
