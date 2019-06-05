package com.reven.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

/**
 * @ClassName:  IBaseService   
 * @Description: Service 层 基础接口，其他Service 接口 请继承该接口
 * @author reven
 * @date   2018年8月28日
 * @param <T>
 */
public interface IBaseService<T, ID extends Serializable> {
    /**   
     * 持久化  
     * @param model      
     */
    void save(T model);

    /**   
     * 批量持久化
     * @param models      
     */
    void save(List<T> models);

    /**   
     * 通过主鍵刪除   
     * @param id      
     */
    void deleteById(ID id);

    /**   
     * 更新 
     * @param model      
     */
    void update(T model);

    /**   
     * 通过主键ID查找  
     * @param id
     * @return      
     */
    T getById(ID id);

    List<T> findBy(String fieldName, Object value) throws ReflectiveOperationException;

    List<T> findAll();

    Page<T> findAll(Integer pageNo, Integer pageSize);

    Page<T> findAll(Integer pageNo, Integer pageSize, Sort sort);

    Page<T> find(Specification<T> specification, Integer pageNo, Integer pageSize);

    Page<T> find(Specification<T> specification, Integer pageNo, Integer pageSize, Sort sort);

}
