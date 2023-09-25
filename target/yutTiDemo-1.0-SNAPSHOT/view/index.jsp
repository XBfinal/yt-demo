<%--
  Created by IntelliJ IDEA.
  User: 35886
  Date: 2023/9/23
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        html {
            height: 100%;
            width: 100%;
            overflow: hidden;
            margin: 0;
            padding: 0;

            background-repeat: no-repeat;
            background-size: 100% 100%;
            -moz-background-size: 100% 100%;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }

        #loginDiv {
            width: 400px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 400px;
            background-color: rgba(75, 81, 95, 0.3);
            box-shadow: 7px 7px 17px rgba(52, 56, 66, 0.5);
            border-radius: 250px;
        }


        p {
            margin-top: 30px;
            margin-left: 20px;
            color: azure;
        }

        input {
            margin-left: 15px;
            border-radius: 5px;
            border-style: hidden;
            height: 30px;
            width: 140px;
            background-color: rgba(216, 191, 216, 0.5);
            outline: none;
            color: #f0edf3;
            padding-left: 10px;
        }

        .button {
            border-color: cornsilk;
            background-color: rgba(100, 149, 237, .7);
            color: aliceblue;
            border-style: hidden;
            border-radius: 5px;
            width: 100px;
            height: 31px;
            font-size: 16px;
        }
    </style>
    <style type="text/css">
        input::-ms-input-placeholder {
            text-align: center;
        }

        input::-webkit-input-placeholder {
            text-align: center;
        }
    </style>
</head>

<body>
<div id="loginDiv">
    <form action="${pageContext.request.contextPath}/login" id="form" method="post">
        <h1 style="text-align: center;color: aliceblue;">立即登录</h1>
        <p>用户:<input id="name" name="name" type="text"></p>
        <p>密码:<input id="password" name="password" type="password" placeholder="密码长度至少为6位"></p>

        验证码:<input type="text" name="verificationCode"/>
        <!-- src 此时使用loginServlet的get提交方法， 使用框架时，对应验证方法名的路径 -->
        <img id="imgObj" alt="验证码" src="<%=request.getContextPath() %>/imgObj" onclick="myAjax()">
        <p style="text-align: center;color: darkgray;"><a href="#">忘记密码?</a></p>

        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null ) {
                msg="";
            }

        %>
        <a> <%=msg%> </a>

        <div style="text-align: center;margin-top: 30px;">
            <input type="submit" class="button" value="登录">
<%--            <input type="button" class="button" value="注册">--%>
            <button onclick="window.location.href='view/register.jsp';return false;" >注册</button>

        </div>
    </form>


    <script type="text/javascript">
        function myAjax() {
            //1.创建异步对象
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.responseType = "blob"//接收图片
            //2.绑定事件
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

                    var blob = this.response;
                    var elementById = document.getElementById("imgObj");
                    elementById.src = URL.createObjectURL(blob);

                }
            }
            //3.初始化异步对象
            xmlhttp.open("get", "http://localhost:8080/yutTiDemo_war/imgObj", true);
            //4.发送请求
            xmlhttp.send();
        }


    </script>

</div>

</body>
</html>


