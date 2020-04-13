package com.ljx.blog.dao;

import com.ljx.blog.bean.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 标签持久层接口
 * @date: 2020-04-04 18:09
 */
@Repository
public interface TagDao extends BaseDao<Tag>{

    /**
    * @Description:  定义根据标签名称查询进行校验
    * @Param: [name]
    * @return: com.ljx.blog.bean.Tag
    * @Date: 2020/4/4-18:10
    */
    Tag findByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
