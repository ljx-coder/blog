package com.ljx.blog.controller;

import com.ljx.blog.bean.Comment;
import com.ljx.blog.bean.User;
import com.ljx.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author: Mr.Liu
 * @description:
 * @date: 2020-04-09 16:29
 */
@Controller
public class AboutShowController {

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String commentAvatar;

    @Value("${admin.avatar}")
    private String adminAvatar;

    @GetMapping("/about")
    public String about(){
        return "about";
    };

    @GetMapping("/messagesList")
    public String messages(Model model){
        model.addAttribute("comments",commentService.listComment());
        return "about :: messagesList";
    }

    @RequestMapping("/messages")
    public String postMessages(Comment comment, HttpSession session){
        User user= (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(adminAvatar);
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(commentAvatar);
        }
        commentService.saveComment(comment);
        return "redirect:/messagesList";
    }







}
