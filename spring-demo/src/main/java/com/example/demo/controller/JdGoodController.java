package com.example.demo.controller;

import com.example.demo.model.JDgood;
import com.example.demo.repositories.JDgoodRepository;
import com.john.crawler.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by cjl20 on 6/5/2017.
 */
@RestController
@RequestMapping("/jdgood")
public class JdGoodController {

    @Autowired
    private JDgoodRepository jDgoodRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "/getJdGoodsByName", method = RequestMethod.GET)
    public void getJdGoodsByName(@RequestParam String name, @RequestParam String platform) {
        Task task = new Task();
        task.setGoodName(name);
        task.setPlatform(platform);
        String res = restTemplate.postForObject("http://localhost:8000/node/executeByGoodName", task, String.class);

    }

}
