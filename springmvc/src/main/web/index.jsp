<%--
  Created by IntelliJ IDEA.
  User: chen
  Date: 2022/7/10
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>

<base href="${pageContext.request.scheme}://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/ "/>

<script type="text/javascript" src="static/js/jquery-3.5.1.min.js"></script>
<link href="static/bootstrap-3.4.1-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="static/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
<body>

<button class="btn btn-success" id="mybtn">按钮</button>

</body>
</html>
<script>
    $(function () {
        $("#mybtn").click(function () {
            alert(2)
        })
    })
</script>