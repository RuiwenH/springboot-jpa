package com.reven.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.reven.common.BaseRepository;
import com.reven.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {

    //自定义repository。手写sql
    @Query(value = "update user set name=?1 where id=?4",nativeQuery = true)   //占位符传值形式
    @Modifying
    int updateById(String name,int id);

    @Query("from User u where u.userName=:userName")   //SPEL表达式
    User findUser(@Param("userName") String userName);// 参数userName 映射到数据库字段userName
    
}