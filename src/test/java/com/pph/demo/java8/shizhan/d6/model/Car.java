package com.pph.demo.java8.shizhan.d6.model;

import lombok.Data;

import java.util.Optional;

/**
 * @author: pph
 * @date: 2019/12/10 09:34
 * @description:
 */
@Data
public class Car {

    private Optional<Insurance> insurance;
}
