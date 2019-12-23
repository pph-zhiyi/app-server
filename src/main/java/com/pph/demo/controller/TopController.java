package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: pph
 * @date: 2019/12/22 19:12
 * @description:
 */
@RestController
@RequestMapping("/top")
public class TopController {

    @Autowired
    public TopController(TopService topService) {
        this.topService = topService;
    }

    private final TopService topService;

    @SkipToken
    @RequestMapping(value = "/excel/to/db", method = RequestMethod.GET)
    public Object ExcelToDb() throws Exception {
        return topService.excelToDb();
    }
}
