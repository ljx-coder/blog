package com.ljx.blog.service;

import com.ljx.blog.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 分类业务层接口
 * @date: 2020-04-03 22:27
 */
public interface TypeService {
    /**
    * @Description: 保存分类
    * @Param: []
    * @return: com.ljx.blog.bean.Type
    * @Date: 2020/4/3-22:29
    */
    Type saveType(Type type);

    /**
    * @Description: 获取单个分类
    * @Param: []
    * @return: com.ljx.blog.bean.Type
    * @Date: 2020/4/3-22:30
    */
    Type getType(Long id);

    /**
    * @Description:  根据名称查询分类进行校验
    * @Param: [name]
    * @return: com.ljx.blog.bean.Type
    * @Date: 2020/4/4-16:03
    */
    Type getTypeByName(String name);

    /**
    * @Description: 分页查询分类
    * @Param: [pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Type>
    * @Date: 2020/4/3-22:34
    */
    Page<Type> listType(Pageable pageable);

    /**
    * @Description: 获取所有标签
    * @Param: []
    * @return: java.util.List<com.ljx.blog.bean.Type>
    * @Date: 2020/4/5-15:56
    */
    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    /**
    * @Description:  修改分类
    * @Param: [id, type]
    * @return: com.ljx.blog.bean.Type
    * @Date: 2020/4/3-22:35
    */
    Type updateType(Long id,Type type);

    /**
    * @Description: 删除分类
    * @Param: [id]
    * @return: void
    * @Date: 2020/4/3-22:35
    */
    void deleteType(Long id);






}
