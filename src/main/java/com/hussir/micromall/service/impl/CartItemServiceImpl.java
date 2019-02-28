package com.hussir.micromall.service.impl;

import com.hussir.micromall.dao.CartItemMapper;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.service.CartItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Resource
    private CartItemMapper cartItemMapper;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemMapper.insert(cartItem);
    }

    @Override
    public List<CartItem> getListByBuyerId(Integer buyerId) {
        return cartItemMapper.selectListByBuyerId(buyerId);
    }

    @Override
    public void modify(CartItem cartItem) {
        cartItemMapper.updateByPrimaryKeySelective(cartItem);
    }

    @Override
    public CartItem getByGoodsIdAndBuyerId(Integer goodsId, Integer buyerId) {
        return cartItemMapper.selectByGoodsIdAndBuyerId(goodsId, buyerId);
    }

    @Override
    public void deleteAllByBuyerId(Integer buyerId) {
        cartItemMapper.deleteByBuyerId(buyerId);
    }

    @Override
    public void deleteById(Integer cartItemId) {
        cartItemMapper.deleteByPrimaryKey(cartItemId);
    }

    @Override
    public CartItem getById(Integer cartItemId) {
        return cartItemMapper.selectByPrimaryKey(cartItemId);
    }
}
