<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />
<fmt:message bundle="${loc}" key="local.account.accNumber"
	var="accNumber" />
<fmt:message bundle="${loc}" key="local.account.amount" var="amount" />
<fmt:message bundle="${loc}" key="local.account.paymentDate"
	var="paymentDate" />
<fmt:message bundle="${loc}" key="local.account.spentTraffic"
	var="spentTraffic" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.edit.editBuy" var="editBuy" />
<fmt:message bundle="${loc}" key="local.edit.editTerminate"
	var="editTerminate" />
<fmt:message bundle="${loc}" key="local.title.account" var="account" />
<fmt:message bundle="${loc}" key="local.pageMessages.showAccount"
	var="showAccount" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${account}</title>
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
				<h4 align="center">${showAccount}</h4>
				<c:choose>
					<c:when test="${requestScope.haveAccount}">
						<c:set var="account" value="${requestScope.curAccount}" />
						<c:set var="aTariff" value="${requestScope.accTariff}" />

						<form name="accountForm" class="form-horizontal" method="post"
							action="Controller">

							<div class="form-group">
								<input type="hidden" name="command" value="terminate_account" />
								<input type="hidden" name="accId" value="${account.getId()}" />
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${nameT}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="nameTariff"
										value="${aTariff.getName()}" disabled>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${accNumber}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="accNumber"
										value="${account.getAccountNumber()}" disabled>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${amount}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="amount"
										value="${account.getAmount()}" disabled>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${paymentDate}</label>
								<div class="col-md-9 col-sm-9">
									<input type="date" class="form-control datepicker"
										name="regRequest" value="${account.getPaymentDate()}" disabled>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${spentTraffic}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="spentTraffic"
										value="${account.getSpentTraffic()}" disabled>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">${editTerminate}</button>
									<a href="<c:url value="/Controller?command=cancel" />"
										class="btn btn-primary" role="button">${editCancel}</a> <span
										class="divider-vertical"> </span>
								</div>
							</div>

						</form>

					</c:when>
					<c:otherwise>
						<form name="buyAccountForm" class="form-horizontal" method="post"
							action="Controller">

							<div class="form-group">
								<input type="hidden" name="command" value="buy_tariff" />
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${nameT}</label>
								<div class="col-md-9 col-sm-9 selectContainer">
									<select class="form-control" name="tariffType">
										<c:forEach items="${requestScope.allTariffs}" var="tariffs">
											<option value="${tariffs.getId()}"
												${tariffs.getId() == selectedTariff ? 'selected="selected"' : ''}>${tariffs.getName()}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">${editBuy}</button>
									<a href="<c:url value="/Controller?command=cancel" />"
										class="btn btn-primary" role="button">${editCancel}</a> <span
										class="divider-vertical"> </span>
								</div>
							</div>

						</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>