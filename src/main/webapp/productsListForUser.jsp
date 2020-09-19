<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products list</title>
</head>
<body>
<form action="/basket" method="get">
    <table border="3" width="50%">
        <thead>
        <th width="6%" align="center">id</th>
        <th width="40%" align="center">Product name</th>
        <th width="5%" align="center">Price</th>
        <th width="5%" align="center">Add to basket</th>
        </thead>
        <tbody>
        <c:forEach items = "${sessionScope.productsList}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.product}</td>
                <td>${item.price}</td>
                <td><input type="text" name="qualityProduct"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit"/>
    </p>
</form>
</body>
</html>
