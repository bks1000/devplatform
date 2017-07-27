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
<title>新增图书</title>
<%@include file="base/jscss.jsp"%>
<script type="text/javascript">
	${symbol_dollar}(document).ready(function(){
		
	});
	function save() {
		/*var data = ${symbol_dollar}('${symbol_pound}form').serialize();
		${symbol_dollar}.post('${symbol_dollar}{ctx}/book/save.do',{form:data},function(d){
			if(d.code>0){
				alert('保存成功！');
			}
		});
		
		${symbol_dollar}.post('${symbol_dollar}{ctx}/book/save.do',data,function(d){
			if(d.code>0){
				alert('保存成功！');
			}
		});*/
		
		${symbol_dollar}.ajax({
		    type: 'post',
		    url: '${symbol_dollar}{ctx}/book/save.do',
		    data: ${symbol_dollar}("form").serialize(),
		    success: function(d) {
		    	if(d.code>0){
					alert('保存成功！');
				}
		    }
		});
	}
</script>
</head>
<body>
	<div>
		<form id="form" name="form" class="form-horizontal" action="${symbol_dollar}{ctx }/book/save.do" method="post">
			<div class="control-group">
				<label class="control-label" for="bookName">图书名称</label>
				<div class="controls">
					<input type="text" class="form-control" id="bookName" name="bookName"
						placeholder="图书名称" value="${symbol_dollar}{bt.bookName }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="typeid">图书类型</label>
				<div class="controls">
					<input type="text" class="form-control" id="typeid" name="typeid"
						placeholder="图书类型" value="${symbol_dollar}{bt.typeid }">
					<!-- <select id="typeid" name="typeid" class="form-control">
					  <option>1</option>
					  <option>2</option>
					  <option>3</option>
					  <option>4</option>
					  <option>5</option>
					</select> -->
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="author">作者</label>
				<div class="controls">
					<input type="text" class="form-control" id="author" name="author" placeholder="作者"
						value="${symbol_dollar}{bt.author }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="oldName">原名</label>
				<div class="controls">
					<input type="text" class="form-control" id="oldName" name="oldName" placeholder="原名"
						value="${symbol_dollar}{bt.oldName }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="translator">译者</label>
				<div class="controls">
					<input type="text" class="form-control" id="translator" name="translator" placeholder="译者"
						value="${symbol_dollar}{bt.translator }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="publishing">出版日期</label>
				<div class="controls">
					<input type="text" class="form-control" id="publishing" name="publishing" placeholder="出版日期"
						value="${symbol_dollar}{bt.publishing }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="papers">页数</label>
				<div class="controls">
					<input type="text" class="form-control" id="papers" name="papers" placeholder="页数"
						value="${symbol_dollar}{bt.papers }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="money">单价</label>
				<div class="controls">
					<input type="text" class="form-control" id="money" name="money" placeholder="单价"
						value="${symbol_dollar}{bt.money }">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="isbn">ISBN</label>
				<div class="controls">
					<input type="text" class="form-control" id="isbn" name="isbn" placeholder="ISBN"
						value="${symbol_dollar}{bt.isbn }">
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<input type="hidden" id="bookId" name="bookId" value="${symbol_dollar}{bt.bookId }" /> <input
						id="btnSave" type="button" class="btn btn-primary"
					    value="保存" onclick="save()" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>