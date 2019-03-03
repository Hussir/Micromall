<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<title>我的购物车</title>

		<!-- 引入js文件 -->
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/layer/layer.js" type="text/javascript"></script>

		<!-- 引入css文件 -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			font {
				color: #3164af;
				font-size: 18px;
				font-weight: normal;
				padding: 0 10px;
			}
		</style>
	</head>

	<body>

		<!-- 动态包含 -->
		<jsp:include page="./common/header.jsp" />

		<div class="container">
			<c:if test="${empty cartItemVOList}">
				<h1>购物车空空如也~~赶紧逛逛去!!</h1>
				<div style="margin-right:130px;">
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<c:if test="${!fn:contains(refererURL,'/cart/list.page')}" >
							<a href="${refererURL}">
								<input type="submit" width="100" value="退出购物车" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
										height:35px;width:100px;color:white;">
							</a>
						</c:if>
						<c:if test="${fn:contains(refererURL,'/cart/list.page')}" >
							<a href="${pageContext.request.contextPath}/index.jsp">
								<input type="submit" width="100" value="退出购物车" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
										height:35px;width:100px;color:white;">
							</a>
						</c:if>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty cartItemVOList}">
				<div class="row">
					<div style="margin: 10px auto 0;width:950px;">
						<strong style="font-size:16px;margin:5px 0;">购物车详情</strong>
						<table class="table table-bordered">
							<tbody>
								<tr class="warning">
									<th style="text-align:center;">选择</th>
									<th style="text-align:center;">商品图片</th>
									<th style="text-align:center;">商品名称</th>
									<th style="text-align:center;">商品价格</th>
									<th style="text-align:center;">购买数量</th>
									<th style="text-align:center;">价格小计</th>
									<th style="text-align:center;">可选操作</th>
								</tr>
								<c:forEach items="${cartItemVOList}" var="cartItemVO">
									<tr class="active">
										<td width="4%" style="display:table-cell; vertical-align:middle">
											<label><input type="checkbox" name="cartItemIds" value="${cartItemVO.id}"/></label>
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<input type="hidden" id="goodsId${cartItemVO.id}" value="${cartItemVO.goodsId}">
											<img src="${cartItemVO.picture}" width="70" height="60">
										</td>
										<td width="20%" style="display:table-cell; vertical-align:middle">
											<a target="_blank">${cartItemVO.title}</a>
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											&yen;${cartItemVO.price}
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<label><input id="quantity${cartItemVO.id}" type="text" name="quantity" value="${cartItemVO.quantity}" maxlength="4" size="10" readonly="readonly"></label>
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<span class="subtotal">${cartItemVO.subtotal}</span>
										</td>
										<td width="10%" style="display:table-cell; vertical-align:middle">
											<a href="javascript:void(0);" onclick="removeFromCart('${cartItemVO.id}')">删除</a> |
											<a href="javascript:void(0);" onclick="purchaseFromCart('${cartItemVO.id}', '${cartItemVO.goodsId}', '${cartItemVO.quantity}')">购买</a>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td width="4%" style="display:table-cell; vertical-align:middle">
										<label><input name="selectAll" class="group-checkable" type="checkbox" data-set="#table .checkboxes"/></label>
									</td>
									<td>
										全选
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					
				</div>
	
				<div style="margin-right:130px;">
					<div style="text-align:right;">
						 购物车商品总金额: <strong style="color:#ff6600;">&yen;${totalAmount}元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<%--
							<a href="${pageContext.request.contextPath }/cart/clear"><input type="submit" width="100" value="清空购物车" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
                                    height:35px;width:100px;color:white;">
							</a>
						--%>
						<a href="${pageContext.request.contextPath }/cart/clear" id="clear" class="clear">清空购物车</a>
							<c:if test="${!fn:contains(refererURL,'/cart/list.page')}" >
								<a href="${refererURL}">
									<input type="submit" width="100" value="退出购物车" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
											height:35px;width:100px;color:white;">
								</a>
							</c:if>
							<c:if test="${fn:contains(refererURL,'/cart/list.page')}" >
								<a href="${pageContext.request.contextPath}/index.jsp">
									<input type="submit" width="100" value="退出购物车" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
											height:35px;width:100px;color:white;">
								</a>
							</c:if>
						<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;" onclick="batchOrder()">
					</div>
				</div>
			</c:if>
		</div>

		<jsp:include page="./common/footer.jsp" />

	</body>
	<script type="text/javascript">
		function removeFromCart(id){
			if(confirm("您确认狠心要丢弃我吗?")){
				location.href="${pageContext.request.contextPath}/cart/remove?id=" + id;
			}
		}

		function purchaseFromCart(id, goodsId, quantity){
			if(confirm("您确定要购买吗?")){
				location.href="${pageContext.request.contextPath}/order/place?id=" + id + "&goodsId=" + goodsId + "&quantity=" + quantity;
			}
		}

		function batchOrder(){

            var selected = $('input[name="cartItemIds"]:checked');

            if (selected.length <= 0) {
                alert("请勾选需要购买的商品！");
            } else if(confirm("您确定要购买吗?")){

                var cartItemIds = [];
                var goodsIds = [];
                var quantities = [];

                selected.each(function(){
                    var id = $(this).val();
                    cartItemIds.push(id);//向数组中添加元素
                    quantities.push($("#quantity"+id).val());
                    goodsIds.push($("#goodsId"+id).val());
                });

                $.ajax({
                    url: "/order/batch",
                    type: "POST",
                    data: {
                        "cartItemIds": cartItemIds,
                        "quantities": quantities,
						"goodsIds": goodsIds
                    },
                    dataType: 'json',
                    traditional: true,
                    success: function (data) {
                        if (data.code === 107) {
                            window.location.href = "${pageContext.request.contextPath}/order/list.page";
                        } else {
                            layer.msg(data.message);
                        }
                    },
                    error: function () {
                        layer.closeAll();
                    }
                });
            }
		}

        $('input[name="selectAll"]').on("click",function(){
            if($(this).is(':checked')){
                $('input[name="cartItemIds"]').each(function(){
                    $(this).prop("checked",true);
                });
            }else{
                $('input[name="cartItemIds"]').each(function(){
                    $(this).prop("checked",false);
                });
            }
        });
	</script>
</html>