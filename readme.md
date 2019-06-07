
# 功能
* BaseRepository
* Repository添加自定义方法
* 增删改
* 查
* 分页查
* 多表关联查询 待添加

# 技术栈
* 多表查询的工具框架 推荐QueryDSL

# 测试地址
* 添加数据 http://localhost:8083/jpa/user/insert?userName=zhangsan&birthday=2000-01-03&sex=male&address=shenzhen
* 批量数据 http://localhost:8083/jpa/user/insert?userName=lisi&birthday=2003-01-03&sex=female%20&address=beijing
* 查一个 http://localhost:8083/jpa/user/2/select
* 查一个不存在的 http://localhost:8083/jpa/user/100002/select
* 删除一个 http://localhost:8083/jpa/user/delete/3
* 删除一个不存在 http://localhost:8083/jpa/user/delete/100002 ，结果：抛出异常
* 更新一个 http://localhost:8083/jpa/user/update/2?userName=zhangsanU&birthday=1900-01-03&sex=male&address=shenzhen2
* 更新一个不存在 http://localhost:8083/jpa/user/update/10000?userName=zhangsanU&birthday=1900-01-03&sex=male&address=shenzhen2-2
* 通用方法按属性查找 http://localhost:8083/jpa/user/findByName/zhangsan
* 通用方法按属性查找 http://localhost:8083/jpa/user/findBy/sex/male
* 查找所有 http://localhost:8083/jpa/user/findAll
* 分页查找 http://localhost:8083/jpa/user/findAll/2/10
* 分页查找 http://localhost:8083/jpa/user/findAll/0/5  
* 分页查找超过总页数 http://localhost:8083/jpa/user/findAll/100/5

* 自定义Repository方法查询 http://localhost:8083/jpa/user/gorupByAddres


# 参考文章
* https://github.com/icnws/spring-data-jpa-demo
* spring boot2 整合（二）JPA https://www.jianshu.com/p/3b31270a44b1
* springboot2.0 JPA配置自定义repository，并作为基类BaseRepository使用 https://www.cnblogs.com/blog5277/p/10661441.html
* Spring Data JPA系列：使用@Query注解 http://www.icnws.com/2017/spring-data-jpa-useing-query/
* Spring Data JPA系列：使用Sort进行排序 http://www.icnws.com/2017/spring-data-jpa-useing-sort/
* Spring Data JPA 复杂查询（动态查询、分页查询等）https://www.cnblogs.com/hdwang/p/7843405.html
* QueryByExampleExecutor接口的查询 https://blog.csdn.net/zhao_tuo/article/details/78604324
* spring data jpa 定义全局接口BaseDao https://blog.csdn.net/yingxiake/article/details/51017797
* 自定义Repository,BaseRepository https://blog.csdn.net/xiao_xuwen/article/details/53579353

