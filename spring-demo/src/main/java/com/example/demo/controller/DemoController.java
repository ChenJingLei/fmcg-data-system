package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cjl20 on 2017/5/25.
 */
@Controller
public class DemoController {

    @RequestMapping("/")
    public String test() {
        return "test";
    }
}
