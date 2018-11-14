<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
//<![CDATA[
	$(document).ready(
	function() {
		var formObj = $("form[role='form']");
		console.log(formObj);
		
		$("#modify").on(
			"click", function() {
				formObj.attr("action", "/board/modify");
				formObj.attr("method", "get");
				formObj.submit();
			}
		);

		$("#remove").on(
			"click", function() {
				formObj.attr("action", "/board/remove");
				formObj.submit();
			}
		);
		
		$("#golist").on(
			"click", function() {
				formObj.attr("method", "get");
				formObj.attr("action", "/board/listPerPage");
				formObj.submit();
			}
		);
	}
	);
//]]>
</script>

<form role="form" method="post">
	<input type='hidden' name='bno' value='${boardVO.bno}'>
	<input type='hidden' name='pageNum' value='${pageNum}'>
</form>

<%@ taglib prefix="print" tagdir="/WEB-INF/tags" %>
<print:box-body jsp="read" title="${boardVO.title}" content="${boardVO.content}" writer="${boardVO.writer}" />
<%-- <div class="box-body">
	<div class="form-group">
		<label for="exampleInputEmail1">Title</label>
		<input type="text" name="title" class="form-control" value="${boardVO.title}" readonly="readonly">
	</div>
	<div class="form-group">
		<label for="exampleInputPassword1">Content</label>
		<textarea class="form-control" name="content" rows="3" readonly="readonly">${boardVO.content}</textarea>
	</div>
	<div class="form-group">
		<label for="exampleInputEmail1">Writer</label>
		<input type="text" name="writer" class="form-control" value="${boardVO.writer}" readonly="readonly">
	</div>
</div> --%>
<!-- /.box-body -->

<div class="box-footer">
	<button type="submit" id="modify" class="btn btn-warning modifyBtn">Modify</button>
	<button type="submit" id="remove" class="btn btn-danger removeBtn">REMOVE</button>
	<button type="submit" id="golist" class="btn btn-primary goListBtn">GO LIST</button>
</div>

<form role="form" action="modify" method="post">
	<input type="hidden" name="bno" value="${boardVO.bno }">
	<input type="hidden" name="pageNum" value="${pcr.pageNum }">
	<input type="hidden" name="pageSize" value="${pcr.pageSize }">
</form>

<%@ include file="../include/footer.jsp"%>