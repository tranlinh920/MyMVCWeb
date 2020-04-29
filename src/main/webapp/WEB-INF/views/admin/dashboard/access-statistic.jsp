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
						<h2 class="h3 mb-0 text-gray-800">Thống kê lượt truy cập</h2>
						<a href="#"
							class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
							class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>

					</div>

					<div class="mb-2">
						<div class="float-left">
							Ngày: <select id="date" onchange="getAccessStatistic()">
								<c:forEach var="date" items="${DateContant.DATE}">
									<c:choose>
										<c:when test="${date == currentDate}">
											<option selected value="volvo">${date}</option>
										</c:when>
										<c:otherwise>
											<option value="volvo">${date}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="float-left">
							Tháng: <select id="month" onchange="getAccessStatistic()">
								<c:forEach var="month" items="${DateContant.MONTH}">
									<c:choose>
										<c:when test="${month == currentMonth}">
											<option selected value="volvo">${month+1}</option>
										</c:when>
										<c:otherwise>
											<option value="volvo">${month+1}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="float-left">
							Năm: <select id="year" onchange="getAccessStatistic()">
								<c:forEach var="year" items="${DateContant.YEAR}">
									<c:choose>
										<c:when test="${year == currentYear}">
											<option selected value="volvo">${year}</option>
										</c:when>
										<c:otherwise>
											<option value="volvo">${year}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="clearfix"></div>
					</div>

					<!-- Content Row -->
					<div class="row">

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div id="dateAccessLabel"
												class="text-xs font-weight-bold text-primary text-uppercase mb-1">Lượt
												truy cập trong ngày ${currentDate}:</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="dateAccesses">0</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div id="weekAccessLabel"
												class="text-xs font-weight-bold text-success text-uppercase mb-1">Lượt
												truy cập trong tuần</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="weekAccesses">0</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Earnings (Monthly) Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div id="monthAccessLabel"
												class="text-xs font-weight-bold text-info text-uppercase mb-1">Lượt
												truy cập trong tháng ${currentMonth +1}:</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"
														id="monthAccesses">0</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Pending Requests Card Example -->
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div id="#yearAccessLabel"
												class="text-xs font-weight-bold text-warning text-uppercase mb-1">Lượt
												truy cập trong năm ${currentYear}:</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800"
												id="yearAccesses">0</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Content Row -->

					<div class="row">

						<!-- Area Chart -->
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">
										Lượt truy cập theo tháng trong năm: <span><select
											id="yearOfMonthChart" onchange="drawMonthAccessChart()">
												<c:forEach var="year" items="${DateContant.YEAR}">
													<c:choose>
														<c:when test="${year == currentYear}">
															<option selected value="volvo">${year}</option>
														</c:when>
														<c:otherwise>
															<option value="volvo">${year}</option>
														</c:otherwise>
													</c:choose>
												</c:forEach>
										</select></span>
									</h6>

									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div id="monthAccessChart" style="height: 320px; width: 100%;"></div>
								</div>
							</div>
						</div>

						<!-- Pie Chart -->
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Revenue
										Sources</h6>
									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-pie pt-4 pb-2">
										<canvas id="myPieChart"></canvas>
									</div>
									<div class="mt-4 text-center small">
										<span class="mr-2"> <i
											class="fas fa-circle text-primary"></i> Direct
										</span> <span class="mr-2"> <i
											class="fas fa-circle text-success"></i> Social
										</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
											Referral
										</span>
									</div>
								</div>
							</div>
						</div>
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
											<th>Ngày</th>
											<th>Lượt truy cập</th>
											<th>Hành động</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Ngày</th>
											<th>Lượt truy cập</th>
											<th>Hành động</th>
										</tr>
									</tfoot>
									<tbody id="accessTableBody">
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



					<!-- Content Row -->
					<div class="row">

						<!-- Content Column -->
						<div class="col-lg-6 mb-4">

							<!-- Project Card Example -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Projects</h6>
								</div>
								<div class="card-body">
									<h4 class="small font-weight-bold">
										Server Migration <span class="float-right">20%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-danger" role="progressbar"
											style="width: 20%" aria-valuenow="20" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										Sales Tracking <span class="float-right">40%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: 40%" aria-valuenow="40" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										Customer Database <span class="float-right">60%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar" role="progressbar"
											style="width: 60%" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										Payout Details <span class="float-right">80%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: 80%" aria-valuenow="80" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										Account Setup <span class="float-right">Complete!</span>
									</h4>
									<div class="progress">
										<div class="progress-bar bg-success" role="progressbar"
											style="width: 100%" aria-valuenow="100" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
								</div>
							</div>

							<!-- Color System -->
							<div class="row">
								<div class="col-lg-6 mb-4">
									<div class="card bg-primary text-white shadow">
										<div class="card-body">
											Primary
											<div class="text-white-50 small">#4e73df</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 mb-4">
									<div class="card bg-success text-white shadow">
										<div class="card-body">
											Success
											<div class="text-white-50 small">#1cc88a</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 mb-4">
									<div class="card bg-info text-white shadow">
										<div class="card-body">
											Info
											<div class="text-white-50 small">#36b9cc</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 mb-4">
									<div class="card bg-warning text-white shadow">
										<div class="card-body">
											Warning
											<div class="text-white-50 small">#f6c23e</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 mb-4">
									<div class="card bg-danger text-white shadow">
										<div class="card-body">
											Danger
											<div class="text-white-50 small">#e74a3b</div>
										</div>
									</div>
								</div>
								<div class="col-lg-6 mb-4">
									<div class="card bg-secondary text-white shadow">
										<div class="card-body">
											Secondary
											<div class="text-white-50 small">#858796</div>
										</div>
									</div>
								</div>
							</div>

						</div>

						<div class="col-lg-6 mb-4">

							<!-- Illustrations -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Illustrations</h6>
								</div>
								<div class="card-body">
									<div class="text-center">
										<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
											style="width: 25rem;"
											src="<c:url value="/resources/admin/img/undraw_posting_photo.svg" />"
											alt="">
									</div>
									<p>
										Add some quality, svg illustrations to your project courtesy
										of <a target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>,
										a constantly updated collection of beautiful svg images that
										you can use completely free and without attribution!
									</p>
									<a target="_blank" rel="nofollow" href="https://undraw.co/">Browse
										Illustrations on unDraw &rarr;</a>
								</div>
							</div>

							<!-- Approach -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">Development
										Approach</h6>
								</div>
								<div class="card-body">
									<p>SB Admin 2 makes extensive use of Bootstrap 4 utility
										classes in order to reduce CSS bloat and poor page
										performance. Custom CSS classes are used to create custom
										components and custom utility classes.</p>
									<p class="mb-0">Before working with this theme, you should
										become familiar with the Bootstrap framework, especially the
										utility classes.</p>
								</div>
							</div>

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
				accessStatistis: {
					all: baseUrl + 'access-statistics?sort_param=asDate&sort_type=desc',
					customize: baseUrl + 'access-statistics?get_by=customize',
					allMonth: baseUrl + 'access-statistics?get_by=all-month',
				},
			}

		getAccessStatistic();
		drawMonthAccessChart();
		initAccessData();
		setPaginationToGetData(apiUrl.accessStatistis.all);
		//------------------------------------------------------
		
		function getAccessStatistic(){
			let date = $( '#date option:selected' ).text();
			let month = $( '#month option:selected' ).text();
			let year = $( '#year option:selected' ).text();
			
			$.ajax({
				 url: apiUrl.accessStatistis.customize + '&day='+date+'&month='+month+'&year='+year,
				 async: false,
				 success: (res)=>{
					 if(res.data[0] != null){
						 
						 $('#dateAccessLabel').html('lượt truy cập trong ngày: ' + date);
						 $('#dateAccesses').html(res.data[0].dateAccesses);
						 
						 $('#weekAccessLabel').html('lượt truy cập trong tuần: ');
						 $('#weekAccesses').html(res.data[0].weekAccesses);
						 
						 $('#monthAccessLabel').html('lượt truy cập trong tháng: ' + month);
						 $('#monthAccesses').html(res.data[0].monthAccesses);
						 
						 $('#yearAccessLabel').html('lượt truy cập trong năm: ' + year);
						 $('#yearAccesses').html(res.data[0].yearAccesses);
						 
					 }else{
						alert('Không có dữ liệu cho thời gian này'); 
					 }
			    }});		
		}
		
		// tạo biểu đồ từ dữ liệu thu được
		function drawMonthAccessChart(){
			var options = {
					animationEnabled: true,  
					title:{
//						text: "Lượt truy cập theo tháng"
					},
					axisX: {
	 					valueFormatString: "##",
						prefix: "Tháng ",
						minimum: 1,
						maximum: 12,
					},
					axisY: {
						includeZero: true
					},
					data: [{
					    xValueFormatString:"Tháng ##",
				        yValueFormatString:"#### lượt",
						type: "spline",
						dataPoints: getMonthAccessData(),
					}]
				};
				$("#monthAccessChart").CanvasJSChart(options);
		}
		
		// Khởi tạo dữ liệu cho biều đồ lượt xem của tất cả tháng trong năm
		function getMonthAccessData() {
			let monthsData = [];
			let year = $( '#yearOfMonthChart option:selected' ).text();	
			$.ajax({
				 url: apiUrl.accessStatistis.allMonth + '&year='+year,
				 async: false,
				 success: (res)=>{
					 if(res.data[0] != null){
						 // reset mảng
						 monthsData.splice(0,monthsData.length)
						 // thêm dữ liệu vào màng
						 res.data[0].allMonthAccesses.forEach((views,monthIndex)=>{
							 monthsData.push({
								 x: monthIndex + 1, y: views 
							 });
						 });		
						
					 }else{
						alert('Không thể khởi tạo biểu đồ tháng !'); 
					 }
			    }});
			 return monthsData;
		}
		
		// Khởi tạo dữ liệu thống kê
		function initAccessData(page = FIRST_PAGE){
			console.log(apiUrl.accessStatistis.all + "&page=" + page);
			$.ajax({
				url: apiUrl.accessStatistis.all + "&page=" + page,
				async: false,
				success: (res)=>{
				  accessStatistics = res.data;
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
				if(ele.asDate != null){
					html += '<td>'+ele.asDate.dayOfMonth + "/";
					html += ele.asDate.month + 1 + "/";
					html += ele.asDate.year + " ";
					html += '</td>';
				}else{
					html += '<td></td>';		
				}		
 				html += '<td>'+ ele.asTime +'</td>';
				html += '<td><span class="justify-content-between">';	
				html += '<a name="action" style="color:red" href="" onclick="openAccessDelete('+ele.proId+')"><i class="ml-1 fa fa-trash-o"></i></a>';
				html += '</span></td>';
				html += '</tr>';
			});
 			$('#accessTableBody').html(html);
		}
		
	</script>

</body>

</html>
