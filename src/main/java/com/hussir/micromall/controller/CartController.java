package com.hussir.micromall.controller;

import com.hussir.micromall.constant.ExceptionMsg;
import com.hussir.micromall.constant.StandardCode;
import com.hussir.micromall.exception.ParamException;
import com.hussir.micromall.exception.PermissionException;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.service.CartService;
import com.hussir.micromall.view.CartItemVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    @RequestMapping("/remove")
    public String removeFromCart(@Param("id") Integer cartItemId, HttpServletRequest request) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (cartItemId == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        int code = cartService.remove(cartItemId, buyer);

        //code 为0 表示删除失败，1表示删除成功
        if (code == StandardCode.QUERY_FAILED_CODE) {
            throw new ParamException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        return "redirect:/cart/list.page";
    }

    @RequestMapping("/clear")
    public String clearCart(HttpServletRequest request) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        cartService.clear(buyer);

        return "redirect:/cart/list.page";
    }

    @RequestMapping("/add")
    public String addGoodsToCart(Integer goodsId, Integer quantity, HttpServletRequest request) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (goodsId == null || quantity == null || quantity <= 0) {
            throw new ParamException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        cartService.addGoods(goodsId, quantity, buyer);

        return "redirect:/cart/list.page";
    }

    @RequestMapping("/list.page")
    public String showCartItemList(HttpServletRequest request, Model model) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        List<CartItemVO> cartItemVOList = cartService.getCartItemVOListByBuyerId(buyer.getId());

        double totalAmount = cartService.getCartTotalAmountByBuyerId(buyer.getId());

        model.addAttribute("cartItemVOList", cartItemVOList);
        model.addAttribute("totalAmount", totalAmount);

        return "cart";
    }
}
