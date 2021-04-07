package com.baizhi.dao;

import com.baizhi.entity.Book;
import com.baizhi.page.PageParams;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface BookDao {
    //添加新书
    void addBook(Book book);

    //查询所有书包括不出售的
    List<Book> findAll(PageParams pageParams);

    //查询书籍+分页
    ArrayList<Book> getBookList(@Param("pageParams") PageParams pageParams, @Param("content") String content);

    //查询总量->分页
    Integer findTotals();

    //根据用户id查询自己发布的书籍
    List<Book> findByUser(int userId);

    //根据id查询书籍
    Book findById(Integer id);

    //根据用户id查询要售出的书籍
    List<Book> findByUId(@Param("userId") Integer userId,@Param("content") String content
            , @Param("saleNow") Boolean saleNow ,@Param("pageParams") PageParams pageParams);

    //编辑书
    void updateBook(Book book);

    //订单完成后，库存变化
    void updateStorage(@Param("bookId") Integer bookId, @Param("orderNum") Integer orderNum);

    //根据id删除书籍
    void delete(Integer bookId);

    //查询要出售的书籍，不分页
    List<Book> findALlSaleNow();

    //查询总量->不分页
    Integer findTotalsByAdmin();

    Integer findTotalsOnSale(@Param("userId") Integer userId,@Param("saleNow") Boolean saleNow, @Param("content") String content);

}
