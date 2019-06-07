package com.reven.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomDao {
    List<Object[]> gorupByAddres();
}