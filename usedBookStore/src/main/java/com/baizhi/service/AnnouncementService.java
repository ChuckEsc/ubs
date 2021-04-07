package com.baizhi.service;

import com.baizhi.entity.Announcement;

import java.util.List;

public interface AnnouncementService {
    //获取所有声明
    List<Announcement> getAll();

    //管理员新增 声明
    void addAnnounce(String msg);

    //删除声明
    void delete(Integer id);
}
