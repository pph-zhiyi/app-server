package com.pph.demo.service.impl;

import com.pph.demo.async.AsyncService;
import com.pph.demo.mapper.UserMapper;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import com.pph.demo.utils.Constants;
import com.pph.demo.utils.common.Params;
import com.pph.demo.vo.request.user.CreateUserVo;
import com.pph.demo.vo.request.user.DeleteUserVo;
import com.pph.demo.vo.request.user.UpdateUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * @Author: PPH
 * @date 2019-05-24 11:15
 * @description
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
    /**
     * 异步任务
     */
    @Autowired
    private Map<String, AsyncService> asyncService;

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

    @Override
    public User queryUserByUserPwd(String user, String password) {
        LOGGER.info("^^^queryUserByUserPwd user: {}，password: {}", user, password);
        return userMapper.queryUserByUserPwd(user, password);
    }

    @Override
    public Integer register(String user, String password) {
        LOGGER.info("^^^register user: {}，password: {}", user, password);
        List<User> users = queryUserByTerms(new HashMap<String, Object>(2) {
            {
                put("user", user);
                put(Constants.Page.IS_PAGE.val(), false);
            }
        });
        if (!users.isEmpty()) {
            throw new RuntimeException("当前用户名已被注册！");
        }
        return userMapper.register(user, password);
    }

    @Override
    public List<String> queryUsers() {
        return userMapper.queryUsers();
    }

    @Override
    public List<String> asyncTest() {
        AsyncService asyncService = this.asyncService.get("asyncServiceImpl");
        AsyncService pphAsyncService = this.asyncService.get("pphAsyncServiceImpl");
        List<Integer> list = new ArrayList<Integer>() {{
            add(111);
            add(222);
            add(333);
        }};

        List<CompletableFuture<String>> tasks = Params.taskRun(list, i -> String.format("我是字符串: %d", i));

        Stream.iterate(1, n -> n + 1)
                .limit(10)
                .forEach(i -> {
                    asyncService.executeAsync(i);
                    pphAsyncService.executeAsync(i);
                });

        return Params.taskResult(tasks);
    }
}
