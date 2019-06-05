package com.reven.common;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:  AbstractService   
 * @author reven
 * @date   2018年8月28日
 * @param <T>
 */
@Transactional(readOnly = true)
public abstract class AbstractService<T, ID extends Serializable> implements IBaseService<T, ID> {

    @Autowired
    private BaseRepository<T, ID> baseRepository;

    private Class<T> modelClass;

    @SuppressWarnings("unchecked")
    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(T model) {
        baseRepository.save(model);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(List<T> models) {
        baseRepository.batchSave(models);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(ID id) {
        baseRepository.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(T model) {
        baseRepository.save(model);
    }

    @Override
    public T getById(ID id) {
        Optional<T> value = baseRepository.findById(id);
        if (value.isPresent()) {
            return value.get();
        }
        return null;
    }

    @Override
    public List<T> findBy(String fieldName, Object value) throws ReflectiveOperationException {
        T model = modelClass.newInstance();
        Field field = modelClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(model, value);
        // 创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching(); // 忽略属性：是否关注。因为是基本类型，需要忽略掉
        // 创建实例
        Example<T> ex = Example.of(model, matcher);
        return baseRepository.findAll(ex);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Page<T> findAll(Integer pageNo, Integer pageSize) {
        Pageable pageable = buildPageable(pageNo, pageSize, null);
        return baseRepository.findAll(pageable);
    }

    @Override
    public Page<T> findAll(Integer pageNo, Integer pageSize, Sort sort) {
        Pageable pageable = buildPageable(pageNo, pageSize, sort);
        return baseRepository.findAll(pageable);
    }

    @Override
    public Page<T> find(Specification<T> specification, Integer pageNo, Integer pageSize) {
        Pageable pageable = buildPageable(pageNo, pageSize, null);
        return baseRepository.findAll(specification, pageable);
    }

    @Override
    public Page<T> find(Specification<T> specification, Integer pageNo, Integer pageSize, Sort sort) {
        Pageable pageable = buildPageable(pageNo, pageSize, sort);
        return baseRepository.findAll(specification, pageable);
    }

    private Pageable buildPageable(Integer pageNo, Integer pageSize, Sort sort) {
        if (pageNo == null) {
            pageNo = 0;
        }
        // 重置为用户习惯的地一页（baseRepository是从0开始的）
        if (pageNo > 0) {
            pageNo = pageNo - 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }

        Pageable pageable;
        if (sort != null) {
            pageable = PageRequest.of(pageNo, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNo, pageSize);
        }
        return pageable;
    }
}
