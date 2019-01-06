package com.wechat.recycle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class IndexController {

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String index() {
        return "hello world!";
    }

}
