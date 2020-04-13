package com.ljx.blog.service;

import com.ljx.blog.bean.Blog;
import com.ljx.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author: Mr.Liu
 * @description: 博客业务层接口
 * @date: 2020-04-04 22:59
 */
public interface BlogService {

    /**
    * @Description:  查询单个博客
    * @Param: [id]
    * @return: com.ljx.blog.bean.Blog
    * @Date: 2020/4/4-23:03
    */
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    /**
    * @Description:  后台动态条件分页查询博客
    * @Param: [pageable, blog]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/4-23:03
    */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    /**
    * @Description:   分页查看博客全部数据
    * @Param: [pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/6-16:16
    */
    Page<Blog> listBlog(Pageable pageable);

    /**
    * @Description:   标签页根据标签id分页查询博客
    * @Param: [tagId, pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/8-16:39
    */
    Page<Blog> listBlog(Long tagId,Pageable pageable);

    /**
    * @Description:  全局模糊分页查询
    * @Param: [query, pageable]
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/8-16:36
    */
    Page<Blog> listBlog(String query,Pageable pageable);

    /**
    * @Description:  查询最新推荐博客
    * @Param: [size]
    * @return: java.util.List<com.ljx.blog.bean.Blog>
    * @Date: 2020/4/8-16:37
    */
    List<Blog> listRecommendBlogTop(Integer size);

    /**
    * @Description:  查询按年月归档的博客信息
    * @Param: [] 
    * @return: java.util.Map<java.lang.String,java.util.List<com.ljx.blog.bean.Blog>> 
    * @Date: 2020/4/8-23:09
    */
    Map<String,List<Blog>> archiveBlog();

   /**
   * @Description:  查询指定月份的博客数量
   * @Param: [month]
   * @return: java.lang.Long
   * @Date: 2020/4/11-19:43
   */
   Map<String, Long> archiveBlogCount();

    /**
    * @Description:  查询发布状态的博客总数归档展示 
    * @Param: [] 
    * @return: java.lang.Long 
    * @Date: 2020/4/11-19:25
    */
    Long countBlog();

    /**
    * @Description:   保存博客
    * @Param: [blog]
    * @return: com.ljx.blog.bean.Blog
    * @Date: 2020/4/4-23:03
    */
    Blog saveBlog(Blog blog);

    /**
    * @Description:   更新博客
    * @Param: [id, blog]
    * @return: com.ljx.blog.bean.Blog
    * @Date: 2020/4/4-23:03
    */
    Blog updateBlog(Long id,Blog blog);

    /**
    * @Description:   删除博客
    * @Param: [id]
    * @return: void
    * @Date: 2020/4/4-23:03
    */
    void deleteBlog(Long id);
}
