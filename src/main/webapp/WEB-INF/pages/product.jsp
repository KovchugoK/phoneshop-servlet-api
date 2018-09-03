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
            <td>${product.price}</td>
        </tr>

        <tr>
            <td>Currency</td>
            <td>${product.currency}</td>
        </tr>
        <tr>
            <td>Stock</td>
            <td>${product.stock}</td>
        </tr>
        </thead>
    </table>
</div>
<div class="footer">
    <%@include file="/WEB-INF/pages/footer.jsp" %>
</div>
</body>
</html>