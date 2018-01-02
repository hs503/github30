<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %><html>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}
	tr,th,td{
		padding:7px 0;
		border-bottom:1px solid #dddddd;
	}
	table{
	border-collapse: collapse; border-spacing: 0;
	}
</style>
<body onload="writeFocus();">
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<h2 style="width:800px; margin:0 auto;">글쓰기</h2>
	<br><br>
	
	<form action="writePro.bd" method="post" name="writeform" onsubmit="return writeCheck();">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${ref_step}">
		<input type="hidden" name="ref_level" value="${ref_level}">
		
		<table align="center" style="border:1px solid #dddddd; width:800px">
			<tr >
				<th colspan="2" style="border-top:2px solid gray; background-color:#eeeeee; border-bottom:2px solid gray;">글쓰기</th>
			</tr>
			<tr>
				<td width="80"> 
					&nbsp&nbsp&nbsp작성자
				</td>		
				<td width="720">
					<input class="input" type="text" name="writer" style="width:98%">
				</td>
			</tr>
			<tr>
				<td> 
					&nbsp&nbsp&nbsp비밀번호
				</td>
				<td>
					<input class="input" type="password" name="pwd" maxlength="10"style="width:98%">
				</td>
			</tr>
			<tr>
				<td> 
					&nbsp&nbsp&nbsp제목
				</td>
				<td>
					<input class="input" type="text" name="subject" maxlength="50" style="width:98%">
				</td>
			</tr>
			<tr>
				<td> 
					&nbsp&nbsp&nbsp내용
				</td>
				<td>
					<textarea class="input" rows="20" cols="40" name="content" style="width:98%"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2"> 
					<input class="inputButton" type="submit" value="작성">
					<input class="inputButton" type="reset" value="취소">
					<input class="inputButton" type="button" value="목록보기" onclick="window.location='list.bd'">
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