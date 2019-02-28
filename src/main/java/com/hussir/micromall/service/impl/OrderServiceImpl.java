package com.hussir.micromall.service.impl;

import com.hussir.micromall.dao.OrderMapper;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.model.Goods;
import com.hussir.micromall.model.Order;
import com.hussir.micromall.model.SoldGoods;
import com.hussir.micromall.service.CartItemService;
import com.hussir.micromall.service.GoodsService;
import com.hussir.micromall.service.OrderService;
import com.hussir.micromall.service.SoldGoodsService;
import com.hussir.micromall.view.OrderItemVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private SoldGoodsService soldGoodsService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private CartItemService cartItemService;

    @Override
    public List<OrderItemVO> getOrderItemVOListByBuyerId(Integer buyerId) {

        List<OrderItemVO> orderItemVOList = new ArrayList<>();

        List<Order> orderList = getListByBuyerId(buyerId);
        for (Order order : orderList) {

            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setAmount(order.getAmount());
            orderItemVO.setBuyerId(buyerId);
            orderItemVO.setId(order.getId());
            orderItemVO.setSoldGoodsList(soldGoodsService.getListByOrderId(order.getId()));

            orderItemVOList.add(orderItemVO);
        }

        return orderItemVOList;
    }

    @Override
    public List<Order> getListByBuyerId(Integer buyerId) {
        return orderMapper.selectListByBuyerId(buyerId);
    }

    @Override
    public Double getOrderTotalAmountByBuyerId(Integer buyerId) {

        List<Order> orderList = getListByBuyerId(buyerId);

        double totalAmount = 0;
        for (Order order : orderList) {
            totalAmount += order.getAmount();
        }

        return totalAmount;
    }

    @Override
    @Transactional
    public void generateOrder(CartItem cartItem, Integer buyerId) {

        Integer cartItemId = cartItem.getId();
        Integer goodsId = cartItem.getGoodsId();
        Integer quantity = cartItem.getQuantity();

        double goodsPrice = goodsService.getPriceById(goodsId);

        Order order = new Order();

        order.setAmount(quantity * goodsPrice);
        order.setBuyerId(buyerId);
        order.setOrderTime(new Date());

        addOrder(order);

        int orderId = order.getId();

        Goods goods = goodsService.getGoodsById(goodsId);

        SoldGoods soldGoods = new SoldGoods();
        soldGoods.setDescription(goods.getDescription());
        soldGoods.setGoodsId(goodsId);
        soldGoods.setOrderId(orderId);
        soldGoods.setSummary(goods.getSummary());
        soldGoods.setTitle(goods.getTitle());
        soldGoods.setQuantity(quantity);
        soldGoods.setPrice(goodsPrice);
        soldGoods.setSumPrice(goodsPrice * quantity);
        soldGoods.setPicture(goods.getPicture());

        soldGoodsService.addSoldGoods(soldGoods);

        cartItemService.deleteById(cartItemId);

        goods.setQuantitySold((goods.getQuantitySold() == null ? 0 : goods.getQuantitySold()) + quantity);
        goodsService.updateGoods(goods);
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.insertSelective(order);
    }
}
