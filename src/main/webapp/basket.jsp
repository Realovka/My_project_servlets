<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/basket" method="get">
    <table border="3">
        <thead>
            <th align="center">Quality</th>
            <th align="center">Product name</th>
        </thead>

                <c:forEach items="${sessionScope.basket}" var="basket">
                    <tr>
                        <td> ${basket.value}</td>
                        <td>${basket.key}</td>
                    </tr>
                    </c:forEach>
    </table>
</form>
</body>
</html>
