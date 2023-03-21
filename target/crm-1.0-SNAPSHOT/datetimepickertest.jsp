<%--
  Created by IntelliJ IDEA.
  User: YQ
  Date: 2023/2/6
  Time: 14:21
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
    <title>演示datetimepicker插件</title>
    <%--JQuery--%>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <%--bootstrap框架--%>
    <link rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/css/bootstrap.min.css"></script>
    <%--BOOTSTRAP_DATETIMEPICKER插件--%>
    <link rel="stylesheet" href="jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css">
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="jquery/bootstrap-datetimepicker-master/locale/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript">
        $(function(){
            //当容器加载完成，对容器调用工具函数
            $("#MyDate").datetimepicker({
                language:'zh-CN',//语言
                format:'yyyy-mm-dd',//日期的格式
                minView:'month',//可以选择的最小视图
                initialDate:new Date(),//初始化显示的日期
                autoclose:true,//设置选择完日期或时间后，是否自动关闭日历
                todayBtn:true,//是否显示今天的日期,默认市false
                clearBtn:true//是否显示清空按钮,默认市false
            });
        })
    </script>
</head>
<body>
<input  type="text" id="MyDate" readonly>
</body>
</html>
