package com.reven.common;

import java.io.Serializable;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>
        extends 
        JpaRepository<T, ID>, 
        JpaSpecificationExecutor<T>,
        PagingAndSortingRepository<T, ID> {


    <S extends T> Iterable<S> batchSave(Iterable<S> var1);

    <S extends T> Iterable<S> batchUpdate(Iterable<S> var1);
    
    Session getSession();

}