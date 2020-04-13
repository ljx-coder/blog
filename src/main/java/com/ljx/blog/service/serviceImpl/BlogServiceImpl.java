package com.ljx.blog.service.serviceImpl;

import com.ljx.blog.bean.Blog;
import com.ljx.blog.bean.Type;
import com.ljx.blog.dao.BlogDao;
import com.ljx.blog.service.BlogService;
import com.ljx.blog.util.MarkdownUtils;
import com.ljx.blog.util.MyBeanUtils;
import com.ljx.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author: Mr.Liu
 * @description: 博客业务实现类
 * @date: 2020-04-04 23:05
 */
@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.findById(id).get();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> months=blogDao.findGroupMonth();
        Map<String, List<Blog>> blogMap =new HashMap<>();
        for (String month : months){
            blogMap.put(month,blogDao.findByMonth(month));
        }
        return blogMap;
    }

    @Override
    public Map<String, Long> archiveBlogCount() {
        List<String> months=blogDao.findGroupMonth();
        Map<String, Long> countMap =new HashMap<>();
        for (String month : months){
            countMap.put(month,blogDao.countByMonth(month));
        }
        return countMap;
    }

    @Override
    public Long countBlog() {
        return blogDao.countByPublished();
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog=blogDao.findById(id).get();
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        String content = b.getContent();
        b.setContent(MarkdownUtils.markdownToHtmlExtensions(content));

        blogDao.updateViews(id);
        return b;
    }

    /**
    * @Description: 动态条件分页查询方法 
    * @Param: [pageable, blog] 
    * @return: org.springframework.data.domain.Page<com.ljx.blog.bean.Blog> 
    * @Date: 2020/4/4-23:36
    */
    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogDao.findAll((Specification<Blog>) (root, cq, cb) -> {
            List<Predicate> predicates =new ArrayList<>();
            if (!"".equals(blog.getTitle())&&blog.getTitle()!=null){
                predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
            }
            if (blog.getTypeId()!=null){
                predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
            }
            if (blog.isRecommend()){
                predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
            }
            cq.where(predicates.toArray(new Predicate[predicates.size()]));
            return null;
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findPublishedBlog(pageable);
    }

    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {
        return blogDao.findAll((Specification<Blog>) (root, cq, cb) -> {
            //建立两表关联
            Join join=root.join("tags");
            return cb.equal(join.get("id"),tagId);
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(String query,Pageable pageable) {
        return blogDao.findByQuery(query,pageable);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"updateTime");
        Pageable pageable=PageRequest.of(0,size,sort);
        return blogDao.findTop(pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        blog.setViews(0);
        return blogDao.save(blog);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {

        Blog b=blogDao.findById(id).get();
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        return blogDao.save(b);
    }

    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteById(id);

    }
}
