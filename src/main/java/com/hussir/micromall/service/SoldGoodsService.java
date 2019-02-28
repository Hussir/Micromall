package com.hussir.micromall.service;

import com.hussir.micromall.model.SoldGoods;

import java.util.List;

public interface SoldGoodsService {

    List<Integer> getSoldGoodsIdListByBuyerId(Integer buyerId);

    List<SoldGoods> getListByBuyerId(Integer buyerId);

    void addSoldGoods(SoldGoods soldGoods);

    List<SoldGoods> getListByOrderId(Integer orderId);
}
