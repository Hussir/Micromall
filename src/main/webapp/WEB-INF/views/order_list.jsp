<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>订单列表</title>

		<!-- 引入js文件 -->
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>

		<!-- 引入css文件 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
	</head>

	<body>

		<!-- 动态包含 -->
	    <jsp:include page="./common/header.jsp" />

		<div class="container">
			<div class="row">

				<div style="margin: 10px auto 0;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						
						<c:forEach items="${orderItemVOList }" var="orderItemVO">
							<tbody>
								<tr class="success">
									<th colspan="5">订单编号:${orderItemVO.id } 订单金额:￥${orderItemVO.amount}
									</th>
								</tr>
								<tr class="warning">
									<th style="text-align:center;">商品图片</th>
									<th style="text-align:center;">商品名称</th>
									<th style="text-align:center;">商品价格</th>
									<th style="text-align:center;">购买数量</th>
									<th style="text-align:center;">价格小计</th>
								</tr>
								<c:forEach items="${orderItemVO.soldGoodsList}" var="soldGoods">
									<tr class="active">
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<input type="hidden" name="id" value="${soldGoods.id}">
											<img src="${soldGoods.picture}" width="70" height="60">
										</td>
										<td width="20%" style="display:table-cell; vertical-align:middle">
											<a target="_blank">${soldGoods.title}</a>
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											&yen;${soldGoods.price}
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											${soldGoods.quantity}
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<span class="subtotal">&yen;${soldGoods.sumPrice }</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
					<div style="margin-right:130px;">
						<div style="text-align:right;">
							订单总金额: <strong style="color:#ff6600;">&yen;${totalAmount}元</strong>
						</div>
					</div>
				</div>
			</div>
		</div>

		<jsp:include page="./common/footer.jsp" />

	</body>

</html>