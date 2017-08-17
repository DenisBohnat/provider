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
<fmt:message bundle="${loc}" key="local.user.login" var="login" />
<fmt:message bundle="${loc}" key="local.user.password" var="password" />
<fmt:message bundle="${loc}" key="local.user.email" var="email" />
<fmt:message bundle="${loc}" key="local.user.birthDate" var="birthDate" />
<fmt:message bundle="${loc}" key="local.user.phone" var="phone" />
<fmt:message bundle="${loc}" key="local.user.role" var="role" />
<fmt:message bundle="${loc}" key="local.user.customer" var="customer" />
<fmt:message bundle="${loc}" key="local.user.administrator"
	var="administrator" />
<fmt:message bundle="${loc}" key="local.adduser.enterName"
	var="enterName" />
<fmt:message bundle="${loc}" key="local.adduser.enterSurname"
	var="enterSurname" />
<fmt:message bundle="${loc}" key="local.adduser.enterLogin"
	var="enterLogin" />
<fmt:message bundle="${loc}" key="local.adduser.enterPassword"
	var="enterPassword" />
<fmt:message bundle="${loc}" key="local.adduser.enterEmail"
	var="enterEmail" />
<fmt:message bundle="${loc}" key="local.adduser.enterBirthDate"
	var="enterBirthDate" />
<fmt:message bundle="${loc}" key="local.adduser.enterPhone"
	var="enterPhone" />
<fmt:message bundle="${loc}" key="local.adduser.enterAddButton"
	var="enterAddButton" />
<fmt:message bundle="${loc}" key="local.adduser.enterCancelButon"
	var="enterCancelButon" />
<fmt:message bundle="${loc}" key="local.title.addUser" var="addUser" />
<fmt:message bundle="${loc}" key="local.pageMessages.addingUser" var="addingUser" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${addUser}</title>
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

  <h3 align="center">${addingUser}</h3> 
				<form name="addUserForm" class="form-horizontal" method="post"
					action="Controller" onSubmit="return validateForm(event);">

					<div class="form-group">
						<input type="hidden" name="command" value="add_user" />
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${nameU}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control"
								placeholder="${enterName}" name="nameUser">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${surname}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control"
								placeholder="${enterSurname}" name="surnameUser">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${role}</label>
						<div class="col-md-9 col-sm-9 selectContainer">
							<select class="form-control" name="roleUser">
								<option value="1">${customer}</option>
								<option value="2">${administrator}</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${login}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control"
								placeholder="${enterLogin}" name="loginUser">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${password}</label>
						<div class="col-md-9 col-sm-9">
							<input type="password" class="form-control"
								placeholder="${enterPassword}" name="passwordUser" id="pwdNew">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${password}</label>
						<div class="col-md-9 col-sm-9">
							<input type="password" class="form-control"
								placeholder="${enterPassword}" name="passwordUserRep" id="pwdRep">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${email}</label>
						<div class="col-md-9 col-sm-9">
							<input type="email" class="form-control"
								placeholder="${enterEmail}" name="emailUser">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${birthDate}</label>
						<div class="col-md-9 col-sm-9">
							<input type="date" class="form-control datepicker"
								placeholder="${enterBirthDate}" name="birthDateUser">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${phone}</label>
						<div class="col-md-9 col-sm-9">
							<input type="text" class="form-control"
								placeholder="${enterPhone}" name="phoneUser">
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
							<button type="submit" class="btn btn-primary">${enterAddButton}</button>
							<a href="<c:url value="/Controller?command=cancel" />"
								class="btn btn-primary" role="button">${enterCancelButon}</a> <span
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
	if(document.addUserForm.nameUser.value=="") {
		alert("User name can not be empty");
		document.addUserForm.nameUser.focus();
		return false;
		}
	else if(document.addUserForm.surnameUser.value=="") {
		alert("User surname can not be empty");
		document.addUserForm.surnameUser.focus();
		return false;
		}
	else if(document.addUserForm.loginUser.value=="") {
		alert("Login can not be empty");
		document.addUserForm.loginUser.focus();
		return false;
		}
	else if(document.addUserForm.passwordUser.value=="") {
		alert("Password can not be empty");
		document.addUserForm.passwordUser.focus();
		return false;
		}
	else if(document.addUserForm.passwordUserRep.value=="") {
		alert("Password can not be empty");
		document.addUserForm.passwordUserRep.focus();
		return false;
		}
	else if (document.addUserForm.passwordUser.value != document.addUserForm.passwordUserRep.value) {
	  	  alert("Passwords do not match! Try again.");
	  	  document.getElementById("pwdNew").value="";
	  	  document.getElementById("pwdRep").value="";
	  	  document.addUserForm.passwordUser.focus();
	  	  return false;
	    }
	else if(document.addUserForm.emailUser.value=="") {
		alert("E-mail can not be empty");
		document.addUserForm.emailUser.focus();
		return false;
		}
	else if(document.addUserForm.birthDateUser.value=="") {
		alert("Birth date can not be empty");
		document.addUserForm.birthDateUser.focus();
		return false;
		}
	else if(document.addUserForm.phoneUser.value=="") {
		alert("Phone can not be empty");
		document.addUserForm.phoneUser.focus();
		return false;
		}
	else {
        document.addUserForm.submit();
    }
	}
</script>	
	
</body>
</html>