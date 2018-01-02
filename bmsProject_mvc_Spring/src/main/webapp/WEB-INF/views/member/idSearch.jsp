<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body>
<h2>아이디 찾기</h2>
		<c:if test="${cnt==null}">
			<form action="idSearchPro.do" method="post" name="confirmform"
			onsubmit="return idSearchPro(document.confirmform.email.value);"  >
				<table class="tb">
					<tr>
						<th colspan="2">
							아이디 검색
						</th>
					</tr>
					<tr>
						<th>이메일 :</th>
						<td><input class="input" type="text" name="email" maxlength="20" 
							style="width:200px">
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input class="inputButton" type="submit" value="검색" style="padding:15px 40px; background-color:white;border:1px solid gray; margin:10px;">
							<input class="inputButton" type="reset" value="취소" onclick="self.close();" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
						</th>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${cnt==1}">
			<table class="tb">
				<tr>
					<td align="center">
						아이디가 없습니다 회원가입을 해주세요.
					</td>
				</tr>
				<tr>
					<th>
						<input class="inputButton" type="button" value="회원가입하러 가기" onclick="SignUp();" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
					</th>
				</tr>
			</table>
		</c:if>
		<c:if test="${cnt==2}">
			<form action="" method="post" name="confirmform">
				<table class="tb">
					<tr>
						<td align="center">
							[${email}] 에 인증번호를 보냈습니다.
						</td>
					</tr>
					<tr>
						<td align="center">인증번호 : <input type="text" name="CheckNum"></td>
					</tr>
					<tr>
						<th>
							<!-- 부모 쪽으로 넘길 id를 set -->
							<input class="inputButton" type="button" value="인증하기" onclick="SearchId('${id}','${result}',document.confirmform.CheckNum.value);" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
						</th>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${cnt==3}">
			<table class="tb">
				<tr>
					<th>아이디 : ${id}</th>
				</tr>
				<tr>
					<th>
						<input class="inputButton" type="reset" value="확인" onclick="self.close();" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
					</th>
				</tr>
			</table>
		</c:if>
	


</body>
</html>