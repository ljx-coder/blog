package com.ljx.blog.service.serviceImpl;

import com.ljx.blog.bean.User;
import com.ljx.blog.dao.UserDao;
import com.ljx.blog.service.UserService;
import com.ljx.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.Liu
 * @description: 用户业务逻辑实现类
 * @date: 2020-04-03 11:07
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUsernameAndPassword(username, MD5Utils.code(password));
    }
}
