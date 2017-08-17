<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.account.amount" var="amount" />
<fmt:message bundle="${loc}" key="local.account.spentTraffic"
	var="spentTraffic" />
<fmt:message bundle="${loc}" key="local.tariff.subscription"
	var="subscription" />
<fmt:message bundle="${loc}" key="local.account.fee" var="fee" />
<fmt:message bundle="${loc}" key="local.account.bringFee" var="bringFee" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />
<fmt:message bundle="${loc}" key="local.edit.editFund" var="editFund" />
<fmt:message bundle="${loc}" key="local.account.wholeAmount" var="wAmount" />
<fmt:message bundle="${loc}" key="local.title.fee" var="subFee" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${subFee}</title>
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

				<form name="feeForm" class="form-horizontal" method="post"
					action="Controller">

					<c:set var="account" value="${requestScope.curAccount}" />
					<c:set var="aTariff" value="${requestScope.accTariff}" />
					<c:set var="spentTraficAmount" value="${requestScope.traficAmount}" />
					<c:set var="wholeAmount" value="${requestScope.wholeAmount}" />

					<div class="form-group">
						<input type="hidden" name="command" value="bring_monthly_fee" />
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${amount}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="amount"
								value="${account.getAmount()}" disabled>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${subscription}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="amount"
								value="${accTariff.getSubscriptionFee()}" disabled>
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
						<label class="control-label col-md-3 col-sm-3">${fee}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="spentTraffic"
								value="${spentTraficAmount}" disabled>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${wAmount}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="spentTraffic"
								value="${wholeAmount}" disabled>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${bringFee}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="accFee" required min="0">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
							<button type="submit" class="btn btn-primary">${editFund}</button>
							<a href="<c:url value="/Controller?command=cancel" />"
								class="btn btn-primary" role="button">${editCancel}</a> <span
								class="divider-vertical"> </span>
						</div>
					</div>

				</form>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>