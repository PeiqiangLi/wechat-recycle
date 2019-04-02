package com.wechat.recycle.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Article;
import com.wechat.recycle.mapper.ArticleMapper;
import com.wechat.recycle.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Override
    public Article selectOne(Integer id) {
        return articleMapper.selectOne(id);
    }

    @Override
    public PageInfo<Article> selectAll(Integer pageNum, Integer pageSize, String str) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleMapper.selectAll(str);
        return new PageInfo<>(articles);
    }

    @Override
    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public int deleteOne(Integer id) {
        return articleMapper.deleteOne(id);
    }
}
