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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by heng on 2017/9/4.
 */
@Controller
public class ArticleController {

    @Autowired
    private CategoryServiceImpl categoryService;


    @Autowired
    private ArticleServiceImpl articleService;


    @Autowired
    private AssociationServiceImpl associationService;


    @RequestMapping("/create")
    public String create(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        return "article/createArticle";
    }


    @RequestMapping("/article/publish")
    public String publish(String title, Integer[] cidList, String markdown, String html, Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        Article article = new Article();
        article.setUid(user.getUid());
        article.setTitle(title);
        article.setMarkdown(markdown);
        article.setHtml(html);
        article.setGmtPost(new Date());
        article.setView(1);

        /*
         * 插入文章
         * */
        articleService.insertArticle(article);


        /*
         *
         * 关联文章与分类
         * */

        associationService.saveAssociation(article.getAid(), Arrays.asList(cidList));

        model.addAttribute("tip", "发布成功!");
        model.addAttribute("title", title);
        model.addAttribute("aid", article.getAid());

        return "article/publishDone";
    }


    @RequestMapping("/article/display/{aid}")
    public String display(@PathVariable("aid") Integer aid, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes attributes) {

        // 获取文章，浏览数+1 ，再获取分类
        Article article = articleService.getArticleByAid(aid);
        if (article == null) {
            attributes.addFlashAttribute("reason", "文章不存在!");
            return "redirect:/err";
        }

        int view = article.getView();
        article.setView(++view);

        model.addAttribute("article", article);


        /*
         *
         * 获取所有分类标签
         * */
        List<Category> tags = categoryService.listCategory();
        model.addAttribute("tags", tags);


        /*
         *
         * 通过 aid 获取其所属分类，再通过 cidList 获取 categoryList
         * */
        List<Integer> cidList = associationService.listCidByAid(aid);
        List<Category> categoryList = categoryService.listCategoryByCid(cidList);
        model.addAttribute("categoryList", categoryList);


        List<Article> mostView = articleService.viewMost(5);
        model.addAttribute("mostView", mostView);
        return "article/displayArticle";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("aid") Integer aid, Model model, HttpSession session, RedirectAttributes attributes) {

        User user = (User) session.getAttribute("user");

        Article article = articleService.getArticleByAid(aid);
        if (article == null) {
            attributes.addFlashAttribute("reason", "文章不存在!");
            return "redirect:/err";
        }

        if (article.getUid() != user.getUid()) {
            attributes.addFlashAttribute("reason", "您没有权限编辑此文章.");
            return "redirect:/err";
        }

        model.addAttribute("article", article);

        /*
         *
         * 文章所属分类 id
         * */
        List<Integer> cidList = associationService.listCidByAid(aid);
        model.addAttribute("cidList", cidList);

        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList", categoryList);

        return "article/editArticle";
    }

    /*
     *
     * 更新文章
     * */

    @PostMapping("/article/update")
    public String update(Integer aid, String title, Integer[] cidList, String markdown, String html, Model model) {

        /*
         *
         * 1.更新文章内容
         * 2.更新分类(先删除旧关联，再插入新关联)
         *
         * */
        articleService.updateArticle(aid, title, markdown, html);

        associationService.deleteCidByAid(aid);
        associationService.saveAssociation(aid, Arrays.asList(cidList));

        model.addAttribute("tip", "保存修改成功!");
        model.addAttribute("title", title);
        model.addAttribute("aid", aid);

        return "article/publishDone";
    }

    /*
     *
     *
     * 文章归档
     *
     * */

    @RequestMapping("/article/archive")
    public String archive(Model model, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            user = new User();
            user.setUid(1);
        }


        //所有文章
        List<Article> articleList = articleService.listArticle();

        /*
         *
         * 按年月归类
         * */

        LinkedHashMap<Integer, LinkedHashMap<Integer, ArrayList<Article>>> articleMap = new LinkedHashMap();

        articleList.forEach(article -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(article.getGmtPost());
            Integer year = calendar.get(Calendar.YEAR);
            Integer month = calendar.get(Calendar.MONTH) + 1;

            LinkedHashMap<Integer, ArrayList<Article>> monthMap = articleMap.get(year);
            if (monthMap == null) {
                monthMap = new LinkedHashMap<>();
            }

            ArrayList<Article> articles = monthMap.get(month);
            if (articles == null) {
                articles = new ArrayList<>();
            }

            articles.add(article);
            monthMap.put(month, articles);
            articleMap.put(year, monthMap);

        });


        model.addAttribute("articleMap", articleMap);
        return "article/archiveArticle";
    }


    /*
     *
     * 删除文章
     *
     * */
    @RequestMapping("/article/delete")
    @ResponseBody
    public String deleteArticle(@RequestParam("aidList") Integer[] aidList, HttpSession session) {

        User user = (User) session.getAttribute("user");

        articleService.deleteArticle(Arrays.asList(aidList), user.getUid());

        return "success";
    }

    /**
     * 增加文章阅读数
     *
     * @param
     */
    @RequestMapping("/article/addView")
    @ResponseBody
    public void addView(@RequestBody HashMap<String, Object> param) {
        Integer aid = (Integer) param.get("aid");
        if (aid != null) {
            articleService.updateView(aid, 1);
        }
    }

}
