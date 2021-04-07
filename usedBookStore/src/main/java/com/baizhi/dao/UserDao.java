package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    //查询所有用户
    List<User> findAll();

    //注册新用户
    void addUser(User user);

    //查询用户名是否重复
    Integer checkUsername(String username);

    //登录
    User login(@Param("username") String username, @Param("password") String password);

    //更新用户
    void  save(User user);

    //根据用户id查询信息
    User findById(Integer id);

    //删除用户
    void delete(Integer id);

    int changePassword(@Param("userId") Integer userId,@Param("newPassword") String confirmPwd);
}
