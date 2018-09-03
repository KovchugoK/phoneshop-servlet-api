<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<html>
<head>
    <title>Cart</title>
</head>
<body class="bodyStile">
<div>
    <jsp:include page="/WEB-INF/pages/header.jsp" />
</div>
<div>
    <table>
        <thead>
        <tr>
            <td>Id</td>
            <td>Code</td>
            <td>Description</td>
            <td>Price</td>
            <td>Quantity</td>
        </tr>
        </thead>
        <c:forEach var="cartItems" items="${cart.cartItems}">
            <tr>
                <td>${cartItems.product.id}</td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/products/${cartItems.product.id}">${cartItems.product.code}</a>
                </td>
                <td>${cartItems.product.description}</td>
                <td>${cartItems.product.price} ${cartItems.product.currency}</td>
                <td>${cartItems.quantity}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="footer">
    <jsp:include page="/WEB-INF/pages/footer.jsp" />
</div>
</body>
</html>
