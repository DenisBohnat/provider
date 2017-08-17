<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.leftsidebar.tariffs"
	var="tariffs" />
<fmt:message bundle="${loc}" key="local.leftsidebar.showTariff"
	var="showTariff" />
<fmt:message bundle="${loc}" key="local.leftsidebar.addTariff"
	var="addTariff" />
<fmt:message bundle="${loc}" key="local.leftsidebar.users" var="users" />
<fmt:message bundle="${loc}" key="local.leftsidebar.showUsers"
	var="showUsers" />
<fmt:message bundle="${loc}" key="local.leftsidebar.addUsers"
	var="addUsers" />
<fmt:message bundle="${loc}" key="local.leftsidebar.request"
	var="request" />
<fmt:message bundle="${loc}" key="local.leftsidebar.profile"
	var="profile" />
<fmt:message bundle="${loc}" key="local.leftsidebar.writeRequest"
	var="writeRequest" />	
<fmt:message bundle="${loc}" key="local.leftsidebar.writeRequest"
	var="writeRequest" />
<fmt:message bundle="${loc}" key="local.leftsidebar.account"
	var="account" />
<fmt:message bundle="${loc}" key="local.leftsidebar.monthlyFee"
	var="monthlyFee" />	
<fmt:message bundle="${loc}" key="local.leftsidebar.nonPayers"
	var="nonPayers" />	
<fmt:message bundle="${loc}" key="local.user.customer" var="customer" />
<fmt:message bundle="${loc}" key="local.user.administrator"
	var="administrator" />

<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-pills nav-stacked">
		<c:choose>
			<c:when test="${sessionScope.role == 1}">
			<li><a href="<c:url value="/Controller?command=open_user_account_page" />">${account}</a></li>
			<c:choose>
			<c:when test="${sessionScope.haveAccount}">
			<li><a href="<c:url value="/Controller?command=open_user_request_page" />">${writeRequest}</a></li>
			<li><a href="<c:url value="/Controller?command=open_user_fee_page" />">${monthlyFee}</a></li>
			</c:when>
			</c:choose>
			<li><a href="<c:url value="/Controller?command=open_profile" />">${profile}</a></li>
			</c:when>
			<c:otherwise>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">${tariffs}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a
							href="<c:url value="/Controller?command=show_tariffs" />">${showTariff}</a></li>
						<li><a
							href="<c:url value="/Controller?command=adding_tariff" />">${addTariff}</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">${users}<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<c:url value="/Controller?command=show_customers&pageNumber=1" />">${customer}</a></li>
						<li><a href="<c:url value="/Controller?command=show_admins&pageNumber=1" />">${administrator}</a></li>
						<li><a href="<c:url value="/Controller?command=open_add_user_page" />">${addUsers}</a></li>
					</ul></li>
				<li><a href="<c:url value="/Controller?command=open_all_non_payers_page&pageNumber=1" />">${nonPayers}</a></li>	
				<li><a href="<c:url value="/Controller?command=open_all_requests_page&pageNumber=1" />">${request}</a></li>
				<li><a
					href="<c:url value="/Controller?command=open_profile" />">${profile}</a></li>
			</c:otherwise>
		</c:choose>

	</ul>
</div>