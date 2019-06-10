package com.reven.service;

import java.util.List;

import com.reven.common.IBaseService;
import com.reven.entity.User;

public interface UserService  extends IBaseService<User, Integer> {

    List<User> findByCriterion(String userName);
    
    
     
}