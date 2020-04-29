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

	<div style="padding: 20px">
		<div class="slideshow-container">

			<div class="mySlides fade">
				<img src="${baseUrl}resources/home/img/img_nature_wide.jpg"
					style="width: 100%">
			</div>

			<div class="mySlides fade">
				<img src="${baseUrl}resources/home/img/img_snow_wide.jpg"
					style="width: 100%">
			</div>

			<div class="mySlides fade">
				<img src="${baseUrl}resources/home/img/img_mountains_wide.jpg"
					style="width: 100%">
			</div>
		</div>
	</div>

	<div class="special">
		<div class="container">
			<h3>
				Ốc <a href="${baseUrl}san-pham/oc"><button class="viewAll">Xem
						thêm &#187;</button></a>
			</h3>
			<div class="specia-top">
				<ul class="grid_2" id="oc">
					<!-- data here -->
				</ul>
			</div>
			<h3>
				Đèn trợ sáng <a href="${baseUrl}san-pham/den-tro-sang"><button
						class="viewAll">Xem thêm &#187;</button></a>
			</h3>
			<div class="specia-top">
				<ul class="grid_2" id="denTroSang">
					<!-- data here -->
				</ul>
			</div>
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
									style="background-image: url('${baseUrl}resources/home/img/img-sprite.png');">
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


	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/home/common/_footer.jsp"%>
	<%@ include file="/WEB-INF/views/home/common/_footer-declare.jsp"%>

	<div id="custom-overlay">
		<div class="loading-spinner">Loading (custom)...</div>
	</div>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
		apiUrl = {
			products: {
				all: baseUrl + 'products',
				image: baseUrl + 'resources/images/products/',
				cart: baseUrl + 'products/add-to-cart/',
				byProductType: baseUrl +  'products/product-type',
			}
		}
	
		initData("#oc", apiUrl.products.byProductType + '/oc?get_random=true&limit=4');
		initData("#denTroSang", apiUrl.products.byProductType + '/den-tro-sang?get_random=true&limit=4');
		var slideIndex = 0;
		showSlides();
		//--------------------------------------------

		function initData(elemenId, url) {	
			 $('body').loading({
				  stoppable: false,
				  overlay: $("#custom-overlay")
			 });
			$.ajax({
				 url: url,
				 async: false,
				 success: (res, status)=>{
					 console.log(res);
					 renderHtml(elemenId,res.data);
				 },
			});
			
		}
		
		function renderHtml(elemenId,data){
			html = '';
			data.forEach(ele => {
				html += '<li>';
				html += '	<a href="'+baseUrl+'chi-tiet-san-pham/'+ele.proId+'">';
				html += '		<img src="'+ apiUrl.products.image +ele.proImages[0].proImageName+'" class="img-responsive" alt="loading...">';
				html += '	</a>';
				html += '	<div center;" class="special-info grid_1 simpleCart_shelfItem">';
				html += '		<h5><a class="product_link" href="'+baseUrl+'chi-tiet-san-pham/'+ele.proId+'">'+ele.proName+'</a></h5>';
				if(!ele.proIsDiscount){
					html += '		<div class="item_add">';
					html += '			<span class="item_price">';
					html += '				<p>&#160;</p>';
					html += '				<h4>'+(ele.proPrice/1000).toFixed(3)+' đ'+'</h4>';
					html += '			</span>';
					html += 			ele.proAmount < 3 ? '<p style="color:green">Chỉ còn '+ele.proAmount+' sản phẩm</p>':'<p>&#160;</p>';
					html += '		</div>';
				}else{
					html += '		<div class="item_add">';
					html += '			<span class="item_price">';
					html += '				<p style="color:#212020"><del>'+(ele.proPrice/1000).toFixed(3)+ ' đ' +'</del></p>';
					html += '				<h4>'+((ele.proPrice-ele.proPrice*ele.proDiscountRatio/100)/1000).toFixed(3)+ ' đ</h4>';
					html += '			</span>';
					html += 			ele.proAmount < 3 ? '<p style="color:green">Chỉ còn '+ele.proAmount+' sản phẩm</p>':'<p>&#160;</p>';
					html += '		</div>';
				}
				html += '		<div class="item_add">';
				html += '			<span class="item_price"><a href="#" onClick="addToCart('+ele.proId+')">Thêm vào giỏ</a></span>';
				html += '		</div>';
				html += '	</div>';
				html += '</li>';
			});		
			html += '<div class="clearfix"></div>';
			$(elemenId).html(html);
		}
		
		// Thêm sản phẩm vào giỏ
		function addToCart(proId){
			console.log('addToCart: ' + proId);
			 url = apiUrl.products.cart + proId; 
			 $.post(url,{},(res, status) => {
				 $('#simpleCart_quantity').html(res.data);
				 alert("Thêm thành công");
			    }
			 );
		}
		
		function showSlides() {
			  var i;
			  var slides = document.getElementsByClassName("mySlides");
			  for (i = 0; i < slides.length; i++) {
			    slides[i].style.display = "none";  
			  }
			  slideIndex++;
			  if (slideIndex > slides.length) {slideIndex = 1}    

			  slides[slideIndex-1].style.display = "block";  
			  setTimeout(showSlides, 2000); // Change image every 2 seconds
		}
	</script>

</body>
</html>