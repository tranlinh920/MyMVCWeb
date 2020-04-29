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

<style>
.my-custom-scrollbar {
	position: relative;
	height: 300px;
	overflow: auto;
}

.my-table-wrapper-scroll-y {
	display: block;
}

.my-vertical-menu {
	background-color: #eee;
	border-top-style: solid;
	height: 70px;
	overflow-y: auto;
}

.my-vertical-menu a {
	background-color: #eee;
	color: black;
	display: block;
	padding: 12px;
	text-decoration: none;
}
</style>

<!-- Header declare -->
<%@ include file="/WEB-INF/views/admin/common/_header-declare.jsp"%>

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="/WEB-INF/views/admin/common/_sidebar.jsp"%>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@ include file="/WEB-INF/views/admin/common/_topbar.jsp"%>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h5 mb-2 text-gray-800">Quản lý sản phẩm</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="row">
								<div class="col-12 col-md-8">
									<h6 class="m-0 font-weight-bold text-primary">Danh sách
										sản phẩm</h6>
								</div>
								<div class="col col-md-4"></div>
							</div>
							<div class="row mt-1">
								<div class="col-12 col-md-7">
									<label>Loại sản phẩm: </label> <select id="productTypeFilter"
										onchange="getByProductType()" name="proTypeName">
										<!-- data is here -->
									</select>
								</div>
								<div class="col-10 col-md-4">
									<div class="input-group md-form form-sm form-2 pl-0">
										<input class="form-control my-0 py-1 amber-border" type="text"
											placeholder="Nhập tên sản phẩm..." aria-label="Search"
											id="proSearchStr">
										<button class="btn btn-light input-group-append"
											onclick="searchProduct()">
											<i class="fas fa-search text-secondary" aria-hidden="true"></i></span>
										</button>
									</div>
								</div>
								<div class="col-2 col-md-1">
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-success float-right"
										onClick="openProductAdding()" data-target="#addModal">Thêm</button>
								</div>
							</div>
						</div>
						<div class="card-body">
							<div
								class="table-responsive my-table-wrapper-scroll-y my-custom-scrollbar">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Tên sản phẩm</th>
											<th>Loại</th>
											<th>Thương hiệu</th>
											<th>Xuất sứ</th>
											<th>Giá</th>
											<th>Số lượng</th>
											<th>Ngày thêm</th>
											<th>Hành động</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Tên sản phẩm</th>
											<th>Loại</th>
											<th>Thương hiệu</th>
											<th>Xuất sứ</th>
											<th>Giá</th>
											<th>Số lượng</th>
											<th>Ngày thêm</th>
											<th>Hành động</th>
										</tr>
									</tfoot>
									<tbody id="productTableBody">
										<!-- render at here -->
									</tbody>
								</table>
							</div>
						</div>
						<div class="card-footer py-3">
							<ul id="pagination-demo"
								class="pagination pagination-sm  float-right"></ul>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<%@ include file="/WEB-INF/views/admin/common/_footer.jsp"%>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Footer declare -->
	<%@ include file="/WEB-INF/views/admin/common/_footer-declare.jsp"%>

	<!-- common modal declare -->
	<%@ include file="/WEB-INF/views/admin/common/_modal.jsp"%>

	<!-- Add Modal -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<form id="productForm" accept-charset="UTF-8">
					<div class="modal-header">
						<h5 class="modal-title" id="addModalLabel">Thêm sản phẩm</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group row">
							<!-- Tên -->
							<label for="inputProductName" class="col-sm-2 col-form-label">Tên:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputProductName"
									name="proName" placeholder="Tên sản phẩm...">
							</div>
							<!-- Loại -->
							<label for="inputProductType" class="col-sm-2 col-form-label">Loại:</label>
							<div class="col-sm-2">
								<select class="form-control" id="inputProductType"
									name="proTypeCode">
									<!-- data is here -->
								</select>
							</div>
							<!-- Thương hiệu -->
							<label for="inputProductBrand" class="col-sm-2 col-form-label">Thương
								hiệu:</label>
							<div class="col-sm-2">
								<select class="form-control" id="inputProductBrand"
									name="proBrandName">
									<!-- data is here -->
								</select>
							</div>
							<!-- Xuất sứ -->
							<label for="inputProductOrigin" class="col-sm-2 col-form-label">Xuất
								sứ:</label>
							<div class="col-sm-2">
								<select class="form-control" id="inputProductOrigin"
									name="proOriginName">
									<!-- data is here -->
								</select>
							</div>
							<!-- Giá -->
							<label for="inputProductPrice" class="col-sm-2 col-form-label">Giá:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="inputProductPrice"
									name="proPrice" placeholder="Giá..." required>
							</div>
							<!-- Số lượng -->
							<label for="inputProductAmount" class="col-sm-2 col-form-label">Số
								lượng:</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" id="inputProductAmount"
									placeholder="Số lượng..." name="proAmount" required>
							</div>
							<!-- Giảm giá -->
							<label for="" class="col-sm-2 col-form-label">Giảm giá:</label>
							<div class="col-sm-2">
								<input class="mt-3" type="checkbox" id="proIsDiscount"
									name="proIsDiscount">Có
							</div>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="proDiscountRatio"
									placeholder="% Giảm..." name="proDiscountRatio">
							</div>
							<!-- Khan hiếm -->
							<label for="" class="col-sm-2 col-form-label">Khan hiếm:</label>
							<div class="col-sm-4">
								<input class="mt-3" type="checkbox" name="proIsScarcity"
									id="proIsScarcity">Có
							</div>
							<!-- Mô tả -->
							<label for="inputProductDescription"
								class="col-sm-2 col-form-label">Mô tả:</label>
							<div class="col-sm-10">
								<textarea rows="" cols="" id="inputProductDescription"></textarea>
							</div>
							<!-- Hình ảnh -->
							<label for="inputProductDescription"
								class="col-sm-2 col-form-label">Hình ảnh:</label>
							<div class="col-sm-10">
								<input type="file" id="files" name="proFiles" multiple />
							</div>
							<!-- Hiển thị tên hình -->
							<input type="hidden" id="BeforeListProductImage"></input>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary" id="productSubmit">Lưu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Image Modal -->
	<div class="modal fade" id="imageModal" tabindex="-1" role="dialog"
		aria-labelledby="imageModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="imageModalLabel">Hình ảnh</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="imageModalBody">
					<!-- data is here  -->
				</div>
			</div>
		</div>
	</div>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
		let getUrl = baseUrl + 'products?';
		let getOrderByDateUrl = baseUrl + 'products?sort_param=createdDate&sort_type=desc&';
		let imagesUrl =  baseUrl + 'resources/images/';
	
		const FIRST_PAGE = 1;
		let startPage ;
		let totalPages;
		let visiblePages;
		let currentPage = 1;// default
		let data;
		
		initProductTypesData();
		initProductTypesFilterData();
		initProductsData();
		setPaginationToGetData(getUrl);
		setCkeditor();
		setDiscountInModal();

		// -------------------------------------
		
		// Khởi tạo dữ liệu kiểu sản phẩm (product type)
		function initProductTypesData(proTypeName = null){
			 $.ajax({
				 url: baseUrl + "product-types",
				 async: true,
				 success: (res)=>{
					 html = '';
					 res.data.forEach(ele => {
							html += ele.proTypeName === proTypeName ? '<option selected value="'+ele.proTypeCode+'">':'<option value="'+ele.proTypeCode+'">';
							html += ele.proTypeName;
							html += '</option>';
						});		 
					 $('#inputProductType').html(html);
			    }});
			
		}
		
		// Khởi tạo dữ liệu bộ lọc kiểu sản phẩm 
		function initProductTypesFilterData(proTypeName = null){
			 $.ajax({
				 url: baseUrl + "product-types",
				 async: true,
				 success: (res)=>{
					 html = '<option value="tat-ca">Tất cả</option>';
					 res.data.forEach(ele => {
							html += '<option value="'+ele.proTypeCode+'">';
							html += ele.proTypeName;
							html += '</option>';
						});		 
					 $('#productTypeFilter').html(html);
			    }});
			
		}
		
		// Lấy dữ liệu sản phẩm theo kiểu
		function getByProductType(){
			let proTypeCode = $( '#productTypeFilter').val();	
			if(proTypeCode === 'tat-ca'){
				initProductsData();
				setPaginationToGetData(getUrl);
			}else{
				let pagUrl = baseUrl + 'products/product-type/'+proTypeCode+'?limit=10&';
				$.ajax({
					url: pagUrl + 'page=' + currentPage,
					async: false,
					success: (res)=>{
					setPagingInfo(res);
					renderHtml(res.data);
					 $('#pagination-demo').twbsPagination('destroy');
					 setPaginationToGetData(pagUrl,currentPage);
				}});	
			}	
		}
		
		// Khởi tạo dữ liệu sản phẩm (product)
		function initProductsData(page = FIRST_PAGE){
			$.ajax({
				url: baseUrl + "products?page=" + page,
				async: false,
				success: (res)=>{
				setPagingInfo(res);
				renderHtml(res.data);
			}});	
		}
		
		//Tạo chức năng phân trang cho dữ liệu
		function setPaginationToGetData(url, page = startPage){
			$('#pagination-demo').twbsPagination(
					{
						totalPages:totalPages,
						visiblePages:visiblePages,
						startPage :page,
						initiateStartPageClick:false,
						onPageClick : (event, page) => {
							currentPage = page;
							$.get( url + 'page='+page,
									(res, status) => {
										setPagingInfo(res);
										renderHtml(res.data);
									}
							);
						}
			});
		}
		
		// Tạo thông tin để phân trang
		function setPagingInfo(res){
			totalPages = res.paging.totalPages;
			visiblePages = res.paging.visiblePages;
			startPage = res.paging.page;
		}
		
		// Tạo bảng từ dữ liệu sản phẩm
		function renderHtml(data){
			let html = "";
			data.forEach(ele => { 
				html += '<tr>';
				html += '<td>'+ele.proName+'</td>';
				html += '<td>'+ele.proType.proTypeName+'</td>';
  				html += '<td>'+ (ele.proBrand != undefined ? ele.proBrand.proBrandName:"") +'</td>';
 				html += '<td>'+ (ele.proOrigin != undefined ? ele.proOrigin.proOriginName:"") +'</td>';
				html += '<td>'+ele.proPrice+'</td>';
				html += '<td>'+ele.proAmount+'</td>';			
				if(ele.createdDate != null){
					html += '<td>'+ele.createdDate.dayOfMonth + "/";
					html += ele.createdDate.month + 1 + "/";
					html += ele.createdDate.year + " ";
					html += ele.createdDate.hourOfDay + ":";
					html += ele.createdDate.minute + "p";
					html += '</td>';
				}else{
					html += '<td></td>';		
				}		
				html += '<td><span class="justify-content-between">';	
				html += '<a href="#" onclick="showImage('+ele.proId+')"><i class="ml-1 fa fa-picture-o text-success"></i></a>';
				html += '<a href="#" onclick="openProductEditing('+ele.proId+')"><i class="fa fa-pencil-square-o .text-primary ml-1"></i></a>';	
				html += '<a style="color:red" href="#" onclick="openProductDelete('+ele.proId+')"><i class="ml-1 fa fa-trash-o"></i></a>';
				html += '</span></td>';
				html += '</tr>';
			});
 			$('#productTableBody').html(html);
		}
		
		// tạo Ckeditor
		function setCkeditor(){
			CKEDITOR.replace( 'inputProductDescription', {
// 				toolbar: [
// 					[ 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink' ],
// 					[ 'FontSize', 'TextColor' ]
// 				]
				 allowedContent: true
			});
			CKEDITOR.editorConfig = function( config ) {
// 			    config.toolbar_Full =  [
// 			        ['Styles', 'Bold', 'Italic', 'Underline', 'SpellChecker', 'Scayt', '-', 'NumberedList', 'BulletedList'],
// 			        ['Link', 'Unlink'], ['Undo', 'Redo', '-', 'SelectAll'], '/', ['timestamp', '-', 'linkbutton']
// 			    ];
			};
		};
		
		// Mở modal hiển thị ảnh sản phẩm
		function showImage(proid){
			$('#imageModal').modal('show'); 
			
			html = '<ul class="list-group list-group-horizontal">';
			$.ajax({
				 url: baseUrl + "products/images/" + proid,
				 async: false,
				 success: (res)=>{
					 res.data.forEach(ele => {   
						 url = imagesUrl + "products/" + ele.proImageName;
						 html += '<li class="list-group-item"><img style="width: 200px" src="'+ url +'" alt="loading..." class="img-thumbnail"></li>';
					 });
			    }});
			 html += '</ul>';
			 
			 $('#imageModalBody').html(html);
		}
		
		// Bật-tắt chức năng giảm giá sản phẩm
		function setDiscountInModal(state = true){
			let isDiscount = state;
			$('#proIsDiscount').change((event) => {
				isDiscount = !isDiscount;
				$("#proDiscountRatio").prop('disabled', isDiscount);
			});	
		}
		
		// Mở modal thêm sản phẩm
		function openProductAdding(){
			 $('#addModal').modal();
			 $('#productSubmit').attr('onClick','submitProduct()');
			 $("#proDiscountRatio").prop('disabled', true);
			 setDiscountInModal();
		}
		
		// Mở modal sửa sản phẩm. 
		function openProductEditing(proId){
			 $('#addModal').modal();
			 $('#productSubmit').attr('onClick','submitProduct('+proId+')');
			 $("#proDiscountRatio").prop('disabled', true);
			 $.ajax({
				 url: baseUrl + 'products/' + proId,
				 async: false,
				 success: (res)=>{
					 $('#inputProductName').val(res.data.proName);
					 $('#inputProductPrice').val(res.data.proPrice);
					 $('#inputProductAmount').val(res.data.proAmount);
					 if(res.data.proIsDiscount){
						 $('#proIsDiscount').prop('checked', true);
						 $('#proDiscountRatio').prop('disabled', false);
					 }
					 $('#proDiscountRatio').val(res.data.proDiscountRatio);
					 if(res.data.proIsScarcity)
						 $('#proIsScarcity').prop('checked', true);
					 CKEDITOR.instances['inputProductDescription'].setData(res.data.proDescribe);
					 getImageList(res.data);
					 initProductTypesData(res.data.proType.proTypeName);
			    }});
			 
			 clearModal();
		}
	
		// Gửi dữ liệu lên server cho chức năng thêm-sửa
		function submitProduct(proId = null){
	        event.preventDefault();
	        			
			let postUrl = '';
			let getUrl = '';
			let pagUrl = '';  
			let page = '';
			
			if(proId == null){
				// post 
				postUrl = baseUrl + "products"; 
				getUrl =  getOrderByDateUrl + 'page=' + FIRST_PAGE;
				pagUrl = getOrderByDateUrl;
				page = FIRST_PAGE;
			}else{
				// put
				postUrl = baseUrl + "products/" + proId; 
				getUrl =  baseUrl + "products?page=" + currentPage;
				pagUrl = baseUrl + "products?";
				page = currentPage;
			}		

			let form = $('#productForm')[0];
			let formData = new FormData(form);
			formData.append('proDescribe', CKEDITOR.instances['inputProductDescription'].getData());
			$("#productSubmit").prop("disabled", true);
			
			$.ajax({
				 url: postUrl,
				 type: 'post',
				 enctype: 'multipart/form-data',
//				 headers: { 
//					'Accept': 'application/json',
//					 'Content-Type': 'application/json' 
//				 },
				 async: false,
//				 dataType:"json",
				 processData: false,
				 contentType: false,
				 data: formData,
				 success: (res)=>{
					 $('#productForm')[0].reset();
					 CKEDITOR.instances['inputProductDescription'].setData("");
					 $("#productSubmit").prop("disabled", false);
					 $('#addModal').modal('toggle');
					 $.ajax({
						 url: getUrl,
						 async: false,
						 success: (res)=>{
							 setPagingInfo(res);
							 renderHtml(res.data);
							 $('#pagination-demo').twbsPagination('destroy');
							 setPaginationToGetData(pagUrl,page);
					    }});
	 
			      },
			      error: (res)=>{
			    	$("#productSubmit").prop("disabled", false);
			    	console.log(res);
			    	alert('Them that bai: ' + res.responseText);
			      }
			   });
		}
			
		// Hiển thị hình ảnh bên trong modal ở chức năng sửa
		function getImageList(data){
			picUrl = imagesUrl + 'other/pic-frame.png';
			html = '<div name="listProductImage" class="col-lg-2"></div>';
			html += '<div name="listProductImage" class="col-lg-5">';
			html += '<ul class="my-vertical-menu">';
			data.proImages.forEach(ele => {
				imageName = ele.proImageName;
				html += '<li style="cursor: pointer" onClick = "setImageByName('+ '\'' + imageName + '\'' +')">';
				html += '<input name="proImages" type="checkbox" value="'+ele.proImageId+'">';
				html += ele.proImageName + '</li>';
			});
			html += '</ul>';
			html += '</div>';
			html += '<div name="listProductImage"  class="col-lg-3"><p style="color: red">* Chọn tệp để thêm hình</p><p style="color: red">* Tích vào đê xóa hình</p></div>'
			html += '<div name="listProductImage"  class="col-lg-2">';
			html += '<img style="width:300px" id="editImage" class="img-thumbnail" alt="loading..." data-action="zoom" src="'+picUrl+'">';
			html += '</div>';
			$('#BeforeListProductImage').after(html);
		}
		
		// Khởi tạo hình ảnh từ tên của nó
		function setImageByName(imgName){
			 url = imagesUrl + "products/" + imgName;
			 $('#editImage').attr("src",url);
		}
		
		// Xóa dữ liệu bên trong modal khi thoát
		function clearModal(){
			 $('#addModal').on('hidden.bs.modal', function(){
				 $('#productForm')[0].reset();
				 $('div[name ="listProductImage"]').remove();
				 initProductTypesData();
			 });
		}
		
		// Mở modal xóa sản phẩm
		function openProductDelete(proId){
			$('#confirmModal').modal('show'); 
			$('#confirmSubmit').attr('onClick','deleteProduct('+proId+')');
		}
		
		// Xóa sản phẩm
		function deleteProduct(proId){
			console.log('da vao: '+ proId);
			delUrl = baseUrl + 'products/' + proId;
		    $.ajax({ url: delUrl, method: "DELETE" })
	            .then((data) => {
	            	initProductsData(currentPage);
	            })
	            .catch((err) => {
	            	 alter(err);
	            });
			$('#confirmModal').modal('toggle'); 
		}
		
		// Tìm kiếm sản phẩm
		function searchProduct(){
			let searchStr = $('#proSearchStr').val();
			let pagUrl = baseUrl + "products/search?p="+searchStr;
			$.ajax({
				url: pagUrl+"&page=" + FIRST_PAGE,
				async: false,
				success: (res)=>{
				setPagingInfo(res);
				renderHtml(res.data);
				 $('#pagination-demo').twbsPagination('destroy');
				 setPaginationToGetData(pagUrl,currentPage);
			}});	
		}
				
	</script>

</body>

</html>
