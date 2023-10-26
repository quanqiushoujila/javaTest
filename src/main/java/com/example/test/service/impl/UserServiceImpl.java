package com.example.test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.test.entity.User;
import com.example.test.mapper.UserMapper;
import com.example.test.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    public IPage<User> getPage (Map<String, String> params) {
        IPage<User> ipage = new Page<>(Integer.parseInt(params.get("page")), Integer.parseInt(params.get("limit")));
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        if (!"".equals(params.get("username"))) {
//            wrapper.like("username", params.get("username"));
//        }
        return page(ipage, wrapper);
    }
}
