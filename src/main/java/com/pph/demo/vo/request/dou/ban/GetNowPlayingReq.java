package com.pph.demo.vo.request.dou.ban;

import lombok.Data;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * @author pph
 * @date 2020/1/31 15:33
 * @description
 */
@Data
public class GetNowPlayingReq {

    @NotNull(message = "city can not be null!")
    @Min(value = 0, message = "pageNo min 0")
    private Integer pageNo;

    @NotNull(message = "city can not be null!")
    @Length(min = 1, max = 100, message = "pageSize length must in (1 ~ 100)")
    private Integer pageSize;

    @NotNull(message = "city can not be null!")
    @NotBlank(message = "city ca not be blank!")
    private String city;
}
