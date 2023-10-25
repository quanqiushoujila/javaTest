package com.example.test.controller;

import com.example.test.entity.User;
import com.example.test.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/listAll")
    public List<User> listAll () {
        List<User> all = userService.list();
        return all;
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    public Map<String, Object> getPage (@RequestParam Map<String, String> params) {
        Integer total = userService.getTotal();
        List<User> list = userService.getPage(params);
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("data", list);
        return map;
    }

    @PostMapping("/save")
    public boolean save (@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public User get (@PathVariable Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }
}
