package com.heng.service.impl;

import com.heng.entity.Category;
import com.heng.mapper.CategoryMapper;
import com.heng.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public void insertCategory(Integer uid, String category) {
        categoryMapper.insertCategory(uid,category);
    }

    @Override
    public List<Category> listCategory() {
        return categoryMapper.listCategory();
    }

    @Override
    public List<Category> listCategoryByCid(List<Integer> cidList) {
        return categoryMapper.listCategoryByCid(cidList);
    }

    @Override
    public Category getCategoryByCid(Integer cid) {
        return categoryMapper.getCategoryByCid(cid);
    }

    @Override
    public void updateCategory(Integer cid, Integer uid, String category) {
        categoryMapper.updateCategory(cid,uid,category);
    }

    @Override
    public List<Category> existCategory(Integer uid, String category) {
        return categoryMapper.existCategory(uid,category);
    }

    @Override
    public void deleteCategories(Integer uid, List<Integer> cidList) {
        categoryMapper.deleteCategories(uid,cidList);
    }
}
