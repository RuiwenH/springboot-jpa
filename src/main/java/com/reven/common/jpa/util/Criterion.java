package com.reven.common.jpa.util;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Criterion {
    enum Operator {
        /**   
         * @Fields EQ : 等于   
         */  
        EQ, /**   
         * @Fields NE : 不等于   
         */  
        NE, LIKE, 
        /**   
         * @Fields GT : 大于    
         */  
        GT, 
        /**   
         * @Fields LT :   小于
         */  
        LT, /**   
         * @Fields GTE :   大于等于
         */  
        GTE, /**   
         * @Fields LTE :  小于等于
         */  
        LTE, AND, OR, 
        /**   
         * @Fields IS_MEMBER : 相当于sql的in
         */  
        IS_MEMBER, 
        /**   
         * @Fields IS_NOT_MEMBER : 相当于sql的not in  
         */  
        IS_NOT_MEMBER
    }

    Predicate toPredicate(Root<?> root, CriteriaQuery<?> query,
                          CriteriaBuilder builder);
}