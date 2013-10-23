<%@include file="/WEB-INF/tags/taglibs.jsp"%>

<h1> <fmt:message key='topics' /> </h1>
<hr />

<a href="${contextPage}/home/"> <fmt:message key='home' /> </a>

<jopss:messages msgSuccess="${msgSuccess}" msgError="${msgError}" />

<form:form id="topic_form" method="POST" action="${contextPage}/topic/save/" modelAttribute="topicForm">
    
    <textarea name="topic.description" cols="50" rows="3"></textarea>
    
    <cp-componentes:captcha-logico nameRadiosRespostas="responseUser" />
    
    <br/>
    <input type="submit" value="<fmt:message key='topics.button.submit' />" />
    
    <br/>
    
    <c:forEach var="topic" items="${topics}" varStatus="count">
        <a href="${contextPage}/comment/topic/${topic.id}/">${topic.id}. ${topic.dateCreated}</a> ${topic.description} <br/>
    </c:forEach>
    
    <jopss:paginator pageCount="${pageCount}" action="${contextPage}/topic/" />
        
</form:form>