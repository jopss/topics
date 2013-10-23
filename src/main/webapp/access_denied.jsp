<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/tags/taglibs.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Language" content="pt-br" />
        <meta http-equiv="Cache-Control" content="no-cache, no-store" />
        <meta http-equiv="Pragma" content="no-cache, no-store" />
        <meta http-equiv="expires" content="Mon, 06 Jan 1990 00:00:01 GMT" />
        <meta name="ROBOTS" content="NOINDEX, NOFOLLOW" />
        <meta name="Author" content="Jopss" />

        <title><fmt:message key="system.name" /></title>
    </head>
    <body>
        <h1>ACCESS DENIED!</h1>
        <hr/>
        <h3>You will be transferred to 'login'.</h3>
        <script type="text/javascript">
                setTimeout(logOff,4000); 
                window.location.href = '<c:url value="/pages/login/" />';	
        </script>
    </body>
</html>
