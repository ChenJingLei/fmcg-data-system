package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cjl20 on 2017/5/26.
 */
@Controller
@RequestMapping("/user")
public class UserPageController {

    @RequestMapping("/main")
    public String main(){
        return "page/userpage";
    }



}
