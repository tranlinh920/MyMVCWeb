<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Header declare -->
<%@ include file="/WEB-INF/views/home/common/_header-declare.jsp"%>
</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/home/common/_header.jsp"%>

	<!-- content -->
	<div class="container">
		<div class="women_main">

			<!-- start content -->
			<div class="col-md-9 w_content">
				<div class="women">
					<c:if test="${not empty pageModel}">
						<h3>${pageModel.pagName}</h3>
					</c:if>
					<div class="clearfix"></div>
				</div>
				<!-- grids_of_4 -->
				<c:if test="${not empty pageModel}">
					<div class="res-page">${pageModel.pagContent}</div>
				</c:if>
				<!-- end grids_of_4 -->
				<div class="clearfix"></div>
			</div>

			<!-- start sidebar -->
			<%@ include file="/WEB-INF/views/home/common/_sidebar.jsp"%>

			<div class="clearfix"></div>


			<!-- end content -->
		</div>
	</div>

	<div class="foot-top">
		<div class="container">
			<div class="col-md-6 s-c">
				<li>
					<div class="fooll">
						<h5>Theo dõi chúng tôi:</h5>
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
							<input type="text" placeholder="Nhập email để nhân thông báo mới"
								required="">
						</form>
					</div>
					<div class="btn-1">
						<form>
							<input type="submit" value="Tham gia">
						</form>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>

	<!-- Footer -->
	<%@ include file="/WEB-INF/views/home/common/_footer.jsp"%>

	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/home/common/_footer-declare.jsp"%>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
		
	</script>
</body>
</html>