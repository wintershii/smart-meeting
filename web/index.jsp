<%--
  Created by IntelliJ IDEA.
  User: shidongxuan
  Date: 19-5-5
  Time: 下午5:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>websocket测试</title>
    <script type="text/javascript">
        //判断当前浏览器是否支持WebSocket
        var webSocket = null;
        if ('WebSocket' in window) {
            webSocket = new WebSocket("ws://www.shidongxuan.top:8080/smartMeeting_Web/socket/1000");
        }
        else if ('MozWebSocket' in window) {
            webSocket = new MozWebSocket("ws://www.shidongxuan.top:8080/smartMeeting_Web/socket/1000");
        }
        else {
            alert('Not support webSocket');
        }

        //打开socket,握手
        webSocket.onopen = function (event) {
            alert("websocket已经连接");
        }
        //接收推送的消息
        webSocket.onmessage = function (event) {
            console.info(event);
            alert(event.data);
        }
        //错误时
        webSocket.onerror = function (event) {
            console.info("发生错误");
            alert("websocket发生错误" + event);
        }

        //关闭连接
        webSocket.onclose = function () {
            console.info("关闭连接");
        }

        //监听窗口关闭
        window.onbeforeunload = function (event) {
            webSocket.close();
        }
    </script>
</head>
<body>

</body>
</html>
