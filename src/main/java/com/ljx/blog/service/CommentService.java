package com.ljx.blog.service;

import com.ljx.blog.bean.Comment;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description:  评论业务层接口
 * @date: 2020-04-07 19:04
 */
public interface CommentService {

    /**
    * @Description:  根据博客id查询评论
    * @Param: [blogId]
    * @return: java.util.List<com.ljx.blog.bean.Comment>
    * @Date: 2020/4/7-19:08
    */
    List<Comment> listCommentByBlogId(Long blogId);

    List<Comment> listComment();

    /**
    * @Description:  保存评论信息
    * @Param: [comment]
    * @return: com.ljx.blog.bean.Comment
    * @Date: 2020/4/7-19:08
    */
    Comment saveComment(Comment comment);






}
