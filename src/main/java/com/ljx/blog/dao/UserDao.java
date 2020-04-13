package com.ljx.blog.dao;

import com.ljx.blog.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author: Mr.Liu
 * @description: 用户持久层接口
 * @date: 2020-04-03 11:09
 */
@Repository
public interface UserDao extends BaseDao<User>{

    /**
    * @Description: 持久层方法操作数据库检查用户名和密码是否正确 
    * @Param: [username, password] 
    * @return: com.ljx.blog.bean.User 
    * @Date: 2020/4/3-11:30
    */
    User findByUsernameAndPassword(String username, String password);

}
