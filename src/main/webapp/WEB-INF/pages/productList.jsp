<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<html>
<head>
    <title>Info</title>
    <style type="text/css">
        <%@include file="/WEB-INF/style/style.css" %>
    </style>
</head>
<body class="bodyStile">
<div>
    <%@include file="/WEB-INF/pages/header.jsp" %>
</div>
<div>
    <table>
        <thead>
        <tr>
            <td>Id</td>
            <td>Code</td>
            <td>Description</td>
            <td>Price</td>
            <td>Currency</td>
            <td>Stock</td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/products/${product.id}">${product.code}</a>
                </td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.currency}</td>
                <td>${product.stock}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer">
    <%@include file="/WEB-INF/pages/footer.jsp" %>
</div>
</body>
</html>