package com.reven.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.reven.dao.UserCustomDao;

public class UserRepositoryImpl implements UserCustomDao {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> gorupByAddres() {
        List<Object[]> list = entityManager.createNativeQuery("select address,count(*) from user group by address")
                .getResultList();
        return list;
    }

}
