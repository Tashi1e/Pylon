<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<html>
<head>
<meta charset="UTF-8">
<title>Error</title>

<style type="text/css">

html {
background-image: url(${pageContext.request.contextPath}/resources/images/newspaper_background.jpg);
background-repeat: no-repeat;
background-position: center center;
background-attachment: fixed;
-webkit-background-size: cover;
-moz-background-size: cover;
-o-background-size: cover;
background-size: cover;
}

div{
/* background-image: url(${pageContext.request.contextPath}/resources/images/error_background.jpg); */
max-height: 30%;
max-width: 40%;
padding: 15px;
border: solid 2px grey;
font-family: "Times New Roman";
font-size: 28px;
position: absolute;
top: 50%;
left: 50%;
margin-right: -50%;
transform: translate(-50%, -50%)
}

</style>

</head>

<body onclick="history.back()">
<div align="center">
<h1 align = "center">
<font color="red">
<c:if test="${not(errorCode eq null) or errorCode eq ''}">
<fmt:message bundle="${loc}" key="local.error.code.${errorCode}" />
</c:if>
<c:if test="${errorCode eq null}">
Unknown Error!
</c:if>
</font>
</h1>
</div>
</body>
</html>