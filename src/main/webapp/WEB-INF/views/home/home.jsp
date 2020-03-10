<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Header declare -->
<%@ include file="/WEB-INF/views/home/common/_header-declare.jsp"%>
</head>
<body>

	<!-- Header -->
	<%@ include file="/WEB-INF/views/home/common/_header.jsp"%>

	<div class="special">
		<div class="container">
			<h3>Sản phẩm mới</h3>
			<div class="specia-top">
				<ul class="grid_2">
					<li><a href="details.html"><img
							src="${pageContext.request.contextPath}/resources/home/img/8.jpg"
							class="img-responsive" alt=""></a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5>Lorem ipsum dolor</h5>
							<div class="item_add">
								<span class="item_price">
									<h6>ONLY $40.00</h6>
								</span>
							</div>
							<div class="item_add">
								<span class="item_price"><a href="#">Thêm vào giỏ</a></span>
							</div>
						</div></li>
					<li><a href="details.html"><img
							src="${pageContext.request.contextPath}/resources/home/img/9.jpg"
							class="img-responsive" alt=""></a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5>Consectetur adipis</h5>
							<div class="item_add">
								<span class="item_price">
									<h6>ONLY $60.00</h6>
								</span>
							</div>
							<div class="item_add">
								<span class="item_price"><a href="#">Thêm vào giỏ</a></span>
							</div>
						</div></li>
					<li><a href="details.html"><img
							src="${pageContext.request.contextPath}/resources/home/img/10.jpg"
							class="img-responsive" alt=""></a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5>Commodo consequat</h5>
							<div class="item_add">
								<span class="item_price">
									<h6>ONLY $14.00</h6>
								</span>
							</div>
							<div class="item_add">
								<span class="item_price"><a href="#">Thêm vào giỏ</a></span>
							</div>
						</div></li>
					<li><a href="details.html"><img
							src="${pageContext.request.contextPath}/resources/home/img/11.jpg"
							class="img-responsive" alt=""></a>
						<div class="special-info grid_1 simpleCart_shelfItem">
							<h5>Voluptate velit</h5>
							<div class="item_add">
								<span class="item_price">
									<h6>ONLY $37.00</h6>
								</span>
							</div>
							<div class="item_add">
								<span class="item_price"><a href="#">Thêm vào giỏ</a></span>
							</div>
						</div></li>
					<div class="clearfix"></div>
				</ul>
			</div>
			<h3>Sản phẩm bán chạy</h3>

		</div>
	</div>
	<div class="foot-top">
		<div class="container">
			<div class="col-md-6 s-c">
				<li>
					<div class="fooll">
						<h5>follow us on</h5>
					</div>
				</li>
				<li>
					<div class="social-ic">
						<ul>
							<li><a href="#"><i class="facebok"
									style="background-image: url('${pageContext.request.contextPath}/resources/home/img/img-sprite.png');">
								</i></a></li>
							<li><a href="#"><i class="twiter"> </i></a></li>
							<li><a href="#"><i class="goog"> </i></a></li>
							<li><a href="#"><i class="be"> </i></a></li>
							<li><a href="#"><i class="pp"> </i></a></li>
							<div class="clearfix"></div>
						</ul>
					</div>
				</li>
				<div class="clearfix"></div>
			</div>
			<div class="col-md-6 s-c">
				<div class="stay">
					<div class="stay-left">
						<form>
							<input type="text"
								placeholder="Enter your email to join our newsletter"
								required="">
						</form>
					</div>
					<div class="btn-1">
						<form>
							<input type="submit" value="join">
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/home/common/_footer.jsp"%>
</body>
</html>