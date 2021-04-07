package com.baizhi.service.impl;

import com.baizhi.dao.OrderDao;
import com.baizhi.entity.Order;
import com.baizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findOrderByBuyerId(Integer buyerId) {
        return orderDao.findOrderByBuyerId(buyerId);
    }

    @Override
    public List<Order> findOrderBySellerId(Integer sellerId) {
        return orderDao.findOrderBySellerId(sellerId);
    }

    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void contactBuyer(Integer orderId, boolean contactBuyer) {
        orderDao.contactBuyer(orderId,contactBuyer);
    }

    @Override
    public void receive(Integer orderId, boolean received) {
        orderDao.receive(orderId,received);
    }

    @Override
    public void sendOut(Integer orderId, boolean sendOut) {
        orderDao.sendOut(orderId,sendOut);
    }

    @Override
    public Order selectById(Integer orderId) {
        return orderDao.selectById(orderId);
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }

    @Override
    public void deleteById(Integer orderId) {
        orderDao.deleteById(orderId);
    }
}
