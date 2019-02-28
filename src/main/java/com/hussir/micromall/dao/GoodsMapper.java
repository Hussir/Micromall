package com.hussir.micromall.dao;

import com.hussir.micromall.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    //Write By Hussir.
    List<Goods> selectAllGoods();

    Double selectPriceByPrimaryKey(@Param("id") Integer id);

    Integer selectSoldQuantityByPrimaryKey(@Param("id") Integer id);

    List<Goods> selectSoldGoodsBySellerId(@Param("sellerId") Integer sellerId);
}