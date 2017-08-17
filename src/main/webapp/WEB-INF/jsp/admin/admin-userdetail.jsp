<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty sessionScope.language ? sessionScope.language : 'en' }"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.user.detail" var="detail" />
<fmt:message bundle="${loc}" key="local.user.account" var="account" />
<fmt:message bundle="${loc}" key="local.user.request" var="request" />
<fmt:message bundle="${loc}" key="local.user.name" var="nameU" />
<fmt:message bundle="${loc}" key="local.user.surname" var="surname" />
<fmt:message bundle="${loc}" key="local.user.login" var="login" />
<fmt:message bundle="${loc}" key="local.user.password" var="password" />
<fmt:message bundle="${loc}" key="local.user.email" var="email" />
<fmt:message bundle="${loc}" key="local.user.birthDate" var="birthDate" />
<fmt:message bundle="${loc}" key="local.user.regDate" var="regDate" />
<fmt:message bundle="${loc}" key="local.user.phone" var="phone" />
<fmt:message bundle="${loc}" key="local.account.accNumber"
	var="accNumber" />
<fmt:message bundle="${loc}" key="local.account.amount" var="amount" />
<fmt:message bundle="${loc}" key="local.account.paymentDate"
	var="paymentDate" />
<fmt:message bundle="${loc}" key="local.account.spentTraffic"
	var="spentTraffic" />
<fmt:message bundle="${loc}" key="local.edit.editButton"
	var="editButton" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />
<fmt:message bundle="${loc}" key="local.edit.editDelete"
	var="editDelete" />
<fmt:message bundle="${loc}" key="local.edit.editChange"
	var="editChange" />
<fmt:message bundle="${loc}" key="local.request.description"
	var="description" />
<fmt:message bundle="${loc}" key="local.request.regDate" var="regDate" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.request.noRequest"
	var="noRequest" />
<fmt:message bundle="${loc}" key="local.account.noAccount"
	var="noAccount" />
<fmt:message bundle="${loc}" key="local.edit.editBan" var="editBan" />
<fmt:message bundle="${loc}" key="local.title.userDetails"
	var="userDetails" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${userDetails}</title>
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

				<c:if test="${successMessage != null && !successMessage.isEmpty()}">
					<div class="alert alert-success fade in">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">
							&times;</a> ${successMessage}
					</div>
				</c:if>

				<c:choose>
					<c:when test="${requestScope.curUserRole == 1}">

						<ul class="nav nav-tabs nav-justified">
							<li class="active"><a data-toggle="tab" href="#detail">${detail}</a></li>
							<li><a data-toggle="tab" href="#account">${account}</a></li>
							<li><a data-toggle="tab" href="#request">${request}</a></li>
						</ul>

						<div class="tab-content">
							<div id="detail" class="tab-pane fade in active">
								<c:set var="user" value="${requestScope.curUser}" />

								<form name="profileForm" class="form-horizontal" method="post"
									action="Controller" onSubmit="return validateForm(event);">

									<div class="form-group">
										<input type="hidden" name="command" value="edit_profile" /> <input
											type="hidden" name="roleUser" value="${user.getRole()}" /> <input
											type="hidden" name="userId" value="${user.getId()}" />
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${nameU}</label>
										<div class="col-md-9 col-sm-9">
											<input type="text" class="form-control" name="nameUser"
												value="${user.getName()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${surname}</label>
										<div class="col-md-9 col-sm-9">
											<input type="text" class="form-control" name="surnameUser"
												value="${user.getSurname()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${login}</label>
										<div class="col-md-9 col-sm-9">
											<input type="text" class="form-control" name="loginUser"
												value="${user.getLogin()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${password}</label>
										<div class="col-md-9 col-sm-9">
											<input type="password" class="form-control"
												name="passwordUser" value="${user.getPassword()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${email}</label>
										<div class="col-md-9 col-sm-9">
											<input type="email" class="form-control" name="emailUser"
												value="${user.geteMail()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${birthDate}</label>
										<div class="col-md-9 col-sm-9">
											<input type="date" class="form-control datepicker"
												name="birthDateUser" value="${user.getBirthDate()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${regDate}</label>
										<div class="col-md-9 col-sm-9">
											<input type="date" class="form-control datepicker"
												name="regDateUser" value="${user.getRegDate()}">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3">${phone}</label>
										<div class="col-md-9 col-sm-9">
											<input type="text" class="form-control" name="phoneUser"
												value="${user.getPhone()}">
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-5 col-sm-5 col-md-offset-4 col-sm-offset-4">
											<button type="submit" class="btn btn-primary">${editButton}</button>
											<a href="<c:url value="/Controller?command=cancel" />"
												class="btn btn-primary" role="button">${editCancel}</a> <span
												class="divider-vertical"> </span>
										</div>
									</div>

								</form>


							</div>

							<div id="account" class="tab-pane fade">
								<c:choose>
									<c:when test="${requestScope.haveAccount}">
										<c:set var="account" value="${requestScope.curAccount}" />
										<c:set var="aTariff" value="${requestScope.accTariff}" />

										<form name="accountForm" class="form-horizontal" method="post"
											action="Controller">

											<div class="form-group">
												<input type="hidden" name="command" value="ban_user" /> <input
													type="hidden" name="accId" value="${account.getId()}" />
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
													<input type="number" class="form-control" name="accNumber"
														value="${account.getAmount()}" disabled>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-3 col-sm-3">${paymentDate}</label>
												<div class="col-md-9 col-sm-9">
													<input type="date" class="form-control datepicker"
														name="regRequest" value="${account.getPaymentDate()}"
														disabled>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-3 col-sm-3">${spentTraffic}</label>
												<div class="col-md-9 col-sm-9">
													<input type="number" class="form-control" name="accNumber"
														value="${account.getSpentTraffic()}" disabled>
												</div>
											</div>

											<div class="form-group">
												<div
													class="col-md-5 col-sm-5 col-md-offset-4 col-sm-offset-4">
													<button type="submit" class="btn btn-primary">${editBan}</button>
													<a href="<c:url value="/Controller?command=cancel" />"
														class="btn btn-primary" role="button">${editCancel}</a> <span
														class="divider-vertical"> </span>
												</div>
											</div>

										</form>
									</c:when>
									<c:otherwise>
										<div class="container-fluid text-center">
											<div class="row content">
												<div class="col-sm-12 text-left">
													<h3>${noAccount}</h3>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>

							<div id="request" class="tab-pane fade">

								<c:choose>
									<c:when test="${requestScope.haveRequest}">
										<c:set var="request" value="${requestScope.curRequest}" />
										<c:set var="rTariff" value="${requestScope.reqTariff}" />

										<form name="requestForm" class="form-horizontal" method="post"
											action="Controller">

											<div class="form-group">
												<input type="hidden" name="command"
													value="change_tariff_plan" /> <input type="hidden"
													name="tariffId" value="${rTariff.getId()}" />
											</div>

											<div class="form-group">
												<label class="control-label col-md-3 col-sm-3">${nameT}</label>
												<div class="col-md-9 col-sm-9">
													<input type="text" class="form-control" name="nameTariff"
														value="${rTariff.getName()}" disabled>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-3 col-sm-3">${description}</label>
												<div class="col-md-9 col-sm-9">
													<textarea class="form-control" name="description" rows="3"
														disabled>${request.getDescription()}</textarea>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label col-md-3 col-sm-3">${regDate}</label>
												<div class="col-md-9 col-sm-9">
													<input type="date" class="form-control datepicker"
														name="regRequest" value="${request.getReqDate()}" disabled>
												</div>
											</div>

											<div class="form-group">
												<div
													class="col-md-5 col-sm-5 col-md-offset-4 col-sm-offset-4">
													<button type="submit" class="btn btn-primary">${editChange}</button>
													<a
														href="<c:url value="/Controller?command=delete_request&requestId=${request.getId()}" />"
														class="btn btn-primary" role="button">${editDelete}</a> <a
														href="<c:url value="/Controller?command=cancel" />"
														class="btn btn-primary" role="button">${editCancel}</a> <span
														class="divider-vertical"> </span>
												</div>
											</div>

										</form>
									</c:when>
									<c:otherwise>
										<div class="container-fluid text-center">
											<div class="row content">
												<div class="col-sm-12 text-left">
													<h3>${noRequest}</h3>
												</div>
											</div>
										</div>
									</c:otherwise>

								</c:choose>

							</div>

						</div>
					</c:when>
					<c:otherwise>
						<c:set var="user" value="${requestScope.curUser}" />

						<form name="profileForm" class="form-horizontal" method="post"
							action="Controller" onSubmit="return validateForm(event);">

							<div class="form-group">
								<input type="hidden" name="command" value="edit_profile" /> <input
									type="hidden" name="roleUser" value="${user.getRole()}" /> <input
									type="hidden" name="userId" value="${user.getId()}" />
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${nameU}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="nameUser"
										value="${user.getName()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${surname}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="surnameUser"
										value="${user.getSurname()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${login}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="loginUser"
										value="${user.getLogin()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${password}</label>
								<div class="col-md-9 col-sm-9">
									<input type="password" class="form-control" name="passwordUser"
										value="${user.getPassword()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${email}</label>
								<div class="col-md-9 col-sm-9">
									<input type="email" class="form-control" name="emailUser"
										value="${user.geteMail()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${birthDate}</label>
								<div class="col-md-9 col-sm-9">
									<input type="date" class="form-control datepicker"
										name="birthDateUser" value="${user.getBirthDate()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${regDate}</label>
								<div class="col-md-9 col-sm-9">
									<input type="date" class="form-control datepicker"
										name="regDateUser" value="${user.getRegDate()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${phone}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="phoneUser"
										value="${user.getPhone()}">
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-5 col-sm-5 col-md-offset-4 col-sm-offset-4">
									<button type="submit" class="btn btn-primary">${editButton}</button>
									<a
										href="<c:url value="/Controller?command=delete_user&userId=${user.getId()}" />"
										class="btn btn-primary" role="button">${editDelete}</a> <a
										href="<c:url value="/Controller?command=cancel" />"
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
	
<script type="text/javascript">	
	
function validateForm(event)
{
	event.preventDefault();
	if(document.profileForm.nameUser.value=="") {
		alert("User name can not be empty");
		document.profileForm.nameUser.focus();
		return false;
		}
	else if(document.profileForm.surnameUser.value=="") {
		alert("User surname can not be empty");
		document.profileForm.surnameUser.focus();
		return false;
		}
	else if(document.profileForm.loginUser.value=="") {
		alert("Login can not be empty");
		document.profileForm.loginUser.focus();
		return false;
		}
	else if(document.profileForm.passwordUser.value=="") {
		alert("Password can not be empty");
		document.profileForm.passwordUser.focus();
		return false;
		}	
	else if(document.profileForm.emailUser.value=="") {
		alert("E-mail can not be empty");
		document.profileForm.emailUser.focus();
		return false;
		}
	else if(document.profileForm.birthDateUser.value=="") {
		alert("Birth date can not be empty");
		document.profileForm.birthDateUser.focus();
		return false;
		}
	else if(document.profileForm.regDateUser.value=="") {
		alert("Birth date can not be empty");
		document.profileForm.regDateUser.focus();
		return false;
		}
	else if(document.profileForm.phoneUser.value=="") {
		alert("Phone can not be empty");
		document.profileForm.phoneUser.focus();
		return false;
		}
	else {
        document.profileForm.submit();
    }
	}
</script>
	
</body>
</html>