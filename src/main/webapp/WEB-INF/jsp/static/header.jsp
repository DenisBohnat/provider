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
<fmt:message bundle="${loc}" key="local.header.home" var="home" />
<fmt:message bundle="${loc}" key="local.header.tariff" var="tariff" />
<fmt:message bundle="${loc}" key="local.header.unTariff" var="unTariff" />
<fmt:message bundle="${loc}" key="local.header.liTariff" var="liTariff" />
<fmt:message bundle="${loc}" key="local.header.aboutUs" var="aboutUs" />
<fmt:message bundle="${loc}" key="local.header.login" var="login" />
<fmt:message bundle="${loc}" key="local.header.logout" var="logout" />
<fmt:message bundle="${loc}" key="local.header.password" var="password" />
<fmt:message bundle="${loc}" key="local.header.signIn" var="signIn" />
<fmt:message bundle="${loc}" key="local.header.signUp" var="signUp" />
<fmt:message bundle="${loc}" key="local.header.ruLanguage" var="ru" />
<fmt:message bundle="${loc}" key="local.header.enLanguage" var="en" />

<nav class="navbar navbar-inverse">
<div class="container-fluid">
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target="#myNavbar">
			<span class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="#">ByNet</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
		<ul class="nav navbar-nav">
			<li class="active"><a
				href="<c:url value="/Controller?command=home" />">${home}</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">${tariff} <span class="caret"></span></a>
				<ul class="dropdown-menu" role="menu">
					<li><a
						href="<c:url value="/Controller?command=limited_tariffs" />">${liTariff}</a></li>
					<li><a
						href="<c:url value="/Controller?command=unlimited_tariffs" />">${unTariff}</a></li>
				</ul></li>
			<li><a href="<c:url value="/Controller?command=about_us" />">${aboutUs}</a></li>
		</ul>
		<c:choose>

			<c:when test="${sessionScope.regUser!=null}">

				<form class="navbar-form navbar-nav nav navbar-right"
					action="Controller" method="post">

					<div class="form-group">
						<a href="<c:url value="/Controller?command=open_start_page" />"
							style="color: white"><span class="glyphicon glyphicon-user"
							style="color: white"></span> ${sessionScope.regUser}</a>
						</li>
					</div>

					<div class="form-group">
						<input type="hidden" name="command" value="logout" />
					</div>

					<button type="submit" class="btn btn-primary" name="Log out">${logout}</button>


					<div class="form-group lang-group">
						<c:choose>
							<c:when test="${language eq 'en'}">
								<span style="color: white">${en}</span>
								<a
									href="<c:url value="/Controller?command=change_language">
						<c:param name="language" value="ru" ></c:param> </c:url>">${ru}</a>
							</c:when>
							<c:otherwise>
								<a
									href="<c:url value="/Controller?command=change_language">
						<c:param name="language" value="en" ></c:param> </c:url>">${en}</a>
								<span style="color: white">${ru}</span>
							</c:otherwise>
						</c:choose>
					</div>
				</form>

			</c:when>
			<c:otherwise>
				<form class="navbar-form navbar-nav nav navbar-right"
					action="Controller" method="post">
					<div class="form-group">
						<input type="hidden" name="command" value="login" />
					</div>
					<div class="form-group">
						<input type="text" placeholder="${login}" class="form-control"
							name="login" required>
					</div>
					<div class="form-group">
						<input type="password" placeholder="${password}"
							class="form-control" name="password" required>
					</div>
					<button type="submit" class="btn btn-primary" name="Sign in">${signIn}</button>
					<a href="<c:url value="/Controller?command=open_sign_up_page" />"
						class="btn btn-primary" role="button">${signUp}</a> <span
						class="divider-vertical"> </span>

					<div class="form-group lang-group">
						<c:choose>
							<c:when test="${language eq 'en'}">
								<span style="color: white">${en}</span>
								<a
									href="<c:url value="/Controller?command=change_language">
						<c:param name="language" value="ru" ></c:param> </c:url>">${ru}</a>
							</c:when>
							<c:otherwise>
								<a
									href="<c:url value="/Controller?command=change_language">
						<c:param name="language" value="en" ></c:param> </c:url>">${en}</a>
								<span style="color: white">${ru}</span>
							</c:otherwise>
						</c:choose>
					</div>
				</form>

			</c:otherwise>

		</c:choose>

	</div>
</div>
</nav>