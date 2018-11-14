<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>

<%@ include file="../include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
//<![CDATA[
	$(document).ready(function() {
		$("#searchBtn").on("click", function(event) {
			self.location = "listSearch"
				+ "${pageMaker.makeQuery(1)}"
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + encodeURIComponent($("#keywordInput").val());
		});

		$("#newBtn").on("click", function() {
			self.location = "registerPage";
		}
		);}
	);
//]]>
</script>

<div class="box-body">
	<select name="searchType">
		<option value="NONE" <c:out value="${pcr.searchType == null?'selected':'' }"/>>NONE</option>
		<option value="TITLE" <c:out value="${pcr.searchType eq 'TITLE'?'selected':'' }"/>>Title</option>
		<option value="CONTENT" <c:out value="${pcr.searchType eq 'CONTENT'?'selected':'' }"/>>Content</option>
		<option value="WIRTER" <c:out value="${pcr.searchType eq 'WIRTER'?'selected':'' }"/>>Writer</option>
		<option value="TITLE_CONTENT" <c:out value="${pcr.searchType eq 'TITLE_CONTENT'?'selected':'' }"/>>Title OR Content</option>
		<option value="CONTENT_WRITER" <c:out value="${pcr.searchType eq 'CONTENT_WRITER'?'selected':'' }"/>>Content OR Writer</option>
		<option value="TITLE_CONTENT_WRITER" <c:out value="${pcr.searchType eq 'TITLE_CONTENT_WRITER'?'selected':'' }"/>>Title OR Content OR Writer</option>
	</select>
	
	<input type="text" name="keyword" id="keywordInput" value="${pcr.keyword }">
	<button id="searchBtn">Search</button>
	<button id="newBtn">New Board</button>
</div>

<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>Title</th>
		<th>Writer</th>
		<th>RegDate</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>

	<c:forEach items="${listSearchPerPage}" var="boardVO">
		<tr>
			<td>${boardVO.bno}</td>
			<td><a href='/sboard/readPage${pageMaker.makeSearch(pageMaker.pcr.pageNum) }&bno=${boardVO.bno}'>${boardVO.title}</a></td>
			<td>${boardVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
					value="${boardVO.regdate}" /></td>
			<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
		</tr>
	</c:forEach>
</table>


<div class="text-center">
	<ul class="pagination">

		<c:if test="${pageMaker.prev }">
			<li><a
				href="listSearch${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }"
			var="idx">
			<li
				<c:out value="${pageMaker.pcr.pageNum == idx?'class =active':''}"/>>
				<a href="listSearch${pageMaker.makeSearch(idx) }">${idx }</a>
			</li>
		</c:forEach>

		<c:if test="${pageMaker.next }">
			<li><a
				href="listSearch${pageMaker.makeSearch(pageMaker.endPage + 1) }">&raquo;</a></li>
		</c:if>

	</ul>
</div>

<%@ include file="../include/footer.jsp"%>