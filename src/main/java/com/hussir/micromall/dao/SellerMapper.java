package com.hussir.micromall.dao;

import com.hussir.micromall.model.Seller;
import org.apache.ibatis.annotations.Param;

public interface SellerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    // Write By Hussir.
    Seller selectUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}