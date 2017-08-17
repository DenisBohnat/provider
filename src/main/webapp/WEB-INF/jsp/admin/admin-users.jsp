<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.user.users" var="users" />
<fmt:message bundle="${loc}" key="local.user.name" var="nameU" />
<fmt:message bundle="${loc}" key="local.user.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.user.login" var="login" />
<fmt:message bundle="${loc}" key="local.user.details" var="details" />
<fmt:message bundle="${loc}" key="local.tariff.action" var="action" />
<fmt:message bundle="${loc}" key="local.tariff.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.tariff.delete" var="delete" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${users}</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/styles.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/static/header.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row content ">
			<jsp:include page="/WEB-INF/jsp/static/left-sidebar.jsp"></jsp:include>
			<div class="col-md-10 col-sm-9 main content">

				<table class="table table-bordered table-responsive table-condensed">
					<thead>
						<tr>
							<th>${nameU}</th>
							<th>${surname}</th>
							<th>${login}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${usersList}" var="uList">
							<tr>
								<td>${uList.getName()}</td>
								<td>${uList.getSurname()}</td>
								<td>${uList.getLogin()}</td>
								<td><a
									href="<c:url value="/Controller?command=open_user_details_page&curUserId=${uList.getId()}" />"
									class="btn btn-default" role="button">${details}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<c:choose>
					<c:when test="${!requestScope.isAdmin}">

						<c:if test="${requestScope.pageAmount > 1}">
							<ul class="pagination">
								<c:forEach begin="1" end="${requestScope.pageAmount}" step="1"
									var="pageNumber">
									<c:choose>
										<c:when test="${pageNumber eq requestScope.curPage}">
											<li class="active"><a
												href="<c:url value="/Controller?command=show_customers&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="<c:url value="/Controller?command=show_customers&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</c:if>

					</c:when>
					<c:otherwise>

						<c:if test="${requestScope.pageAmount > 1}">
							<ul class="pagination">
								<c:forEach begin="1" end="${requestScope.pageAmount}" step="1"
									var="pageNumber">
									<c:choose>
										<c:when test="${pageNumber eq requestScope.curPage}">
											<li class="active"><a
												href="<c:url value="/Controller?command=show_admins&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="<c:url value="/Controller?command=show_admins&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</ul>
						</c:if>


					</c:otherwise>

				</c:choose>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>