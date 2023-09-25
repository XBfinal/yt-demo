<%--
  Created by IntelliJ IDEA.
  User: 35886
  Date: 2023/9/24
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" id="form" method="post">
    <h1 style="text-align: center;color: aliceblue;">注册账号</h1>
    <p>用户:<input id="name" name="name" type="text"></p>
    <p>密码:<input id="password" name="password" type="password" placeholder="密码长度至少为6位"></p>

    <%
        String msg = (String) request.getAttribute("msg");
        if (msg == null ) {
            msg="";
        }

    %>
    <a> <%=msg%> </a>
    <div style="text-align: center;margin-top: 30px;">
        <input type="submit" class="button" value="注册">

    </div>
</form>
</body>
</html>
