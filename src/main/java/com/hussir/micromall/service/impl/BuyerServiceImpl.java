package com.hussir.micromall.service.impl;

import com.hussir.micromall.dao.BuyerMapper;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.service.BuyerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Resource
    private BuyerMapper buyerMapper;

    @Override
    public Buyer getByUsernameAndPassword(String username, String password) {
        return buyerMapper.selectByUsernameAndPassword(username, password);
    }
}
