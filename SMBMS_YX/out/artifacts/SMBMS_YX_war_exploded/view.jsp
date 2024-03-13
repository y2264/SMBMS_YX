<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: 22641
  Date: 2022/4/1
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table>
    <tr>
        <td>id</td>
        <td>姓名</td>
        <td>住址</td>
        <td>手机</td>
    </tr>
    <c:forEach var="beanList" items="${beanList}">
        <tr>
            <td>${beanList.userId}</td>
            <td>${beanList.userName}</td>
            <td>${beanList.address}</td>
            <td>${beanList.mobile}</td>
        </tr>
    </c:forEach>

<select>
    <c:forEach var="beanList" items="${beanList}">
        <option>${beanList.userId}</option>
        <option>${beanList.userName}</option>
        <option>${beanList.address}</option>
        <option>${beanList.mobile}</option>
    </c:forEach>
</select>
</table>

</body>
</html>
