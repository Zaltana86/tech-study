<%--
  Author: Inklo
  Date: 2023/3/2
  Time: 21:44
  Target:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    用户名： <label>
    <input type="text" name="username">
</label>
    密码： <label>
    <input type="password" name="password">
</label>
    <input type="submit" value="提交">
</form>
</body>
</html>
