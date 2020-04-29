<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.my.contant.DateContant"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Dashboard</title>

<!-- Header declare -->
<%@ include file="/WEB-INF/views/admin/common/_header-declare.jsp"%>

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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h2 class="h3 mb-0 text-gray-800">Thống kê sản phẩm</h2>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>

					</div>

					<div class="mb-2">
						<div class="float-left">
							Ngày: <select id="date" onchange="getBuyingStatisticsData()">
								<option value="0">Tất cả</option>
								<c:forEach var="date" items="${DateContant.DATE}">
									<option value="${date}">${date}</option>
								</c:forEach>
							</select>
						</div>
						<div class="float-left">
							Tháng: <select id="month" onchange="getBuyingStatisticsData()">
								<option value="0">Tất cả</option>
								<c:forEach var="month" items="${DateContant.MONTH}">
									<c:choose>
										<c:when test="${month == currentMonth}">
											<option selected value="${month+1}">${month+1}</option>
										</c:when>
										<c:otherwise>
											<option value="${month+1}">${month+1}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="float-left">
							Năm: <select id="year" onchange="getBuyingStatisticsData()">
								<c:forEach var="year" items="${DateContant.YEAR}">
									<c:choose>
										<c:when test="${year == currentYear}">
											<option selected value="${year}">${year}</option>
										</c:when>
										<c:otherwise>
											<option value="${year}">${year}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<div class="row">
								<div class="col-12 col-md-8">
									<h6 class="m-0 font-weight-bold text-primary">Danh sách
										truy cập</h6>
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
											<th>Tên sản phâm</th>
											<th>Lượt mua</th>
											<th>Tổng số lượng</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>ID</th>
											<th>Tên sản phâm</th>
											<th>Lượt mua</th>
											<th>Tổng số lượng</th>
										</tr>
									</tfoot>
									<tbody id="buyingStatisticTable">
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

	<script
		src="${pageContext.request.contextPath}/resources/plugin/canvasjs/canvasjs.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugin/canvasjs/jquery.canvasjs.min.js"></script>

	<!-- common javascript -->
	<%@ include file="/WEB-INF/views/_common-javascript.jsp"%>

	<script>
	
		const FIRST_PAGE = 1;
		let startPage ;
		let totalPages;
		let visiblePages;
		let currentPage = 1;// default
		let accessStatistics = [];	
		
		apiUrl = {
				productBill: {
					buyStatistics: baseUrl + '/product-bills/buying-statistics',
				},
			}
		

		let maxBuyTimesValue = 0;
		let maxBuyAmountValue = 0;


		let date = $( '#date option:selected' ).val();
		let month = $( '#month option:selected' ).val();
		let year = $( '#year option:selected' ).val();

		initBuyingStatisticsData();
		setPaginationToGetData(apiUrl.productBill.buyStatistics + '?day='+date+'&month='+month+'&year='+year);
		//------------------------------------------------------
		
			// Khởi tạo dữ liệu thống kê
		function initBuyingStatisticsData(page = FIRST_PAGE){
			
			date = $( '#date option:selected' ).val();
			month = $( '#month option:selected' ).val();
			year = $( '#year option:selected' ).val();
			
			let pagUrl = apiUrl.productBill.buyStatistics + '?day='+date+'&month='+month+'&year='+year + "&page=" + page;
			
			$.ajax({
				url: pagUrl,
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
							$.get( url + '&page='+page,
									(res, status) => {
										setPagingInfo(res);
										renderHtml(res.data);
									}
							);
						}
			});
		}
		
		function getBuyingStatisticsData(){
			initBuyingStatisticsData();	
			$('#pagination-demo').twbsPagination('destroy');
			setPaginationToGetData(apiUrl.productBill.buyStatistics + '?day='+date+'&month='+month+'&year='+year);
		}
		
		// Tạo thông tin để phân trang
		function setPagingInfo(res){
			totalPages = res.paging.totalPages;
			visiblePages = res.paging.visiblePages;
			startPage = res.paging.page;
		}
		
		// Tạo bảng từ dữ liệu 
		function renderHtml(data){
			let html = "";
			data.forEach(ele => { 
				maxBuyTimesValue = maxBuyTimesValue < ele.buyTimes ? ele.buyTimes:maxBuyTimesValue;
				maxBuyAmountValue = maxBuyAmountValue < ele.buyAmount ? ele.buyAmount:maxBuyAmountValue;
				let buyTimesRatio = ele.buyTimes*100/maxBuyTimesValue;
				let buyAmountRatio = ele.buyAmount*100/maxBuyAmountValue;
				html += '<tr>';	
 				html += '<td>'+ ele.proId +'</td>';
 				html += '<td>'+ ele.proName +'</td>';
 				html += '<td style="width: 25%;">';
 				html += '	<div class="progress" style="height: 20px;">';
 				html += ' 		<div class="progress-bar bg-success role="progressbar" aria-valuenow="70"  aria-valuemin="0" aria-valuemax="100" style="width:'+buyTimesRatio+'%">' + ele.buyTimes + '</div>';
 				html += '	<div>';
 				html += '</td>';
 				html += '<td style="width: 25%;">';
 				html += '	<div class="progress" style="height: 20px;">';
 				html += ' 		<div class="progress-bar bg-warning" role="progressbar" aria-valuenow="70"  aria-valuemin="0" aria-valuemax="100" style="width:'+buyAmountRatio+'%">' + ele.buyAmount + '</div>';
 				html += '	<div>';
 				html += '</td>';
				html += '</tr>';
			});
 			$('#buyingStatisticTable').html(html);
		}
		
	</script>

</body>

</html>
