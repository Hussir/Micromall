package com.hussir.micromall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hussir.micromall.constant.ExceptionMsg;
import com.hussir.micromall.exception.ParamException;
import com.hussir.micromall.exception.PermissionException;
import com.hussir.micromall.model.Buyer;
import com.hussir.micromall.model.Goods;
import com.hussir.micromall.model.Seller;
import com.hussir.micromall.service.GoodsService;
import com.hussir.micromall.service.SoldGoodsService;
import com.hussir.micromall.util.JsonUtils;
import com.hussir.micromall.view.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/goods")
@PropertySource(value = {"classpath:server/ImageServer.properties"})
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private SoldGoodsService soldGoodsService;

    @Value("${ImgServerDir}")
    private String realPath;

    @RequestMapping("/remove")
    public String removeGoods(HttpServletRequest request, @RequestParam("goodsId") Integer goodsId) {

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (seller == null || seller.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (goodsId == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        goodsService.remove(goodsId);

        return "redirect:/goods/list.page";
    }

    @RequestMapping("/bought/list.page")
    public String showBoughtGoodsListPage(HttpServletRequest request, Model model) {

        Buyer buyer = (Buyer) request.getSession().getAttribute("buyer");

        if (buyer == null || buyer.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        List<Integer> soldGoodsIdList = soldGoodsService.getSoldGoodsIdListByBuyerId(buyer.getId());

        HashSet<Integer> noRepeatGoodsIdSet = new HashSet<>(soldGoodsIdList);

        List<Goods> goodsList = new ArrayList<>();

        for (Integer goodsId : noRepeatGoodsIdSet) {
            Goods goods = goodsService.getGoodsById(goodsId);
            goods.setFlag(1);
            goodsList.add(goods);
        }

        model.addAttribute("goodsList", goodsList);

        return "goods_list";
    }

    @RequestMapping("/sold/list.page")
    public String showSoldGoodsListPage(HttpServletRequest request, Model model) {

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (seller == null || seller.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        List<Goods> goodsList = goodsService.getSoldGoodsBySellerId(seller.getId());

        for (Goods goods : goodsList) {
            goods.setFlag(2);
        }

        model.addAttribute("goodsList", goodsList);

        return "goods_list";
    }

    @RequestMapping("/api/upload")
    @ResponseBody
    public String uploadGoodsImage(@RequestParam(value = "file", required = false) CommonsMultipartFile file, HttpServletRequest request) throws JsonProcessingException {

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (seller == null || seller.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        // 项目在容器中实际发布运行的根路径
        //String realPath = request.getSession().getServletContext().getRealPath("/");
        //String realPath = "D:\\TomcatInstall\\ImgServer";

        // 自定义的文件名称
        String trueFileName = UUID.randomUUID().toString().replace("-", "") + "_" + file.getOriginalFilename();

        //<Context path="/images" docBase="/usr/local/apps/tomcat/ImgServer" reloadable="false"/>
        // 设置上传图片文件的路径
        String uploadPath = realPath + "/products/";
        String completePath = uploadPath + trueFileName;

        try {

            File uploadDir = new File(uploadPath);

            //若待存放图片目录不存在，则创建该目录
            if (!uploadDir.exists()) {
                if (uploadDir.mkdir()) {
                    System.out.println(uploadPath + "目录创建成功");
                } else {
                    System.out.println(uploadPath + "目录创建失败");
                }
            }

            // 转存文件到指定的路径
            File newFile = new File(completePath);
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Response response = new Response();

        String mapPath = "/images/" + "products/" + trueFileName;

        response.setResult(mapPath);

        return JsonUtils.obj2String(response);
    }

    @RequestMapping("/edit")
    public String editGoods(Goods goods, HttpServletRequest request) {

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (seller == null || seller.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (goods == null || goods.getId() == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        goodsService.updateGoods(goods);

        return "redirect:/goods/info.page?goodsId=" + goods.getId();
    }

    @RequestMapping("/edit.page")
    public String showGoodsEditPage(@RequestParam("goodsId") Integer goodsId, Model model) {

        if (goodsId == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        Goods goods = goodsService.getGoodsById(goodsId);

        model.addAttribute("goods", goods);

        return "goods_edit";
    }

    @RequestMapping("/release.page")
    public String showGoodsReleasePage() {
        return "goods_release";
    }

    @RequestMapping("/release")
    public String releaseGoods(Goods goods, HttpServletRequest request) {

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        if (seller == null || seller.getId() == null) {
            throw new PermissionException(ExceptionMsg.ILLEGAL_ACCESS_EXCEPTION);
        }

        if (goods == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        goods.setSellerId(seller.getId());

        goodsService.insertGoods(goods);

        return "redirect:/goods/list.page";
    }

    @RequestMapping("/list.page")
    public String showGoodsListPage(HttpServletRequest request, Model model) {

        List<Goods> goodsList = goodsService.getGoodsListWithFlag(request);

        model.addAttribute("goodsList", goodsList);

        return "goods_list";
    }

    @RequestMapping("/info.page")
    public String showGoodsInfoPage(@RequestParam("goodsId") Integer goodsId, Model model, HttpServletRequest request) {

        if (goodsId == null) {
            throw new ParamException(ExceptionMsg.ILLEGAL_PARAMETER_EXCEPTION);
        }

        Seller seller = (Seller) request.getSession().getAttribute("seller");

        Goods goods = goodsService.getGoodsById(goodsId);

        model.addAttribute("goods", goods);

        if (seller != null && seller.getId() != null) {
            int soldQuantity = goodsService.getSoldQuantityById(goodsId);
            model.addAttribute("soldQuantity", soldQuantity);
        }

        return "goods_info";
    }
}
