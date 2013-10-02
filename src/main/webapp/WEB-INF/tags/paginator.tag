<%@tag description="Componente para paginacao: gera os links em formato RESTful, adicionando /page/{nr} à URL base." pageEncoding="utf-8"%>
<%@include file="/WEB-INF/tags/taglibs.jsp"%>

<%@ attribute name="action" required="true" rtexprvalue="true"%>
<%@ attribute name="pageCount" required="true" rtexprvalue="true"%>

<script type="text/javascript">
function submitPaginator(idLink){
        var link = $("#"+idLink);
        link.addClass("current");
        window.location="${action}"+(link.attr("title"));
}
</script>

<div class="pagination">
    <c:choose>
        <c:when test="${pageCount > 1}">
            <center>
                <c:forEach begin="1" end="${pageCount}" step="1" var="page">
                        <a id="linkPaginator${page}" href="#" class="number" title="${page}" onclick="submitPaginator(this.id)">${page}</a>
                </c:forEach>
            </center>
        </c:when>
        <c:otherwise>
            <center>
                1
            </center>
        </c:otherwise>
    </c:choose>
</div>