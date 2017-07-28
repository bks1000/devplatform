<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" import="com.june.dto.sys.Menu" language="java" %>
<nav class="navbar navbar-static-top" role="navigation">
    <div>
        <div class="navbar-inner">
            <ul class="nav navbar-nav">
                <li><a>Develop Platform</a></li>
                <c:forEach items="${menus}" var="menu">
                    <li name="nav" onclick="OP.setState(this)"><a onclick="OP.loadMenu('${menu.id}','${menu.name}')">${menu.name}</a></li>
                </c:forEach>

                <%--<li class="active"><a href="#">iOS</a></li>
                    <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Java
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">jmeter</a></li>
                        <li><a href="#">EJB</a></li>
                        <li><a href="#">Jasper Report</a></li>
                        <li class="divider"></li>
                        <li><a href="#">分离的链接</a></li>
                        <li class="divider"></li>
                        <li><a href="#">另一个分离的链接</a></li>
                    </ul>
                </li>--%>
            </ul>
        </div>
    </div>
</nav>

