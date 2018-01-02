<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<body onload="pwdFocus();">
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<form action="BDmodifyView" method="post" name="pwdForm"
			onsubmit="return pwdCheck();">
		<!-- submit으로 값을 넘기되 보이지 않게 처리하고 싶을때 hidden을 사용한다. -->
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<table align="center">
			<tr>
				<th colspan="2">
					비밀번호를 입력하세요!!
				</th>			
			</tr>
			<tr>
				<th> 비밀번호</th>
				<td>
					<input type="password" name="pwd" maxlength="10">
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input class="inputButton" type="submit" value="확인">
					<input class="inputButton" type="reset" value="취소" onclick="window.history.back();">
				</th>			
			</tr>
		</table>
	</form>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>