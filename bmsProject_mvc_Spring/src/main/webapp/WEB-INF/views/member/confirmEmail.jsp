<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body>
<h2>중복확인 페이지</h2>

	<form action="confirmEmail.do" method="post" name="confirmform"
		onsubmit="return confirmIdCheck();"  >
		<c:if test="${emailCheck==1}"><!-- id 중복 -->
			<table class="tb">
				<tr>
					<th colspan="2">
						<span>${email}</span>는 사용할 수 없습니다.
					</th>
				</tr>
				<tr>
					<th>이메일 : </th>
					<td><input class="input" type="text" name="email" maxlength="20" 
						style="width:100px">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="inputButton" type="submit" value="확인" style="padding:15px 40px; background-color:white;border:1px solid gray; margin:10px;">
						<input class="inputButton" type="reset" value="취소" onclick="self.close();" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
					</th>
				</tr>
			</table>
		</c:if>
		<c:if test="${emailCheck!=1}"><!-- id 중복이 아닌 경우 -->
			<table class="tb">
				<tr>
					<td align="center">
						${email}에 인증번호를 보냈습니다.
					</td>
				</tr>
				<tr>
					<td align="center">인증번호 :<input type="text" name="CheckNum"></td>
				</tr>
				<tr>
					<th>
						<!-- 부모 쪽으로 넘길 id를 set -->
						<input class="inputButton" type="button" value="인증하기" onclick="setEmail('${result}',document.all.CheckNum.value);" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
					</th>
				</tr>
			</table>
		</c:if>
	</form>


</body>
</html>