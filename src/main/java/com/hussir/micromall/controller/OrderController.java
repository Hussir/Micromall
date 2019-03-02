package com.hussir.micromall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hussir.micromall.constant.ExceptionMsg;
import com.hussir.micromall.constant.ResponseCode;
import com.hussir.micromall.constant.ResponseMsg;
import com.hussir.micromall.exception.ParamException;
import com.hussir.micromall.exception.PermissionException;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.CartItem;
import com.hussir.micromall.service.OrderService;
import com.hussir.micromall.util.JsonUtils;
import com.hussir.micromall.view.OrderItemVO;
import com.hussir.micromall.view.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //批量下单
    @RequestMapping("/batch")
    @ResponseBody
    public String  batchOrder(Integer[] cartItemIds,Integer[] goodsIds, Integer[] quantities, HttpServletRequest request) throws JsonProcessingException {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (cartItemIds == null || cartItemIds.length <= 0 || goodsIds == null || goodsIds.length <= 0 || quantities == null || quantities.length <= 0) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        Response response = new Response();
        try {
            orderService.batchGenerateOrder(cartItemIds, goodsIds, quantities, buyer.getId());
            response.setCode(ResponseCode.ORDER_SUCCESS_CODE);
            response.setMessage(ResponseMsg.ORDER_SUCCESS_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            response.setCode(ResponseCode.ORDER_FAILED_CODE);
            response.setMessage(ResponseMsg.ORDER_FAILED_MESSAGE);
        }

        return JsonUtils.obj2String(response);
    }
}
