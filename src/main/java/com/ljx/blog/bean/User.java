package com.ljx.blog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 用户实体类
 * @date: 2020-04-02 22:35
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User extends BaseBean{

    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs=new ArrayList<>();





}
