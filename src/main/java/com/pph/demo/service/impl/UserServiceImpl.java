package com.pph.demo.service.impl;

import com.pph.demo.dao.UserMapper;
import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-05-22 13:41
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }
}
