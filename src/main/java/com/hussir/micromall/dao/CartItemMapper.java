package com.hussir.micromall.dao;

import com.hussir.micromall.model.CartItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CartItem record);

    int insertSelective(CartItem record);

    CartItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartItem record);

    int updateByPrimaryKey(CartItem record);

    // Write By Hussir.
    List<CartItem> selectListByBuyerId(@Param("buyerId") Integer buyerId);

    CartItem selectByGoodsIdAndBuyerId(@Param("goodsId")Integer goodsId, @Param("buyerId") Integer buyerId);

    void deleteByBuyerId(@Param("buyerId") Integer buyerId);
}