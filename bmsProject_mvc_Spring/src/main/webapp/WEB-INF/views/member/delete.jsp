<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body>
	<c:if test="${cnt!=0}">
		<script type="text/javascript">
			alert("탈퇴되었습니다.");
			window.location="main.do";
		</script>
	</c:if>
	<c:if test="${cnt==0}">
		<script type="text/javascript">
			alert("실패하였습니다. 다시시도해주세요.");
			window.history.back();
		</script>
	</c:if>
</body>
</html>