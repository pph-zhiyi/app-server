package com.pph.demo.service.impl;

import com.pph.demo.mapper.LoginMapper;
import com.pph.demo.service.LoginService;
import com.pph.demo.vo.request.login.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: PPH
 * @Date: 2019-05-26 19:04
 * @Description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Boolean isExists(LoginVo r) {
        logger.info("isExists params: {}", r);
        return loginMapper.queryLoginByUserAndPwd(r.getUser(), r.getPassword()) == null ? false : true;
    }

    @Override
    public Integer register(LoginVo r) {
        logger.info("isExists params: {}", r);
        return loginMapper.createLogin(r);
    }
}
