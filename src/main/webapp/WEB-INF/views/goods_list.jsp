<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>商城首页</title>

    <!-- 引入js文件 -->
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- 引入css文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
</head>

<body>

    <jsp:include page="./common/header.jsp" />

    <div class="row n-plist" style="width:1200px;margin:0 auto;">
        <c:forEach	items="${goodsList }" var="goods">
            <div class="col-md-3" style="display: table-cell;text-align: center;vertical-align: middle">
                <a href="${pageContext.request.contextPath}/goods/info.page?goodsId=${goods.id}">
                    <img src="${goods.picture}" width="200" height="240" style="display: inline-block;" />
                    <c:if test="${goods.flag == 1}" >
                        <span class="had"><b>已购买</b></span>
                    </c:if>
                    <c:if test="${goods.flag == 2}" >
                        <span class="had"><b>已出售</b></span>
                    </c:if>
                </a>
                <p><a href="${pageContext.request.contextPath}/goods/info.page?goodsId=${goods.id}" style='color:green'>${goods.title}</a></p>
                <p><span style="color: #FF0000; ">商品售价：&yen;${goods.price}</span></p>
            </div>
        </c:forEach>
    </div>

    <jsp:include page="./common/footer.jsp" />
</body>

</html>