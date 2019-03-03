package com.hussir.micromall.service.impl;

import com.hussir.micromall.constant.StandardCode;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.model.Goods;
import com.hussir.micromall.service.CartItemService;
import com.hussir.micromall.service.CartService;
import com.hussir.micromall.service.GoodsService;
import com.hussir.micromall.view.CartItemVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Resource
    private CartItemService cartItemService;

    @Resource
    private GoodsService goodsService;

    @Override
    public void addGoods(Integer goodsId, Integer quantity, Buyer buyer) {

        CartItem cartItem = cartItemService.getByGoodsIdAndBuyerId(goodsId, buyer.getId());

        if (cartItem == null) {

            cartItem = new CartItem();

            cartItem.setGoodsId(goodsId);
            cartItem.setQuantity(quantity);
            cartItem.setBuyerId(buyer.getId());

            cartItemService.addCartItem(cartItem);

        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemService.modify(cartItem);
        }
    }

    @Override
    public List<CartItem> getCartItemListByBuyerId(Integer buyerId) {
        return cartItemService.getListByBuyerId(buyerId);
    }

    @Override
    public List<CartItemVO> getCartItemVOListByBuyerId(Integer buyerId) {

        List<CartItem> cartItemList = getCartItemListByBuyerId(buyerId);

        List<CartItemVO> cartItemVOList = new ArrayList<>();
        for (CartItem cartItem : cartItemList) {

            int quantity = cartItem.getQuantity();

            Goods goods = goodsService.getGoodsById(cartItem.getGoodsId());

            if (goods != null && goods.getId() != null) {
                CartItemVO cartItemVO = new CartItemVO();
                cartItemVO.setId(cartItem.getId());
                cartItemVO.setTitle(goods.getTitle());
                cartItemVO.setGoodsId(goods.getId());
                cartItemVO.setPicture(goods.getPicture());
                cartItemVO.setPrice(goods.getPrice());
                cartItemVO.setQuantity(quantity);
                cartItemVO.setSubtotal(quantity * goods.getPrice());

                cartItemVOList.add(cartItemVO);
            }
        }

        return cartItemVOList;
    }

    @Override
    public Double getCartTotalAmountByBuyerId(Integer buyerId) {

        List<CartItem> cartItemList = getCartItemListByBuyerId(buyerId);

        double totalAmount = 0;
        for (CartItem cartItem : cartItemList) {
            Double price = goodsService.getPriceById(cartItem.getGoodsId());
            totalAmount += (price * cartItem.getQuantity());
        }

        BigDecimal tempAmount = new BigDecimal(totalAmount);
        totalAmount = tempAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        return totalAmount;
    }

    @Override
    public void clear(Buyer buyer) {
        cartItemService.deleteAllByBuyerId(buyer.getId());
    }

    @Override
    public int remove(Integer cartItemId, Buyer buyer) {

        CartItem cartItem = cartItemService.getById(cartItemId);

        if (cartItem != null && cartItem.getBuyerId().intValue() == buyer.getId()) {
            cartItemService.deleteById(cartItemId);
            return StandardCode.QUERY_SUCCESS_CODE;
        }

        return StandardCode.QUERY_FAILED_CODE;
    }
}
