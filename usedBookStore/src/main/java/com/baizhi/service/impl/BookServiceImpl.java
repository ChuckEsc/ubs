package com.baizhi.service.impl;

import com.baizhi.common.CommonConstants;
import com.baizhi.dao.BookDao;
import com.baizhi.entity.Book;
import com.baizhi.page.PageParams;
import com.baizhi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public List<Book> findAll(PageParams pageParams) {
        List<Book> books = bookDao.findAll(pageParams);
        return books;
    }

    public List<Book> setImgPrefix(List<Book> books) {
        books.forEach(book -> book.setPic(CommonConstants.PREFIX + book.getPic()));
        return books;
    }

    @Override
    public List<Book> getBookList(PageParams pageParams,String content) {
        List<Book> bookList = bookDao.getBookList(pageParams,content);
        return bookList;
    }

    @Override
    public Integer findTotals() {
        return bookDao.findTotals();
    }

    @Override
    public List<Book> findByUser(int userId) {
        return bookDao.findByUser(userId);
    }

    @Override
    public Book findById(Integer id) {
        Book book = bookDao.findById(id);
//        book.setPic(CommonConstants.prefix+book.getPic());
        return book;
    }

    @Override
    public List<Book> findByUId(Integer userId,String content,Boolean saleNow,PageParams pageParams) {
        return bookDao.findByUId(userId,content,saleNow,pageParams);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }


    @Override
    public void updateStorage(Integer bookId, Integer orderNum) {
        bookDao.updateStorage(bookId, orderNum);
    }

    @Override
    public void delete(Integer bookId) {
        bookDao.delete(bookId);
    }

    @Override
    public List<Book> findALlSaleNow() {
        return bookDao.findALlSaleNow();
    }

    @Override
    public Integer findTotalsByAdmin() {
        return bookDao.findTotalsByAdmin();
    }

    @Override
    public Integer findTotalsOnSale(Integer userId, Boolean saleNow, String content) {
        return bookDao.findTotalsOnSale(userId,saleNow,content);
    }
}
