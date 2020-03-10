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
							</a></span>
							<br>
							<div class="price">
								<span class="text">Giá:</span> <span class="price-new">${product.proPrice}</span><span
									class="price-old">$100.00</span>
							</div>
							<div class="det_nav1">
								<h4>Select a size :</h4>
								<div class=" sky-form col col-4">
									<ul>
										<li><label class="checkbox"><input
												type="checkbox" name="checkbox"><i></i>L</label></li>
										<li><label class="checkbox"><input
												type="checkbox" name="checkbox"><i></i>S</label></li>
										<li><label class="checkbox"><input
												type="checkbox" name="checkbox"><i></i>M</label></li>
										<li><label class="checkbox"><input
												type="checkbox" name="checkbox"><i></i>XL</label></li>
									</ul>
								</div>
							</div>
							<div class="btn_form">
								<a href="checkout.html">Mua ngay</a> <a href="checkout.html">Thêm
									vào giỏ</a>
							</div>
							<a href="#"><span>login to save in wishlist </span></a>
						</c:if>
					</div>
					<div class="clearfix"></div>
				</div>
				<c:if test="${not empty product}">
					<div class="single-bottom1">
						<h6>Chi tiết sản phẩm</h6>
						<p class="prod-desc">${product.proDescribe}</p>
					</div>
				</c:if>
				<div class="single-bottom2">
					<h6>Related Products</h6>
					<div class="product">
						<div class="product-desc">
							<div class="product-img">
								<img src="images/w8.jpg" class="img-responsive " alt="" />
							</div>
							<div class="prod1-desc">
								<h5>
									<a class="product_link" href="#">Excepteur sint</a>
								</h5>
								<p class="product_descr">Vivamus ante lorem, eleifend nec
									interdum non, ullamcorper et arcu. Class aptent taciti sociosqu
									ad litora torquent per conubia nostra, per inceptos himenaeos.
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="product_price">
							<span class="price-access">$597.51</span>
							<button class="button1">
								<span>Add to cart</span>
							</button>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="product">
						<div class="product-desc">
							<div class="product-img">
								<img src="images/w10.jpg" class="img-responsive " alt="" />
							</div>
							<div class="prod1-desc">
								<h5>
									<a class="product_link" href="#">Excepteur sint</a>
								</h5>
								<p class="product_descr">Vivamus ante lorem, eleifend nec
									interdum non, ullamcorper et arcu. Class aptent taciti sociosqu
									ad litora torquent per conubia nostra, per inceptos himenaeos.
								</p>
							</div>
							<div class="clearfix"></div>
						</div>
						<div class="product_price">
							<span class="price-access">$597.51</span>
							<button class="button1">
								<span>Add to cart</span>
							</button>
						</div>
						<div class="clearfix"></div>
					</div>
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
		const firstPage = 1;
		let startPage;
		let totalPages = ${paging.totalPages};
		let visiblePages = ${paging.visiblePages};
		let currentPage = ${paging.page};// default
		let limit = 8;
		let baseUrl = 'http://localhost:8080/';
	
		apiUrl = {
			products: {
				all: baseUrl + 'products?limit='+limit+'&',
				image: baseUrl + 'resources/images/products/',
			}
		}
	
		viewMore(apiUrl.products.all);
		//--------------------------------------------------------------
		function viewMore(url) {
			$('#viewMoreBtn').click( () => {
				currentPage += 1;
				console.log(url + 'page='+currentPage);
				$.get( url + 'page='+currentPage,
						(res, status) => {
							renderHtml(res.data);
							// xóa nút xem thêm
							if(currentPage === totalPages)
								$('#viewMoreBtn').remove();
						}
				);
			});
		}
		
		function renderHtml(data){
			let html = "";
			data.forEach(ele => { 
				html += '<div class="grid1_of_4">';
				html += '	<div class="border">';
				html += '		<div class="content_box"><a href="details.html">';
				html += '			<img src="'+apiUrl.products.image + ele.proImages[0].proImageName +'" class="img-responsive" alt="loading..." />';
				html += '			</a>';
				html += '		</div>';
				html += '		<h5><a href="details.html">'+ele.proName+'</a></h5>';
				html += '		<div class="item_add"><span class="item_price">';
				html += '			<p><del>'+ele.proPrice+'</del></p>';
				html += '		</span></div>';
				html += '		<div class="item_add"><span class="item_price">';
				html += '			<h4>'+ele.proPrice+'</h4>';
				html += '		</span></div>';
				html += '		<p>Chỉ còn 2 sản phẩm</p>';
				html += '		<div class="item_add"><span class="item_price"><a href="#">add to cart</a></span></div>';
				html += '	</div>';
				html += '</div>';
				
			});
			$('#viewMoreLocation').after(html);  
			// xóa location ban đầu
			$('#viewMoreLocation').remove();
			// tạo location mới
			$('#viewMoreInit').before('<input id="viewMoreLocation" type="hidden"></input>');
		}
	</script>
</body>
</html>