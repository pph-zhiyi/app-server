package com.pph.demo.controller;

import com.pph.demo.model.User;
import com.pph.demo.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: PPH
 * @Date: 2019-05-22 13:46
 * @Description:
 */

@RestController
@RequestMapping("/user")
public class UserController {

//    private final LoggerFactory logger = new LoggerFactory(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/query/all", method = RequestMethod.GET)
    public List<User> queryAll() {
        List<User> users = userService.queryAll();

        System.out.println(users.get(0).getName());

        return users;
    }
}
