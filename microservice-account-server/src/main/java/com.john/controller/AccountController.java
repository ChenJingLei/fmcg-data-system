package com.john.controller;

import com.john.mapper.AuthorityMapper;
import com.john.mapper.UserAuthorityMapper;
import com.john.mapper.UserMapper;
import com.john.model.Authority;
import com.john.model.User;
import com.john.model.UserAuthority;
import com.john.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * Created by cjl20 on 6/4/2017.
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private UserAuthorityMapper userAuthorityMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public List<UserAuthority> login(@RequestBody User user) {
        try {
            User loginer = userMapper.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (loginer != null) {
                List<UserAuthority> userAuthority = userAuthorityMapper.findById(loginer.getId());
                return userAuthority;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        try {
            user.setEnable(false);
            int result = userMapper.insertByUser(user);
            String code = String.valueOf(new Random().nextInt(10000));
            stringRedisTemplate.opsForValue().set(user.getUsername(), code);
            emailService.sendCodeTemplateMail(user.getFirstname() + " " + user.getLastname(), user.getEmail(), code);
            return String.valueOf(result);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/forgot", method = RequestMethod.POST)
    public String forgot(@RequestBody User user) {
        try {
            User forgoter = userMapper.findByEmail(user.getEmail());
            if (forgoter != null) {
                String code = String.valueOf(new Random().nextInt(10000));
                stringRedisTemplate.opsForValue().set(user.getUsername(), code);
                emailService.sendCodeTemplateMail(user.getFirstname() + " " + user.getLastname(), user.getEmail(), code);
                return "1";
            } else {
                return "0";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/forgot/code", method = RequestMethod.POST)
    public String forgotCode(@RequestParam String email, @RequestParam String newPassword, @RequestParam String code) {
        try {
            User forgoter = userMapper.findByEmail(email);
            String sys_code = stringRedisTemplate.opsForValue().get(email);
            if (forgoter != null && sys_code.equals(code)) {
                forgoter.setEnable(true);
                forgoter.setPassword(newPassword);
                userMapper.update(forgoter);
                return "Success";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "0";
    }

    @RequestMapping(value = "/register/code", method = RequestMethod.POST)
    public String registerCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        try {
            User user = userMapper.findByEmail(email);
            String sys_code = stringRedisTemplate.opsForValue().get(email);
            if (user != null && sys_code.equals(code)) {
                user.setEnable(true);
                //授权
                Authority authority = new Authority();
                authority.setId(user.getId());
                authority.setAuthority("User");
                authorityMapper.insertByAuthority(authority);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Success";
    }

    @RequestMapping(value = "/register/checkUsername", method = RequestMethod.GET)
    public String checkUsername(@RequestParam String username) {
        try {
            User user = userMapper.findByUsername(username);
            if (user != null) {
                return "Exist";
            } else {
                return "NotExist";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/register/checkEmail", method = RequestMethod.GET)
    public String checkEmail(@RequestParam String email) {
        try {
            User user = userMapper.findByEmail(email);
            if (user != null) {
                return "Exist";
            } else {
                return "NotExist";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
