#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="base/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<%@ include file="base/jscss.jsp" %>
<script language="javascript" type="text/javascript">   
	function jump(url,obj){
    	${symbol_dollar}("${symbol_pound}mainBody").load(url,function(){ 
    		${symbol_dollar}("${symbol_pound}mainBody").fadeIn(100);
    	});
    	${symbol_dollar}("li").each(function(){
    		${symbol_dollar}(this).removeAttr("class");
    	});
    	${symbol_dollar}(obj).parent().attr("class","active");
    }
</script>
</head>
<body>
	<jsp:include page="base/navbar.jsp"></jsp:include>
	<div class="container-fluid">
		<div class="row-fluid">
			<%@include file="base/sidebar.jsp" %>
			<div class="span9" id="content">
				<div class="row-fluid">
					<div id="mainBody"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 通用的模态弹出框 -->
	<div class="modal fade" id="mainModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
</html>