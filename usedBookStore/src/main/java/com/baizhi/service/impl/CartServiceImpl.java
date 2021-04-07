package com.baizhi.service.impl;

import com.baizhi.dao.CartDao;
import com.baizhi.entity.Cart;
import com.baizhi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    String prefix = "http://localhost:8083/static/covers/";

    @Override
    public List<Cart> findAll(Integer userId) {
        List<Cart> cartList = cartDao.findAll(userId);
//        setImgPrefix(cartList);
        return cartList;
    }

    private void setImgPrefix(List<Cart> cartList) {
        cartList.forEach(cart -> cart.setSmallImg(prefix + cart.getSmallImg()));
    }

    @Override
//    @Transactional
    public void insertCart(Cart cart) {
        cartDao.insertCart(cart);
    }

    @Override
    public void plusOne(Integer cartId) {
        cartDao.plusOne(cartId);
    }

    @Override
    public void minusOne(Integer cartId) {
        cartDao.minusOne(cartId);
    }

    @Override
    public void delete(Integer id) {
        cartDao.delete(id);
    }

    @Override
    public Cart findById(Integer cartId) {
        return cartDao.findById(cartId);
    }
}
