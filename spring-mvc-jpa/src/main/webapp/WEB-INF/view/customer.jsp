<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New customer</title>
</head>
<body>

<c:url value="/customers/create" var="createUrl"/>
<form action="${createUrl}" method="post">
    <%-- Очень часто применяемый способ передачи id через форму --%>
    <input type="hidden" name="id" id="id" value="${customer.id}">
    <p>
        <label for="name">Name</label>
        <input type="text" id="name" name="name" value="${customer.name}" />
    </p>

    <p>
        <label for="contacts">Contacts</label>
        <input type="text" id="contacts" name="contacts" value="${customer.contacts}" />
    </p>
    <input type="submit" />
</form>

</body>
</html>
