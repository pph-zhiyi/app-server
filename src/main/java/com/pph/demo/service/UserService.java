package com.pph.demo.service;

import com.pph.demo.model.User;
import com.pph.demo.vo.request.user.CreateUserVo;

import java.util.List;
import java.util.Map;

/**
 * @Author: PPH
 * @Date: 2019-05-24 11:15
 * @Description:
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param createUserVo
     * @return
     */
    Object createUser(CreateUserVo createUserVo);

    /**
     * 根据条件过滤查询
     *
     * @param filter
     * @return
     */
    List<User> queryUserByTerms(Map<String, Object> filter);

    /**
     * 总条数
     *
     * @param filter
     * @return
     */
    Integer queryCountByTerms(Map<String, Object> filter);
}
