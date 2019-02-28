package com.hussir.micromall.service;

import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.view.CartItemVO;

import java.util.List;

public interface CartService {

    void addGoods(Integer goodsId, Integer quantity, Buyer buyer);

    List<CartItem> getCartItemListByBuyerId(Integer buyerId);

    List<CartItemVO> getCartItemVOListByBuyerId(Integer buyerId);

    Double getCartTotalAmountByBuyerId(Integer buyer);

    void clear(Buyer buyer);

    int remove(Integer cartItemId, Buyer buyer);
}
