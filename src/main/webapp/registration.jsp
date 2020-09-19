<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Registration</h2>
</body>
<form action="/registration" method="post">
        <p>${sessionScope.errorRegistration}</p>
        Name <input type="text" name="nameRegistration"/>
        Login<input type="text" name="loginRegistration"/>
        Password<input type="text" name="passwordRegistration"/>
        Role <select name="role">
        <option>ADMIN</option>
        <option>USER</option>
    </select>
        <input type="submit">
</form>
</html>
