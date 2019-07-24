<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">

	$(function(){
		//给添加到购物车的按钮绑定单击事件
		$(".addBook").click(function(){
			//获取图书的id
			var bookId = $(this).attr("id");
			//向CartServlet发送请求
			location = "CartServlet?method=addBook2Cart&bookId="+bookId;
			
		});
	});
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/welcome.jsp" %>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.page.list }">
			<h1>没有任何图书</h1>
		</c:if>
		<c:if test="${!empty requestScope.page.list }">
		<div id="book">
			<div class="book_cond">
				<form action="BookClientServlet?method=getPageBooksByPrice" method="post">
				价格：<input type="text" name="min"> 元 - 
					<input type="text" name="max"> 元 
					<input type="submit" value="查询"> 
				</form>
			</div>
			<div style="text-align: center">
				<c:if test="${not empty sessionScope.cart.cartItems }">
					<span>您的购物车中有${sessionScope.cart.totalCount }件商品</span>
				</c:if>
				<c:if test="${ empty sessionScope.cart.cartItems }">
					<span>您的购物车中空空如也</span>
				</c:if>
				<c:if test="${not empty sessionScope.bookTitle }">
					
					<div>
						您刚刚将<span style="color: red">${sessionScope.bookTitle }</span>加入到了购物车中
						<c:remove var="bookTitle"/>
					</div>
				
				</c:if>
				<div>
					<span style="color: red">${sessionScope.msg }</span>
					<c:remove var="msg"/>
				</div>
				
				
			</div>
			
		<c:forEach items="${requestScope.page.list }" var="book">
			<div class="b_list">
				<div class="img_div">
					<img class="book_img" alt="" src="${book.imgPath }" />
				</div>
				<div class="book_info">
					<div class="book_name">
						<span class="sp1">书名:</span>
						<span class="sp2">${book.title }</span>
					</div>
					<div class="book_author">
						<span class="sp1">作者:</span>
						<span class="sp2">${book.author }</span>
					</div>
					<div class="book_price">
						<span class="sp1">价格:</span>
						<span class="sp2">￥${book.price }</span>
					</div>
					<div class="book_sales">
						<span class="sp1">销量:</span>
						<span class="sp2">${book.sales }</span>
					</div>
					<div class="book_amount">
						<span class="sp1">库存:</span>
						<span class="sp2">${book.stock }</span>
					</div>
					<div class="book_add">
						<c:if test="${book.stock > 0 }">
							<button id="${book.id }" class="addBook">加入购物车</button>
						</c:if>
						<c:if test="${book.stock == 0 }">
							<span style="color: red" >小二拼命补货中</span>
						</c:if>
						
					</div>
				</div>
			</div>
			</c:forEach>	
		</div>
		
		<div id="page_nav">
			<!-- 
				1.当总页数小于5时
				2.当总页数大于等于5时且当前页码小于等于3时
				3.当总页数大于等于5时且当前页码大于3时
			 -->
			 <c:choose>
			 	<c:when test="${page.totalPageNo < 5 }">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPageNo }"></c:set>
			 	</c:when>
			 	<c:when test="${page.pageNo <= 3  }">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="5"></c:set>
			 	</c:when>
			 	<c:otherwise>
			 		<c:set var="begin" value="${page.pageNo - 2 }"></c:set>
					<c:set var="end" value="${page.pageNo + 2 }"></c:set>
					<c:if test="${page.pageNo + 2 >  page.totalPageNo }">
						<c:set var="begin" value="${page.totalPageNo - 4 }"></c:set>
						<c:set var="end" value="${page.totalPageNo }"></c:set>
					</c:if>	
			 	</c:otherwise>
			 </c:choose>
			<c:if test="${page.pageNo > 1 }">
				<a href="BookClientServlet?method=getPageBooksByPrice&min=${param.min}&max=${param.max}">首页</a>
				<a href="BookClientServlet?method=getPageBooksByPrice&pageNo=${page.pageNo-1 }&min=${param.min}&max=${param.max}">上一页</a>
			</c:if>
			<c:forEach begin="${begin }" end="${end }" var="index">
				<c:if test="${page.pageNo == index }">
					[<a href="BookClientServlet?method=getPageBooksByPrice&pageNo=${index }&min=${param.min}&max=${param.max}">${index }</a>]
				</c:if>
				<c:if test="${page.pageNo != index }">
					<a href="BookClientServlet?method=getPageBooksByPrice&pageNo=${index }&min=${param.min}&max=${param.max}">${index }</a>
				</c:if>
			</c:forEach>
			<c:if test="${page.pageNo < page.totalPageNo }">
				<a href="BookClientServlet?method=getPageBooksByPrice&pageNo=${page.pageNo+1 }&min=${param.min}&max=${param.max}">下一页</a>
				<a href="BookClientServlet?method=getPageBooksByPrice&pageNo=${page.totalPageNo }&min=${param.min}&max=${param.max}">末页</a>
			</c:if>
			共${page.totalPageNo }页，${page.totalRecord }条记录 到第<input value="${page.pageNo }" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id="sub">
			<script type="text/javascript">
				//给确定按钮绑定单击事件
				$("#sub").click(function(){
					//获取输入的页码
					var pageNo = $("#pn_input").val();
					//发送请求
// 					window.location.href = "BookManagerServlet?method=getPageBooks&pageNo="+pageNo;
// 					window.location= "BookManagerServlet?method=getPageBooks&pageNo="+pageNo;
					location = "BookClientServlet?method=getPageBooksByPrice&pageNo="+pageNo+"&min=${param.min}&max=${param.max}";
				});
			</script>
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