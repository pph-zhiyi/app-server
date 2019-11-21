package com.pph.demo.controller;

import com.pph.demo.service.CauserieService;
import com.pph.demo.utils.PageResult;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.causerie.AddCauserieCommentReq;
import com.pph.demo.vo.request.causerie.AddCauserieReq;
import com.pph.demo.vo.request.causerie.DeleteCauserieReq;
import com.pph.demo.vo.request.causerie.LikeCauserieReq;
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

    /**
     * 获取记录列表
     *
     * @param filter 过滤入参
     * @return 结果
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public PageResult<QueryCauserieRes> queryCauserie(@RequestBody Map<String, Object> filter) {
        List<QueryCauserieRes> result = causerieService.queryCauserieByTerms(filter);
        Integer total = causerieService.queryCountByTerms(filter);
        return PageResult.make(result, total, filter);
    }

    /**
     * 用户点赞
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public Object likeCauserie(@RequestBody LikeCauserieReq req) {
        OvalVerify.verifyObj(req);
        return causerieService.likeCauserie(req);
    }

    /**
     * 新增记录
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Object addCauserie(@RequestBody AddCauserieReq req) {
        OvalVerify.verifyObj(req);
        return causerieService.addCauserie(req);
    }

    /**
     * 删除记录
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Object deleteCauserie(@RequestBody DeleteCauserieReq req) {
        OvalVerify.verifyObj(req);
        return causerieService.deleteCauserie(req);
    }

    /**
     * 新增评论
     *
     * @param req 入参
     * @return 结果
     */
    @RequestMapping(value = "/add/comment", method = RequestMethod.POST)
    public Object addCauserieComment(@RequestBody AddCauserieCommentReq req) {
        OvalVerify.verifyObj(req);
        return causerieService.addCauserieComment(req);
    }
}
