<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.menu.welcome" var="welcome_guest" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html lang="ru, en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>
<fmt:message bundle="${loc}" key="local.headline.title" />
</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/newsStyle34.css">

</head>
<body style="background-image: url(${pageContext.request.contextPath}/resources/images/newspaper_background.jpg)">

<!-- The Modal -->
<div id="regModal" class="modal">
  <!-- Modal content -->
  <div class="modal-content" align="center" style="background-image: url(${pageContext.request.contextPath}/resources/images/newspaper_background.jpg)">
  		<c:import url="/WEB-INF/pages/modal/registration.jsp" />
  </div>
</div>
	<div class="page" >
		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">

				<c:if test="${userRole eq 'guest' or userRole == null}">
				<br/>
				<h4 style = "padding-left: 15px">
				    ${welcome_guest}
				    </h4> 
				</c:if>
				<c:if test="${not(userRole eq 'guest') and userRole != null}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
		</div>

		<div class="content" style= "background-image: url(${pageContext.request.contextPath}/resources/images/newspaper_design_cut1.jpg)">

					<c:import url="/WEB-INF/pages/tiles/body.jsp" />


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/logRegForm2.js"></script>
	
</body>
</html>