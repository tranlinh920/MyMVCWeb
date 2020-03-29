<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Header declare -->
<%@ include file="/WEB-INF/views/home/common/_header-declare.jsp"%>

<script
	src="${pageContext.request.contextPath}/resources/home/js/jquery.etalage.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/home/js/menu_jquery.js"></script>
<script>
	jQuery(document)
			.ready(
					function($) {

						$('#etalage')
								.etalage(
										{
											thumb_image_width : 250,
											thumb_image_height : 250,
											source_image_width : 400,
											source_image_height : 400,
											show_hint : true,
											click_callback : function(
													image_anchor, instance_id) {
												alert('Callback example:\nYou clicked on an image with the anchor: "'
														+ image_anchor
														+ '"\n(in Etalage instance: "'
														+ instance_id + '")');
											}
										});

					});
</script>

</head>
<body>
	<!-- Header -->
	<%@ include file="/WEB-INF/views/home/common/_header.jsp"%>

	<!-- content -->
	<div class="container">
		<div class="women_main">

			<!-- start content -->
			<div class="col-md-9 det">
				<div class="single_left">
					<div class="grid images_3_of_2">
						<c:if test="${not empty product}">
							<ul id="etalage">
								<li><a href="#"> <img class="etalage_thumb_image"
										src="http://localhost:8080/resources/images/products/${product.proImages[0].proImageName}"
										class="img-responsive" /> <img class="etalage_source_image"
										src="http://localhost:8080/resources/images/products/${product.proImages[0].proImageName}"
										class="img-responsive" title="" />
								</a></li>
								<li><img class="etalage_thumb_image"
									src="http://localhost:8080/resources/images/products/${product.proImages[1].proImageName}"
									class="img-responsive" /> <img class="etalage_source_image"
									src="http://localhost:8080/resources/images/products/${product.proImages[1].proImageName}"
									class="img-responsive" /></li>
								<li><img class="etalage_thumb_image"
									src="http://localhost:8080/resources/images/products/${product.proImages[2].proImageName}"
									class="img-responsive" /> <img class="etalage_source_image"
									src="http://localhost:8080/resources/images/products/${product.proImages[2].proImageName}"
									class="img-responsive" /></li>
							</ul>
						</c:if>
						<div class="clearfix"></div>
					</div>
					<div class="desc1 span_3_of_2">
						<c:if test="${not empty product}">
							<h3>${product.proName}</h3>
							<span class="brand">Loại: <a
								href="http://localhost:8080/san-pham/${product.proType.proTypeCode}">${product.proType.proTypeName }
							</a><input type="hidden" id="productTypeCode"
								value="${productType.proTypeCode}"></span>
							<c:if test="${not empty product.proBrand}">
								<br>
								<span class="brand">Thương hiệu: <a
									href="http://localhost:8080/san-pham/${product.proBrand.proBrandName}">${product.proBrand.proBrandName }
								</a></span>
							</c:if>
							<br>
							<c:if test="${!product.proIsDiscount}">
								<div class="price">
									<span class="text">Giá:</span> <span class="price-new">
										<fmt:formatNumber value="${product.proPrice}" type="currency" />
									</span>
								</div>
							</c:if>
							<c:if test="${product.proIsDiscount}">
								<div class="price">
									<span class="text">Giá:</span> <span class="price-new">
										<fmt:formatNumber
											value="${product.proPrice - product.proPrice*product.proDiscountRatio/100}"
											type="currency" />
									</span><span class="price-old"> <fmt:formatNumber
											value="${product.proPrice}" type="currency" />
									</span>
								</div>
							</c:if>
							<div class="btn_form">
								<a onclick="buyNow(${product.proId})">Mua ngay</a> <a href="#"
									onclick="addToCart(${product.proId})">Thêm vào giỏ</a>
							</div>
						</c:if>
					</div>
					<div class="clearfix"></div>
				</div>
				<c:if test="${not empty product}">
					<div class="single-bottom1">
						<h6>Mô tả</h6>
						<p class="prod-desc">${product.proDescribe}</p>
					</div>
				</c:if>
				<div class="single-bottom2">
					<h6>Sản phẩm liên quan</h6>
					<input type="hidden" id="relatedProducts">
					<!-- data here -->
				</div>
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

	<!-- Footer -->
	<%@ include file="/WEB-INF/views/home/common/_footer.jsp"%>

	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/home/common/_footer-declare.jsp"%>

	<script>
		let baseUrl = 'http://localhost:8080/';
		let proTypeCode = $('#productTypeCode').val();
	
		apiUrl = {
			products: {
				all: baseUrl + 'products',
				image: baseUrl + 'resources/images/products/',
				cart: baseUrl + 'products/add-to-cart/',
				byProductType: baseUrl +  'products/product-type?product_type_code=' + proTypeCode,
			}
		}
	
		getRelatedProducts(apiUrl.products.byProductType + '&get_random=true&limit=3');
		//--------------------------------------------------------------
		function getRelatedProducts(url) {
			$.get( url, (res, status) => {
				renderHtml(res.data);
			});
		}
		
		function renderHtml(data){
			let html = "";
			data.forEach(ele => { 
				html += '<div class="product">';
				html += '		<div class="product-desc">';
				html += '			<div class="product-img">';
				html += '				<img src="'+ apiUrl.products.image +ele.proImages[0].proImageName+'" class="img-responsive " alt="loading..." />';
				html += '			</div>';
				html += '			<div class="prod1-desc">';
				html += '				<h5><a class="product_link" href="http://localhost:8080/chi-tiet-san-pham/'+ele.proId+'">'+ele.proName+'</a></h5>';
				html += '               <p>'+(ele.proPrice/1000).toFixed(3)+ ' đ' + '</p>';
				html += '			</div>';
				html += '			<div class="clearfix"></div>';
				html += '		</div>';
				html += '		<div class="product_price">';
				html += '			<button class="button1" onclick="addToCart('+ele.proId+')"><span>Thêm vào giỏ</span></button>';
				html += '		</div>';
				html += '		<div class="clearfix"></div>';
				html += '</div>';
			});
			
			$('#relatedProducts').after(html);
		}
		
		// Thêm sản phẩm vào giỏ
		function addToCart(proId){
			 url = apiUrl.products.cart + proId; 
			 $.post(url,{},(res, status) => {
				 $('#simpleCart_quantity').html(res.data);
				 alert("Thêm thành công");
			    }
			 );
		}
		
		// mua ngay
		function buyNow(proId){
			url = apiUrl.products.cart + proId; 
			 $.post(url,{},(res, status) => {
				 window.location.href = 'http://localhost:8080/gio-hang';
			    }
			 );
		}
	</script>
</body>
</html>