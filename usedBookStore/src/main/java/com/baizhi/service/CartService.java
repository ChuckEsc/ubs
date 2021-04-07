package com.baizhi.service;

import com.baizhi.entity.Cart;

import java.util.List;

public interface CartService {
    //查询购物车所有
    List<Cart> findAll(Integer userId);

    //加入到购物车
    void insertCart(Cart cart);

    //购物车+1
    void plusOne(Integer cartId);

    //购物车-1
    void minusOne(Integer cartId);

    //移除一条购物车记录
    void delete(Integer id);

    //根据id查询购物车
    Cart findById(Integer cartId);
}
