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
					<c:if test="${not empty searchNotify}">
						<a href="#">
							<h4>${searchNotify}</h4> <input type="hidden" id="searchString"
							value="${searchString}">
						</a>
					</c:if>
					<!-- 					<ul class="w_nav"> -->
					<!-- 						<li>Sort :</li> -->
					<!-- 						<li><a class="active" href="#">popular</a></li> | -->
					<!-- 						<li><a href="#">new </a></li> | -->
					<!-- 						<li><a href="#">discount</a></li> | -->
					<!-- 						<li><a href="#">price: Low High </a></li> -->
					<!-- 						<div class="clear"></div> -->
					<!-- 					</ul> -->
					<div class="clearfix"></div>
				</div>
				<!-- grids_of_4 -->
				<c:if test="${not empty products}">
					<c:forEach var="product" items="${products}">
						<div class="grid1_of_4">
							<div class="border">
								<div class="content_box">
									<a href="${baseUrl}chi-tiet-san-pham/${product.proId}"> <img
										src="${baseUrl}upload/images/products/${product.proImages[0].proImageName}"
										class="img-responsive" alt="" />
									</a>
								</div>
								<h5>
									<a href="${baseUrl}chi-tiet-san-pham/${product.proId}">${product.proName}</a>
								</h5>
								<c:if test="${!product.proIsDiscount}">
									<div class="item_add">
										<span class="item_price">
											<p>&#160;</p>
										</span>
									</div>
								</c:if>
								<c:if test="${product.proIsDiscount}">
									<div class="item_add">
										<span class="item_price"><p>
												<del>

													<fmt:formatNumber value="${product.proPrice}"
														type="currency" />
												</del>
											</p> </span>
									</div>
								</c:if>
								<c:if test="${!product.proIsDiscount}">
									<div class="item_add">
										<span class="item_price">
											<h4>
												<fmt:formatNumber value="${product.proPrice}"
													type="currency" />
											</h4>
										</span>
									</div>
								</c:if>
								<c:if test="${product.proIsDiscount}">
									<div class="item_add">
										<span class="item_price">
											<h4>
												<fmt:formatNumber
													value="${product.proPrice - product.proPrice*product.proDiscountRatio/100}"
													type="currency" />
												&#160;(&#45;${Math.round(product.proDiscountRatio)}%)
											</h4>
										</span>
									</div>
								</c:if>
								<c:if test="${product.proAmount < 3}">
									<p style="color: green">Chỉ còn ${product.proAmount} sản
										phẩm</p>
								</c:if>
								<c:if test="${product.proAmount > 2}">
									<p>&#160;</p>
								</c:if>
								<div class="item_add">
									<span class="item_price"><a href="#"
										onClick="addToCart(${product.proId})">Thêm vào giỏ</a></span>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
				<!-- end grids_of_4 -->
				<input id="viewMoreLocation" type="hidden"></input> <input
					id="viewMoreInit" type="hidden"></input>
				<div class="clearfix"></div>
				<div class=" text-center">
					<button id="viewMoreBtn"
						style="margin-top: 20px; background-color: #00405d; color: white;"
						class="btn btn-default">Xem thêm</button>
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
							<input type="text"
								placeholder="Nhập email để nhân thông báo mới"
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
		const firstPage = 1;
		let startPage;
		let totalPages = ${paging.totalPages};
		let visiblePages = ${paging.visiblePages};
		let currentPage = ${paging.page};// default
		let limit = 8;
	
		let searchString = $('#searchString').val();
			
		apiUrl = {
			products: {
				all: baseUrl + 'products?limit='+limit,
				image: baseUrl + 'upload/images/products/',
				cart: baseUrl + 'products/add-to-cart/',
				search : baseUrl + 'products/search?limit=8&p=' + searchString
			}
		}
	
		viewMore(apiUrl.products.search);
		//--------------------------------------------------------------
		
		// Xem thêm sản phẩm
		function viewMore(url) {
			$('#viewMoreBtn').click( () => {
				currentPage += 1;
				$.get( url + '&page='+currentPage
						,(res, status) => {
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
				html += '		<div class="content_box"><a href="'+baseUrl+'chi-tiet-san-pham/'+ele.proId+'">';
				html += '			<img src="'+apiUrl.products.image + ele.proImages[0].proImageName +'" class="img-responsive" alt="loading..." />';
				html += '			</a>';
				html += '		</div>';
				html += '		<h5><a href="'+baseUrl+'chi-tiet-san-pham/'+ele.proId+'">'+ele.proName+'</a></h5>';
				if(!ele.proIsDiscount){
					html += '		<div class="item_add"><span class="item_price">';
					html += '			<p>&#160;</p>';
					html += '		</span></div>';
					html += '		<div class="item_add"><span class="item_price">';
					html += '			<h4>'+(ele.proPrice/1000).toFixed(3)+ ' đ' + '</h4>';
					html += '		</span></div>';
				}else{
					html += '		<div class="item_add"><span class="item_price">';
					html += '			<p><del>'+(ele.proPrice/1000).toFixed(3)+ ' đ' + '</del></p>';
					html += '		</span></div>';
					html += '		<div class="item_add"><span class="item_price">';
					html += '			<h4>'+((ele.proPrice-ele.proPrice*ele.proDiscountRatio/100)/1000).toFixed(3)+ ' đ&#160;(&#45;' + ele.proDiscountRatio + '&#37;)</h4>';
					html += '		</span></div>';
				}	
				html += 			ele.proAmount < 3 ? '<p style="color:green">Chỉ còn '+ele.proAmount+' sản phẩm</p>':'<p>&#160;</p>';
				html += '		<div class="item_add"><span class="item_price"><a href="#">Thêm vào giỏ</a></span></div>';
				html += '	</div>';
				html += '</div>';
				
			});
			$('#viewMoreLocation').after(html);  
			// xóa location ban đầu
			$('#viewMoreLocation').remove();
			// tạo location mới
			$('#viewMoreInit').before('<input id="viewMoreLocation" type="hidden"></input>');
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
	</script>
</body>
</html>