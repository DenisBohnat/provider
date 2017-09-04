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
<fmt:message bundle="${loc}" key="local.account.amount" var="amount" />
<fmt:message bundle="${loc}" key="local.account.accNumber"
	var="accNumber" />
<fmt:message bundle="${loc}" key="local.user.details" var="details" />	
<fmt:message bundle="${loc}" key="local.edit.editBan"
	var="editBan" />
<fmt:message bundle="${loc}" key="local.title.nonPayers" var="nonPayers" />
<fmt:message bundle="${loc}" key="local.pageMessages.showNonpayers" var="showNonpayers" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${nonPayers}</title>
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
<h4 align="center">${showNonpayers}</h4>

<table class="table table-bordered table-responsive table-condensed">
					<thead>
						<tr>
							<th>${nameU}</th>
							<th>${surname}</th>
							<th>${amount}</th>
							<th>${accNumber}</th>
						</tr>
					</thead>
<tbody>
<c:forEach var="defAccount" items="${requestScope.defAccounts}" varStatus="iterator">
<c:set var="userName" value="${requestScope.uNames[iterator.index]}" />
<c:set var="userSurname" value="${requestScope.uSurnames[iterator.index]}" />
<tr>

								<td>${userName}</td>
								<td>${userSurname}</td>
								<td>${defAccount.getAmount()}</td>
								<td>${defAccount.getAccountNumber()}</td>
<td>
									<div class="dropdown">
										<button class="btn btn-default dropdown-toggle" type="button"
											data-toggle="dropdown">
											${action} <span class="caret"></span>
										</button>
										<ul class="dropdown-menu">
										<li><a
												href="<c:url value="/Controller?command=open_user_details_page&curUserId=${defAccount.getUserId()}" />">${details}</a></li>
											<li><a
												href="<c:url value="/Controller?command=ban_user&accId=${defAccount.getId()}" />">${editBan}</a></li>
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
												href="<c:url value="/Controller?command=open_all_non_payers_page&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="<c:url value="/Controller?command=open_all_non_payers_page&pageNumber=${pageNumber}" />">${pageNumber}</a></li>
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