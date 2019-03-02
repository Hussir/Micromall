package com.hussir.micromall.service;

import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.model.Order;
import com.hussir.micromall.view.OrderItemVO;

import java.util.List;

public interface OrderService {

    List<OrderItemVO> getOrderItemVOListByBuyerId(Integer buyerId);

    List<Order> getListByBuyerId(Integer buyerId);

    Double getOrderTotalAmountByBuyerId(Integer buyerId);

    //选中一件商品是下单
    void generateOrder(CartItem cartItem, Integer buyerId);

    //批量下单
    void batchGenerateOrder(Integer[] cartItemIds, Integer[] goodsIds, Integer[] quantities, Integer buyerId);

    void addOrder(Order order);
}
