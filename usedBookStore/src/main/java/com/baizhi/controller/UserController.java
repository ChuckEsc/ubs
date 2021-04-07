package com.baizhi.controller;

import com.baizhi.common.CommonConstants;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired(required = true)
    private UserService userService;

    @GetMapping("all")
    public List<User> findAll() {
        List<User> all = userService.findAll();
        return all;
    }

    @PostMapping("register")
//    public Map<String,Object> register(@RequestParam String username,String password,String phone,String gender){
    public Map<String, Object> register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            userService.addUser(user);
            map.put("msg", "注册成功");
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("status", false);
        }
        System.out.println("user:" + user);
        return map;
    }

    @RequestMapping(path = "check", method = RequestMethod.GET)
    public Map<String, Object> checkUsername(@RequestParam("username") String username) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("username" + username);
        Integer id = userService.checkUsername(username);
        if (id == null) {
            map.put("msg", "用户名可以使用");
            map.put("status", true);
        } else {
            map.put("msg", "用户名已被注册！");
            map.put("status", false);
        }
        return map;
    }

    @RequestMapping(path = "login")
    public Map<String, Object> login(HttpServletRequest request, String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (username.equals("admin") && password.equals("1234567")) {
            request.getSession().setAttribute("adminInfo", "admin");
        }
        User user1 = userService.login(username, password);
        if (user1 != null) {
            map.put("msg", "登录成功");
            map.put("status", true);
            map.put("userInfo", user1);
        } else {
            map.put("msg", "用户名或密码错误");
            map.put("status", false);
        }
        return map;
    }


    @PostMapping("update")
    public Map<String, Object> updateUser(User user, MultipartFile photo) throws IOException {
        log.info("用户信息:[{}]", user.toString());
        Map<String, Object> map = new HashMap<>();
        try {
            //photo.isEmpty()
//            if (photo != null && photo.getSize() != 0) {
            if (photo != null && photo.getSize() != 0) {
                log.info("文件信息:[{}]", photo.getOriginalFilename());
                String newName = user.getId().toString() + UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());
                //头像保存
                photo.transferTo(new File(CommonConstants.PROFILEPATH, newName));
                //存储头像名
                user.setProfile(newName);
                //保存用户修改的信息
            }
            userService.save(user);
            map.put("state", true);
            map.put("msg", "资料编辑成功");
            map.put("userInfo", user);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "资料编辑失败！");
            map.put("state", false);
        }
        return map;
    }

    @RequestMapping("delete")
    public Map<String, Object> deleteUser(Integer id) {
        log.info("用户id:[{}]", id);
        Map<String, Object> map = new HashMap<>();
        try {
            userService.delete(id);
            map.put("status", true);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "删除失败，请稍后再试");
        }
        return map;
    }

    @RequestMapping("getInfo")
    public User getUserInfoById(Integer userId) {
        log.info("用户id:[{}]", userId);
        Map<String, Object> map = new HashMap<>();
        User userInfo = userService.findById(userId);
        return userInfo;
    }

    @RequestMapping("changePwd")
    public Map<String, Object> changePassword(Integer userId, String oldPwd, String newPwd, String confirmPwd) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.findById(userId);
        if (user==null) {
            map.put("status", false);
            map.put("msg", "用户不存在！");
        } else if (!user.getPassword().equals(oldPwd)){
            map.put("status", false);
            map.put("msg", "密码不一致！");
        }else if (!newPwd.equals(confirmPwd)){
            map.put("status", false);
            map.put("msg", "两次密码不一致！");
        }else {
            int i = userService.changePassword(userId,confirmPwd);
            map.put("status", true);
            map.put("msg", "修改成功");
        }
        return map;
    }
}
