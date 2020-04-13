package com.ljx.blog.controller;

import com.ljx.blog.bean.Comment;
import com.ljx.blog.bean.User;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author: Mr.Liu
 * @description: 评论web控制器
 * @date: 2020-04-07 18:55
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String commentAvatar;

    @Value("${admin.avatar}")
    private String adminAvatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments",commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session){
        Long blogId=comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        User user= (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(adminAvatar);
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(commentAvatar);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+blogId;
    }


}
