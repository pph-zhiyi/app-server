package com.pph.demo.controller;

import com.pph.demo.service.DouBanService;
import com.pph.demo.utils.oval.OvalVerify;
import com.pph.demo.vo.request.dou.ban.GetNowPlayingReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pph
 * @date 2020/1/31 15:27
 * @description:
 */
@RestController
@RequestMapping("/dou/ban")
public class DouBanController {

    @Autowired
    public DouBanController(DouBanService douBanService) {
        this.douBanService = douBanService;
    }

    private final DouBanService douBanService;

    @RequestMapping(value = "/get/now/paying", method = RequestMethod.POST)
    public Object getNowPlaying(@RequestBody GetNowPlayingReq req) {
        OvalVerify.verifyObj(req);
        return douBanService.getNowPlaying(req);
    }
}
