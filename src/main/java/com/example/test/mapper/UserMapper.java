package com.example.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();

    int update(User user);

    int insert(User user);

    Integer deleteById(@Param("id") Integer id);

    User getById(@Param("id") Integer id);

    Integer selectTotal();

    List<User> getPage(@Param("page") Integer page, @Param("limit") Integer limit);
}
