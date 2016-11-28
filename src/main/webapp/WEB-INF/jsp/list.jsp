<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--<jsp:include page="common/tag.jsp"/>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>秒杀商品列表页</title>

    <jsp:include page="common/head.jsp"/>

    <style type="text/css">
        .container {
            margin-top: 30px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>秒杀列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <th>商品名</th>
                        <th>商品库存</th>
                        <th>开始日期</th>
                        <th>结束日期</th>
                        <th>创建日期</th>
                        <th>详情页</th>
                    </thead>
                    <tbody>
                        <c:forEach var="sk" items="${seckills}">
                            <tr>
                                <td>${sk.name}</td>
                                <td>${sk.invNum}</td>
                                <td><fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td>
                                    <a href="/seckills/${sk.seckillId}/detail" target="_blank" class="btn btn-info">详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
