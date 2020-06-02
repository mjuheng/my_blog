package com.heng.service.impl;

import com.heng.entity.Article;
import com.heng.mapper.ArticleMapper;
import com.heng.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int insertArticle(Article article) {
        return articleMapper.insertArticle(article);
    }

    @Override
    public Article getArticleByAid(Integer aid) {
        return articleMapper.getArticleByAid(aid);
    }


    @Override
    public List<Article> listArticle() {
        return articleMapper.listArticle();
    }


    @Override
    public List<Article> listArticleByAidList(List<Integer> aidList) {
        return articleMapper.listArticleByAidList(aidList);
    }

    @Override
    public void updateView(Integer aid, Integer times) {
        articleMapper.updateView(aid, times);
    }

    @Override
    public void updateArticle(Integer aid, String title, String markdown, String html) {
        articleMapper.updateArticle(aid, title, markdown, html);
    }

    @Override
    public List<Article> search(String keyword, Integer uid) {
        return articleMapper.search(keyword,uid);
    }

    @Override
    public void deleteArticle(List<Integer> aidList,Integer uid) {
        articleMapper.deleteArticle(aidList,uid);
    }


    @Override
    public List<Article> listArticleByPage(Integer startIndex, Integer count) {
        return articleMapper.listArticleByPage(startIndex, count);
    }


    @Override
    public List<Article> viewMost(Integer count) {
        return articleMapper.viewMost(count);
    }
}
