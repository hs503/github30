<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}
	tr,th,td{
		padding:10px 0;
	}
	table{
		border-collapse: collapse; border-spacing: 0;
	}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<h2 style="width:800px; margin:0 auto;">게시판</h2>
	<br><br>
	
	<table align="center" style="border:1px solid #cccccc; width:800px">
		<tr>
			<th style="border-bottom:2px solid gray;border-top:2px solid gray; background-color:#eeeeee;">${dto.subject}</th>
		</tr>
		<tr>
			<td style="border-bottom:1px solid #cccccc; font-size:13px;">
				&nbsp&nbsp&nbsp
				${dto.writer}
				&nbsp|&nbsp
				<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.req_date}"/>
				&nbsp|&nbsp
				조회수&nbsp
				${dto.readCnt}
				&nbsp|&nbsp
				글번호&nbsp
				${number}
			</td>
		</tr>
		<tr>
			<td><div style="margin:0 10px; border:1px solid #cccccc; height:500px; padding:20px; border-bottom:1px solid #cccccc;">${dto.content}</div></td>
		</tr>
		<tr>
			<th>
				<c:if test="${sessionScope.memId==dto.id}"><!-- 로그인 상태 -->
					<input class="inputButton" type="button" value="글수정" onclick="window.location='BDmodifyForm?num=${dto.num}&pageNum=${pageNum}'">
					<input class="inputButton" type="button" value="글삭제" onclick="window.location='deleteForm?num=${dto.num}&pageNum=${pageNum}'">
				</c:if>
				<c:if test="${sessionScope.memId!=null}"><!-- 로그인 상태 -->
					<input class="inputButton" type="button" value="답글쓰기" onclick="window.location='writeForm.bd?num=${dto.num}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'">
				</c:if>
				<input class="inputButton" type="button" value="목록보기" onclick="window.location='list.bd?pageNum=${pageNum}'">
			</th>
		</tr>	
	</table>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>