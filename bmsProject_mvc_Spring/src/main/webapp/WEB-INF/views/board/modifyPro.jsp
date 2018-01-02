<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(updateError);
		</script>	
	</c:if>
	<!-- 글쓰기 성공 -->
	<c:if test="${cnt!=0}">
		<c:redirect url="list.bd?pageNum=${pageNum}"/>
	</c:if>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>