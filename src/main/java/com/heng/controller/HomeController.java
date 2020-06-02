package com.heng.controller;

import com.heng.entity.Article;
import com.heng.entity.User;
import com.heng.service.impl.ArticleServiceImpl;
import com.heng.service.impl.CategoryServiceImpl;
import com.heng.utils.Page;
import com.heng.utils.PageUtil;
import com.heng.utils.RemoveHTMLTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by heng on 2017/9/4.
 */
@Controller
public class HomeController {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private CategoryServiceImpl categoryService;


    @RequestMapping(value = {"/", "/home"})
    public String home() {
        return "forward:/p/1";
    }


    @RequestMapping(value = {"/p/{currentPage}"})
    public String mainPage(@PathVariable("currentPage") Integer currentPage, Model model, HttpSession session) {


        // 设置分页参数
        List<Article> articleList = articleService.listArticle();
        Page page = PageUtil.createPage(5, articleList.size(), currentPage);
        // 处理页数越界
        if (currentPage <= 0) {
            currentPage = 1;
        }
        if (currentPage > page.getTotalPage()) {
            currentPage = page.getTotalPage();
        }


        articleList = articleService.listArticleByPage(page.getStartIndex(), page.getEveryPage());


        // 取出HTML标签
        articleList.forEach(article -> {
            String html = RemoveHTMLTag.removeTags(article.getHtml());
            if (html.length() > 140) {
                html = html.substring(0, 140);
            }
            article.setHtml(html + " ...... ");
        });


        List<Article> mostView = articleService.viewMost(5);

        model.addAttribute("mostView", mostView);
        model.addAttribute("page", page);
        model.addAttribute("articleList", articleList);


        return "front/home";
    }

    @RequestMapping("/err")
    public String error() {
        return "common/error";
    }


    @RequestMapping("/search")
    public String search(@RequestParam(value = "keyword", defaultValue = "") String keyword, Model model, HttpSession session, RedirectAttributes attributes) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setUid(1);
        }

        List<Article> articleList = articleService.search(keyword, user.getUid());


        //String reg = "(?i)" + keyword;
        String reg = keyword;
        articleList.forEach(article -> {
            article.setTitle(article.getTitle().replaceAll(reg, "<mark>" + keyword + "</mark>"));
        });

        model.addAttribute("articleList", articleList);
        model.addAttribute("keyword", keyword);

        return "front/search";
    }

}
