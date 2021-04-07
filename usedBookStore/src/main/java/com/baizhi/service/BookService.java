package com.baizhi.service;

import com.baizhi.entity.Book;
import com.baizhi.page.PageParams;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface BookService {
    //添加新书
    void addBook(Book book);

    //查询所有书
    List<Book> findAll(PageParams pageParams);

    //书籍分页
    List<Book> getBookList(PageParams pageParams,String content);

    //查询总量->分页
    Integer findTotals();

    //根据用户id查询自己发布的书籍
    List<Book> findByUser(int userId);

    Book findById(Integer id);

    //根据用户id查询要售出的书籍
    List<Book> findByUId(Integer id,String content,Boolean saleNow,PageParams pageParams);

    //编辑书
    void updateBook(Book book);

    //订单完成后，库存变化
    void updateStorage(Integer bookId,Integer orderNum);

    //根据id删除书籍
    void delete(Integer bookId);

    //查询要出售的书籍，不分页
    List<Book> findALlSaleNow();

    //查询总量->不分页
    Integer findTotalsByAdmin();

    Integer findTotalsOnSale(Integer userId, Boolean saleNow, String content);
}
