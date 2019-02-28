package com.hussir.micromall.service.impl;

import com.hussir.micromall.dao.SoldGoodsMapper;
import com.hussir.micromall.model.SoldGoods;
import com.hussir.micromall.service.SoldGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SoldGoodsServiceImpl implements SoldGoodsService {

    @Resource
    private SoldGoodsMapper soldGoodsMapper;

    @Override
    public List<Integer> getSoldGoodsIdListByBuyerId(Integer buyerId) {
        return soldGoodsMapper.selectSoldGoodsIdListByBuyerId(buyerId);
    }

    @Override
    public List<SoldGoods> getListByBuyerId(Integer buyerId) {
        return soldGoodsMapper.selectListByBuyerId(buyerId);
    }

    @Override
    public void addSoldGoods(SoldGoods soldGoods) {
        soldGoodsMapper.insertSelective(soldGoods);
    }

    @Override
    public List<SoldGoods> getListByOrderId(Integer orderId) {
        return soldGoodsMapper.selectByOrderId(orderId);
    }
}
