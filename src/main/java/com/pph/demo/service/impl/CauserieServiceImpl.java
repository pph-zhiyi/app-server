package com.pph.demo.service.impl;

import com.pph.demo.mapper.CauserieContentMapper;
import com.pph.demo.mapper.UserMapper;
import com.pph.demo.model.CauserieContentLike;
import com.pph.demo.service.CauserieService;
import com.pph.demo.utils.common.Params;
import com.pph.demo.vo.request.causerie.AddCauserieCommentReq;
import com.pph.demo.vo.request.causerie.AddCauserieReq;
import com.pph.demo.vo.request.causerie.DeleteCauserieReq;
import com.pph.demo.vo.request.causerie.LikeCauserieReq;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: pph
 * @date 2019/11/11 17:59
 * @Description:
 */
@Service
public class CauserieServiceImpl implements CauserieService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CauserieServiceImpl.class);

    @Autowired
    public CauserieServiceImpl(CauserieContentMapper causerieContent, UserMapper userMapper) {
        this.causerieContentMapper = causerieContent;
        this.userMapper = userMapper;
    }

    private final CauserieContentMapper causerieContentMapper;

    private final UserMapper userMapper;

    @Override
    public List<QueryCauserieRes> queryCauserieByTerms(Map<String, Object> filter) {
        Params.makePageInfo(filter);
        LOGGER.info("^^^queryCauserieByTerms filter: {}", filter);
        return causerieContentMapper.queryCauserieByTerms(filter);
    }

    @Override
    public Integer queryCountByTerms(Map<String, Object> filter) {
        LOGGER.info("^^^queryCountByTerms filter: {}", filter);
        return causerieContentMapper.queryCountByTerms(filter);
    }

    @Override
    public String likeCauserie(LikeCauserieReq req) {
        LOGGER.info("^^^likeCauserie params: {}", req);
        CauserieContentLike like = causerieContentMapper.queryLikeByUser(req);
        final String lk = "1".equals(req.getLike() + "")
                ? "点赞" : "0".equals(req.getLike() + "")
                ? "踩楼"
                : "取消";
        if (Objects.nonNull(like)) {
            if (Objects.isNull(req.getLike())) {
                return causerieContentMapper.deleteLikeByUser(req) > 0 ?
                        String.format("%s成功", lk)
                        : String.format("%s失败", lk);
            } else {
                return causerieContentMapper.updateLikeByUser(req) > 0
                        ? String.format("%s成功", lk)
                        : String.format("%s失败", lk);
            }
        } else {
            return causerieContentMapper.addLikeByUser(req) > 0
                    ? String.format("%s成功", lk)
                    : String.format("%s失败", lk);
        }
    }

    @Override
    public String addCauserie(AddCauserieReq req) {
        LOGGER.info("^^^addCauserie params: {}", req);
        return causerieContentMapper.addCauserie(req) > 0 ? "发布成功" : "发布失败";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String deleteCauserie(DeleteCauserieReq req) {
        LOGGER.info("^^^deleteCauserie params: {}", req);
        if (Objects.isNull(causerieContentMapper.queryContentByIdUser(req.getId(), req.getUser()))) {
            throw new IllegalArgumentException("仅创建人课删除");
        } else {
            int count;
            if ((count = causerieContentMapper.deleteCauserie(req)) > 0) {
                causerieContentMapper.deleteCauserieLike(req);
            }
            return count > 0 ? "删除成功" : "删除失败";
        }
    }

    @Override
    public String addCauserieComment(AddCauserieCommentReq req) {
        LOGGER.info("^^^addCauserieComment params: {}", req);
        return causerieContentMapper.addCauserieComment(req) > 0 ? "发表评论成功" : "发表评论失败";
    }
}
