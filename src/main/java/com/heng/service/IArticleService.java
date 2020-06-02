package com.heng.service;

import com.heng.entity.Article;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IArticleService {
    int insertArticle(Article article);

    Article getArticleByAid(Integer aid);

    List<Article> listArticle();

    List<Article> listArticleByAidList(List<Integer> aidList);

    void updateView(Integer aid, Integer times);

    void updateArticle(Integer aid, String title, String markdown, String html);

    void deleteArticle(List<Integer> aidList,Integer uid);

    List<Article> listArticleByPage(Integer startIndex, Integer count);

    List<Article> viewMost(Integer count);

    List<Article> search(String keyword, Integer uid);
}
