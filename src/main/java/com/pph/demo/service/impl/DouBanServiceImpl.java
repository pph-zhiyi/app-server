package com.pph.demo.service.impl;

import com.pph.demo.service.DouBanService;
import com.pph.demo.vo.request.dou.ban.GetNowPlayingReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author: pph
 * @date 2020/1/31 15:29
 * @description:
 */
@Service
public class DouBanServiceImpl implements DouBanService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DouBanServiceImpl.class);
    /**
     * 密钥
     */
    private static final String apikey = "0df993c66c0c636e29ecbb5344252a4a";

    @Override
    public Object getNowPlaying(GetNowPlayingReq req) {
        LOGGER.info("^^^getNowPlaying params: {}", req);
        return null;
    }
}
