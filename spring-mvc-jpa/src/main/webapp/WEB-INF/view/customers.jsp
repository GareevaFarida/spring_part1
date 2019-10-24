<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>First Spring MVC View</title>
</head>
<body>

<a href="create">Create new Customer</a>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Contacts</th>
    </tr>

    <c:forEach items="${customers}" var="customer">
        <tr>
            <c:url value="/customers/edit" var="editUrl">
                <c:param name="id" value="${customer.id}"/>
            </c:url>
            <td>${customer.id}</td>

            <td>
                <a href="${editUrl}">${customer.name}</a>
            </td>

            <td>${customer.contacts}</td>
        </tr>
    </c:forEach>

</table>
</body>

</html>
