<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Products</title>
</head>

<body>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
    </tr>

    <form action="" method="get">
        <label for="categoryFilter" style="padding: 10px 20px 20px 20px">Category filter</label>
        <select id="categoryFilter" name="categoryId">
            <option value="${-1}" ${param['categoryId'] == null || param['categoryId'] == -1 ? 'selected' : ''}></option>
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}" ${param['categoryId'] == category.id ? 'selected' : ''} >${category.name}</option>
            </c:forEach>
        </select>
        <br>
        <label for="priceFilter" style="padding: 10px 20px 20px 20px">Price filter</label>
        <select id="priceFilter" name="priceSelected">
            <option value="priceNotSelected" ${param['priceSelected']=="priceNotSelected"?'selected':''}></option>
            <option value="priceMax" ${param['priceSelected']=="priceMax"?'selected':''}>максимальная цена</option>
            <option value="priceMin" ${param['priceSelected']=="priceMin"?'selected':''}>минимальная цена</option>
            <option value="priceMaxAndMin" ${param['priceSelected']=="priceMaxAndMin"?'selected':''}>максимальная и минимальная цены</option>
        </select>
        <br>
        <input type="submit" value="Apply" />
    </form>


    <c:forEach items="${products}" var="prod">
        <tr>
            <td>${prod.id}</td>

            <td>${prod.name}</td>

            <td>${prod.description}</td>

            <td>${prod.category.name}</td>

            <td>${prod.price}</td>
        </tr>
    </c:forEach>

</table>

</body>

</html>
