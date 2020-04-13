package com.ljx.blog.dao;

import com.ljx.blog.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 评论持久层接口
 * @date: 2020-04-07 19:11
 */
@Repository
public interface CommentDao extends BaseDao<Comment>{

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);

    @Query("select c from Comment c where  c.blog=null and  c.parentComment=null ")
    List<Comment> findByBlogIdNullAndParentCommentNull(Sort sort);

}
