<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body onload="passwdFocus();">
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end --> 

	<h2>회원정보 수정</h2>
		<!--  
			  아이디와 패스워드가 일치하면 (selectCnt == 1) 입력한 회원정보를 읽어온다.
			  패스워드가 일치하지 않으면 (selectCnt == -1) | 아이디가 존재하지 않으면 (selectCnt == 0)
		-->
	<c:if test="${param.selectCnt==-1}">
		<span style="color:red">패스워드가 일치하지 않습니다.</span>
	</c:if>
	
	<form action="modifyView.do" method="post" name="passwdform"
			onsubmit="return passwdCheck();">
		<table border="1px solid gray">
			<tr>
				<th colspan="2" style="padding:10px">
					비밀번호 입력확인
				</th>
			</tr>
			
			<tr>
				<th style="padding:10px"> 비밀번호 </th>
				<td style="padding:10px">
					<input class="input" type="password" name="pwd" maxlength="10">
				</td>
			</tr>
			
			<tr>
				<th colspan="2" style="padding:10px">
					<input class="inputButton" type="submit" value="정보수정">
					<input class="inputButton" type="reset" value="수정취소"
							onclick="window.location='main.do'">
				</th>	 
			</tr>		
		</table>		
	</form>

	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>