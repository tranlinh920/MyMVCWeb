<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Quản lý sản phẩm</title>
<!-- Header declare -->
<%@ include file="/WEB-INF/views/admin/common/_header-declare.jsp"%>

</head>
<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Chào mừng!</h1>
									</div>
									<form class="user" action="/j_spring_security_check"
										method="post">
										<div class="form-group">
											<input type="text" class="form-control form-control-user"
												id="username" name="username" aria-describedby=""
												placeholder="Tên tài khoản..." required>
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="password" name="password" placeholder="Mật khẩu..."
												required>
										</div>
										<!-- 										<div class="form-group"> -->
										<!-- 											<div class="custom-control custom-checkbox small"> -->
										<!-- 												<input type="checkbox" class="custom-control-input" -->
										<!-- 													id="customCheck"> <label -->
										<!-- 													class="custom-control-label" for="customCheck">Remember -->
										<!-- 													Me</label> -->
										<!-- 											</div> -->
										<!-- 										</div> -->
										<button class="btn btn-primary btn-user btn-block"
											type="submit">Đăng nhập</button>
										<hr>
									</form>
									<hr>
									<div class="text-center">
										<a class="small" href="forgot-password.html">Quên mật khẩu</a>
									</div>
									<div class="text-center">
										<a class="small" href="register.html">Tạo tài khoản!</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/admin/common/_footer-declare.jsp"%>

</body>
</html>