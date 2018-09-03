<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<html>
<head>
    <title>Cart</title>
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
        <c:forEach var="cartItems" items="${cart.cartItems}">
            <tr>
                <td>${cartItems.product.id}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/products/${cartItems.product.id}">${cartItems.product.code}</a>
                </td>
                <td>${cartItems.product.description}</td>
                <td>${cartItems.product.price}</td>
                <td>${cartItems.product.currency}</td>
                <td>${cartItems.product.stock}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer">
    <%@include file="/WEB-INF/pages/footer.jsp" %>
</div>
</body>
</html>
