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
    <title>演示pageination分页插件</title>

    <!--  JQUERY -->
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>

    <!--  BOOTSTRAP -->
    <link rel="stylesheet" href="jquery/bootstrap_3.3.0/css/bootstrap.min.css">
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/css/bootstrap.min.css"></script>

    <!--  PAGINATION plugin -->
    <link rel="stylesheet" type="text/css" href="jquery/bs_pagination-master/css/jquery.bs_pagination.min.css">
    <script type="text/javascript" src="jquery/bs_pagination-master/js/jquery.bs_pagination.min.js"></script>
    <script type="text/javascript" src="jquery/bs_pagination-master/localization/en.js"></script>

    <script type="text/javascript">
        $(function() {
            $("#demo_pag1").bs_pagination({
                currentPage:1,//当前页号，相当与pageNo
                rowsPerPage:10,//每页显示条数，相当于pageSize
                totalRows:1000,//总条数
                totalPages: 100,//总页数 必填参数

                visiblePageLinks: 5,//最多可显示的卡片数

                showGoToPage: true,//是否显示“跳转到”部分，默认 true
                showRowsPerPage:true,//是否显示“每页显示条数”部分，默认true
                showRowsInfo: true,//是否显示记录的信息，默认true

                //用户每次切换页号，都自动出发本函数
                //用户返回切换页号之后的pageNo和pageSize
                onChangePage:function (envent,pageObj) {
                    //js代码
                    alert(pageObj.currentPage);
                    alert(pageObj.rowsPerPage);
                }

            });

        });
    </script>
</head>
<body>
<!--  Just create a div and give it an ID -->

<div id="demo_pag1"></div>
</body>
</html>
