<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form enctype="multipart/form-data" action="${pageContext.servletContext.contextPath}/" method="post">
    <input type="file" name="uploaded">
    <input type="submit" value="submit">
</form>
</body>
</html>
