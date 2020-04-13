package com.ljx.blog.service.serviceImpl;

import com.ljx.blog.bean.Type;
import com.ljx.blog.dao.TypeDao;
import com.ljx.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Mr.Liu
 * @description: 分类业务实现类
 * @date: 2020-04-03 22:37
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Override
    public Type getType(Long id) {
        return typeDao.findById(id).get();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }

    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable=PageRequest.of(0,size,sort);
        return typeDao.findTop(pageable);
    }

    @Override
    public Type updateType(Long id, Type type) {

        Type t= typeDao.findById(id).get();
        BeanUtils.copyProperties(type,t);
        return typeDao.save(t);
    }

    @Override
    public void deleteType(Long id) {
        typeDao.deleteById(id);

    }
}
