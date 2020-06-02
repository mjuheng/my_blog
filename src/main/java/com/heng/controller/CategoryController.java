package com.heng.controller;


import com.heng.entity.Article;
import com.heng.entity.Category;
import com.heng.entity.User;
import com.heng.service.impl.ArticleServiceImpl;
import com.heng.service.impl.AssociationServiceImpl;
import com.heng.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by heng on 2017/9/9.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ArticleServiceImpl articleService;


    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private AssociationServiceImpl associationService;


    @RequestMapping("/all")
    public String all(Model model, HttpSession session) {

        List<Category> categoryList = categoryService.listCategory();

        if (categoryList.size() == 0) {
            System.out.println("还没有分类呢....");
            return "redirect:/err";
        }

        HashMap<Category, List<Integer>> categoryAidMap = new HashMap<>();

        categoryList.forEach(category -> {
            List<Integer> aidList = associationService.listAidByCid(category.getCid());
            categoryAidMap.put(category, aidList);
        });


        HashMap<Category, List<Article>> categoryArticleMap = new HashMap<>();

        for (Map.Entry<Category, List<Integer>> entry : categoryAidMap.entrySet()) {

            List<Integer> aidList = entry.getValue();

            if (aidList.size() == 0) {
                System.out.println("此分类下无文章!");
                categoryArticleMap.put(entry.getKey(), new ArrayList<Article>());
                continue;
            }

            List<Article> articleList = articleService.listArticleByAidList(aidList);
            categoryArticleMap.put(entry.getKey(), articleList);

        }

        model.addAttribute("categoryArticleMap", categoryArticleMap);

        return "category/allCategory";
    }


    @RequestMapping("/edit/{cid}")
    public String editCategory(@PathVariable("cid") Integer cid, Model model) {

        Category category = categoryService.getCategoryByCid(cid);
        model.addAttribute("category", category);

        return "category/editCategory";
    }

    @RequestMapping("/exist/{category}")
    public
    @ResponseBody
    List<Category> existCategory(@PathVariable("category") String category, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return categoryService.existCategory(user.getUid(), category);
    }

    @RequestMapping("/edit")
    public String editCategory(@RequestParam("cid") Integer cid, @RequestParam("category") String category, HttpSession session) {
        User user = (User) session.getAttribute("user");

        categoryService.updateCategory(cid, user.getUid(), category);
        System.out.println("cid=" + cid + "&category=" + category);

        return "redirect:/manage/category";
    }

    @RequestMapping("/delete")
    public String deleteCategories(@RequestParam("cidList") Integer[] cidList, HttpSession session) {
        User user = (User) session.getAttribute("user");

        categoryService.deleteCategories(user.getUid(), Arrays.asList(cidList));

        return "redirect:/manage/category";
    }

    @PostMapping("/insert")
    public String insertCategory(@RequestParam("category") String category, HttpSession session) {

        User user = (User) session.getAttribute("user");
        categoryService.insertCategory(user.getUid(), category);

        System.out.println("分类名称:" + category);

        return "redirect:/manage/category";
    }
}
