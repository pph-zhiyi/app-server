package com.pph.demo.service.impl;

import com.pph.demo.mapper.UserMapper;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.common.Params;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;
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
     * 用户相关操作
     */
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUserByTerms(Map<String, Object> filter) {
        Params.makePageInfo(filter);
        LOGGER.info("^^^queryUserByTerms aspect: {}", filter);
        return userMapper.queryUserByTerms(filter);
    }

    @Override
    public Integer queryCountByTerms(Map<String, Object> filter) {
        LOGGER.info("^^^queryCountByTerms aspect: {}", filter);
        return userMapper.queryCountByTerms(filter);
    }

    @Override
    public Integer createUser(CreateUserVo user) {
        LOGGER.info("^^^createUser user: {}", user);
        userMapper.createUser(user);
        return user.getId();
    }

    @Override
    public Integer updateUserById(UpdateUserVo user) {
        LOGGER.info("^^^updateUserById user: {}", user);
        userMapper.updateUserById(user);
        return user.getId();
    }

    @Override
    public Integer deleteUserById(DeleteUserVo user) {
        LOGGER.info("^^^deleteUserById user: {}", user);
        userMapper.deleteUserById(user);
        return user.getId();
    }
}
