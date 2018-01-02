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

		<c:if test="${cnt==0}">
		<script type="text/javascript">
			errorAlert(insertError);
		</script>
		</c:if>
		<!-- /* insert 성공 */ -->
	<div style="width:100px; height:20px;">
	</div>
	<div style="width:700px;  margin-top:100px; border:1px solid #cccccc; margin:0 auto; margin-bottom:30px; padding:50px 0">
		
		<table style="margin-bottom:100px;margin:0 auto;" id="td1">
			<tr>
				<td><img src="/Spring/resources/images/signUpIMG.png"></td>
			</tr>
			<tr>
				<td>${name}님의 회원가입을 축하합니다.<br>
			</tr>
			<tr>
				<td><br>
			</tr>
			<tr>
				<td><hr style="width:600px"><br>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="홈으로" onclick="window.location='main.do'" style="padding:20px 80px;background-color:white;border:1px solid gray; margin:10px;">
					<input type="button" value="로그인" onclick="window.location='login.do'" style="padding:20px 80px;background-color:black; color:white; margin:10px;">
				</td>
			</tr>
		
		</table>
		
	</div>
	</form>
	<!-- main_end -->	
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>