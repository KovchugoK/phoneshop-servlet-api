<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<html>
<head>
    <title>Product List</title>
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
            <td>Currency</td>
            <td>Stock</td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.id}</td>
                <td>
                    <a href="<c:url value = "/products"/>/${product.id}">${product.code}</a>
                </td>
                <td>${product.description}</td>
                <td>${product.price}</td>
                <td>${product.currency}</td>
                <td>${product.stock}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<form method="post">
    <input name="search" id="search" value="${empty param.search ? null : param.search}">
    <input type="submit" value="Search">
    <c:if test="${error}">
        <label for="search">${errorMsg}</label>
    </c:if>
</form>
<div class="footer">
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>