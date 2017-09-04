<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.user.name" var="nameU" />
<fmt:message bundle="${loc}" key="local.user.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.request.regDate" var="regDate" />
<fmt:message bundle="${loc}" key="local.edit.editDelete"
	var="editDelete" />
<fmt:message bundle="${loc}" key="local.edit.editChange"
	var="editChange" />
<fmt:message bundle="${loc}" key="local.user.details" var="details" />
<fmt:message bundle="${loc}" key="local.title.requests" var="requests" />
<fmt:message bundle="${loc}" key="local.pageMessages.showRequests" var="showRequests" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${requests}</title>
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

<h4 align="center">${showRequests}</h4>
				<table class="table table-bordered table-responsive table-condensed">
					<thead>
						<tr>
							<th>${nameU}</th>
							<th>${surname}</th>
							<th>${nameT}</th>
							<th>${regDate}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="uRequest" items="${requestScope.uRequests}"
							varStatus="iterator">
							<c:set var="userName"
								value="${requestScope.uNames[iterator.index]}" />
							<c:set var="userSurname"
								value="${requestScope.uSurnames[iterator.index]}" />
							<c:set var="tariffName"
								value="${requestScope.tNames[iterator.index]}" />
							<tr>

								<td>${userName}</td>
								<td>${userSurname}</td>
								<td>${tariffName}</td>
								<td>${uRequest.getReqDate()}</td>
								<td>
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle" type="button"
											data-toggle="dropdown">
											${action} <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
											<li><a
												href="<c:url value="/Controller?command=open_user_details_page&curUserId=${uRequest.getUserId()}" />">${details}</a></li>
											<li><a href="<c:url value="/Controller?command=change_tariff_plan&userId=${uRequest.getUserId()}&tariffId=${uRequest.getTariffId()}&requestId=${uRequest.getId()}" />">${editChange}</a></li>
											<li><a
												href="<c:url value="/Controller?command=delete_request&requestId=${uRequest.getId()}" />">${editDelete}</a></li>
										</ul>
									</div>
								</td>

							</tr>

						</c:forEach>
					</tbody>
				</table>

				<c:if test="${requestScope.pageAmount > 1}">
					<ul class="pagination">
						<c:forEach begin="1" end="${requestScope.pageAmount}" step="1"
							var="pageNumber">
							<c:choose>
								<c:when test="${pageNumber eq requestScope.curPage}">
									<li class="active"><a
										href="<c:url value="/Controller?command=open_all_requests_page&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="<c:url value="/Controller?command=open_all_requests_page&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</c:if>

			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>