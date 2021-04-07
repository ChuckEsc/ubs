package com.baizhi.controller;


import com.baizhi.entity.Book;
import com.baizhi.entity.Order;
import com.baizhi.entity.User;
import com.baizhi.service.BookService;
import com.baizhi.service.OrderService;
import com.baizhi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
@CrossOrigin
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    /**
     * 根据用户id查询卖出去的订单
     *
     * @param sellerId
     * @return
     */
    @RequestMapping("sold")
    public Map<String, Object> soldOrders(Integer sellerId) {
        Map<String, Object> map = new HashMap<>();
        List<Order> soldOrders = orderService.findOrderBySellerId(sellerId);
        //根据卖家id查询电话
//        User byId = userService.findById(sellerId);
//        String sellerPhone = byId.getPhone();
//        map.put("sellerPhone",sellerPhone);
        map.put("soldOrders", soldOrders);
        map.put("state", true);
        map.put("msg", "success");
        return map;
    }

    /**
     * 根据用户id查询买到的订单
     *
     * @param buyerId
     * @return
     */
    @RequestMapping("bought")
    public Map<String, Object> boughtOrders(Integer buyerId) {
        Map<String, Object> map = new HashMap<>();
        List<Order> boughtOrders = orderService.findOrderByBuyerId(buyerId);
        map.put("boughtOrders", boughtOrders);
        map.put("state", true);
        map.put("msg", "success");
        return map;
    }

    @RequestMapping("contactBuyer")
    public Map<String, Object> contactBuyer(Integer orderId, boolean contactBuyer) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.contactBuyer(orderId, contactBuyer);
            map.put("state", true);
            map.put("msg", "success");
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("receive")
    public Map<String, Object> receive(boolean received, Integer orderId) {
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.receive(orderId, received);
            //完成交易，book列表storage库存-订单num数量
            Integer orderNum = orderService.selectById(orderId).getNum();
            Integer bookId = orderService.selectById(orderId).getBookId();
            bookService.updateStorage(bookId,orderNum);
            map.put("msg", "success");
            map.put("state", true);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }

        return map;
    }

    @RequestMapping(path = "buy")
    public Map<String, Object> buyNow(@RequestBody Order order) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("info:" + order.toString());
        Book byId = bookService.findById(order.getBookId());
        if (byId==null){
            map.put("status", false);
            map.put("msg", "商品已下架，暂不能购买");
            return map;
        }
        String sellerName = userService.findById(order.getSellerId()).getUsername();
        order.setCreateDate(new Date()).setActive(true).setSellerName(sellerName);
        orderService.addOrder(order);
        map.put("status", true);
        map.put("msg", "success");
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, path = "sendOut")
    public Map<String, Object> sendOut(Integer orderId, boolean sendOut) {
        Map<String, Object> map = new HashMap<>();
        orderService.sendOut(orderId, sendOut);
        return map;
    }

    @RequestMapping("getAll")
    public Map<String, Object> getAll(){
        Map<String, Object> map = new HashMap<>();
        List<Order> orderList = orderService.getAll();
        map.put("orderList",orderList);
        map.put("status",true);
        return map;
    }

    @RequestMapping("delete")
    public Map<String, Object> delete(Integer OrderId){
        Map<String, Object> map = new HashMap<>();
        try {
            orderService.deleteById(OrderId);
            map.put("status",true);
            map.put("msg","删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status",false);
            map.put("msg","删除失败，请稍后再试");
        }
        return map;
    }
}
