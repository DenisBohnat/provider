<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : 'en' }" scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="local" var="loc" />
<fmt:message bundle="${loc}" key="local.tariff.name" var="nameT" />
<fmt:message bundle="${loc}" key="local.tariff.recSpeed" var="recSpeed" />
<fmt:message bundle="${loc}" key="local.tariff.transSpeed" var="transSpeed" />
<fmt:message bundle="${loc}" key="local.tariff.subscription" var="subscription" />
<fmt:message bundle="${loc}" key="local.tariff.trafficVolume" var="trafficVolume" />
<fmt:message bundle="${loc}" key="local.tariff.overdraft" var="overdraft" />
<fmt:message bundle="${loc}" key="local.header.tariff" var="tariff" />
<fmt:message bundle="${loc}" key="local.header.liTariff" var="liTariff" />
<fmt:message bundle="${loc}" key="local.title.limTariffs" var="limT" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <title>${limT}</title>
<link rel="stylesheet" href="css/bootstrap.min.css" >
<link rel="stylesheet" href="css/styles.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/static/header.jsp"></jsp:include>

<div class="container">
  <h3>${liTariff}</h3>           
  <table class="table table-bordered table-responsive table-condensed">
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
			</tr>
		</c:forEach>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/jsp/static/footer.jsp"></jsp:include>
</body>
</html>