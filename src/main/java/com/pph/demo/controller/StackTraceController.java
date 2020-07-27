package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.model.Stack;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author pph
 * @date 2019/12/19 19:59
 * @description
 */
@RestController
@RequestMapping("/stack")
public class StackTraceController {

    @SkipToken(required = false)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Stack> get() {
        return Thread.getAllStackTraces().entrySet().stream()
                .map(e -> Stack.init(e.getKey().getName(),
                        Arrays.stream(e.getValue())
                                .map(String::valueOf)
                                .collect(toList())))
                .collect(toList());
    }
}
