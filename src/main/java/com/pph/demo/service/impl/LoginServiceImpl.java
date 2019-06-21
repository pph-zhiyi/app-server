package com.pph.demo.service.impl;

import com.pph.demo.configs.redis.RedisService;
import com.pph.demo.mapper.LoginMapper;
import com.pph.demo.model.Login;
import com.pph.demo.service.LoginService;
import com.pph.demo.utils.RedisKeyUtil;
import com.pph.demo.vo.request.login.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Override
    public Boolean isExists(LoginVo r) {
        LOGGER.info("isExists params: {}", r);
        return loginMapper.queryLoginByUserAndPwd(r.getUser(), r.getPassword()) == null ? false : true;
    }

    @Override
    public Integer register(LoginVo r) {
        LOGGER.info("isExists params: {}", r);
        return loginMapper.createLogin(r);
    }

    @Override
    public List<String> queryUserNameAll() {
        return null;
    }

    @Override
    public String getRedisString(String key) {
        LOGGER.info("getRedisString key: {}", key);
        Object o = valueOperations.get(key);
        return String.valueOf(o);
    }

    @Override
    public void setRedisString(String key, String val) {
        LOGGER.info("setRedisString key: {}, val: {}", key, val);
        /*
         * 将字符串插入到 redis
         */
        valueOperations.set(key, val);
        /*
         * 设置过期时间 30 分钟
         */
        redisService.expireKey(key, 30, TimeUnit.MINUTES);
    }
}
