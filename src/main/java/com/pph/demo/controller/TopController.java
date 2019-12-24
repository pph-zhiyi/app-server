package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.model.TopProductData;
import com.pph.demo.service.TopService;
import com.pph.demo.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

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

    @RequestMapping(value = "/excel/to/db", method = RequestMethod.GET)
    public Object ExcelToDb() throws Exception {
        return topService.excelToDb();
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageResult<TopProductData> query(@RequestBody Map<String, Object> filter) {
        List<TopProductData> data = topService.queryProductByTerms(filter);
        int total = topService.queryTotalByTerms(filter);
        return PageResult.make(data, total, filter);
    }

    @SkipToken
    @RequestMapping(value = "/query/filter", method = RequestMethod.GET)
    public Map<String, List<Map<String, String>>> queryFilter() {
        return topService.queryFilter();
    }
}
