package com.baizhi.service.impl;

import com.baizhi.dao.AnnouncementDao;
import com.baizhi.entity.Announcement;
import com.baizhi.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    @Override
    public List<Announcement> getAll() {
        return announcementDao.getAll();
    }

    @Override
    public void addAnnounce(String msg) {
        announcementDao.addAnnounce(msg);
    }

    @Override
    public void delete(Integer id) {
        announcementDao.delete(id);
    }
}
