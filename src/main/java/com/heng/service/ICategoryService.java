package com.heng.service;

import com.heng.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ICategoryService {

    void insertCategory(Integer uid, String category);

    List<Category> listCategory();

    List<Category> listCategoryByCid(List<Integer> cidList);

    Category getCategoryByCid(Integer cid);

    void updateCategory(Integer cid, Integer uid, String category);

    List<Category> existCategory(Integer uid, String category);

    void deleteCategories(Integer uid, List<Integer> cidList);
}
