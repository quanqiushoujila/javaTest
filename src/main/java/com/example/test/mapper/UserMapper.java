package com.example.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.test.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {

    Integer selectTotal();

    List<User> getPage(@Param("page") Integer page, @Param("limit") Integer limit);
}
