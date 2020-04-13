package com.ljx.blog.dao;

import com.ljx.blog.bean.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 分类持久层接口
 * @date: 2020-04-03 22:39
 */
@Repository
public interface TypeDao extends BaseDao<Type>{
    /**
    * @Description: 自定义根据分类名称查询分类进行校验
    * @Param: [name]
    * @return: com.ljx.blog.bean.Type
    * @Date: 2020/4/4-16:06
    */
    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);


}
