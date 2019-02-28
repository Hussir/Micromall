<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 菜单栏 -->
<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/logo2.png" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top: 20px">
        <ol class="list-inline">
            <c:if test="${sessionScope.buyer != null}">
                <li>
                        ${sessionScope.buyer.username}, 您好！&#91; <a href="${pageContext.request.contextPath}/user/logout">注销</a> &#93;
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/cart/list.page">购物车</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/order/list.page">财&nbsp;务</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.seller != null}">
                <li>
                        ${sessionScope.seller.username}, 您好！&#91; <a href="${pageContext.request.contextPath}/user/logout">注&nbsp;销</a> &#93;
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/goods/release.page">发布</a>
                </li>
            </c:if>
            <c:if test="${sessionScope.buyer == null && sessionScope.seller == null}">
                <li>
                    <a href="${pageContext.request.contextPath}/login.jsp">登录</a>
                </li>
            </c:if>
        </ol>
    </div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed"
                        data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                        aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span> <span
                        class="icon-bar"></span> <span class="icon-bar"></span> <span
                        class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse"
                 id="bs-example-navbar-collapse-1">
                <ul id="menuId" class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/goods/list.page">书城</a></li>
                </ul>

                <c:if test="${sessionScope.buyer != null}">
                    <form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/goods/bought/list.page">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="查看已购买的商品" readonly>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </c:if>
                <c:if test="${sessionScope.seller != null}">
                    <form class="navbar-form navbar-right" role="search" action="${pageContext.request.contextPath}/goods/sold/list.page">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="查看已出售的商品" readonly>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </c:if>
            </div>
        </div>
    </nav>
</div>