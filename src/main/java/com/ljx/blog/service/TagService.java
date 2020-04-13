package com.ljx.blog.service;

import com.ljx.blog.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 标签业务层接口
 * @date: 2020-04-04 18:11
 */
public interface TagService {

    /**
     * @Description: 保存标签数据
     * @Param: []
     * @return: com.ljx.blog.bean.Tag
     * @Date: 2020/4/3-22:29
     */
    Tag saveTag(Tag tag);

    /**
     * @Description: 获取单个标签
     * @Param: []
     * @return: com.ljx.blog.bean.Tag
     * @Date: 2020/4/3-22:30
     */
    Tag getTag(Long id);

    /**
     * @Description:  根据名称查询标签进行校验
     * @Param: [name]
     * @return: com.ljx.blog.bean.Tag
     * @Date: 2020/4/4-16:03
     */
    Tag getTagByName(String name);

    /**
     * @Description: 分页查询标签
     * @Param: [pageable]
     * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Tag>
     * @Date: 2020/4/3-22:34
     */
    Page<Tag> listTag(Pageable pageable);

    /**
    * @Description: 获取所有标签
    * @Param: []
    * @return: java.util.List<com.ljx.blog.bean.Tag>
    * @Date: 2020/4/5-19:10
    */
    List<Tag> listTag();

    List<Tag> listTagTop(Integer size);

    /**
    * @Description:  通过ids字符串获取标签集合
    * @Param: [ids] 
    * @return: java.util.List<com.ljx.blog.bean.Tag> 
    * @Date: 2020/4/5-20:34
    */
    List<Tag> listTag(String ids);

    /**
     * @Description:  修改标签
     * @Param: [id, Tag]
     * @return: com.ljx.blog.bean.Tag
     * @Date: 2020/4/3-22:35
     */
    Tag updateTag(Long id,Tag tag);

    /**
     * @Description: 删除标签
     * @Param: [id]
     * @return: void
     * @Date: 2020/4/3-22:35
     */
    void deleteTag(Long id);
}
