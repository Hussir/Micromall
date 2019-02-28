package com.hussir.micromall.dao;

import com.hussir.micromall.model.Buyer;
import org.apache.ibatis.annotations.Param;

public interface BuyerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Buyer record);

    int insertSelective(Buyer record);

    Buyer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Buyer record);

    int updateByPrimaryKey(Buyer record);

    // Write By Hussir.
    Buyer selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}