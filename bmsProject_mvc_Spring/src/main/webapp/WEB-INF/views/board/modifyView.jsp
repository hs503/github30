<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body onload="modifyFocus();">
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<c:if test="${selectCnt==0}">
		<script type="text/javascript">
			errorAlert(pwdError);
		</script>
	</c:if>
	<c:if test="${selectCnt!=0}">
		<form action="BDmodifyPro" method="post" name="modifyform" onsubmit="return modifyCheck();">
			
			<input type="hidden" name="num" value="${num}">
			<input type="hidden" name="pageNum" value="${pageNum}">
			
			<table align="center">
				<tr>
					<th colspan="2"> 수정할 정보를 입력하세요. </th>
				</tr>
				<tr>
					<th> 작성자 </th>
					<td> ${dto.writer}</td>
				</tr>
				<tr>
					<th> 글제목 </th>
					<td> 
						<input class="input" type="text" name="subject" maxlength="50"
						       value="${dto.subject}" style="width:270px">
					</td>
				</tr>
				<tr>
					<th> 글내용 </th>
					<td> 
						<textarea class="input" rows="10" cols="40" name="content">${dto.content}</textarea>
					</td>
				</tr>
				<tr>
					<th> 비밀번호 </th>
					<td> 
						<input class="input" type="password" name="pwd" maxlength="10" value="${dto.pwd}">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input class="inputButton" type="submit" value="수정">
						<input class="inputButton" type="reset" value="수정취소">
						<input class="inputButton" type="button" value="목록보기" onclick="window.location='list.bd?pageNum=${pageNum}'">
						
					</th>
				</tr>
			</table>
		</form>
	</c:if>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>