<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.news.edit.button" var="edit_button" />
<fmt:message bundle="${loc}" key="local.news.delete.button" var="delete_button" />

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_view_text"><div>
					<h3><c:out value="${news.title}" /></h3>
				</div></td>
		</tr>
		<tr>
			<td class="space_around_view_text align_container">
				<div class="word-breaker align_left" >
<%-- 					<c:out value="${requestScope.news.date}" /> --%>
						<fmt:timeZone value="${sessionScope.locale}">
						<fmt:formatDate type = "both" dateStyle = "long" timeStyle = "short" value = "${news.date}"/>
						</fmt:timeZone>
					
				</div>
				<div class="word-breaker align_right"><br>
				<c:if test="${not(news.author eq null)}">
				By ${news.author}
				</c:if>
				<c:if test="${news.author eq null}">
				By unknown Author
				</c:if>
				</div>
			</td>
				
		</tr>
		<c:if test="${not(requestScope.news.image eq null)}">
		<tr>
			<td class="space_around_view_text"><div class="word-breaker">
					<img src="${requestScope.news.image}" alt="img"
						style="width: 50%; height: auto; margin-left: 20px" align="right" />
					<c:out value="${news.content}" />
				</div></td>
		</tr>
		</c:if>
	</table>
</div>

<c:if test="${role eq 'admin' || role eq 'editor'}">
	<div class="view-news-grid-container">
		<form:form action="editNews?newsId=${news.id}" >
			<input type="submit" class="button grey" value="${edit_button}" />
		</form:form>

		<form:form action="deleteNews" modelAttribute="news">
		<form:hidden path="markedNewsId" value="${news.id} " />
			<input type="submit" class="button transperent" value="${delete_button}" />
		</form:form>
	</div>
</c:if>