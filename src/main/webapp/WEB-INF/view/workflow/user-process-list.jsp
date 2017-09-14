<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/9/4
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>可用流程</title>
    <jsp:include page="../base/jscss.jsp"/>
    <jsp:include page="../base/global.jsp"/>
    <script type="text/javascript">
        $(function(){

        });
    </script>
</head>
<body>
<table width="100%" class="table table-bordered table-hover">
    <thead>
    <tr>
        <th>ProcessDefinitionId</th>
        <th>DeploymentId</th>
        <th>名称</th>
        <th>KEY</th>
        <th>版本号</th>
        <th>XML</th>
        <th>图片</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ProcessDefinition }" var="process">
        <tr>
            <td>${process.id }</td>
            <td>${process.deploymentId }</td>
            <td>${process.name }</td>
            <td>${process.key }</td>
            <td>${process.version }</td>
            <td><a target="_blank" href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a></td>
            <td><a target="_blank" href='${ctx }/workflow/resource/read?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a></td>
            <td>
                <a href="${ctx}/mywf/start?processid=${process.id }&deployid=${process.deploymentId}&key=${process.key}" target="_blank">发起流程</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
