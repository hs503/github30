<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end --> 
	<!-- main -->
	
	<c:set var="memId" value="${sessionScope.memId}"/>
	<c:if test="${sessionScope.memId==null}">
		
		<form action="loginPro.do" name="login_fb" method="post" style="width: 650px; height: 600px; margin:10px auto">
			<br><br><h2 style="font-size:20px; font-weight:bold; margin-top:100px">로그인</h2><br><br><br><br>
			
			<c:if test="${cnt==0}">
				<span style="color:red;margin-left:85px;">아이디가 존재하지 않습니다.</span>
			</c:if>
			<c:if test="${cnt==-1}">
				<span style="color:red;margin-left:85px;">비밀번호가 틀렸습니다.</span>
			</c:if>
			<table id="login_table">
				<tr>
					<td><input type="text" name="id" value="아이디" style="padding:15px; width:280px; margin:5px; margin-left:85px" onClick="this.value='';"></td>
					<td rowspan="2"><input type="submit" class="loginButton" value="">
				</tr>
				<tr>
					<td><input type="password" name="pwd" value="비밀번호" style="padding:15px; width:280px; margin:5px;  margin-left:85px;" onClick="this.value='';"></td>
				</tr>
			</table>
			
			<table>
				<tr>
					<td><input type="button" class="idSearch" onclick="idSearch();" style="margin-left:85px;">
					<td><input type="button" class="pwdSearch" onclick="pwdSearch();">
					<td><input type="button" class="signUpbt" onclick="window.location='inputForm.do'">
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${sessionScope.memId!=null}">
			<c:redirect url="main.do?cnt=${cnt}"/>
	</c:if>
	
	<!-- main_end -->	
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
	
</body>
</html>