<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>

<c:if test="${presentation eq 'addNews' || presentation eq 'editNews' }">
	<c:import url="/WEB-INF/pages/tiles/addEditNews.jsp" />
</c:if>