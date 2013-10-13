<%@include file="/WEB-INF/tags/taglibs.jsp"%>

<h1>Login</h1>
<hr />

<c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}" >
    <div style="color:red">
        Invalid: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
    </div>
</c:if>

<br/>

<form name="login_form" action="${context}/j_spring_security_check" method="POST">
    Usuario: <input type='text' name='j_username' /> <br/>
    Senha: <input type='password' name='j_password'>
    
    <br/> <br/>
    
    <input type="submit" value="Login" />
</form>

<br/>
<p>Senha para Testes:</p>
<ul>
    <li>Login: admin</li>
    <li>Senha: 123456</li>
</ul>
