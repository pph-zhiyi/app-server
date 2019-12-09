package com.pph.demo.java8.shizhan.d3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: pph
 * @date: 2019/12/4 09:19
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Trader {
    /**
     * 姓名
     */
    private String name;
    /**
     * 城市
     */
    private String city;
}
