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

	<div class="container">
		<div class="check">
			<div class="col-md-9 cart-items">
				<h1>Giỏ hàng</h1>
				<c:set var="bilAmount" value="${0}" />
				<c:if test="${not empty products}">
					<c:forEach var="product" items="${products}">
						<input type="hidden" name="product" value="${product}">
						<div class="cart-header" id="cart-header${product.proId}">
							<div class="close1" onClick="removeFromCart(${product.proId})"
								style="background-image: url('${baseUrl}resources/home/img/close_1.png');"></div>
							<div class="cart-sec simpleCart_shelfItem">
								<div class="cart-item cyc">
									<img
										src="${baseUrl}resources/images/products/${product.proImages[0].proImageName}"
										class="img-responsive" alt="" />
								</div>
								<div class="cart-item-info">
									<h3>
										<a href="#">${product.proName}</a>
									</h3>
									<ul class="qty">
										<li><c:if test="${product.proIsDiscount}">
												<p>
													Giá thị trường :
													<del>
														<fmt:formatNumber value="${product.proPrice}"
															type="currency" />
													</del>
												</p>
												<p>
													Chỉ còn:
													<fmt:formatNumber
														value="${product.proPrice - product.proPrice*product.proDiscountRatio/100}"
														type="currency" />
												</p>
											</c:if> <c:if test="${!product.proIsDiscount}">
												<p id="proPrice">
													Giá:
													<fmt:formatNumber value="${product.proPrice}"
														type="currency" />
												</p>
											</c:if></li>
										<c:forEach var="ele" items="${numOfProId}">
											<c:if test="${ele.key == product.proId}">
												<p name="numOfProduct">Số lượng mua: ${ele.value}</p>
												<c:if test="${!product.proIsDiscount}">
													<c:set var="proTotalPrice"
														value="${product.proPrice*ele.value}" />
													<c:set var="bilAmount" value="${bilAmount + proTotalPrice}" />
													<input type="hidden" id="proTotalPrice${product.proId}"
														value="${proTotalPrice}">
												</c:if>
												<c:if test="${product.proIsDiscount}">
													<c:set var="proTotalPrice"
														value="${(product.proPrice - product.proPrice*product.proDiscountRatio/100)*ele.value}" />
													<c:set var="bilAmount" value="${bilAmount + proTotalPrice}" />
													<input type="hidden" id="proTotalPrice${product.proId}"
														value="${proTotalPrice}">
												</c:if>
											</c:if>
										</c:forEach>
									</ul>

								</div>
								<div class="clearfix"></div>

							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>

			<div class="col-md-3 cart-total">
				<div class="price-details">
					<h3>Cộng giỏ hàng</h3>
					<span>Tạm tính:</span> <span class="total1" id="tempProductPrice"><fmt:formatNumber
							value="${bilAmount}" type="currency" /></span> <span>Giảm:</span> <span
						class="total1">---</span>
					<div class="clearfix"></div>
				</div>
				<ul class="total_price">
					<li class="last_price">
						<h4>Tổng:</h4>
					</li>
					<li class="last_price"><span id="totalProductPrice"> <fmt:formatNumber
								value="${bilAmount}" type="currency" /></span> <input type="hidden"
						id="totalBillAmount" value="${bilAmount}" /></li>
					<div class="clearfix"></div>
				</ul>
				<br>
				<p style="color: red">
					Lưu ý: Tổng tiền ở trên <strong>CHƯA</strong> bao gồm phí ship (sẽ
					được cộng thêm phụ thuộc vào địa chỉ.)
				</p>

				<div class="clearfix"></div>
				<a class="order" href="#checkout">Tiến hành thanh toán</a> <a
					class="continue" href="#">Tiếp tục mua sắm</a>
			</div>

			<div class="clearfix"></div>
		</div>

		<div id="checkoutAlert" class="alert alert-success" hidden="true">
			<strong>Success!</strong> Indicates a successful or positive action.
		</div>

		<!-- Nếu đăng nhập rồi thì tại đây load thông tin người dùng lên -->
		<div class="main">
			<div class="contact" id="checkout">
				<div class="contact-form">
					<h2>Nhập thông tin thanh toán:</h2>
					<c:choose>
						<c:when test="${not empty user}">
							<form method="post" action="contact-post.html">
								<div>
									<p style="color: green">*Quý khách vui lòng kiểm tra lại
										thông tin bên dưới.</p>
								</div>
								<div>
									<span><label>Họ và tên (*)</label></span> <span><input
										name="fullName" id="cusFullName" type="text"
										value="${user.userFullName}" required></span> <span
										id="cusFullNameVaild"></span>
								</div>
								<div>
									<span><label>E-mail</label></span> <span><input
										name="userEmail" id="cusEmail" type="text"
										value="${user.userEmail}"></span><span id="cusEmailVaild"></span>
								</div>
								<div>
									<span><label>Số điện thoại (*)</label></span> <span><input
										name="userPhone" id="cusPhoneNumber" type="text"
										value="${user.userPhoneNumber}" onkeyup="isPhoneNumber()"
										required></span><span id="cusPhoneVaild"></span>
								</div>
								<div>
									<span><label>Địa chỉ và ghi chú (*)</label></span> <span><textarea
											required name="userMsg" id="cusAddress">${user.userAddress}</textarea></span>
									<span id="cusAddressVaild"></span>
								</div>
								<div>
									<span><input type="submit" onClick="submitCheckout()"
										class="" value="Đặt hàng"></span>
								</div>
							</form>
						</c:when>
						<c:otherwise>
							<form method="post" action="contact-post.html">
								<div>
									<span><label>Họ và tên (*)</label></span> <span><input
										name="fullName" id="cusFullName" type="text" class="textbox"
										required></span><span id="cusFullNameVaild"></span>
								</div>
								<div>
									<span><label>E-mail</label></span> <span><input
										name="userEmail" id="cusEmail" type="text" class="textbox"></span>
									<span id="cusEmailVaild"></span>
								</div>
								<div>
									<span><label>Số điện thoại (*)</label></span> <span><input
										name="userPhone" id="cusPhoneNumber" type="text"
										class="textbox" onkeyup="isPhoneNumber()" required></span> <span
										id="cusPhoneVaild"></span>
								</div>
								<div>
									<span><label>Địa chỉ và ghi chú (*)</label></span> <span><textarea
											required name="userMsg" id="cusAddress"></textarea></span><span
										id="cusAddressVaild"></span>
								</div>
								<div>
									<span><input type="submit" onClick="submitCheckout()"
										class="" value="Đặt hàng"></span>
								</div>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="clearfix"></div>
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
									style="background-image: url('${baseUrl}/resources/home/img/img-sprite.png');">
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
	<%@ include file="/WEB-INF/views/home/common/_modal.jsp"%>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
		let limit = 8;
		let products = ${productsJson};
		let bilProducts = [];
		apiUrl = {
			products: {
				all: baseUrl + 'products?limit='+limit+'&',
				image: baseUrl + 'resources/images/products/',
				cart: baseUrl + 'products/add-to-cart/',
				delCart: baseUrl + 'products/del-from-cart',
			},
			bills: {
				all: baseUrl + 'bills',
			}
		}
		
		initProductsInBill();
		//---------------------------------------------------------------------
		
		// Xóa sản phẩm ra khỏi giỏ
		function removeFromCart(proId){
			$.ajax({
			    url: apiUrl.products.delCart + "/" + proId,
			    type: 'DELETE',
			    success: function(res) {
			    	// xóa sản phẩm trong hóa đơn
			    	bilProducts.forEach( (ele,index) => {
			    		if(ele.pbProduct.proId === proId)
			    			bilProducts.splice(index,1);
			    	});
			    	$('#cart-header'+proId).fadeOut('medium', (proId) => {
						$('#cart-header'+proId).remove();
					});
			    	// cập nhật số lượng giỏ
			    	$('#simpleCart_quantity').html(res.data);
			    	// cập nhật lại giá
			    	let price = $('#totalBillAmount').val() - $('#proTotalPrice'+proId).val();
			    	$('#tempProductPrice').html((price/1000).toFixed(3)+' đ');
			    	$('#totalProductPrice').html((price/1000).toFixed(3)+' đ');
			    	$('#totalBillAmount').val(price);
			    }
			});
		}
		
		// Xóa tất cả sản phẩm ra khỏi giỏ
		function removeAllFromCart(){
			$.ajax({
			    url: apiUrl.products.delCart,
			    type: 'DELETE',
			    success: function(res) {
			    	$('.cart-header').fadeOut('medium', () => {
						$('.cart-header').remove();
					});
			    	$('#tempProductPrice').html('0 đ');
			    	$('#totalProductPrice').html('0 đ');
			    	$('#totalBillAmount').val(0);
			    	// cập nhật số lượng giỏ
			    	$('#simpleCart_quantity').html(res.data);
			    	// xóa sản phẩm trong mảng
			    	bilProducts.length = 0;
			    }
			});
		}
		
		// khởi tạo sản phẩm trong hóa đơn
		function initProductsInBill(){
			 products.forEach(ele => {
				 pbPrice = !ele.proIsDiscount ? ele.proPrice : (ele.proPrice - ele.proPrice*ele.proDiscountRatio/100);
				 bilProducts.push({
        			 "pbProduct": {
	                        "proId": ele.proId
	                    },
	                  "pbPrice": pbPrice,
	                  "pbAmount": ele.numOfProBuyed
        		 });
        	 });
			 
			 $('p[name ="numOfProduct"]')
		}
		
		// Thực hiện thanh toán
		function submitCheckout(){
			event.preventDefault();
			if(isFullNameNull() && isPhoneNumber() && isAddressNull())
				sendCheckout();
		}
		
		// Gửi thanh toán
		function sendCheckout() {	 
			 $('body').loading({
				  stoppable: false,
				  message: 'Đang xử lý...',
			 });
			 	   
			 let bill =  {
		            "bilAmount": $('#totalBillAmount').val(),
		            "bilCus": {
		                "cusFullName": $('#cusFullName').val(),
		                "cusEmail": $('#cusEmail').val(),
		                "cusPhoneNumber": $('#cusPhoneNumber').val(),
		                "cusAddress": $('#cusAddress').val(),
		                "isActive": true
		            },
		            "bilProducts": bilProducts,
		            "bilStatus": {
		            	"bsCode":"cho-xac-nhan"
		            },
		            "isActive": true
		       }
			  
		       $.ajax({
		            url: apiUrl.bills.all,
		            type: 'post',
		            dataType: 'json',
		            contentType: 'application/json',
		            data: JSON.stringify(bill),
		            success: function (data) {
			           	$('body').loading({
			           		start: false
						});
			           	removeAllFromCart();
			      	    massage = '<strong>Đặt hàng thành công.</strong>';
			      	    massage += 'Đơn hàng sẽ được xác nhận khi quý khách nhận được tin nhắn.';
					    setCheckoutAlert('success',massage);
		            },
		            error: function (res) {
		            	console.log(res);
		           		$('body').loading({
			           		start: false
						});
		           		massage = '<strong>Đặt hàng thất bại.</strong>';
			      	    massage += JSON.parse(res.responseText).details[0];
					    setCheckoutAlert('danger',massage);
		            }
		        });
			   
		}
		
		// Kiem tra fullname
		function isFullNameNull(){
			let fullname = $('#cusFullName').val();
			let result = false;
			if(fullname === ''){
				 $( "#cusFullNameVaild" ).html( '<p style="color:red">Vui lòng điền vào đây !</p>' );
				 result = false;
			}
			else{
				 $( "#cusFullNameVaild" ).html( '<p></p>' );
				 result = true;
			}	
			 return result;
		}
		
		// Kiem tra email
		function isEmail() {
			  let email = $('#cusEmail').val();
			  let result = false;
			
			  var emailRegex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			  result = emailRegex.test(email);
			
			  if(result)
			      $( "#cusEmailVaild" ).html( '<p style="color:green">Địa chỉ email hợp lệ !</p>' );
			  else 
				  $( "#cusEmailVaild" ).html('<p style="color:red">Địa chỉ email không hợp lệ !</p>' );
			  
			  return result;
		}
		
		// Kiểm tra số điện thoại
		function isPhoneNumber() {
			 let phone = $('#cusPhoneNumber').val();
			 let result = false;
			
			 var phoneRegex = /^((\+[1-9]{1,4}[ \-]*)|(\([0-9]{2,3}\)[ \-]*)|([0-9]{2,4})[ \-]*)*?[0-9]{3,4}?[ \-]*[0-9]{3,4}?$/;
			 result = phoneRegex.test(phone);
			 
			  if(result)
			      $( "#cusPhoneVaild" ).html('<p></p>');
			  else 
				  $( "#cusPhoneVaild" ).html('<p style="color:red">Số điện thoại không hợp lệ !</p>' );
			  
			  return result;
		}
		
		// Kiem tra address
		function isAddressNull(){
			let address = $('#cusAddress').val();
			let result = false;
			if(address === ''){
				 $( "#cusAddressVaild" ).html( '<p style="color:red">Vui lòng điền vào đây !</p>' );
				 result = false;
			}
			else{
				 $( "#cusAddressVaild" ).html( '<p></p>' );
				 result = true;
			}	
			 return result;
		}
		
		// Khởi tạo notify modal
		function getNotifyModal(massage){
			console.log('da vao modal');
			$('#notifyModalBody').html(massage);
			$('#notifyModal').modal('show');
		}
		
		// Thiết lập thông báo sau khi thanh toán
		function setCheckoutAlert(type,massage){
			$('#checkoutAlert').addClass('alert alert-'+type);
			$('#checkoutAlert').html(massage);
			$('#checkoutAlert').attr('hidden',false);
			window.scrollTo(0,600); 
		}
		
	</script>
</body>
</html>