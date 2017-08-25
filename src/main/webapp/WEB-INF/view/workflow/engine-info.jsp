<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../base/jscss.jsp"/>
    <jsp:include page="../base/global.jsp"/><!--这里的taglib配置不起作用，要放到上面-->
</head>
<body>
<div class='page-title ui-corner-all'>引擎配置</div>
<table class="table table-bordered table-hover table-condensed">
    <tr>
        <th class="text-info" width="30%">属性名称</th>
        <th class="text-info">属性值</th>
    </tr>
    <c:forEach items="${engineProperties}" var="prop">
        <tr>
            <th>${prop.key}</th>
            <td>${prop.value}</td>
        </tr>
    </c:forEach>
</table>

<div class='page-title ui-corner-all'>系统参数</div>
<table class="table table-bordered table-hover table-condensed">
    <tr>
        <th class="text-info">属性名称</th>
        <th class="text-info">属性值</th>
    </tr>
    <c:forEach items="${systemProperties}" var="prop">
        <tr>
            <th>${prop.key}</th>
            <td>${prop.value}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>