package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;

public interface UserService {

    //查询所有用户
    List<User> findAll();

    //注册新用户
    void addUser(User user);

    //查询用户名是否重复
    Integer checkUsername(String username);

    User login(String username, String password);

    //更新用户
    void  save(User user);

    //根据用户id查询信息
    User findById(Integer id);

    //删除用户
    void delete(Integer id);

    //更新用户密码
    int changePassword(Integer userId, String confirmPwd);
}
