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
<fmt:message bundle="${loc}" key="local.header.tariff" var="tariff" />
<fmt:message bundle="${loc}" key="local.header.liTariff" var="liTariff" />
<fmt:message bundle="${loc}" key="local.header.unTariff" var="unTariff" />
<fmt:message bundle="${loc}" key="local.tariff.action" var="action" />
<fmt:message bundle="${loc}" key="local.tariff.edit" var="edit" />
<fmt:message bundle="${loc}" key="local.tariff.delete" var="delete" />
<fmt:message bundle="${loc}" key="local.title.tariffs" var="tariffs" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${tariffs}</title>
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
	  <a href="#" class="close" data-dismiss="alert" aria-label="close"> &times;</a>
		 ${successMessage} 
	</div>
</c:if> 

				<ul class="nav nav-tabs nav-justified">
					<li class="active"><a data-toggle="tab" href="#unTariff">${unTariff}</a></li>
					<li><a data-toggle="tab" href="#liTariff">${liTariff}</a></li>
				</ul>

				<div class="tab-content">
					<div id="unTariff" class="tab-pane fade in active">

						<table
							class="table table-bordered table-responsive table-condensed">
							<thead>
								<tr>
									<th>${nameT}</th>
									<th>${recSpeed}</th>
									<th>${transSpeed}</th>
									<th>${subscription}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${unlimTariffs}" var="uTariffs">
									<tr>
										<td>${uTariffs.getName()}</td>
										<td>${uTariffs.getRecSpeed()}</td>
										<td>${uTariffs.getTransSpeed()}</td>
										<td>${uTariffs.getSubscriptionFee()}</td>
										<td>
											<div class="dropdown">
												<button class="btn btn-default dropdown-toggle"
													type="button" data-toggle="dropdown">
													${action} <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li><a
														href="<c:url value="/Controller?command=editing_tariff&tariffId=${uTariffs.getId()}" />">${edit}</a></li>
													<li><a
														href="<c:url value="/Controller?command=delete_tariff&tariffId=${uTariffs.getId()}" />">${delete}</a></li>
												</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<div id="liTariff" class="tab-pane fade">

						<table
							class="table table-bordered table-responsive table-condensed">
							<thead>
								<tr>
									<th>${nameT}</th>
									<th>${recSpeed}</th>
									<th>${transSpeed}</th>
									<th>${subscription}</th>
									<th>${trafficVolume}</th>
									<th>${overdraft}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${limTariffs}" var="liTariffs">
									<tr>
										<td>${liTariffs.getName()}</td>
										<td>${liTariffs.getRecSpeed()}</td>
										<td>${liTariffs.getTransSpeed()}</td>
										<td>${liTariffs.getSubscriptionFee()}</td>
										<td>${liTariffs.getTrafficVolume()}</td>
										<td>${liTariffs.getOverdraftAmount()}</td>
										<td>
											<div class="dropdown">
												<button class="btn btn-default dropdown-toggle"
													type="button" data-toggle="dropdown">
													${action} <span class="caret"></span>
												</button>
												<ul class="dropdown-menu">
													<li><a
														href="<c:url value="/Controller?command=editing_tariff&tariffId=${liTariffs.getId()}" />">${edit}</a></li>
													<li><a
														href="<c:url value="/Controller?command=delete_tariff&tariffId=${liTariffs.getId()}" />">${delete}</a></li>
												</ul>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>