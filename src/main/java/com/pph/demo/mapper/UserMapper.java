package com.pph.demo.mapper;

import com.pph.demo.model.User;
import com.pph.demo.vo.request.user.CreateUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    Integer createUser(CreateUserVo user);

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