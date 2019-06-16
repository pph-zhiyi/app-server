package com.pph.demo.service.impl;

import com.pph.demo.mapper.UserMapper;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.ParamsDisposeUtil;
import com.pph.demo.vo.request.user.CreateUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 11:15
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 参数相关操作
     */
    @Autowired
    private ParamsDisposeUtil paramsDisposeUtil;

    /**
     * 用户相关操作
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public Object createUser(CreateUserVo user) {
        LOGGER.info("^^^createUser user: {}", user.toString());
        return userMapper.createUser(user);
    }

    @Override
    public List<User> queryUserByTerms(Map<String, Object> filter) {
        paramsDisposeUtil.addFilterPageInfo(filter);
        LOGGER.info("^^^queryUserByTerms filter: {}", filter.toString());
        return userMapper.queryUserByTerms(filter);
    }

    @Override
    public Integer queryCountByTerms(Map<String, Object> filter) {
        LOGGER.info("^^^queryCountByTerms filter: {}", filter.toString());
        return userMapper.queryCountByTerms(filter);
    }
}
