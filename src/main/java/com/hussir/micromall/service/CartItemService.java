package com.hussir.micromall.service;

import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.CartItem;

import java.util.List;

public interface CartItemService {

    void addCartItem(CartItem cartItem);

    List<CartItem> getListByBuyerId(Integer buyerId);

    void modify(CartItem cartItem);

    CartItem getByGoodsIdAndBuyerId(Integer goodsId, Integer buyerId);

    void deleteAllByBuyerId(Integer buyerId);

    void deleteById(Integer cartItemId);

    CartItem getById(Integer cartItemId);
}
