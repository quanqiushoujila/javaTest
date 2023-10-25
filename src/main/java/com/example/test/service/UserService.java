package com.example.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    @Autowired
    private UserMapper userMapper;

    public boolean saveUser (User user) {
        return saveOrUpdate(user);
    }

    public List<User> findAll () {
        return userMapper.findAll();
    }

    public Integer deleteById (Integer id) {
        return userMapper.deleteById(id);
    }

    public User getById (Integer id) {
        return userMapper.getById(id);
    }

    public Integer getTotal () {
        return userMapper.selectTotal();
    }

    public List<User> getPage (Map<String, String> params) {

        int limit = Integer.parseInt(params.get("limit"));
        int page = (Integer.parseInt(params.get("page")) - 1) * limit;
        return userMapper.getPage(page, limit);
    }
}
