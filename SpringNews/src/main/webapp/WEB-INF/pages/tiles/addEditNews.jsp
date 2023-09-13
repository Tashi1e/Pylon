<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="localization.locale" var="loc" />

<fmt:message bundle="${loc}" key="local.news.save.button" var="save_button" />
<fmt:message bundle="${loc}" key="local.news.add.image.button" var="add_image_button" />

<style>
#edit_field{
	width: 95%;
	height: 70vh;
	padding-top: 20px;
}

label {
 	margin_top: 10px; 
 	font-weight: bold; 
 	font-style: italic; 
}

textarea#title {
	height: 1.2em;
}
	
textarea#brief {
	height: 3.6em;
}

textarea#content {
	height: 38vh;
}

input#publish {
	width: 100px;
}
</style>

<c:if test=""></c:if>

<div id="edit_field">
<form:form action="saveNews" modelAttribute="newsData"> 
<!-- enctype="multipart/form-data"> -->
<c:if test="${presentation eq 'editNews'}">
<input type="hidden" name="command" value="do_edit_news" />
<input type="hidden" name="id" value="${news.id}" />
</c:if>
<c:if test="${presentation eq 'addNews'}">
<input type="hidden" name="command" value="do_add_news" />
</c:if>
<p> 
<label for="title">Title</label>
<form:textarea path="title" class="text_edit" id="title" placeholder="Enteer Your title here" />
<%-- ${news.title} --%>
</p>
<p>
<label for="brief">Brief</label>
<form:textarea path="brief" class="text_edit" id="brief" placeholder="Enteer Your title here" />
<%-- ${news.brief}</form:textarea> --%>
</p>
<p>
<label for="content">Content</label>
<form:textarea path="content" class="text_edit" id="content" placeholder="Enteer Your content here" />
<%-- ${news.content}</form:textarea> --%>
</p>
<p class="view-news-grid-container" style="margin-left: 0px">
<input type="submit" id="save_button" class="button grey" value="${save_button}" />
<input type="file" name="add_image" id="add_image" accept="image/*" style="display: none" onchange="getName(this.value);" />
<label for="add_image" id="add_image_label">
${add_image_button}
</label>
</p>

</form:form>

<script type="text/javascript">
function getName (str){
    if (str.lastIndexOf('\\')){
        var i = str.lastIndexOf('\\')+1;
    }
    else{
        var i = str.lastIndexOf('/')+1;
    }						
    var filename = str.slice(i);			
    var uploaded = document.getElementById("add_image_label");
    var len = filename.length;
    if (len > 20){
   		filename = '...'+filename.substring((len-17),len);
    }
    uploaded.innerHTML = filename;
}
</script>
</div>
