<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/providertaglib.tld" prefix="prov"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="navbar-fixed-bottom row-fluid">
	<div class="navbar-inner">
		<div class="container">
			<c:choose>
				<c:when test="${sessionScope.locDate != null}">

					<p class="text-center">
						Provider,<prov:locDate date="${locDate}" />
					</p>

				</c:when>
				<c:otherwise>
				
					<p class="text-center">Provider</p>
					
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>