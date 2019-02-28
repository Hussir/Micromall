package com.hussir.micromall.interceptor;

import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.Seller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommonInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");
        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (buyer == null && seller == null) {
            response.sendRedirect("/login.jsp");
            return false;
        }

        String requestURI = request.getRequestURI();

        boolean isUriContainBoughtGoods = requestURI.contains("/goods/bought");

        boolean isUriContainOrder = requestURI.contains("/order");
        boolean isUriContainCart = requestURI.contains("/cart");
        if ((isUriContainBoughtGoods || isUriContainOrder || isUriContainCart) && buyer != null){
            return true;
        }

        boolean isUriContainGoods = requestURI.contains("/goods");
        if (isUriContainGoods && !isUriContainBoughtGoods && seller != null) {
            return true;
        }

        response.sendRedirect("/login.jsp");

        return true;
    }
}
