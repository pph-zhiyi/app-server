package com.pph.demo.java8.shizhan.d5.patterns.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author pph
 * @date 2019/12/9 09:32
 * @description
 */
@Data
@AllArgsConstructor
@ToString
public class Customer {

    private final Integer id;

    private final String name;
}
