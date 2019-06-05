package com.reven.service;

import java.util.Iterator;

import com.reven.common.IBaseService;
import com.reven.entity.User;

public interface UserService  extends IBaseService<User, Integer> {

    Iterator<User> selectAll(int pageNum, int pageSize);
     
}