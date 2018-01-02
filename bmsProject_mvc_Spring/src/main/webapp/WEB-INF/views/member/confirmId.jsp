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

	<form action="confirmId.do" method="post" name="confirmform"
		onsubmit="return confirmIdCheck();"  >
		<c:if test="${cnt==1}"><!-- id 중복 -->
			<table class="tb">
				<tr>
					<th colspan="2">
						<span>${id}</span>는 사용할 수 없습니다.
					</th>
				</tr>
				<tr>
					<th>아이디 : </th>
					<td><input class="input" type="text" name="id" maxlength="20" 
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
		<c:if test="${cnt!=1}"><!-- id 중복이 아닌 경우 -->
			<table class="tb">
				<tr>
					<td align="center">
						<span>${id}</span>는 사용할 수 있습니다.
					</td>
				</tr>
				<tr>
					<td><br></td>
				</tr>
				<tr>
					<th>
						<!-- 부모 쪽으로 넘길 id를 set -->
						<input class="inputButton" type="button" value="확인" onclick="setId('${id}');" style="padding:15px 40px;background-color:black; color:white; margin:10px;">
					</th>
				</tr>
			</table>
		</c:if>
	</form>


</body>
</html>