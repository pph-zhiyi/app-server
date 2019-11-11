package com.pph.demo.controller;

import com.pph.demo.annotation.SkipToken;
import com.pph.demo.service.CauserieService;
import com.pph.demo.utils.PageResult;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: pph
 * @Date: 2019/11/11 17:56
 * @Description:
 */
@RestController
@RequestMapping("/causerie")
public class CauserieController {

    @Autowired
    public CauserieController(CauserieService causerieService) {
        this.causerieService = causerieService;
    }

    private final CauserieService causerieService;

    @SkipToken
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Object queryCauserie(@RequestBody Map<String, Object> filter) {
        List<QueryCauserieRes> result = causerieService.queryCauserieByTerms(filter);
        Integer total = causerieService.queryCountByTerms(filter);
        return PageResult.make(result, total, filter);
    }
}
