package com.ljx.blog.service.serviceImpl;

import com.ljx.blog.bean.Tag;
import com.ljx.blog.dao.TagDao;
import com.ljx.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 标签业务层实现类
 * @date: 2020-04-04 18:12
 */
@Service
@Transactional
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;

    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Override
    public Tag getTag(Long id) {
        return tagDao.findById(id).get();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagDao.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC ,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return tagDao.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAllById(convertToList(ids));
    }
    
    /**
    * @Description:  ids字符串分割成id属性集合
    * @Param: [ids] 
    * @return: java.util.List<java.lang.Long> 
    * @Date: 2020/4/5-20:38
    */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {

        Tag t=tagDao.findById(id).get();
        BeanUtils.copyProperties(tag,t);
        return tagDao.save(t);
    }

    @Override
    public void deleteTag(Long id) {
        tagDao.deleteById(id);

    }
}
