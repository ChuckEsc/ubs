package com.baizhi.controller;


import com.baizhi.entity.Cart;
import com.baizhi.service.BookService;
import com.baizhi.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cart")
@CrossOrigin
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    /**
     * 有则更新购物车，没有就添加
     *
     * @param cart
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "add")
    public Map<String, Object> addOne(Cart cart) {
        log.info("购物车信息:[{}]", cart.toString());
        Map<String, Object> map = new HashMap<>();
        cartService.insertCart(cart);
        map.put("state", true);
        map.put("msg", "添加成功！");
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, path = "getAll")
    public Map<String, Object> getAll(Integer userId) {
        Map<String, Object> map = new HashMap<>();
        List<Cart> all = cartService.findAll(userId);
        map.put("list", all);
        map.put("state", true);
        map.put("msg", "success");
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, path = "plusOrMinus")
    public Map<String, Object> plusOne(Integer cartId, boolean types) {
        Map<String, Object> map = new HashMap<>();
        try {
            Cart cart = cartService.findById(cartId);
            Integer storage;
            if (types) {
                Integer bookId = cart.getBookId();
                //查询到该书的库存
                storage = bookService.findById(bookId).getStorage();
                Integer num = cart.getNum();
                //购物车数量不能大于库存
                if (!(num > storage)) {
                    cartService.plusOne(cartId);
                    map.put("msg", "数量+1");
                } else {
                    map.put("msg", "数量已经达到最大库存！");
                }
            } else {
                //根据购物车记录里的书籍id查询书籍，如果没有，则下架了
                Integer bookId = cart.getBookId();
                storage = bookService.findById(bookId).getStorage();
                if (storage >= 1) {
                    cartService.minusOne(cartId);
                    map.put("msg", "数量-1");
                } else map.put("msg", "已经没有库存了哦");

            }
            map.put("status", true);
        } catch (Exception e) {
            //如果已被下架或其他的，则直接返回
            e.printStackTrace();
            map.put("status", false);
            map.put("msg", "商品已经被下架了哦");
            return map;
        }
        return map;
    }

    @RequestMapping("delete")
    public Map<String, Object> deleteCartGoods(Integer id) {
        Map<String, Object> map = new HashMap<>();
        cartService.delete(id);
        map.put("msg", "移除成功");
        map.put("status", true);
        return map;
    }


}
