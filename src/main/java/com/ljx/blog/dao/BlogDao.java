package com.ljx.blog.dao;

import com.ljx.blog.bean.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author: Mr.Liu
 * @description: 博客持久层接口
 * @date: 2020-04-04 23:06
 */
@Repository
public interface BlogDao extends BaseDao<Blog> , JpaSpecificationExecutor<Blog>{

    /**
    * @Description:  查询推荐博客
    * @Param: [pageable]
    * @return: java.util.List<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/10-0:05
    */
    @Query("select b from Blog b where b.recommend=true and b.published=true")
    List<Blog> findTop(Pageable pageable);

    /**
    * @Description:  查询已发布状态的博客
    * @Param: [pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/10-0:14
    */
    @Query("select b from Blog b where b.published=true")
    Page<Blog> findPublishedBlog(Pageable pageable);

    /**
    * @Description:   根据博客标题和内容查询全局模糊查询
    * @Param: [query, pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/10-0:05
    */
    @Query("select b from Blog b where (b.title like ?1 or b.content like ?1) and b.published=true")
    Page<Blog> findByQuery(String query,Pageable pageable);

    /**
    * @Description:  浏览博客后更新浏览次数
    * @Param: [id]
    * @return: int
    * @Date: 2020/4/10-0:07
    */
    @Transactional
    @Modifying
    @Query("update Blog b set b.views =b.views+1 where b.id= ?1")
    int updateViews(Long id);

    @Query("select count(b) from Blog b where b.published=true ")
    Long countByPublished();

    /**
    * @Description:  按年份和月份查询进行归档
    * @Param: []
    * @return: java.util.List<java.lang.String>
    * @Date: 2020/4/10-0:08
    */
    @Query("select distinct function('date_format',b.createTime,'%Y-%m') as month from Blog b group by function('date_format',b.createTime,'%Y-%m') order by month desc ")
    List<String> findGroupMonth();

    /**
    * @Description:  查询指定归档时间的博客
    * @Param: [month]
    * @return: java.util.List<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/10-0:09
    */
    @Query("select b from Blog b where b.published=true and function('date_format',b.createTime,'%Y-%m') = ?1")
    List<Blog> findByMonth(String month);

    /**
    * @Description:  查询指定月份的博客数量 
    * @Param: [month] 
    * @return: java.lang.Long 
    * @Date: 2020/4/11-19:41
    */
    @Query("select  count(b) from Blog b where b.published=true and function('date_format',b.createTime,'%Y-%m') =?1")
    Long countByMonth(String month);



}
