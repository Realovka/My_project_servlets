<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products list</title>
</head>
<body>
<form action="/productList" method="get">
    <table border="3" width="50%">
        <thead>
        <th width="5%" align="center">id</th>
        <th width="85%" align="center">Product name</th>
        <th width="10%" align="center">Price</th>
        </thead>
        <tbody>
        <c:forEach items = "${sessionScope.productsList}" var="item">
            <tr>
                <td>${item.id}</td>
                <td>${item.product}</td>
                <td>${item.price}</td>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p> Add product
        <input type="text" name="addNameProduct"/>
        <input type="text" name="addPriceProduct"/>
        ${sessionScope.errorAddProduct}
    </p>
    <p> Delete product
        <input type="text" name="numberDeleteProduct"/>
        ${sessionScope.errorDeleteProduct}
    </p>
    <p>
        <input type="submit"/>
    </p>
</form>
</body>
</html>
