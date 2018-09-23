<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="cart" type="com.es.phoneshop.model.Cart" scope="request"/>
<html>
<head>
    <title>Cart</title>
</head>
<body class="bodyStile">
<div>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
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
        <c:forEach var="cartItems" items="${cart.cartItems}" varStatus="status">
            <tr>
                <td>${cartItems.product.id}</td>
                <td>
                    <a href="<c:url value = "/products"/>/${cartItems.product.id}">${cartItems.product.code}</a>
                </td>
                <td>${cartItems.product.description}</td>
                <td><fmt:formatNumber value="${cartItems.product.price}"/> ${cartItems.product.currency}</td>
                <td>${cartItems.quantity}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>Total sum: ${totalSum} ${cart.cartItems[0].product.currency} </td>
        </tr>
    </table>
</div>
<form method="post">
    <p>
        <label for="name">Name</label>
        <input name="name" id="name">
        <c:if test="${nameError}">
            ${nameErrorMsg}
        </c:if>
    </p>
    <p>
        <label for="address">Address</label>
        <input name="address" id="address">
        <c:if test="${adresError}">
            ${adresErrorMsg}
        </c:if>
    </p>
    <p>
        <label for="phone">Phone</label>
        <input name="phone" id="phone">
        <c:if test="${phoneError}">
            ${phoneErrorMsg}
        </c:if>
    </p>
    <input type="submit" value="Confirm order">
</form>
<div class="footer">
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>
