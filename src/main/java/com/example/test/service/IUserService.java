package com.example.test.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.test.entity.User;

import java.util.Map;

public interface IUserService extends IService<User> {
    IPage<User> getPage(Map<String, String> params);
}
