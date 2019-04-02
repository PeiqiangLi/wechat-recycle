package com.wechat.recycle.service;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.entity.Article;

public interface ArticleService {

    Article selectOne(Integer id);

    PageInfo<Article> selectAll(Integer pageNum, Integer pageSize, String str);

    int addArticle(Article article);

    int deleteOne(Integer id);

}
