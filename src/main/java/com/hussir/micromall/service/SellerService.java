package com.hussir.micromall.service;

import com.hussir.micromall.model.Seller;

public interface SellerService {
    Seller getByUsernameAndPassword(String username, String password);
}
