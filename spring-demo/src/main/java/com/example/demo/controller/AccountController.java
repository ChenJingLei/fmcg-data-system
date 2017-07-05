package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cjl20 on 2017/5/26.
 */
@Controller
public class AccountController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "account/login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String login(@RequestBody User user) {
//        return userRepository.findByUsernameAndAndPassword(user.getUsername(), user.getPassword()).toString();
//    }


    @RequestMapping("/register")
    public String register() {
        return "account/register";
    }
}
