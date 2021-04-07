package com.baizhi.service.impl;

import com.baizhi.common.CommonConstants;
import com.baizhi.dao.RecommendDao;
import com.baizhi.entity.Book;
import com.baizhi.entity.Recommend;
import com.baizhi.service.BookService;
import com.baizhi.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendDao recommendDao;
    @Autowired
    private BookService bookService;

    @Override
    public List<Recommend> getAll() {
        return recommendDao.getAll();
    }

    @Override
    public void addRecommend(Integer bookId) {
        recommendDao.addRecommend(bookId);
    }

    public void setImgPrefix(List<Book> books){
        books.forEach(book -> book.setPic(CommonConstants.PREFIX +book.getPic()));
    }

    @Override
    public List<Book> getBooksByRecommend() {
        List<Book> books = recommendDao.getBooksByRecommend();
        return books;
    }
}
