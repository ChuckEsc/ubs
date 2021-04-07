package com.baizhi.dao;

import com.baizhi.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AnnouncementDao {
    //获取所有声明
    List<Announcement> getAll();

    //管理员新增 声明
    void addAnnounce(String msg);

    //根据id删除声明
    void delete(Integer id);
}
