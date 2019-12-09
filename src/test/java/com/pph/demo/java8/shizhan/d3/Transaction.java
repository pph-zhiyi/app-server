package com.pph.demo.java8.shizhan.d3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: pph
 * @date: 2019/12/4 09:22
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {
    /**
     * 交易人
     */
    private Trader trader;
    /**
     * 交易时间
     */
    private int year;
    /**
     * 交易额
     */
    private int value;
}
