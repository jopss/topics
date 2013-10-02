<%@include file="/WEB-INF/tags/taglibs.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
	});
</script>

<a href="${contextPage}/topic/"> <fmt:message key='home' /> </a>

<h1> <fmt:message key='topics' /> </h1>

<jopss:messages msgSuccess="${msgSuccess}" msgError="${msgError}" />

<form:form id="topic_form" method="POST" action="${contextPage}/topic/save/" modelAttribute="topic">
    
    <textarea name="description" cols="50" rows="3"></textarea>
    <input type="submit" value="<fmt:message key='topics.button.submit' />" />
    
    <br/>
    
    <c:forEach var="topic" items="${topics}" varStatus="count">
        <a href="${contextPage}/comment/topic/${topic.id}/">${topic.id}. ${topic.dateCreated}</a> ${topic.description} <br/>
    </c:forEach>
    
    <jopss:paginator pageCount="${pageCount}" action="${contextPage}/topic/" />
        
</form:form>