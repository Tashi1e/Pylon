<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%-- <fmt:setLocale value="${sessionScope.locale}" /> --%>

<div class="body-title">
<!-- 	<a href="">News >> </a>  -->
<!-- 	News List -->
</div>

<form action="" method="post">
	<c:forEach var="news" items="${newsList}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
				<c:if test="${role eq 'admin'}">
				<a href="news?newsId=${news.id}" style="color:black; text-decoration:none; font-size: 20px; font-weight: bold">
					<c:out value="${news.title}" />
				</a>
				</c:if>
				<c:if test="${role == null or role eq 'guest' or role eq ''}">
					<h3><c:out value="${news.title}" /></h3>
				</c:if>
				</div>
				<div class="news-date">
<%-- 					<c:out value="${news.date}" /> --%>
<%-- 						<fmt:timeZone value="${sessionScope.locale}"> --%>
						<fmt:formatDate type = "date" dateStyle = "short" value = "${news.date}"/>
<%-- 						</fmt:timeZone> --%>
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${role eq 'admin'}">
						<form:form action="deleteNews" modelAttribute="newsData" id="delete_news_form">
						<span>
						      <a href="editNews?newsId=${news.id}" style="color:black; text-decoration:none">
						      	<img alt="" src="${pageContext.request.contextPath}/resources/images/pencil-square.svg" />
						      </a> 
					 	</span>
						      
   					         <form:checkbox path="markedNewsId" value="${news.id}" id="chbx${news.id}" form="delete_news_form" style="display: none"/>
   					         <label for="chbx${news.id}" style="position: relative; display: inline-block;">
						
   					         <span>
   					                <img alt="" src="${pageContext.request.contextPath}/resources/images/app.svg" />
									</span>
									<span style="position: absolute; left: 0 ; font-size: 18px; visibility: hidden" id="news_check_icon_label_${news.id}">
									<img alt="" src="${pageContext.request.contextPath}/resources/images/check-lg.svg" />
									</span>
							</label>
							</form:form>
							
							<script type="text/javascript">
									let checkBox${news.id} = document.getElementById('chbx' + ${news.id});
									let checkIcon${news.id} = document.getElementById('news_check_icon_label_' + ${news.id});
										checkBox${news.id}.onclick = function(){
										if (checkBox${news.id}.checked) {
											checkIcon${news.id}.style.visibility = 'visible';
 										} else {
											checkIcon${news.id}.style.visibility = 'hidden';
 										}
 									}
 							</script>
							
   					    </c:if>
					</div>
				</div>

			</div>
		</div>

	</c:forEach>

	<div class="no-news">
		<c:if test="${newsList eq null}">
        No news.
	</c:if>
	</div>
</form>


