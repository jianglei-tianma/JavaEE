 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp" %>

<script type="text/javascript">
	$(function(){
		
		
		//给清空购物车设置的超链接绑定单击事件
		$("#emptyCart").click(function(){
			return confirm("亲！您确定要清空购物车吗？");
			
		});
		
		//给删除购物项的超链接绑定单击事件
		$(".delCartItem").click(function(){
			
			var title = $(this).attr("id");
			return confirm("确定要删除【"+title+"】这本图书嘛");
			
		});
		//给输入图书数量的inputValue绑定事件
		//失去焦点blur事件
		
		$(".inputValue").change(function(){
			//获取一下默认值
			
			var defValue = this.defaultValue;
			
			//获取图书的id和用户输入的数量
			var bookId = $(this).attr("id");
			var inputValue= $(this).val();
			
			
			
			//申明一个验证用户输入的值是否合法的正则表达式
			var reg = /^\+?[1-9][0-9]*$/;
			var flag = reg.test(inputValue);
			if(!flag){
				//回复默认值
				this.value=defValue;
				alert("请输入正整数！");
				return false;
			}
			
			//获取图书的库存
			var stock = $(this).attr("name");
			//stock = parseInt(stock);
			stock = new Number(stock);
			if(inputValue>stock){
				//回复默认值
				this.value=defValue;
				alert("该图书的库存只有"+stock+"本！");
				return false;
				
			}else{
				//这时用户输入的值就是一个即合法又不超库存的一个数，这时将默认值修改为当前值
				this.defaultValue = this.value;
			}
			
			//发送请求更新购物项
			//location = "CartServlet?method=updateCartItem&bookId="+bookId+"&bookCount="+inputValue;
			//设置请求路径
			var url="CartServlet?method=updateCartItem";
			//设置请求参数
			var params = {"bookId":bookId,"bookCount":inputValue};
			//获取显示购物项中的金额小计的td元素
			var $tdEle = $(this).parent.next().next();
			//发送Ajax请求
			$.post(url,params,function(data){
				//获取购物车中的总数量
				var totalCount = data.totalCount;
				//将总数量设置到显示的span中
				$("#b_count").text(totalCount);
				//获取购物车中的总金额
				var totalAmout = data.totalAmount;
				//将总金额设置到显示span的元素中
				$("#b_price").text(totalAmout)
				//获取购物项中的金额小计
				var amount = data.amount;
				//将金额小计设置到显示的td元素中去
				$tdEle.text(amount);

			},"json");
		
		});
		
		
		
	});

</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
	
		
			<c:if test="${empty sessionScope.cart.cartItems }">
				<h1 style="text-align:center;">购物车中没有任何图书 ,快去<a href="index.jsp" style="color: red">购物</a>吧！</h1>
			</c:if>
			<c:if test="${!empty sessionScope.cart.cartItems }">
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>	
					<c:forEach items="${sessionScope.cart.cartItems }" var="cartItem">
						<tr>
							<td>${cartItem.book.title }</td>
							<td>
								<input name ="${cartItem.book.stock }"  id =${cartItem.book.id } class="inputValue" type="text" value="${cartItem.count }" style="width: 30px;text-align: center;">
								
							</td>
							<td>${cartItem.book.price }</td>
							<td>${cartItem.amount }</td>
							<td><a id ="${cartItem.book.title }" class="delCartItem" href="CartServlet?method=deleteCartItem&bookId=${cartItem.book.id }" >删除</a></td>
						</tr>				
					</c:forEach>	
				</table>
			
		
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count" id = "b_count">${sessionScope.cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price" id ="b_price">${sessionScope.cart.totalAmount }</span>元</span>
					<span class="cart_span"><a href="index.jsp">继续购物</a></span>
					<span  class="cart_span"><a href="CartServlet?method=emptyCart" id="emptyCart">清空购物车</a></span>
					<span class="cart_span"><a href="OrderClientServlet?method=checkout">去结账</a></span>
				</div>
		</c:if>
	
	</div> 
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>