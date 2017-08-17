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
<fmt:message bundle="${loc}" key="local.user.regDate" var="regDate" />
<fmt:message bundle="${loc}" key="local.user.phone" var="phone" />
<fmt:message bundle="${loc}" key="local.edit.editButton"
	var="editButton" />
<fmt:message bundle="${loc}" key="local.edit.editCancel"
	var="editCancel" />
<fmt:message bundle="${loc}" key="local.title.profile" var="profile" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${profile}</title>
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
								value="${user.getPassword()}" id="pwdNew">
						</div>
					</div>

					<div class="form-group">
						<label class="control-label col-md-3 col-sm-3">${password}</label>
						<div class="col-md-9 col-sm-9">
							<input type="password" class="form-control" name="passwordUserRep"
								value="${user.getPassword()}" id="pwdRep">
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
						<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
							<button type="submit" class="btn btn-primary">${editButton}</button>
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
	else if(document.profileForm.passwordUserRep.value=="") {
		alert("Password can not be empty");
		document.profileForm.passwordUserRep.focus();
		return false;
		}
	else if (document.profileForm.passwordUser.value != document.profileForm.passwordUserRep.value) {
	  	  alert("Passwords do not match! Try again.");
	  	  document.getElementById("pwdNew").value="";
	  	  document.getElementById("pwdRep").value="";
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