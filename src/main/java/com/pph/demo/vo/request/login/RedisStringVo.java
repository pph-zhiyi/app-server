package com.pph.demo.vo.request.login;

import lombok.Data;
import net.sf.oval.constraint.NotBlank;

/**
 * @author PPH
 * @date 2019-06-21 16:12
 * @Description:
 */
@Data
public class RedisStringVo {

    @NotBlank(message = "key can not be blank!")
    private String key;

    private String val;

    @NotBlank(message = "type can not be blank!")
    private String type;
}
