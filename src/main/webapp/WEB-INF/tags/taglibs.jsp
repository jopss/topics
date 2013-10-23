<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="jopss" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="cp-componentes" tagdir="/WEB-INF/tags/cp-componentes"%>

<fmt:setBundle basename="messages"/> 

<c:set var="context" value="${pageContext.request.contextPath}" />
<c:set var="contextPage" value="${context}/pages" />