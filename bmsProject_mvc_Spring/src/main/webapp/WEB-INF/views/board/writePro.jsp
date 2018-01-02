<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<h2> <center>글쓰기 - 처리페이지</center></h2>
	<!-- 글쓰기 실패 -->
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(insertError);
		</script>	
	</c:if>
	<!-- 글쓰기 성공 -->
	<c:if test="${cnt!=0}">
		<c:redirect url="list.bd"/>
	</c:if>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>