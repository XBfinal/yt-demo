<%--
  Created by IntelliJ IDEA.
  User: 35886
  Date: 2023/9/24
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <li>输出访问者ip：<a href="${pageContext.request.contextPath}/commService/userIp.do">查看</a></li>
        <li>输出访问者浏览器信息：<a href="${pageContext.request.contextPath}/commService/userAgent.do">查看</a></li>
        <li>输出访问者全部cookie信息：<a href="${pageContext.request.contextPath}/commService/cookies.do">查看</a></li>
        <li>查看每个接口的总访问量,：<a href="${pageContext.request.contextPath}/commService/uirViews.do">查看</a></li>

        <form action="${pageContext.request.contextPath}/fileService/down.do" method="get">
            文件URL:<input type="text" name="url"/>
            <input type="submit" class="button" value="获取下载链接">
        </form>
</body>
</html>
