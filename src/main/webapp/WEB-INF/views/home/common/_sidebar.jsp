<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- start sidebar -->
<div class="col-md-3 s-d">
	<div class="w_sidebar">
		<c:if test="${not empty sideBars}">
			<c:forEach var="sideBar" items="${sideBars}">
				<section class="sky-form">
					<h4>${sideBar.catName}</h4>
					<div class="row1 scroll-pane">
						<div class="col col-4">
							<c:if test="${not empty sideBar.catCategories}">
								<c:forEach var="item" items="${sideBar.catCategories}">
									<label> <a href="${item.catLink}"> ${item.catName}</a>
									</label>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</section>
			</c:forEach>
		</c:if>
	</div>
</div>