package com.pph.demo.service;

import com.pph.demo.vo.request.dou.ban.GetNowPlayingReq;

/**
 * @author: pph
 * @datetime 2020/1/31 15:29
 * @description:
 */
public interface DouBanService {
    /**
     * 根据条件获取正在热映电影列表
     *
     * @param req 入参
     * @return 结果
     */
    Object getNowPlaying(GetNowPlayingReq req);
}
