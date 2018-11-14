<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false"%>

<%@ include file="../include/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script>
	var result = '${result}'
	if ('success'.equals(result)) {
		alert("등록 성공을 축하합니다!!!");
	}
</script>


<table class="table table-bordered">
	<tr>
		<th style="width: 10px">BNO</th>
		<th>Title</th>
		<th>Writer</th>
		<th>RegDate</th>
		<th style="width: 40px">VIEWCNT</th>
	</tr>
		
	<c:forEach items="${allBoard}" var="boardVO">
		<tr>
			<td>${boardVO.bno}</td>
			<td><a href='/board/read?bno=${boardVO.bno}'>${boardVO.title}</a></td>
			<td>${boardVO.writer}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate}"/></td>
			<td><span class="badge bg-red">${boardVO.viewcnt}</span></td>
		</tr>
	</c:forEach>
</table>

<%@ include file="../include/footer.jsp"%>