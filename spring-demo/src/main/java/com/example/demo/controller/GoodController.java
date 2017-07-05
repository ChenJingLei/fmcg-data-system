package com.example.demo.controller;

import com.example.demo.mapper.UserGoodMapper;
import com.example.demo.model.*;
import com.example.demo.repositories.*;
import com.john.crawler.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by cjl20 on 6/5/2017.
 */
@RequestMapping("/good")
@RestController
public class GoodController {

    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private UserGoodRepository userGoodRepository;

    @Autowired
    private UserGoodMapper userGoodMapper;

    @Autowired
    private TaoBaoGoodRepository taoBaoGoodRepository;

    @Autowired
    private MallGoodRepository mallGoodRepository;

    @Autowired
    private JDgoodRepository jDgoodRepository;

    @RequestMapping(value = "/addUrl", method = RequestMethod.GET)
    public String addUrl(@RequestParam String url, @RequestParam Long uid, @RequestParam String platform) {
        try {
            Good good = new Good();
            good.setGoodUrl(url);
            Good savedGood = goodRepository.save(good);
            UserGood userGood = new UserGood();
            userGood.setUid(uid);
            userGood.setGid(savedGood.getId());
            userGoodRepository.save(userGood);
            RestTemplate restTemplate = new RestTemplate();
            Task task = new Task();
            task.setGoodName(url);
            task.setPlatform(platform);
            String res = restTemplate.postForObject("http://localhost:8000/node/executeByGoodURL", task, String.class);
            return "1";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Good> getAll(@RequestParam Long uid) {
        try {
            List<Good> goods = userGoodMapper.findAllByUid(uid);
            return goods;
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getTaoBaoAll", method = RequestMethod.GET)
    public List<TaoBaoGood> getTaoBaoAll() {
        try {
            return taoBaoGoodRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getMallAll", method = RequestMethod.GET)
    public List<MallGood> getMallAll() {
        try {
            return mallGoodRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/getJDAll", method = RequestMethod.GET)
    public List<JDgood> getJdAll() {
        try {
            return jDgoodRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/deleteByUidAndGid", method = RequestMethod.GET)
    public void deleteByUidAndGid(@RequestParam Long uid, @RequestParam Long gid) {
        try {
            userGoodMapper.deleteByGidAndUid(gid, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
