package com.baizhi.controller;

import com.baizhi.entity.Announcement;
import com.baizhi.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("announce")
@CrossOrigin
@Slf4j
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping("add")
    public Map<String,Object> addAnnounce(String msg){
        Map<String,Object> map = new HashMap<>();
        announcementService.addAnnounce(msg);
        map.put("status",true);
        map.put("msg","发布公告成功");
        return map;
    }

    @RequestMapping("getAll")
    public Map<String,Object> getAllAnnounce(){
        Map<String,Object> map = new HashMap<>();
        List<Announcement> announcementList = announcementService.getAll();
        map.put("announcementList",announcementList);
        map.put("status",true);
        return map;
    }

    @RequestMapping("delete")
    public Map<String,Object> deleteAnnounce(Integer id){
        Map<String,Object> map = new HashMap<>();
        announcementService.delete(id);
        map.put("status",true);
        map.put("msg","删除成功！");
        return map;
    }

}
