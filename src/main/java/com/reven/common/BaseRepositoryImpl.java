package com.reven.common;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class BaseRepositoryImpl<T, ID extends Serializable> 
        extends SimpleJpaRepository<T, ID> 
        implements BaseRepository<T, ID> {

    private static final int BATCH_SIZE = 500;

    private EntityManager em;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.em = em;

    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
    }


    @Override
    @Transactional
    public <S extends T> Iterable<S> batchSave(Iterable<S> var1) {
        Iterator<S> iterator = var1.iterator();
        int index = 0;
        while (iterator.hasNext()){
            em.persist(iterator.next());
            index++;
            if (index % BATCH_SIZE == 0){
                em.flush();
                em.clear();
            }
        }
        if (index % BATCH_SIZE != 0){
            em.flush();
            em.clear();
        }
        return var1;
    }

    @Override

@Transactional

public <S extends T> Iterable<S> batchUpdate(Iterable<S> var1) { Iterator<S> iterator = var1.iterator(); int index = 0; while (iterator.hasNext()){ em.merge(iterator.next()); index++; if (index % BATCH_SIZE == 0){ em.flush(); em.clear(); } } if (index % BATCH_SIZE != 0){ em.flush(); em.clear(); } return var1; } }