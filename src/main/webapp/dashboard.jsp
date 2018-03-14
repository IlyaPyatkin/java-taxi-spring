<%@ page import="model.pojo.Order" %>
<%@ page import="services.impl.OrderService" %>
<%@ page import="services.interfaces.IOrderService" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String username = (String) session.getAttribute("username");
    IOrderService orderService = new OrderService();
    List<Order> orders = orderService.getOrders(username);
%>
<html>
<head>
    <title>Taxi Service - Dashboard</title>
</head>
<body>
<header>
    <h1>Welcome, <%= username%>
    </h1>
    <a href="/logout">
        <button>Logout</button>
    </a>
    <h2>Order a taxi</h2>

    <form method="post" action="order" id="submit-order">
        <label></label>
        <input type="text" placeholder="From" name="origin" required>
        <input type="text" placeholder="To" name="destination" required>
        <input type="submit" name="order" value="Order">
    </form>

    <div class="dashboard">
        <h2>Previous orders: </h2>
        <ul>
            <c:forEach items="${requestScope.orders}" var="order">
                <li>
                        ${order.getOrigin()} - ${order.getDestination()}<br>
                        ${order.getTime()}
                </li>
            </c:forEach>
        </ul>
    </div>

</header>

</body>
</html>
