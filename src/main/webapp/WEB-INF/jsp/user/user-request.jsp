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
<fmt:message bundle="${loc}" key="local.edit.editDelete"
	var="editDelete" />
<fmt:message bundle="${loc}" key="local.request.description"
	var="description" />
<fmt:message bundle="${loc}" key="local.request.regDate" var="regDate" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.edit.editSend" var="editSend" />
<fmt:message bundle="${loc}" key="local.title.request" var="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${request}</title>
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
				<c:choose>
					<c:when test="${requestScope.haveRequest}">
						<c:set var="request" value="${requestScope.curRequest}" />
						<c:set var="rTariff" value="${requestScope.reqTariff}" />

						<form name="requestForm" class="form-horizontal" method="post"
							action="Controller">

							<div class="form-group">
								<input type="hidden" name="command" value="delete_request" /> <input
									type="hidden" name="requestId" value="${request.getId()}" />
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${nameT}</label>
								<div class="col-md-9 col-sm-9">
									<input type="text" class="form-control" name="nameTariff"
										value="${rTariff.getName()}">
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${description}</label>
								<div class="col-md-9 col-sm-9">
									<textarea class="form-control" name="description" rows="3">${request.getDescription()}</textarea>
								</div>
							</div>

							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3">${regDate}</label>
								<div class="col-md-9 col-sm-9">
									<input type="date" class="form-control datepicker"
										name="regRequest" value="${request.getReqDate()}">
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-5 col-sm-5 col-md-offset-4 col-sm-offset-4">
									<button type="submit" class="btn btn-primary">${editDelete}</button>
									<a href="<c:url value="/Controller?command=cancel" />"
										class="btn btn-primary" role="button">${editCancel}</a> <span
										class="divider-vertical"> </span>
								</div>
							</div>

						</form>

					</c:when>
					<c:otherwise>
						<form name="requestForm" class="form-horizontal" method="post"
							action="Controller">

							<div class="form-group">
								<input type="hidden" name="command" value="send_request" />
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
								<label class="control-label col-md-3 col-sm-3">${description}</label>
								<div class="col-md-9 col-sm-9">
									<textarea class="form-control" name="description" rows="3" /></textarea>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-4 col-sm-4 col-md-offset-3 col-sm-offset-3">
									<button type="submit" class="btn btn-primary">${editSend}</button>
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