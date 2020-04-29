<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:useBean id="SecurityUtil" class="com.my.utils.SecurityUtil" />

<!-- header -->
<div class="header_bg">
	<div class="container">
		<div class="header">
			<div class="head-t">
				<div class="logo">
					<a href="${baseUrl}trang-chu"><img
						src="${baseUrl}resources/home/img/logo.png" class="img-responsive"
						alt="" /> </a>
				</div>
				<!-- start header_right -->
				<div class="header_right">
					<div class="rgt-bottom">
						<div class="log">
							<div class="login">
								<div id="loginContainer">

									<sec:authorize access="!isAuthenticated()">
										<a
											href="https://www.facebook.com/dialog/oauth?client_id=1548757658620070&redirect_uri=${baseUrl}dang-nhap-bang-facebook"
											id="loginButton"><span>Đăng nhập bằng Facebook</span></a>
									</sec:authorize>
									<sec:authorize access="isAuthenticated()">
										<div class="dropdown">
											<h5>
												Chào:<strong> ${user.userFullName} <i
													class="fa fa-sort-desc "></i></strong>
											</h5>
											<div class="dropdown-content">
												<a href="http://localhost:8080/dang-xuat">Đăng xuất</a>
											</div>
										</div>
									</sec:authorize>

									<div id="loginBox">
										<form id="loginForm">
											<fieldset id="body">
												<fieldset>
													<label for="email">Email Address</label> <input type="text"
														name="email" id="email">
												</fieldset>
												<fieldset>
													<label for="password">Password</label> <input
														type="password" name="password" id="password">
												</fieldset>
												<input type="submit" id="login" value="Sign in"> <label
													for="checkbox"><input type="checkbox" id="checkbox">
													<i>Remember me</i></label>
											</fieldset>
											<span><a href="#">Forgot your password?</a></span>
										</form>
									</div>
								</div>
							</div>
						</div>
						<!-- 						<div class="reg"> -->
						<!-- 							<a href="register.html">Đăng ký</a> -->
						<!-- 						</div> -->
						<div class="cart box_1">
							<a href="${baseUrl}gio-hang">
								<h3>
									<img src="${baseUrl}resources/home/img/bag.png" alt="">(<span
										id="simpleCart_quantity" class="simpleCart_quantity">${cartSize}</span>)
								</h3>
							</a>
							<div class="clearfix"></div>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="search">
						<form action="${baseUrl}tim-kiem" method="get">
							<input type="text" name="p" value="" placeholder="Tìm kiếm...">
							<input type="submit" value="">
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- end header_right -->
				<div class="clearfix"></div>
			</div>
			<!-- start header menu -->
			<!-- 				<ul class="megamenu skyblue"> -->
			<!-- 					<li class="active grid"><a class="color1" href="index.html">Home</a></li> -->
			<!-- 					<li class="grid"><a class="color2" href="#">new arrivals</a> -->
			<!-- 						<div class="megapanel"> -->
			<!-- 							<div class="row"> -->
			<!-- 								<div class="col1"> -->
			<!-- 									<div class="h_nav"> -->
			<!-- 										<h4>Clothing</h4> -->
			<!-- 										<ul> -->
			<!-- 											<li><a href="women.html">new arrivals</a></li> -->
			<!-- 											<li><a href="women.html">men</a></li> -->
			<!-- 											<li><a href="women.html">women</a></li> -->
			<!-- 											<li><a href="women.html">accessories</a></li> -->
			<!-- 											<li><a href="women.html">kids</a></li> -->
			<!-- 											<li><a href="women.html">brands</a></li> -->
			<!-- 										</ul> -->
			<!-- 									</div> -->
			<!-- 								</div> -->
			<!-- 							</div> -->
			<!-- 						</div></li> -->
			<!-- 				</ul> -->


			<ul class="megamenu skyblue">
				<c:if test="${not empty navs}">
					<c:forEach var="navCat" items="${navs}">
						<c:choose>
							<c:when test="${navCat.catName=='Trang chủ'}">
								<li class="active grid"><a class="color1"
									href="${baseUrl}trang-chu">Trang chủ</a></li>
							</c:when>
							<c:when test="${not empty navCat.catCategories}">
								<li class="grid"><a href="#" class="color1">${navCat.catName}</a>
									<div class="megapanel">
										<div class="row">
											<c:forEach var="catsParent" items="${navCat.catCategories}">
												<div class="col1">
													<div class="h_nav">
														<h4>${catsParent.catName}</h4>
														<c:if test="${not empty catsParent.catCategories}">
															<ul>
																<c:forEach var="catsChild"
																	items="${catsParent.catCategories}">
																	<li><a href="${catsChild.catLink}">${catsChild.catName}</a></li>
																</c:forEach>
															</ul>
														</c:if>
													</div>
												</div>
											</c:forEach>
										</div>
									</div></li>
							</c:when>
							<c:otherwise>
								<li class="grid"><a class="color1" href="${navCat.catLink}">${navCat.catName}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</ul>
			<!-- end header menu -->
		</div>
	</div>
</div>
<!--end header -->