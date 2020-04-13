package com.ljx.blog.service;

import com.ljx.blog.bean.User;

/**
 * @author: Mr.Liu
 * @description: 用户业务逻辑接口
 * @date: 2020-04-03 10:54
 */
public interface UserService {

    /**
    * @Description: 用户登录检查用户名和密码是否正确的方法
    * @Param: [username, password]
    * @return: com.ljx.blog.bean.User
    * @Date: 2020/4/3-11:18
    */
    User checkUser(String username,String password);

}
