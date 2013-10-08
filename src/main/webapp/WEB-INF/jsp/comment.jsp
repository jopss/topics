<%@include file="/WEB-INF/tags/taglibs.jsp"%>

<script type="text/javascript">
    
        function showReply(idButton, commentReference){
            
            //muda o action do formulario dinamicamente
            if(commentReference != null){
                $("#form_comments").attr("action","${contextPage}/comment/reference/"+commentReference+"/save/");
            }else{
                $("#form_comments").attr("action","${contextPage}/comment/topic/${topic.id}/save/");
            }
            
            //exibe os componentes para reply apos do botao selecionado
            var divReply = $("#divReply");
            $("#"+idButton).after(divReply);
            divReply.attr("style","");
        }
        
        function submitForm(){
            $("#loadGif_btSubmit").show();
            $("#btSubmit").attr("disabled", "disabled");
            $("#form_comments").submit();
        }
</script>

<h1> <fmt:message key='comments' /> </h1>
<hr />

<a href="${contextPage}/home/"> <fmt:message key='home' /> </a>

<jopss:messages msgSuccess="${msgSuccess}" msgError="${msgError}" />
        
<form:form id="form_comments" method="POST" modelAttribute="comment">
    
    <form:errors path="*" element="div" />
        
    ${topic.dateCreated} ${topic.description}
    <input id="replyButton_topic" type="button" value="<fmt:message key='comments.button.reply' />" onclick="showReply(this.id);"/>
    
    <br/>
    
    <c:forEach var="comment" items="${comments}">
        ${comment.nivel}. ${comment.dateCreated} ${comment.content} 
        <input id="replyButton_${comment.id}" type="button" value="<fmt:message key='comments.button.reply' />" onclick="showReply(this.id, ${comment.id});" />
        <br/>
    </c:forEach>
    
    <!-- componentes a serem exibidos conforme a selecao no botao 'reply' -->
    <div id="divReply" style="display: none">
        <br/>
        <textarea id='txtReply' name='content'></textarea>
        <input type="button" id="btSubmit" value="<fmt:message key='comments.button.submitReply' />" onclick="submitForm();" />
        <img id='loadGif_btSubmit' src='${context}/resources/images/loading.gif' style="display: none"/>
    </div>
        
</form:form>