package com.baizhi.controller;

import com.baizhi.entity.Book;
import com.baizhi.entity.Recommend;
import com.baizhi.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("recommend")
@CrossOrigin
@Slf4j
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    @RequestMapping("add")
    public Map<String,Object> addRecommend(Integer bookId){
        HashMap<String, Object> map = new HashMap<>();

        try {
            recommendService.addRecommend(bookId);
            map.put("status",true);
            map.put("msg","已推荐");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("msg","操作失败，请稍后再试");
        }
        return map;
    }

    @RequestMapping("getAll")
    public Map<String,Object> getAllRecommend(){
        HashMap<String, Object> map = new HashMap<>();
//        List<Recommend> recommendList = recommendService.getAll();
        List<Book> recommendBooks = recommendService.getBooksByRecommend();
        map.put("recommendBooks",recommendBooks);
        map.put("status",true);
        return map;
    }
}
