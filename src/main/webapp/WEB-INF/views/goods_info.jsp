<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>商品详情</title>

    <!-- 引入js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- 引入css文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>

<body>

<jsp:include page="./common/header.jsp"/>

<div class="container">
    <div class="row">
        <div style="margin:0 auto;width:950px;">

            <div class="col-md-6">
                <img style="opacity: 1;width:400px;height:350px;" title="" class="medium"
                     src="${goods.picture}">
            </div>

            <div class="col-md-6">
                <div>
                    <input type="hidden" id="goodsId" name="goodsId" value="${goods.id}">
                    <strong>${goods.title}</strong>
                </div>

                <div style="margin:10px 0 10px 0;">商品售价: <strong style="color:#ef0101;">&yen;：${goods.price}元</strong>
                </div>

                <div style="padding:10px;border:1px solid #e7dbb1;width:330px;margin:15px 0 10px 0;;background-color: #fffee6;">


                    <c:if test="${sessionScope.seller != null}">
                        <div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">已售数量:${soldQuantity}
                        </div>
                        <a href="${pageContext.request.contextPath}/goods/edit.page?goodsId=${goods.id}">
                            <input style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
                                   value="商品编辑" type="button">
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.seller == null}">
                        <div style="border-bottom: 1px solid #faeac7;margin-top:20px;padding-left: 10px;">购买数量:
                            <input id="quantity" name="quantity" value="1" maxlength="4" size="10" type="text">
                        </div>
                        <div style="margin:20px 0 10px 0;;text-align: center;">
                            <input style="background: url('${pageContext.request.contextPath}/img/product.gif') no-repeat scroll 0 -600px rgba(0, 0, 0, 0);height:36px;width:127px;"
                                   value=" 加入购物车" type="button" onclick="addToCart()">
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="clear"></div>
        <div style="width:950px;margin:0 auto;">
            <div style="background-color:#d3d3d3;width:930px;padding:10px 10px;margin:10px 0 10px 0;">
                <strong>商品介绍</strong>
            </div>
            <div>${goods.description}</div>
        </div>
    </div>
</div>

<jsp:include page="./common/footer.jsp"/>

</body>
<script type="text/javascript">
    function addToCart() {
        var quantity = $("#quantity").val();
        var goodsId = $("#goodsId").val();
        window.location.href = "/cart/add?goodsId=" + goodsId + "&quantity=" + quantity;
    }
</script>
</html>