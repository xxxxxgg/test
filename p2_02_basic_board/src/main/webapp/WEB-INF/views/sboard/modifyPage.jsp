<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	//<![CDATA[
	$(document).ready(function() {
		var formObj = $("form[role='form']");
		console.log(formObj);

		$("#modify").on("click", function() {
			formObj.submit();
		});

		$("#cancel").on("click", function() {
			self.location = "/sboard/listSearch?pageNum=${pcr.pageNum}&pageSize=${pcr.pageSize}";
					//+ "&searchType=${pcr.searchType}&keyword=${pcr.keyword}";
		});
	});
	//]]>
</script>

<form role="form" method="post">
	<input type="hidden" name="pageNum" value="${pcr.pageNum }">
	<input type="hidden" name="pageSize" value="${pcr.pageSize }">
	

	<div class="box-body">

		<div class="form-group">
			<label for="exampleInputEmail1">BNO</label> 
			<input type="text" name="bno" class="form-control" value="${modifyTarget.bno}" readonly="readonly">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Title</label> 
			<input type="text" name="title" class="form-control" value="${modifyTarget.title}">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">Content</label>
			<textarea class="form-control" name="content" rows="3">${modifyTarget.content}</textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">Writer</label> 
			<input type="text" name="writer" class="form-control" value="${modifyTarget.writer}" readonly="readonly">
		</div>
	</div>
	<!-- /.box-body -->
</form>

<div class="box-footer">
	<button type="submit" id="modify" class="btn btn-primary">SAVE</button>
	<button type="submit" id="cancel" class="btn btn-warning">CANCEL</button>
</div>


<%@ include file="../include/footer.jsp"%>