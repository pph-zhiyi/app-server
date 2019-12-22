package com.pph.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: pph
 * @date: 2019/12/19 20:37
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stack {

    private String threadName;

    private List<String> element;

    public static Stack init(String threadName, List<String> element) {
        return new Stack(threadName, element);
    }
}
