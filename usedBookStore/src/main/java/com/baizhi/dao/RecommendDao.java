package com.baizhi.dao;

import com.baizhi.entity.Book;
import com.baizhi.entity.Recommend;

import java.util.List;

public interface RecommendDao {
    //获取所有 推荐
    List<Recommend> getAll();

    //管理员新增 推荐
    void addRecommend(Integer bookId);

    //查询推荐中的所有书
    List<Book> getBooksByRecommend();

}
