package com.baizhi.service;

import com.baizhi.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService {
    //查询所有
    List<Order> findAll();

    //根据买家id查询所有订单
    List<Order> findOrderByBuyerId(Integer buyerId);

    //根据卖家id查询订单
    List<Order> findOrderBySellerId(Integer sellerId);

    //添加订单
    void addOrder(Order order);

    //卖家已联系买家
    void contactBuyer(Integer orderId, boolean contactBuyer);

    //买家确认收货
    void receive(Integer orderId, boolean received);

    //卖家已确认发货
    void sendOut(Integer orderId, boolean sendOut);

    //根据订单id查询
    Order selectById(Integer orderId);

    //获取所有订单，管理员权限
    List<Order> getAll();

    //根据id删除订单
    void deleteById(Integer orderId);

}
