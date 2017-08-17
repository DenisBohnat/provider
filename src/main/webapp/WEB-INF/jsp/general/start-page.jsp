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
<fmt:message bundle="${loc}" key="local.title.startPage" var="startPage" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${startPage}</title>
<link rel="stylesheet" href="css/bootstrap.min.css" >
<link rel="stylesheet" href="css/styles.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
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
 
<c:if test="${successMessage != null && !successMessage.isEmpty()}">
	<div class="alert alert-success fade in">
	  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
		 ${successMessage} 
	</div>
</c:if> 
 
 <c:choose>
	<c:when test="${sessionScope.role == 1}">
 
<h1 class="page-header">User</h1>

</c:when>
<c:otherwise>

<h1 class="page-header">Admin</h1>

</c:otherwise>
</c:choose>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>