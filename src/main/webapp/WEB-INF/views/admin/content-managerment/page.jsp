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

<title>Quản lý trang</title>

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
					<h1 class="h5 mb-2 text-gray-800" id="aaa">Quản lý trang</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="row">
								<div class="col-12 col-md-8">
									<h6 class="m-0 font-weight-bold text-primary">Danh sách
										trang</h6>
								</div>
								<div class="col col-md-4"></div>
							</div>
							<div class="row mt-1">
								<div class="col-12 col-md-8"></div>
								<div class="col col-md-4">
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-success float-right"
										onClick="openPageAdding()" data-target="#addModal">Thêm</button>
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
											<th>Tên trang</th>
											<th>Code trang</th>
											<th>Hành động</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Tên trang</th>
											<th>Code trang</th>
											<th>Hành động</th>
										</tr>
									</tfoot>
									<tbody id="pageTableBody">
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
	<div class="modal fade" id="addModal" role="dialog"
		aria-labelledby="addModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">
				<form id="productForm" accept-charset="UTF-8">
					<div class="modal-header">
						<h5 class="modal-title" id="addModalLabel">Thêm trang</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group row">
							<!-- Tên -->
							<label for="inputPageName" class="col-sm-2 col-form-label">Tên:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputPageName"
									name="pageName" placeholder="Tên trang...">
							</div>
							<!-- Loại -->
							<label for="inputPageCode" class="col-sm-2 col-form-label">Code:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="inputPageCode"
									name="pageCode" placeholder="Code trang...">
							</div>
							<!-- Loại -->
							<label for="inputPageContent" class="col-sm-2 col-form-label">Nội
								dung:</label>
							<div class="col-sm-10">
								<textarea rows="" cols="" id="inputPageContent"></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">Đóng</button>
						<button type="submit" class="btn btn-primary" id="pageSubmit">Lưu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
		apiUrl = {
			pages: {
				all: baseUrl + 'pages',
			},
		}
		
		initPageData();
		setCkeditor();

		// -------------------------------------
	
		// Khởi tạo dữ liệu 
		function initPageData(){
			$.ajax({
				url: apiUrl.pages.all,
				async: false,
				success: (res)=>{
				  renderHtml(res.data);
			}});	
		}
				
		// Tạo bảng từ dữ liệu 
		function renderHtml(data){
			let html = "";
			data.forEach(ele => { 
				html += '<tr>';
				html += '<td>'+ele.pagName+'</td>';
				html += '<td>'+ele.pagCode+'</td>';				
				html += '<td><span class="justify-content-between">';	
				html += '<a href="#" onclick="showImage('+ele.pagId+')"><i class="ml-1 fa fa-picture-o text-success"></i></a>';
				html += '<a href="#" onclick="openPageEditing('+ele.pagId+')"><i class="fa fa-pencil-square-o .text-primary ml-1"></i></a>';	
				html += '<a style="color:red" href="#" onclick="openPageDelete('+ele.pagId+')"><i class="ml-1 fa fa-trash-o"></i></a>';
				html += '</span></td>';
				html += '</tr>';
			});
 			$('#pageTableBody').html(html);
		}
		
		// Mở modal thêm 
		function openPageAdding(){
			 $('#addModal').modal();
			 $('#pageSubmit').attr('onClick','submitPage()');
		}
		
		// Mở modal sửa 
		function openPageEditing(pagId){
			 $('#addModal').modal();
			 
			 $.ajax({
				 url: apiUrl.pages.all + '/' + pagId,
				 async: false,
				 success: (res)=>{
					  $('#inputPageName').val(res.data.pagName);
			          $('#inputPageCode').val(res.data.pagCode);
			          CKEDITOR.instances['inputPageContent'].setData(res.data.pagContent);
			     }});
			 
			 $('#pageSubmit').attr('onClick','submitPage('+pagId+')');
		}
		
		// Gửi dữ liệu lên server cho chức năng thêm-sửa
		function submitPage(pagId = null){
	        event.preventDefault();
	        let page = {
	        	pagName : $('#inputPageName').val(),
	        	pagCode : $('#inputPageCode').val(),
	        	pagContent : CKEDITOR.instances['inputPageContent'].getData(),
	        	isActive: true,
	        }
	        console.log(page);
			let url = apiUrl.pages.all;
	        let type = '';
			
			if(pagId == null){
				// post
				type = 'post';
			}else {
				// put
				url +=  '/' + pagId; 
				type = 'put';
			}		
			
			$.ajax({
				 url: url,
				 type: type,
				 headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json' 
				 },
				 async: false,
				 dataType:"json",
				 data: JSON.stringify(page),
				 success: (res)=>{
					 $("#pageSubmit").prop("disabled", false);
					 $('#addModal').modal('toggle');
					 $.ajax({
						 url: apiUrl.pages.all,
						 async: false,
						 success: (res)=>{
							 renderHtml(res.data);
					    }});
					 	clearModal();
	 
			      },
			      error: (res)=>{
			    	$("#pageSubmit").prop("disabled", false);
			    	console.log(res);
			    	alert('Them that bai: ' + res.responseText);
			      }
			   });
		}
		
		// Mở modal xóa
		function openPageDelete(pagId){
			$('#confirmModal').modal('show'); 
			$('#confirmSubmit').attr('onClick','deletePage('+pagId+')');
		}
		
		// Xóa
		function deletePage(pagId){
			delUrl = apiUrl.pages.all + '/' + pagId;
		    $.ajax({ url: delUrl, method: "DELETE" })
	            .then((data) => {
	            	initPageData();
	            })
	            .catch((err) => {
	            	 alter(err);
	            });
			$('#confirmModal').modal('toggle'); 
		}
		
		// Xóa dữ liệu bên trong modal khi thoát
		function clearModal(){
			 $('#addModal').on('hidden.bs.modal', function(){
				$('#inputPageName').val('');
		        $('#inputPageCode').val('');
		        CKEDITOR.instances['inputPageContent'].setData('');
			 });
		}
		
		// tạo Ckeditor
		function setCkeditor(){
			CKEDITOR.replace( 'inputPageContent', {});
		};
				
	</script>

</body>

</html>
