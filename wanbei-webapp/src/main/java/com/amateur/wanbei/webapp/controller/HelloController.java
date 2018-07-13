package com.amateur.wanbei.webapp.controller;

import com.amateur.wanbei.service.HelloService;
import com.amateur.wanbei.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenhaitao on 2018/6/4.
 */
@RestController
@RequestMapping("hello")
public class HelloController {


    @Autowired
    HelloService helloService;

    @Autowired
    MenuService menuService;

    @RequestMapping("say")
    public String hello(){
        return helloService.hello();
    }

    @RequestMapping("getToken")
    public String getToken(){
        String token = menuService.getWxWccessToken();
        if(null==token){
            return "错误";
        }
        return token;
    }
}
