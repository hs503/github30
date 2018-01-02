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
	<h2><center> 글삭제 - 처리페이지</center></h2>
	
	<!-- 비밀번호가 다른 경우 : "비밀번호가 일치하지 않습니다.\n 확인 후 다시 시도하세요."; 메시지 -->
	<c:if test="${selectCnt==0}">
		<script type="text/javascript">
			errorAlert(pwdError)
		</script>
	</c:if>
	<!-- 비밀번호가 일치하는 경우 -->
	<c:if test="${selectCnt!=0}">
		<!-- 1. 답글이 있는경우 '답글이 있으므로 삭제할수 없습니다.' -->
		<c:if test="${deleteCnt == -1}">
			<script type="text/javascript">
				alert("답글이 있으므로 삭제할수 없습니다.");
				window.location="list.bd?pageNum=${pageNum}";
			</script>		
		</c:if>
	
		<!-- 2. 답글이 없는경우 삭제 실패인 경우 : '삭제 실패' 메시지-->
		<c:if test="${deleteCnt==0}">
			<script type="text/javascript">
				alert("글 삭제에 실패하였습니다. \n 확인 후 다시 시도하세요.");		
				window.location="list.bd?pageNum=${pageNum}";
			</script>
		</c:if>
		<!-- 3. 답글이 없는경우 삭제 성공인 경우 : '삭제 성공' 메시지-->
		<c:if test="${deleteCnt==1}">
			<script type="text/javascript">
				alert("글 삭제에 성공하였습니다.");
				window.location="list.bd?pageNum=${pageNum}";
			</script>
		</c:if>
	</c:if>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>