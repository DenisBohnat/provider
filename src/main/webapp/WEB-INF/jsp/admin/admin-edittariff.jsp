<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.tariff.recSpeed" var="recSpeed" />
<fmt:message bundle="${loc}" key="local.tariff.transSpeed"
	var="transSpeed" />
<fmt:message bundle="${loc}" key="local.tariff.subscription"
	var="subscription" />
<fmt:message bundle="${loc}" key="local.tariff.trafficVolume"
	var="trafficVolume" />
<fmt:message bundle="${loc}" key="local.tariff.overdraft"
	var="overdraft" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTypeLim"
	var="enterTypeLim" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTypeUnlim"
	var="enterTypeUnlim" />
<fmt:message bundle="${loc}" key="local.edit.editButton"
	var="editButton" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />
<fmt:message bundle="${loc}" key="local.title.editTariff"
	var="editTariff" />
<fmt:message bundle="${loc}" key="local.pageMessages.editingTariff"
	var="editingTariff" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${editTariff}</title>
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

				<c:if test="${errorMessage != null && !errorMessage.isEmpty()}">
					<div class="alert alert-danger fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">
							&times;</a> ${errorMessage}
					</div>
				</c:if>

				<h4 align="center">${editingTariff}</h4>
				<c:set var="tariff" value="${requestScope.curTariff}" />
				<form name="editTariffForm" class="form-horizontal" method="post"
					action="Controller" onSubmit="return validateForm(event);">

					<div class="form-group">
						<input type="hidden" name="command" value="edit_tariff" /> <input
							type="hidden" name="tariffType" value="${tariff.getType()}" /> <input
							type="hidden" name="tariffId" value="${tariff.getId()}" />
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${nameT}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control" name="nameTariff"
								value="${tariff.getName()}">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${recSpeed}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="recSpeed"
								value="${tariff.getRecSpeed()}" step="0.1">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${transSpeed}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="transSpeed"
								value="${tariff.getTransSpeed()}" step="0.1">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${subscription}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control" name="subscription"
								value="${tariff.getSubscriptionFee()}" step="0.1">
						</div>
					</div>

					<c:choose>
						<c:when test="${tariff.getType()==1}">

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${trafficVolume}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="trafficVolume"
										value="0" readonly>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${overdraft}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="overdraft"
										value="0" readonly>
								</div>
							</div>

						</c:when>
						
						<c:otherwise>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${trafficVolume}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="trafficVolume"
										value="${tariff.getTrafficVolume()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${overdraft}</label>
								<div class="col-md-9 col-sm-9">
									<input type="number" class="form-control" name="overdraft"
										value="${tariff.getOverdraftAmount()}" step="0.1">
								</div>
							</div>

						</c:otherwise>
					</c:choose>

					<div class="form-group">
						<div class="col-md-3 col-sm-3 col-md-offset-2 col-sm-offset-2">
							<button type="submit" class="btn btn-primary">${editButton}</button>
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

	<script type="text/javascript">
		function validateForm(event) {
			event.preventDefault();
			if (document.editTariffForm.nameTariff.value == "") {
				alert("Tariff name can not be empty");
				document.editTariffForm.nameTariff.focus();
				return false;
			}
			if (document.editTariffForm.recSpeed.value == "") {
				alert("Reception speed can not be empty");
				document.editTariffForm.recSpeed.focus();
				return false;
			}
			if (document.editTariffForm.transSpeed.value == "") {
				alert("Transmission speed can not be empty");
				document.editTariffForm.transSpeed.focus();
				return false;
			}
			if (document.editTariffForm.subscription.value == "") {
				alert("Subscription fee can not be empty");
				document.editTariffForm.subscription.focus();
				return false;
			}
			if (document.editTariffForm.trafficVolume.value == "") {
				alert("Subscription fee can not be empty");
				document.editTariffForm.trafficVolume.focus();
				return false;
			}
			if (document.editTariffForm.overdraft.value == "") {
				alert("Subscription fee can not be empty");
				document.editTariffForm.overdraft.focus();
				return false;
			} else {
				document.editTariffForm.submit();
			}
		}
	</script>

</body>
</html>