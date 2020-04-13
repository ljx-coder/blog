package com.ljx.blog.dao;

import com.ljx.blog.bean.BaseBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: Mr.Liu
 * @description: dao层公共配置类
 * @date: 2020-04-03 10:56
 */
@Repository
public interface BaseDao<T extends BaseBean> extends JpaRepository<T ,Long> {

}
