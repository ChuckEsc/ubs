package com.baizhi.dao;

import com.baizhi.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    //查询所有
    List<Order> findAll();

    //根据买家id查询订单
    List<Order> findOrderByBuyerId(Integer buyerId);

    //根据卖家id查询订单
    List<Order> findOrderBySellerId(Integer sellerId);

    //添加订单
    void addOrder(Order order);

    //卖家已联系买家
    void contactBuyer(@Param("id") Integer orderId,@Param("contactBuyer") boolean contactBuyer);

    //买家已确认收货，交易完成
    void receive(@Param("id") Integer orderId,@Param("received")boolean received);

    //卖家已确认发货
    void sendOut(@Param("id") Integer orderId,@Param("sendOut") boolean sendOut);

    //根据订单id查询
    Order selectById(Integer orderId);

    //获取所有订单，管理员权限
    List<Order> getAll();

    //根据id删除订单
    void deleteById(Integer orderId);
}
