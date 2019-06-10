package com.reven.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reven.common.AbstractService;
import com.reven.common.jpa.util.Criteria;
import com.reven.common.jpa.util.Restrictions;
import com.reven.dao.UserRepository;
import com.reven.entity.User;
import com.reven.service.UserService;

@Service
public class UserServiceImpl extends AbstractService<User, Integer> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByCriterion(String userName) {
        Criteria<User> criteria = new Criteria<>();
        criteria.add(Restrictions.like("userName", userName,true));
        criteria.add(Restrictions.lte("id", 20,true));
        return userRepository.findAll(criteria);
    }

    @Override
    public List<Object[]> gorupByAddres() {
        return userRepository.gorupByAddres();
    }
}
