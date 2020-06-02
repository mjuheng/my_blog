package com.heng.controller;


import com.heng.entity.Article;
import com.heng.entity.Category;
import com.heng.entity.User;
import com.heng.service.impl.ArticleServiceImpl;
import com.heng.service.impl.CategoryServiceImpl;
import com.heng.utils.Page;
import com.heng.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by heng on 2017/9/9.
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

    @Autowired
    private ArticleServiceImpl articleService;


    @Autowired
    private CategoryServiceImpl categoryService;


    @RequestMapping("/article")
    public String manageArticle(Model model) {
        return "forward:/manage/article/p/1";
    }


    @RequestMapping("/article/p/{currentPage}")
    public String manageArticleByPage(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {
        if (currentPage == null) {
            currentPage = 1;
        }

        User user = (User) session.getAttribute("user");

        List<Article> articleList = articleService.listArticle();

        Page page = PageUtil.createPage(7, articleList.size(), currentPage);
        if (page.getTotalPage() < currentPage) {
            System.out.println("页面越界了！");
            return "redirect:/err";
        }

        articleList = articleService.listArticleByPage(page.getStartIndex(), page.getEveryPage());
        model.addAttribute("articleList", articleList);
        model.addAttribute("page", page);

        return "manage/articleManagement";
    }

    @RequestMapping("/category")
    public String manageCategory(Model model, HttpSession session) {

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        return "manage/categoryManagement";
    }

}
