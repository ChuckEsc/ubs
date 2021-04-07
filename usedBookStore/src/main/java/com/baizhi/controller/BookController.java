package com.baizhi.controller;

import com.baizhi.common.CommonConstants;
import com.baizhi.entity.Book;
import com.baizhi.page.PageParams;
import com.baizhi.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("book")
@CrossOrigin
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("publish")
    public Map<String, Object> publish(Book book, MultipartFile covers){
        log.info("book：[{}]", book.toString());
        log.info("pic:[{}]", covers.getOriginalFilename());
        Map<String, Object> map = new HashMap<>();
        try {
            if (covers != null && covers.getSize() != 0) {
                String newName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(covers.getOriginalFilename());
                covers.transferTo(new File(CommonConstants.BOOKPATH, newName));
                book.setPic(newName);
                bookService.addBook(book);
                map.put("msg", "发布成功");
                map.put("state", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "发布信息错误" + e.getMessage());
            map.put("state", false);
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, path = "mypub")
    public Map<String, Object> myPubs(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<Book> list = bookService.findByUser(userId);
        if (!list.isEmpty()) {
            map.put("msg", "success");
            map.put("list", list);
            map.put("state", true);
        } else {
            map.put("msg", "当前还没有发布任何书籍");
            map.put("State", false);

        }
        return map;
    }

    /**
     * 1、查询所有要出售的书，显示在主页
     * 2、搜索，模糊查询
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "findAll")
    public Map<String, Object> findAll(Integer pageNow, Integer size,String content) {
        Map<String, Object> map = new HashMap<>();
        log.info("content=[{}]",content);
        List<Book> bookList = bookService.getBookList(PageParams.build(size, pageNow),content);
        map.put("bookList", bookList);
        Integer totals;
        if (content == null ||content.equals("")){
            totals = bookService.findTotals();
        }else {
            totals = bookList.size();
//            map.put("pageNow",)
        }
        return getStringObjectMap(size, map, totals);
    }

    /**
     * 处理分页返回
     * @param size
     * @param map
     * @param totals
     * @return
     */
    private Map<String, Object> getStringObjectMap(Integer size, Map<String, Object> map, Integer totals) {
        int pages = totals % size == 0 ? (totals / size) : (totals / size + 1);
        map.put("pages", pages); //页数
        map.put("totals", totals); //总数
        map.put("status", true);
        map.put("msg", "success");
        return map;
    }

    /**
     * 管理所有书，分页
     *
     * @param pageNow
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "findAllByAdmin")
    public Map<String, Object> findAllByAdmin(Integer pageNow, Integer size) {
        Map<String, Object> map = new HashMap<>();
        PageParams pageParams = PageParams.build(size, pageNow);
        List<Book> bookList = bookService.findAll(pageParams);
        Integer totals = bookService.findTotalsByAdmin();
        map.put("bookList", bookList);
        return getStringObjectMap(size, map, totals);
    }

    @RequestMapping(method = RequestMethod.GET, path = "findOne")
    public Map<String, Object> findOne(Integer id) {
        Map<String, Object> map = new HashMap<>();
        Book book = bookService.findById(id);
        map.put("msg", "success");
        map.put("state", true);
        map.put("detail", book);
        return map;
    }

    /**
     * 1、查询卖家出售的书
     * 2、查询 我卖出的书
     * 3、分页+搜索
     * @param userId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "findOnsale")
    public Map<String, Object> findOnsale(Integer userId,Integer pageNow, Integer size,String content,Boolean saleNow) {
        Map<String, Object> map = new HashMap<>();
        PageParams pageParams = PageParams.build(size, pageNow);
        List<Book> list = bookService.findByUId(userId,content,saleNow, pageParams);
        Integer totals = bookService.findTotalsOnSale(userId,saleNow,content);
        map.put("bookList", list);
        return getStringObjectMap(size,map,totals);
    }

    @RequestMapping(method = RequestMethod.POST, path = "updateBook")
    public Map<String, Object> updateBook(Book book) {
        Map<String, Object> map = new HashMap<>();
        log.info("书籍编辑信息：[{}]", book.toString());
        try {
            bookService.updateBook(book);
            map.put("msg", "修改成功");
            map.put("status", true);
        }catch (Exception e){
            map.put("msg", "修改失败，请检查提交的信息");
            map.put("status", false);
        }

        return map;
    }


    @RequestMapping("delete")
    public Map<String, Object> deleteBook(Integer bookId) {
        Map<String, Object> map = new HashMap<>();
        try {
            bookService.delete(bookId);
            map.put("status", true);
            map.put("msg", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "删除失败，请稍后再试");
        }
        return map;
    }
}
