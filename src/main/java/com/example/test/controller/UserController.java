package com.example.test.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.test.entity.User;
import com.example.test.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/listAll")
    public List<User> listAll () {
        List<User> all = userService.list();
        return all;
    }

    @GetMapping("/page")
    @ApiOperation("分页")
    public IPage<User> getPage (@RequestParam Map<String, String> params) {
        return userService.getPage(params);
    }

    @PostMapping("/save")
    public boolean save (@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("/{id}")
    public User get (@PathVariable Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @GetMapping("/export")
    public void export (HttpServletResponse res) throws Exception {
        List<User> list = userService.list();

        ExcelWriter writer = ExcelUtil.getWriter(true);

        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("nickname", "昵称");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("createTime", "创建时间");

        writer.write(list, true);

        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset-utf-8");
        String fileName = URLEncoder.encode("用户信息", "utf-8");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = res.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setNickname(row.get(1).toString());
            user.setEmail(row.get(2).toString());
            user.setPhone(row.get(3).toString());
            user.setAddress(row.get(4).toString());

        }
        userService.saveBatch(users);
        return true;
    }
}
