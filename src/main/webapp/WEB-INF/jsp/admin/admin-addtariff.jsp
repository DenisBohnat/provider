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
<fmt:message bundle="${loc}" key="local.addtariff.enterName"
	var="enterName" />
<fmt:message bundle="${loc}" key="local.addtariff.enterRecSpeed"
	var="enterRecSpeed" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTransSpeed"
	var="enterTransSpeed" />
<fmt:message bundle="${loc}" key="local.addtariff.enterSubscription"
	var="enterSubscription" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTrafficVolume"
	var="enterTrafficVolume" />
<fmt:message bundle="${loc}" key="local.addtariff.enterOverdraft"
	var="enterOverdraft" />
<fmt:message bundle="${loc}" key="local.addtariff.enterButton"
	var="enterButton" />
<fmt:message bundle="${loc}" key="local.addtariff.enterType"
	var="enterType" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTypeLim"
	var="enterTypeLim" />
<fmt:message bundle="${loc}" key="local.addtariff.enterTypeUnlim"
	var="enterTypeUnlim" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />	
<fmt:message bundle="${loc}" key="local.title.addTariff" var="addTariff" />
<fmt:message bundle="${loc}" key="local.pageMessages.addingTariff" var="addingTariff" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${addTariff}</title>
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
	  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
		 ${errorMessage} 
	</div>
</c:if>

<h3 align="center">${addingTariff}</h3>
				<form name="addTariffForm" class="form-horizontal" method="post"
					action="Controller" onSubmit="return validateForm(event);">
					
					<div class="form-group">
						<input type="hidden" name="command" value="add_tariff" />
					</div>
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${nameT}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control"
								placeholder="${enterName}" name="nameTariff">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${enterType}</label>
						<div class="col-md-9 col-sm-9 selectContainer">
							<select class="form-control" name="tariffType">
								<option value="1">${enterTypeUnlim}</option>
								<option value="2">${enterTypeLim}</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${recSpeed}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control"
								placeholder="${enterRecSpeed}" name="recSpeed">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${transSpeed}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control"
								placeholder="${enterTransSpeed}" name="transSpeed">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${subscription}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control"
								placeholder="${enterSubscription}" name="subscription">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${trafficVolume}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control"
								placeholder="${enterTrafficVolume}" name="trafficVolume">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${overdraft}</label>
						<div class="col-md-9 col-sm-9">
							<input type="number" class="form-control"
								placeholder="${enterOverdraft}" name="overdraft">
						</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
							<button type="submit" class="btn btn-primary">${enterButton}</button>
							<a
								href="<c:url value="/Controller?command=cancel" />"
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
	
function validateForm(event)
{
	event.preventDefault();
	if(document.addTariffForm.nameTariff.value=="") {
		alert("Tariff name can not be empty");
		document.addTariffForm.nameTariff.focus();
		return false;
		}
	if(document.addTariffForm.recSpeed.value=="") {
		alert("Reception speed can not be empty");
		document.addTariffForm.recSpeed.focus();
		return false;
		}
	if(document.addTariffForm.transSpeed.value=="") {
		alert("Transmission speed can not be empty");
		document.addTariffForm.transSpeed.focus();
		return false;
		}
	if(document.addTariffForm.subscription.value=="") {
		alert("Subscription fee can not be empty");
		document.addTariffForm.subscription.focus();
		return false;
		}
	if(document.addTariffForm.trafficVolume.value=="") {
		alert("Subscription fee can not be empty");
		document.addTariffForm.trafficVolume.focus();
		return false;
		}
	if(document.addTariffForm.overdraft.value=="") {
		alert("Subscription fee can not be empty");
		document.addTariffForm.overdraft.focus();
		return false;
		}
	else {
        document.addTariffForm.submit();
    }
}
</script>	
</body>
</html>