package com.reven.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

public class CustomDaoImpl {

    @Autowired
    @PersistenceContext  //不用的区别 TODO
    protected EntityManager entityManager;


}