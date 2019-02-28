package com.hussir.micromall.dao;

import com.hussir.micromall.model.SoldGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SoldGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SoldGoods record);

    int insertSelective(SoldGoods record);

    SoldGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SoldGoods record);

    int updateByPrimaryKey(SoldGoods record);

    //Write By Hussir.
    List<Integer> selectSoldGoodsIdListByBuyerId(@Param("buyerId") Integer buyerId);

    List<SoldGoods> selectListByBuyerId(@Param("buyerId") Integer buyerId);

    List<SoldGoods> selectByOrderId(Integer orderId);
}