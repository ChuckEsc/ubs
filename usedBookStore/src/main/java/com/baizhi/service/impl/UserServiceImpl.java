package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public Integer checkUsername(String username) {
        return dao.checkUsername(username);
    }

    @Override
    public User login(String username, String password) {
        return dao.login(username,password);
    }

    @Override
    public void save(User user) {
        dao.save(user);
    }

    @Override
    public User findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public int changePassword(Integer userId, String confirmPwd) {
        return dao.changePassword(userId,confirmPwd);
    }
}
