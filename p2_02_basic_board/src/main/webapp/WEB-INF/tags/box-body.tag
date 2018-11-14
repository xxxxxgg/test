<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="jsp" %>
<%@ tag dynamic-attributes="attrMap" %>

<c:if test="${jsp eq 'registerPage'}">
<div class="box-body">
	
	<c:forEach items="${attrMap}" var="attr">
		<div class="form-group">
			<label for="exampleInputEmail1">${attr.key }</label>
			<input type="text" name="${attr.key }" class="form-control" placeholder="Enter Title">
		</div>
	</c:forEach>
		
</div>
</c:if>

<c:if test="${jsp eq 'readPage' }">
<div class="box-body">
	
	<c:forEach items="${attrMap}" var="attr">
		<div class="form-group">
			<label for="exampleInputEmail1">${attr.key}</label>
			<input type="text" name="${attr.key}" class="form-control" value="${attr.value}" readonly="readonly">
		</div>
	</c:forEach>

</div>
</c:if>