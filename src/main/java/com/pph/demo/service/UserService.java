package com.pph.demo.service;

import com.pph.demo.model.User;

import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-05-22 13:39
 * @Description:
 */
public interface UserService {

    /**
     * 查询所有
     *
     * @return
     */
    List<User> queryAll();
}
