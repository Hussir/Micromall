package com.hussir.micromall.service.impl;

import com.hussir.micromall.dao.SellerMapper;
import com.hussir.micromall.model.Seller;
import com.hussir.micromall.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerMapper sellerMapper;

    @Override
    public Seller getByUsernameAndPassword(String username, String password) {
        return sellerMapper.selectUsernameAndPassword(username, password);
    }
}
