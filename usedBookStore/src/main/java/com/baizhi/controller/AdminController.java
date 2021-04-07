package com.baizhi.controller;

import com.baizhi.common.CommonConstants;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Book;
import com.baizhi.service.AdminService;
import com.baizhi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("admin")
@CrossOrigin
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private BookService bookService;

    @RequestMapping("login")
    public Map<String, Object> login(Admin admin, HttpServletRequest request) {
        log.info("admin=>[{}]", admin);
        Map<String, Object> map = new HashMap<>();
        Admin adminInfo = adminService.adminLogin(admin);
        if (adminInfo != null) {
            request.getSession().setAttribute(CommonConstants.ADMININFO, adminInfo);
            map.put(CommonConstants.ADMININFO, adminInfo);
            map.put("msg", "success");
            map.put("status", true);
        }else {
            map.put("status",false);
            map.put("msg","用户名或密码不正确！");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, path = "test")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        System.out.println("==========2===========");
        return map;
    }

    @RequestMapping("getBooks")
    public Map<String, Object> getBooks(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
//        Book book = new Book();
        List<Book> books = bookService.findALlSaleNow();
        map.put("books", books);
        map.put("status", true);
        return map;
    }

    /**
     * 获取当前管理员信息
     *
     * @param request
     * @return
     */
    @RequestMapping("info")
    public Map<String, Object> getAdminInfo(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        Object adminInfo = request.getSession().getAttribute(CommonConstants.ADMININFO);
        map.put(CommonConstants.ADMININFO, adminInfo);
        map.put("status", true);
        map.put("msg", "success");
        return map;
    }
}
