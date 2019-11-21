package com.pph.demo.service;

import com.pph.demo.vo.request.causerie.AddCauserieCommentReq;
import com.pph.demo.vo.request.causerie.AddCauserieReq;
import com.pph.demo.vo.request.causerie.DeleteCauserieReq;
import com.pph.demo.vo.request.causerie.LikeCauserieReq;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;

import java.util.List;
import java.util.Map;

/**
 * @Author: pph
 * @Date: 2019/11/11 17:57
 * @Description:
 */
public interface CauserieService {
    /**
     * 查询记录
     *
     * @param filter 入参
     * @return 结果
     */
    List<QueryCauserieRes> queryCauserieByTerms(Map<String, Object> filter);

    /**
     * 获取总条数
     *
     * @param filter 入参
     * @return 结果
     */
    Integer queryCountByTerms(Map<String, Object> filter);

    /**
     * 用户点赞
     *
     * @param req 入参
     * @return 结果
     */
    String likeCauserie(LikeCauserieReq req);

    /**
     * 新增记录
     *
     * @param req 入参
     * @return 结果
     */
    String addCauserie(AddCauserieReq req);

    /**
     * 删除记录
     *
     * @param req 入参
     * @return 结果
     */
    String deleteCauserie(DeleteCauserieReq req);

    /**
     * 新增评论
     *
     * @param req 入参
     * @return 结果
     */
    String addCauserieComment(AddCauserieCommentReq req);
}
