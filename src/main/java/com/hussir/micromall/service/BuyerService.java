package com.hussir.micromall.service;

import com.hussir.micromall.model.Buyer;

public interface BuyerService {
    Buyer getByUsernameAndPassword(String username, String password);
}
