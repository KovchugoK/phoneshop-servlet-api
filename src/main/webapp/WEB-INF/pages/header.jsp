<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    <jsp:include page="/WEB-INF/style/style.css" />
</style>
<header>
    <a href= "<c:url value = "/products"/>" ><img
            src="https://banner2.kisspng.com/20180415/lee/kisspng-logo-home-business-phones-email-mobile-phones-phone-5ad3bc1f74d414.8213882215238256954785.jpg"
            height="37" width="50"></a>
    <h2>Phoneshop servlet api</h2>
    <a href="${pageContext.servletContext.contextPath}/cart">
        <img
                src="https://banner2.kisspng.com/20180329/hjq/kisspng-shopping-cart-computer-icons-retail-cart-5abcd0e4f3be68.1102386115223236849984.jpg"
                height="37" width="50"></a>
</header>
