package com.pph.demo.service.impl;

import com.pph.demo.mapper.CauserieContentMapper;
import com.pph.demo.service.CauserieService;
import com.pph.demo.utils.common.Params;
import com.pph.demo.vo.response.causerie.QueryCauserieRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: pph
 * @Date: 2019/11/11 17:59
 * @Description:
 */
@Service
public class CauserieServiceImpl implements CauserieService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CauserieServiceImpl.class);

    @Autowired
    public CauserieServiceImpl(CauserieContentMapper causerieContent) {
        this.causerieContentMapper = causerieContent;
    }

    private final CauserieContentMapper causerieContentMapper;

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
}
