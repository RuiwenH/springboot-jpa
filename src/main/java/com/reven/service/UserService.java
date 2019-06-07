package com.reven.service;

import java.util.Iterator;
import java.util.List;

import com.reven.common.IBaseService;
import com.reven.entity.User;

public interface UserService  extends IBaseService<User, Integer> {

    Iterator<User> selectAll(int pageNum, int pageSize);

     List<Object[]> gorupByAddres();
     
}