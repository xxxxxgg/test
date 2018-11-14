<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@include file="../include/header.jsp" %>

<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$(".btn-primary").on("click", function() {
			formObj.attr("action", "/sboard/registerPage");
			formObj.attr("method", "post");
			formObj.submit();
		});
		
		$(".btn-warning").on("click", function() {
			self.location = "/sboard/listSearch";
		});
	});
	//]]>
</script>

<form role="form" method="post">
<%@ taglib prefix="print" tagdir="/WEB-INF/tags" %>
<print:box-body jsp="registerPage" title="Title" content="Content" writer="Writer" />
	<!-- <div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label>
			<input type="text" name="title" class="form-control" placeholder="Enter Title">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3" placeholder="Enter ..."></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label>
			<input type="text" name="writer" class="form-control" placeholder="Enter Writer">
		</div>
	</div> -->
	<!-- /.box-body -->
	
</form>

	<div class="box-footer">
		<button type="submit" class="btn btn-primary">SUBMIT</button>
		<button type="submit" class="btn btn-warning">CANCEL</button>
	</div>

<%@include file="../include/footer.jsp" %>