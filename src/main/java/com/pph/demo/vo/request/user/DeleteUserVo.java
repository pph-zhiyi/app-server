package com.pph.demo.vo.request.user;

import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @author PPH
 * @date 2019-08-02 10:48
 * @description
 */
@Data
public class DeleteUserVo {
    @NotNull(message = "id can not be null!")
    private Integer id;
}
