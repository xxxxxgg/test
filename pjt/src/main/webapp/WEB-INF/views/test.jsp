<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
<style>
#modDiv {
	width:300px;
	height:100px;
	background-color:gray;
	position:absolute;
	top:50%;
	left:50%;
	margin-tip:-50px;
	margin-left:-150px;
	padding:10px;
	z-index:1000;
}
</style>
</head>
<body>
<h2>Ajax Test Page</h2>

<div>
	<div>
		REPLYER <input type="text" name="replyer" id="newReplyWriter">
	</div>
	<div>
		REPLY TEXT <input type="text" name="replytext" id="newReplyText">
	</div>
	<button id="replyAddBtn">ADD REPLY</button>
</div>

<ul id="reply">
</ul>
<ul class="pagination">
</ul>

<div id="modDiv" style="display:none;">
	<div class="modal-title"></div>
	<div>
		<input type="text" id="replytext">
	</div>
	<div>
		<button type="button" id="replyModBtn">Modify</button>
		<button type="button" id="replyDelBtn">Delete</button>
		<button type="button" id="closeBtn">Close</button>
	</div>
</div>

<!-- jQuery 2.1.4 -->
<script type="text/javascript" src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>

var bno = 11290;
var replyPage = 1;

$("#replyAddBtn").on("click", function() {
	var replyer = $("#newReplyWriter").val();
	var replytext = $("#newReplyText").val();
	
	$.ajax({
		type : 'get',
		url : '/messages/reply/all',
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "GET"
		},
		dataType : 'text',
		data : JSON.stringify({
			mid : mid,
			mpw : mpw,
			mname : mname,
			memail : memail
		}),
		success : function(result) {
			if(result == 'listAll success') {
				alert("등록 되었습니다.");
				getAllList();
			}
		}
	});
});
				
$("#reply").on("click", ".replyLi button", function() {
	var reply = $(this).parent();
	var rno = reply.attr("data-rno");
	var replytext = reply.text();

	$(".modal-title").html(rno);
	$("#replytext").val(replytext);
	$("#modDiv").show("slow");
});

$("#replyModBtn").on("click", function() {
	var rno = $(".modal-title").html();
	var replytext = $("#replytext").val();
	
	$.ajax({
		type : 'put',
		url : '/reply/' + rno,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "PUT"
		},
		dataType : 'text',
		data : JSON.stringify({replytext : replytext}),
		success : function(result) {
			console.log("result: " + result)
			if(result == 'modify Success') {
				alert("수정 되었습니다.");
				$("#modDiv").hide("slow");
				getPageList(replyPage);
			}
		}
	});
});
$("#replyDelBtn").on("click", function() {
	var rno = $(".modal-title").html();
	var replytext = $("#replytext").val();
	
	$.ajax({
		type : 'delete',
		url : '/reply/' + rno,
		headers : {
			"Content-Type" : "application/json",
			"X-HTTP-Method-Override" : "DELETE"
		},
		dataType : 'text',
		success : function(result) {
			console.log("result: " + result)
			if(result == 'remove Success') {
				alert("삭제 되었습니다.");
				$("#modDiv").hide("slow");
				getPageList(replyPage);
			}
		}
	});
});


$(".pagination").on("click", "li a", function(event) {
	event.preventDefault();
	
	replyPage = $(this).attr("href");
	
	getPageList(replyPage);
});


function getAllList() {
	$.getJSON("/messages/reply/all", function(data) {
		console.log(data.length);
		var str = "";
		
		$(data).each(function() {
			str += "<li data-rno='"+this.mid+"' class='replyLi'>"
				+ this.mpw + ":" + this.mname + this.memail + "</li>";
		});
		$("#reply").html(str);
	});
}
function getPageList(pageNum) {
	$.getJSON("/reply/" + bno + "/" + pageNum, function(data) {
		console.log(data.replylist.length);
		
		var str = "";
	
		$(data.replylist).each(function() {
			str += "<li data-rno='" + this.rno + "' class='replyLi'>"
				+ this.rno + " : " + this.replytext + " BY " + this.replyer + "  <button>MOD</button></li>";
		});
	
		$("#reply").html(str);
		
		printPaging(data.pageMaker);
	});
}

function printPaging(pageMaker) {
	var str = "";
	console.log(pageMaker.endPage);
	
	if(pageMaker.prev) {
		str += "<li><a href='" + (pageMaker.startPage - 1) + "'> << </a></li>";
	}
	
	for(var i = pageMaker.startPage, len = pageMaker.endPage; i <= len; i++) {
		var strClass = pageMaker.pcr.pageNum == i?' class=\"active\"':'';
		str += "<li" + strClass + "><a href='"+ i +"'>" + i + "</a></li>";
	}
	
	if(pageMaker.next) {
		str += "<li><a href='"+(pageMaker.endPage + 1)+"'> >> </a></li>";
	}
	console.log(str);
	$('.pagination').html(str);
}
</script>

</body>
</html>