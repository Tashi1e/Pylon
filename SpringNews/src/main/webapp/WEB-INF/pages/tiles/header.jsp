<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.login.button.name" var="loginButton" />
<fmt:message bundle="${loc}" key="local.signup.button.name" var="signupButton" />
<fmt:message bundle="${loc}" key="local.signin.button.name" var="signinButton" />
<fmt:message bundle="${loc}" key="local.remember.button.name" var="remembButton" />
<fmt:message bundle="${loc}" key="local.signout.button.name" var="signoutButton" />
<fmt:message bundle="${loc}" key="local.login.label.name" var="loginLabel" />
<fmt:message bundle="${loc}" key="local.password.label.name" var="passLabel" />
<fmt:message bundle="${loc}" key="local.error.code.${errorCode}" var="errorMessage" />

<div class="header-grid-container">

	<div class="header-grid-item-logo">
		<img alt="News Portal" src="${pageContext.request.contextPath}/resources/images/news_portal_logo.png"
			height="100px">
	</div>

	<div class="header-grid-item-message">
				<c:if test="${not (sessionScope.errorCode eq null)}">
					<font color="red"> 
						<c:out value="${errorMessage}" />
					</font> 
				</c:if>
	</div>

	<div class="header-grid-item" align="right">
<%-- 		<form> --%>
			<a href="?locale=en" style="text-decoration: none">
			<img alt="" src="${pageContext.request.contextPath}/resources/images/us.svg" style="height: 15px" />
			</a> 
			&nbsp;&nbsp; 
			<a href="?locale=ru" style="text-decoration: none">
			<img alt="" src="${pageContext.request.contextPath}/resources/images/ru.svg" style="height: 15px" />
			</a>
<%-- 		</form> --%>
	</div>
	<c:if test="${not(role eq 'guest') and role != null}">
	<div class="header-grid-item" align="right" style="padding-top: 15px;">
	${user.nickName}
	</div>
	</c:if>
	
	<div class="header-grid-item-login" align="right">
	<c:if test="${role eq 'guest' or role eq'' or role == null}">
		<form:form action="signin" modelAttribute="userData" id="box">
			<form:checkbox path="rememberMeCheckBox" value="${remembButton}" id="checkbox" style="display: none" />
			<input type="hidden" class = "button grey" value="${signinButton}" id="signin" />
			<input type="button" class = "button transperent" value="${loginButton}" id="login" /> 
			<span class="ph-container"> 
				<form:input path="user.login" id="logName" class="text" placeholder=" " autocomplete="new-username" /> 
				<label for="logName" class="logFormLabels" style="visibility: hidden">${loginLabel}</label>
			</span> 
			<span class="ph-container"> 
			    <form:password path="user.password" id="password" class="text" placeholder=" " autocomplete="new-password" /> 
				<label for="password" class="logFormLabels" style="visibility: hidden">${passLabel}</label>
			</span> 
			<input type="button" value="${signupButton}" class="button yellow" width="150" id="registration" />
			<input type="hidden" class="registration_remember_btn" id="remember" />				
				</form:form>
		</c:if>
		
	<c:if test="${not(role eq 'guest') and role != null and not(role eq '')}">
		<form:form action="signout">
			<input type="submit" class="button transperent" value="${signoutButton}" id="signOut" /> 
		</form:form>
	</c:if>
	</div>
	
</div>