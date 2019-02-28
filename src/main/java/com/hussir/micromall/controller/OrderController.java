package com.hussir.micromall.controller;

import com.hussir.micromall.constant.ExceptionMsg;
import com.hussir.micromall.exception.ParamException;
import com.hussir.micromall.exception.PermissionException;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.service.OrderService;
import com.hussir.micromall.view.OrderItemVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/list.page")
    public String showOrderListPage(HttpServletRequest request, Model model) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        List<OrderItemVO> orderItemVOList = orderService.getOrderItemVOListByBuyerId(buyer.getId());

        Double totalAmount = orderService.getOrderTotalAmountByBuyerId(buyer.getId());

        model.addAttribute("orderItemVOList", orderItemVOList);
        model.addAttribute("totalAmount", totalAmount);

        return "order_list";
    }

    //下单
    @RequestMapping("/place")
    public String  placeOrder(CartItem cartItem, HttpServletRequest request) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (cartItem == null || cartItem.getId() == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        orderService.generateOrder(cartItem, buyer.getId());

        return "redirect:/order/list.page";
    }
}
