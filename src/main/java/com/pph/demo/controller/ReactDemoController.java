package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pph
 * @Date: 2019/10/3 10:56
 * @Description:
 */
@RestController
@RequestMapping(value = "/react/demo")
public class ReactDemoController {

    @Autowired
    ReactDemoController() {
    }

    @SkipToken
    @RequestMapping(value = "/xjj/server/list", method = RequestMethod.GET)
    public List<String> xjjServerList() {
        return new ArrayList<String>(){
            {
                add("基础按摩");
                add("精油推背");
                add("中药泡脚");
            }
        };
    }
}
