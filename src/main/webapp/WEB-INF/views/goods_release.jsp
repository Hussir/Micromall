<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>商品发布</title>

    <!-- 引入css文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

</head>

<body>

<jsp:include page="./common/header.jsp" />

<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>商品发布</h2>
    </div>
    <div class="n-public">
        <form class="m-form m-form-ht" id="form" method="post" action="${pageContext.request.contextPath}/goods/release" onsubmit="return false;" autocomplete="off">
            <div class="fmitem">
                <label class="fmlab">标题：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="title" autofocus="" placeholder="2-80字符" minlength="2" maxlength="80">
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">摘要：</label>
                <div class="fmipt">
                    <input class="u-ipt ipt" name="summary" placeholder="2-140字符" minlength="2" maxlength="140">
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">图片：</label>
                <div class="fmipt" id="uploadType">
                    <label><input name="pic" type="radio" value="url" checked=""> 图片地址</label>
                    <label><input name="pic" type="radio" value="file"> 本地上传</label>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab"></label>
                <div class="fmipt" id="urlUpload">
                    <input class="u-ipt ipt" name="picture" placeholder="图片地址" maxlength="255">
                </div>
                <div class="fmipt" id="fileUpload" style="display:none">
                    <input class="u-ipt ipt" name="file" type="file" id="fileUp">
                    <button style="background-color: #E0E0E0" class="btn btn-default" id="upload">上传</button>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">正文：</label>
                <div class="fmipt">
                    <textarea class="u-ipt" name="description" rows="10" placeholder="2-1000个字符" minlength="2" maxlength="1000"></textarea>
                </div>
            </div>
            <div class="fmitem">
                <label class="fmlab">价格：</label>
                <div class="fmipt">
                    <label><input class="u-ipt price" name="price"  onblur="if(! /^\d+\.?\d{1,2}$/.test(this.value)){alert('只能输入数字，小数点后只能保留两位');this.value='';}" >元</label>
                </div>
            </div>
            <div class="fmitem fmitem-nolab fmitem-btn">
                <div class="fmipt">
                    <button type="submit" style="background-color: #E0E0E0" class="btn btn-default">发 布</button>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="./common/footer.jsp" />

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/public.js"></script>
</html>