<%--
  Author: Inklo
  Date: 2022/4/6
  Time: 22:02
  Target:
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <script src="js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
            $.ajax(
                {
                    url:"allUsers",
                    dataType:"json",
                    success:function(data) {
                        $(data).each(function(){
                            let tr = "<tr>" +
                                "<td>" + this.id + "</td>" +
                                "<td>" + this.name + "</td>" +
                                "<td>" + this.password + "</td>" +
                                "<td>" + this.balance + "</td>" +
                                "</tr>";
                            $("table").append($(tr));
                        })
                    }
                }
            )
        })
    </script>
    <style></style>
</head>
<body>
<table width="50%" border="1" align="center">
    <caption>用户信息表</caption>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>password</th>
        <th>balance</th>
    </tr>
</table>
</body>
</html>
