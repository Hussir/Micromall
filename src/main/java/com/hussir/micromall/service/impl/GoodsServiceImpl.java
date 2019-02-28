package com.hussir.micromall.service.impl;

import com.hussir.micromall.constant.StandardCode;
import com.hussir.micromall.dao.GoodsMapper;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.Goods;
import com.hussir.micromall.model.Seller;
import com.hussir.micromall.service.GoodsService;
import com.hussir.micromall.service.SoldGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private SoldGoodsService soldGoodsService;

    @Override
    public Goods getGoodsById(Integer goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> getGoodsListWithFlag(HttpServletRequest request) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");
        Seller seller = (Seller) request.getSession().getAttribute("seller");

        List<Goods> allGoods = getGoodsList();

        if (buyer != null && buyer.getId() != null) {
            List<Integer> soldGoodsIdList = soldGoodsService.getSoldGoodsIdListByBuyerId(buyer.getId());
            for (Goods goods : allGoods) {
                if (soldGoodsIdList.contains(goods.getId())) {
                    goods.setFlag(StandardCode.BOUGHT_FLAG_CODE);
                }
            }
        } else if (seller != null && seller.getId() != null) {
            for (Goods goods : allGoods) {
                if (goods.getQuantitySold() > 0) {
                    goods.setFlag(StandardCode.SOLD_FLAG_CODE);
                }
            }
        }

        return allGoods;
    }

    @Override
    public List<Goods> getGoodsList() {
        return goodsMapper.selectAllGoods();
    }

    @Override
    public void insertGoods(Goods goods) {
        goodsMapper.insertSelective(goods);
    }

    @Override
    public void updateGoods(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public Double getPriceById(Integer goodsId) {
        return goodsMapper.selectPriceByPrimaryKey(goodsId);
    }

    @Override
    public Integer getSoldQuantityById(Integer goodsId) {
        return goodsMapper.selectSoldQuantityByPrimaryKey(goodsId);
    }

    @Override
    public List<Goods> getSoldGoodsBySellerId(Integer sellerId) {
        return goodsMapper.selectSoldGoodsBySellerId(sellerId);
    }
}
