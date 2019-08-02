package com.pph.demo.vo.request.user;

import lombok.Data;
import net.sf.oval.constraint.NotNull;

/**
 * @Author: PPH
 * @Date: 2019-08-02 10:48
 * @Description:
 */
@Data
public class DeleteUserVo {
    @NotNull(message = "id can not be null!")
    private Integer id;
}
