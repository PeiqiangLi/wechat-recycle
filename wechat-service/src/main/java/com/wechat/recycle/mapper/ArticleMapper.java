package com.wechat.recycle.mapper;

import com.wechat.recycle.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {

    Article selectOne(@Param("id")Integer id);

    List<Article> selectAll(@Param("str")String str);

    int addArticle(Article article);

    int deleteOne(@Param("id")Integer id);

}
