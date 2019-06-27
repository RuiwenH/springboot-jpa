package com.reven.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.reven.common.CustomDaoImpl;
import com.reven.dao.UserCustomDao;

public class UserRepositoryImpl extends CustomDaoImpl implements UserCustomDao {

    @Override
    public List<Object[]> gorupByAddres() {
        @SuppressWarnings("unchecked")
        List<Object[]> list = entityManager.createNativeQuery("select address,count(*) from user group by address")
                .getResultList();
        return list;
    }

    @Transactional(rollbackFor = Exception.class) // @Transactional应该在service配置，这里为了能让junit单元测试能正常运行
    @Override
    public int updateAge() {
        return entityManager.createQuery("update User set age=COALESCE(age,0)+1 where id>=?1 and userName like ?2")
                .setParameter(1, 10).setParameter(2, "zhangsan%").executeUpdate();
    }

    @Transactional(rollbackFor = Exception.class)  // @Transactional应该在service配置，这里为了能让junit单元测试能正常运行
    @Override
    public void useSession() {

//        HibernateEntityManager hEntityManager = (HibernateEntityManager) entityManager;
//        Session session = hEntityManager.getSession();
//        Query query = session.createSQLQuery("delete from test");
//        query.executeUpdate();

        Session session = entityManager.unwrap(org.hibernate.Session.class);
        Query query = session.createSQLQuery("delete from user where id=32");
        query.executeUpdate();

    }

}
