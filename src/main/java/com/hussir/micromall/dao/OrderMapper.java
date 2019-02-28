package com.hussir.micromall.dao;

import com.hussir.micromall.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    //Write By Hussir.
    List<Order> selectListByBuyerId(@Param("buyerId") Integer buyerId);
}