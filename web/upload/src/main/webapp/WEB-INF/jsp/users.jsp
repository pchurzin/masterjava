<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Imported users</title>
</head>
<body>
<h1>Imported users</h1>
<ol>
    <c:forEach items="${users}" var="user">
        <li>${user.value} <a href="mailto:${user.email}">${user.email}</a> ${user.flag}</li>
    </c:forEach>
</ol>
</body>
</html>
