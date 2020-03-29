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

<title>Quản lý hóa đơn</title>

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
					<h1 class="h5 mb-2 text-gray-800" id="aaa">Quản lý hóa đơn</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="row">
								<div class="col-12 col-md-8">
									<h6 class="m-0 font-weight-bold text-primary">Danh sách
										hóa đơn</h6>
								</div>
								<div class="col col-md-4">
									<!-- 									Button trigger modal -->
									<!-- 									<button type="button" class="btn btn-success float-right" -->
									<!-- 										onClick="openProductAdding()" data-target="#addModal">Thêm</button> -->
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
											<th>ID</th>
											<th>Tên khách hàng</th>
											<th>Loại</th>
											<th>Ngày mua</th>
											<th>Giá trị</th>
											<th>Trạng thái</th>
											<th>Hành động</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Tên khách hàng</th>
											<th>Loại</th>
											<th>Ngày mua</th>
											<th>Giá trị</th>
											<th>Trạng thái</th>
											<th>Hành động</th>
										</tr>
									</tfoot>
									<tbody id="billTableBody">
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


	<!-- The Bill details modal -->
	<div class="modal fade" id="billDetailsModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<h5 class="modal-title">Chi tiết đơn hàng</h5>
					<button type="button" class="fa fa-clone"
						onclick="copyBillContentToClipboard()"></button>
				</div>

				<!-- Modal body -->
				<div class="modal-body" id="billDetailsModalBody">
					<!-- data here -->
				</div>

				<!-- Modal footer -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
				</div>

			</div>
		</div>
	</div>

	<script>
		const FIRST_PAGE = 1;
		let startPage ;
		let totalPages;
		let visiblePages;
		let currentPage = 1;// default
		let bills = [];
		
		let baseUrl = 'http://localhost:8080/';	
		apiUrl = {
			bills: {
				all: baseUrl + 'bills',
				status: baseUrl + 'bills/status'
			},
			billStatuses : {
				all: baseUrl + 'bill-statuses'
			}
		}
		
		initBillsData();
		setPaginationToGetData(apiUrl.bills.all);

		// -------------------------------------
		
		// Khởi tạo dữ liệu sản phẩm (product)
		function initBillsData(page = FIRST_PAGE){
			$.ajax({
				url: apiUrl.bills.all + "?page=" + page,
				async: false,
				success: (res)=>{
				  bills = res.data;
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
				html += '<td>'+ele.bilId+'</td>';
				html += '<td>'+(ele.bilUser != undefined ? ele.bilUser.userFullName:ele.bilCus.cusFullName)+'</td>';
				html += '<td>'+(ele.bilUser != undefined ? "Thành viên":"Khách")+'</td>';
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
  				html += '<td>'+ (ele.bilAmount/1000).toFixed(3)+' đ' +'</td>';
 				html += '<td id="billStatusName'+ele.bilId+'" style="color:red">'+ ele.bilStatus.bsName +'</td>';
				html += '<td><span class="justify-content-between">';	
				html += '<a name="action" href="#" onclick="viewBill('+ele.bilId+')"><i class="ml-1 fa fa-eye text-success"></i></a>';
				html += '<a name="action" href="#" onclick="initBillStatusData('+ele.bilId+','+ele.bilStatus.bsId+')"><i class="fa fa-pencil-square-o .text-primary ml-1"></i></a>';	
				html += '<a name="action" style="color:red" href="#" onclick="openProductDelete('+ele.proId+')"><i class="ml-1 fa fa-trash-o"></i></a>';
				html += '</span></td>';
				html += '</tr>';
			});
 			$('#billTableBody').html(html);
		}
		
		// Khởi tạo dữ liệu trạng thái đơn hàng (product type)
		function initBillStatusData(bilId, billStatusId){
			 $.ajax({
				 url: apiUrl.billStatuses.all,
				 async: false,
				 success: (res)=>{
					 console.log(res);
					 html = '<select id="inputBillStatus'+billStatusId+'">';
					 res.data.forEach(ele => {
							html += ele.bsId === billStatusId ? ('<option value="'+ele.bsCode+'" selected>'):('<option value="'+ele.bsCode+'">');
							html += ele.bsName;
							html += '</option>';
						});		 
					 html += '</select>';
					 html += '<a href="#" onclick="updateBillStatus('+bilId+','+billStatusId+')"><i class="ml-1 fa fa-check-square-o text-success"></i></a>';
					 $('#billStatusName'+bilId).html(html);
					 $('button').prop('disabled', true);
			    }});
			
		}
		
		// Cập nhật trang thái đơn hàng
		function updateBillStatus(bilId, billStatusId){
			let statusCode = $('#inputBillStatus'+billStatusId).val();
			let bill = {
				"bilStatus": {
			        "bsCode":statusCode
			     }
			}
			$.ajax({
				 url: apiUrl.bills.status + "/" + bilId,
		         type: 'put',
		         dataType: 'json',
		         contentType: 'application/json',
		         data: JSON.stringify(bill),
		         success: (res) => {
			       $('#billStatusName'+bilId).html(res.data.bsName);
			       $('button').prop('disabled', false);
		         },
		         error: (res) => {
		        	 console.log(res);
		        	 location.reload();
		         }
		     });
		}
		
		// Xem đơn hàng
		function viewBill(bilId) {
			let bill =  getBillById(bilId);
			let html = '<h5>Futuxe.com kính chào quý khách</h5>';
			html += '<p>----------------------------</p>';
			if(bill.bilUser != undefined){
				html += '<p>Khách Hàng <strong> '+ bill.bilUser.userFullName + '</strong></p>';
				html += '<p>Số điện thoại <strong> '+ bill.bilUser.userPhoneNumber +'</strong></p>';
			}
			if(bill.bilCus != undefined){
				html += '<p>Khách Hàng <strong> '+ bill.bilCus.cusFullName + '</strong></p>';
				html += '<p>Số điện thoại <strong> '+ bill.bilCus.cusPhoneNumber +'</strong></p>';
			}
			html += '<p>Đặt hàng ngày: <strong>'+getBillDate(bill.createdDate)+'</strong></p>';
			html += '<p>Trạng thái: <strong style="color: red"> '+bill.bilStatus.bsName+'</strong></p>';
			html += '<p>----------------------------</p>';
			bill.bilProducts.forEach(ele => {
				html += '<p>Tên hàng:<strong> '+ele.pbProduct.proName+'</strong></p>';
				html += '<p>Số lượng:<strong> '+ele.pbAmount+'</strong></p>';
				html += '<p>Giá:<strong> '+ele.pbPrice+'</strong></p>';
			});
			html += '<p>_________</p>';
			html += '<p>TỔNG: <strong> '+bill.bilAmount+' đ</strong></p>';
			html += '<p>----------------------------</p>';
			html += '<p>Địa chỉ: <strong>'+'abvc'+'</strong></p>';
			$('#billDetailsModalBody').html(html);
			$('#billDetailsModal').modal();
		}
		
		// Lấy thông tin đơn hàng
		function getBillById(bilId) {
			let bill;
			bills.forEach(ele => {
				if(ele.bilId === bilId){
					bill = ele;
				}
			});
			return bill;
		}
		
		function getBillDate(createdDate){
			html = '';
			if(createdDate != null){
				html += createdDate.dayOfMonth + "/";
				html += createdDate.month + 1 + "/";
				html += createdDate.year + " ";
				html += createdDate.hourOfDay + "h";
				html += createdDate.minute + "p";
			}else{
				html += '';		
			}
			return html;
		}
		
		function copyBillContentToClipboard() {
			 /* Get the text field */
			  let copyText = $("#billDetailsModalBody").html(); 
			 
			  var tempDom = $('<output>').append($.parseHTML(copyText));
			  console.log(tempDom);
			 
			  let el = document.createElement('textarea');
			  el.value = copyText;
			  document.body.appendChild(el);
			  el.select();
			  document.execCommand('copy');
			  document.body.removeChild(el);
		}
				
	</script>

</body>

</html>
