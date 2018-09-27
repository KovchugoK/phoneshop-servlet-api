<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Info</title>
    <meta charset="utf-8">
</head>
<body class="bodyStile">
<div>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
</div>
<div>
    <c:forEach var="product" items="${products}">
        <table>
            <col width=40%/>
            <thead>
            <tr>
                <td>Id</td>
                <td>${product.id}</td>
            </tr>
            <tr>
                <td>Code</td>
                <td>${product.code}</td>
            </tr>
            <tr>
                <td>Description</td>
                <td>${product.description}</td>
            </tr>

            <tr>
                <td>Price</td>
                <td>${product.price} ${product.currency}</td>
            </tr>
            <tr>
                <td>Stock</td>
                <td>${product.stock}</td>
            </tr>
            </thead>
        </table>
    </c:forEach>
</div>
<div class="footer">
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>