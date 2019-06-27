package com.reven.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reven.controller.common.ResResult;
import com.reven.entity.User;
import com.reven.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // Replace this persistent entity with a simple POJO or DTO object.
    @GetMapping(value = "/insert")
    public ResResult insert(User user) {
        List<User> list = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            User user1 = new User();
            BeanUtils.copyProperties(user, user1);
            user1.setUserName(user.getUserName() + (i + 1));
            user1.setBirthday(DateUtils.addDays(user.getBirthday(), RandomUtils.nextInt(0, 365)));
            list.add(user1);
        }
        userService.save(user);
        userService.save(list);
        return ResResult.success();
    }

    @GetMapping(value = "/{id}/select")
    public ResResult select(@PathVariable("id") int id) {
        return ResResult.success(userService.getById(id));
    }

    @GetMapping(value = "/delete/{id}")
    public ResResult delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return ResResult.success();
    }

    @GetMapping(value = "/update/{id}")
    public ResResult update(@PathVariable("id") Integer id, User user) {
        // 推荐更新指定属性
        User userOriginal = userService.getById(id);
        if (userOriginal != null) {
            userOriginal.setUserName(user.getUserName());
            userOriginal.setAddress(user.getAddress());
            userOriginal.setBirthday(user.getBirthday());
            userService.update(userOriginal);
            return ResResult.success();
        } else {
            return ResResult.fail("user.id=" + id + ",not exsits.");
        }
    }

    @GetMapping(value = "/findByName/{name}")
    public ResResult findByName(@PathVariable("name") String name) throws ReflectiveOperationException {
        List<User> list = userService.findBy("userName", name);
        return ResResult.success(list);
    }

    @GetMapping(value = "/findBy/{attr}/{value}")
    public ResResult findBy(@PathVariable("attr") String attr, @PathVariable("value") String value)
            throws ReflectiveOperationException {
        List<User> list = userService.findBy(attr, value);
        return ResResult.success(list);
    }

    @GetMapping(value = "/findAll")
    public ResResult findAll() throws ReflectiveOperationException {
        List<User> list = userService.findAll();
        return ResResult.success(list);
    }

    @GetMapping(value = "/findAll/{pageNum}/{pageSize}")
    public ResResult findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        Page<User> page = userService.findAll(pageNum, pageSize);
        return ResResult.success(page);
    }
    
    //自定义Repository方法查询
    @GetMapping(value = "/gorupByAddres")
    public ResResult gorupByAddres() {
        return ResResult.success(userService.gorupByAddres());
    }

    @GetMapping(value = "/findByCriterion")
    public ResResult findByCriterion(String userName) {
        List<User> list = userService.findByCriterion(userName);
        return ResResult.success(list);
    }

}