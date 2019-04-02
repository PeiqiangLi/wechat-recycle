package com.wechat.recycle.controller;

import com.github.pagehelper.PageInfo;
import com.wechat.recycle.common.utils.Result;
import com.wechat.recycle.common.utils.ResultUtil;
import com.wechat.recycle.common.utils.StatusCodeEnum;
import com.wechat.recycle.entity.Article;
import com.wechat.recycle.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping(value = "/getArticle",  method = RequestMethod.GET)
    public Result getArticle(Integer id) {
        if (id == null) {
            return ResultUtil.error(StatusCodeEnum.PARAMS_EXCEPTION);
        }
        Article article = articleService.selectOne(id);
        return ResultUtil.success(article);
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    public Result addArticle(@RequestBody Article article) {
        if (StringUtils.isEmpty(article.getTitle())) {
            // 要求前端去掉最后面的空格
            return ResultUtil.error("1003","文章标题为空");
        } else if (StringUtils.isEmpty(article.getIntroduction())) {
            return ResultUtil.error("1003","文章简介为空");
        } else if (StringUtils.isEmpty(article.getContent())) {
            return ResultUtil.error("1003","文章内容为空");
        } else if (StringUtils.isEmpty(article.getCover())) {
            return ResultUtil.error("1003","文章图片为空");
        }
        int id = articleService.addArticle(article);
        if (id <= 0) {
            return ResultUtil.error("1002","新增文章失败");
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getArticles", method = RequestMethod.GET)
    public Result getArticles(Integer pageNum, Integer pageSize, String title) {
        PageInfo<Article> orders = articleService.selectAll(pageNum, pageSize, title);
        return ResultUtil.pageResult(orders);
    }

    @RequestMapping(value = "/deleteArticle", method = RequestMethod.GET)
    public Result deleteArticle(Integer id) {
        int count = articleService.deleteOne(id);
        if (count > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.error(StatusCodeEnum.FAILED);
    }



}
