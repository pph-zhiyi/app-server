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

    /**
     * redis 缓存相关操作
     */
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedisKeyUtil redisKeyUtil;

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
    public Login redisTest(LoginVo request) {
        Login login = new Login();
        login.setUser(request.getUser());
        login.setPassword(request.getPassword());
        login.setGmtCreate(new Date());

        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisService.expireKey("user", 20, TimeUnit.SECONDS);
        String key = redisKeyUtil.getKey("t_login", "user", login.getUser());
        Login result = (Login) operations.get(key);
        LOGGER.info("***RedisTest result: {}", result);

        if (Objects.isNull(result)) {
            result = new Login();
//            TODO: 异常待解决
//            Object a = operations.get("a");
//            result.setUser(String.valueOf(a));
            Object b = operations.get("b");
            result.setPassword(String.valueOf(b));
        }

        return result;
    }
}
