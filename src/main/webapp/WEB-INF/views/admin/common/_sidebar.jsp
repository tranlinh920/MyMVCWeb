<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul
	class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
	id="accordionSidebar">

	<!-- Sidebar - Brand -->
	<a
		class="sidebar-brand d-flex align-items-center justify-content-center"
		href="index.html">
		<div class="sidebar-brand-icon rotate-n-15">
			<i class="fas fa-laugh-wink"></i>
		</div>
		<div class="sidebar-brand-text mx-3">
			SB Admin <sup>2</sup>
		</div>
	</a>

	<!-- Divider -->
	<hr class="sidebar-divider">

	<!-- Nav Item - Dashboard -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseDashboard"
		aria-expanded="true" aria-controls="collapseTwo"> <i
			class="fas fa-fw fa-tachometer-alt"></i> </i> <span>Thống kê</span>
	</a>
		<div id="collapseDashboard" class="collapse"
			aria-labelledby="headingTwo" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/admin/thong-ke-luot-truy-cap">Thống
					kê lượt truy cập</a> <a class="collapse-item"
					href="${pageContext.request.contextPath}/admin/thong-ke-san-pham">Thống
					kê sản phẩm</a>
			</div>
		</div></li>

	<!-- Nav Item - Pages Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true"
		aria-controls="collapseTwo"> <i class="fas fa-fw fa-user"></i> <span>Quản
				lý người dùng</span>
	</a>
		<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
			data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item" href="buttons.html">Khách hàng</a> <a
					class="collapse-item" href="cards.html">Thành viên</a>
			</div>
		</div></li>

	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseProducts"
		aria-expanded="true" aria-controls="collapseProducts"> <i
			class="fas fa-fw fa-archive"></i> <span>Quản lý sản phẩm</span>
	</a>
		<div id="collapseProducts" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/admin/quan-ly-san-pham">Sản
					phẩm</a> <a class="collapse-item" href="utilities-border.html">Loại
					sản phẩm</a> <a class="collapse-item" href="utilities-animation.html">Sản
					phẩm liên quan</a> <a class="collapse-item" href="utilities-other.html">Sản
					phẩm bổ sung</a>
			</div>
		</div></li>

	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapseBill"
		aria-expanded="true" aria-controls="collapseBill"> <i
			class="fas fa-fw fa-file-text"></i> <span>Quản lý hóa đơn</span>
	</a>
		<div id="collapseBill" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/admin/quan-ly-hoa-don">Hóa
					đơn</a>
			</div>
		</div></li>


	<!-- Nav Item - Utilities Collapse Menu -->
	<li class="nav-item"><a class="nav-link collapsed" href="#"
		data-toggle="collapse" data-target="#collapsePage"
		aria-expanded="true" aria-controls="collapsePage"> <i
			class="fas fa-fw fa-columns"></i> <span>Quản lý nội dung</span>
	</a>
		<div id="collapsePage" class="collapse"
			aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
			<div class="bg-white py-2 collapse-inner rounded">
				<a class="collapse-item"
					href="${pageContext.request.contextPath}/admin/quan-ly-trang">Trang</a>
			</div>
		</div></li>

	<!-- Divider -->
	<hr class="sidebar-divider d-none d-md-block">

	<!-- Sidebar Toggler (Sidebar) -->
	<div class="text-center d-none d-md-inline">
		<button class="rounded-circle border-0" id="sidebarToggle"></button>
	</div>
</ul>
