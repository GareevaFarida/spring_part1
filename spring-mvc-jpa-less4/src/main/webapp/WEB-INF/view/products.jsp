<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Products</title>
</head>

<body>

<form action="" method="get>
    <label for="categoryFilter">Категория товара</label>
<select id="categoryFilter" name="categoryId">
    <option value="${-1}" ${param['categoryId'] == null || param['categoryId'] == -1 ? 'selected' : ''}></option>
    <c:forEach items="${categories}" var="category">
        <option value="${category.id}" ${param['categoryId'] == category.id ? 'selected' : ''} >${category.name}</option>
    </c:forEach>
</select>
<br>
<label for="priceFilter" style="padding: 10px 20px 20px 20px">Цена товара</label>
<select id="priceFilter" name="priceType">
    <option value="priceNotSelected" ${param['priceType']=="priceNotSelected"?'selected':''}></option>
    <option value="priceMax" ${param['priceType']=="priceMax"?'selected':''}>максимальная цена</option>
    <option value="priceMin" ${param['priceType']=="priceMin"?'selected':''}>минимальная цена</option>
    <option value="priceMaxAndMin" ${param['priceType']=="priceMaxAndMin"?'selected':''}>диапазон цен</option>
</select>
<input type="number" name="minPrice" placeholder="Минимальная цена" style="margin-left: 20px" value=${param['minPrice']} >
<input type="number" name="maxPrice" placeholder="Максимальная цена" style="margin-left: 20px" value=${param['maxPrice']} >
<br>
<input type="submit" value="Apply"/>
</form>

<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Category</th>
        <th>Price</th>
    </tr>
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
