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
				formObj.attr("action", "/sboard/modifyPage");
				formObj.attr("method", "get");
				formObj.submit();
			}
		);

		$("#remove").on(
			"click", function() {
				formObj.attr("action", "/sboard/removePage");
				formObj.submit();
			}
		);
		
		$("#golist").on(
			"click", function() {
				formObj.attr("method", "get");
				formObj.attr("action", "/sboard/listSearch");
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
<print:box-body jsp="readPage" title="${boardVO.title}" content="${boardVO.content}" writer="${boardVO.writer}" />
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

<div class="row">
	<div class="col-md-12">
		<div class="box box-success">
			<h3 class="box-title">ADD NEW REPLY</h3>
		</div>
		<div class="box-body">
			<label for="newReplyWriter">Writer</label>
			<input class="form-control" type="text" placeholder="USER ID" id="newReplyWriter"> <label for="newReplyText">ReplyText</label>
			<input class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText">
		</div>
		<!-- /.box-body -->
		<div class="box-footer">
			<button type="submit" class="btn btn-primary" id="replyAddBtn">ADD REPLY</button>
		</div>
	</div>
</div>
<!--  The time line -->
	<ul class="timeline">
		<!--  timeline time label -->
		<li class="time-label" id="replyDiv"><span class="bg-green">Reply List</span></li>
	</ul>
	
	<div class="text-center">
		<ul id="pagination" class="pagination pagination-sm no-margin">
		
		</ul>
	</div>

<form role="form" action="modify" method="post">
	<input type="hidden" name="bno" value="${boardVO.bno }">
	<input type="hidden" name="pageNum" value="${pcr.pageNum }">
	<input type="hidden" name="pageSize" value="${pcr.pageSize }">
	<input type="hidden" name="searchType" value="${pcr.searchType }">
	<input type="hidden" name="keyword" value="${pcr.keyword }">
</form>

<%@ include file="../include/footer.jsp"%>