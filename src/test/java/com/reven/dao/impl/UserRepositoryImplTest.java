package com.reven.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.reven.SpringbootJpaApplicationTests;

public class UserRepositoryImplTest extends SpringbootJpaApplicationTests {

    @Resource
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testGorupByAddres() {
        List<Object[]> gorupByAddres = userRepositoryImpl.gorupByAddres();
        for (Object[] objects : gorupByAddres) {
            System.out.println(objects[0] + "/" + objects[1]);
        }
    }

    @Test
    public void testUpdateAge() {
        System.out.println(userRepositoryImpl.updateAge());
    }
    
    @Test
    public void testUseSession() {
        userRepositoryImpl.useSession();
    }


}
