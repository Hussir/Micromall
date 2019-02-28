package com.hussir.micromall.service.impl;

import com.hussir.micromall.constant.ResponseCode;
import com.hussir.micromall.constant.ResponseMsg;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.Seller;
import com.hussir.micromall.service.BuyerService;
import com.hussir.micromall.service.SellerService;
import com.hussir.micromall.service.UserService;
import com.hussir.micromall.view.Response;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private BuyerService buyerService;

    @Resource
    private SellerService sellerService;

    @Override
    public Response doLogin(String username, String password, HttpServletRequest request) {

        Response result = new Response();
        if (username == null || username.trim().equals("")) {
            result.setCode(ResponseCode.LOGIN_USERNAME_EMPTY);
            result.setMessage(ResponseMsg.USERNAME_IS_EMPTY);
            return result;
        }

        if (password == null || password.trim().equals("")) {
            result.setCode(ResponseCode.LOGIN_PASSWORD_EMPTY);
            result.setMessage(ResponseMsg.PASSWORD_IS_EMPTY);
            return result;
        }

        Buyer buyer = buyerService.getByUsernameAndPassword(username, password);
        Seller seller = sellerService.getByUsernameAndPassword(username, password);


        if (buyer == null && seller == null) {
            result.setCode(ResponseCode.LOGIN_FAILED_CODE);
            result.setMessage(ResponseMsg.USERNAME_OR_PWD_ERROR);
        } else if (buyer != null){

            request.getSession().setAttribute("buyer", buyer);

            result.setCode(ResponseCode.LOGIN_SUCCESS_CODE);
            result.setMessage(ResponseMsg.LOGIN_SUCCESS_MESSAGE);
        } else {
            request.getSession().setAttribute("seller", seller);

            result.setCode(ResponseCode.LOGIN_SUCCESS_CODE);
            result.setMessage(ResponseMsg.LOGIN_SUCCESS_MESSAGE);
        }

        return result;
    }
}
