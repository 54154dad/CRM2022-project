<%--
  Created by IntelliJ IDEA.
  User: YQ
  Date: 2023/3/8
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>演示文件下载</title>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <script type="text/javascript">
        $(function () {
            //给下载按钮绑定单击事件
            $("#fileDownLoadBtn").click(function () {
                //发送文件下载的请求
               window.location.href="workbench/activity/fileDownload.do";
            })
        })
    </script>
</head>
<body>
<input type="button" value="下载" id="fileDownLoadBtn">
</body>
</html>
