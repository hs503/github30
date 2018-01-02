<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bmsProject.mvc.Spring.vo.MemberVo" %>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body>
	<%
		
	%>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="width:800px; margin:100px auto;">
		<hr><br>
		<h2>회원정보 수정</h2><br>
		<c:if test="${selectCnt==1}"><!-- // 아이디와 패스워드가 일치하는 경우, mvc_member 테이블로부터 회원정보를 읽어온다. -->
			<form action="modifyPro.do" method="post" name="modifyform"
					onsubmit="return modifyCheck();">
				<table id="modifytb">
					<tr>
						<th> 아이디 </th>
						<td>&nbsp&nbsp ${vo.getId()}</td><!-- 안의 변수를 바로 가져와도 된다. ${vo.id} -->
					</tr>
					<tr>
						<th>비밀번호<span style="color:red;">*</span>
						<td><input type="password" name="pwd" style="width:220px" maxlength="20" value="${vo.getPwd()}"> 			
					</tr>
					<tr>
						<th>이름<span style="color:red;">*</span>
						<td><input type="text" name="name" style="width:220px" maxlength="10" value="${vo.getName()}">			
					</tr>
					<tr>
						<th>성별</th>
						<td>&nbsp&nbsp ${vo.getGender()}</td>	
					</tr>
					<tr>
						<th>생년월일</th>
						<td>&nbsp&nbsp ${vo.getBirth()}</td>
					</tr>
					<tr>
						<th>이메일<span style="color:red;">*</span>
						<td>	
							<%-- <c:forTokens items="${vo.getHp()}" var="emailArr" delims="@"/> --%>
							<c:set var="emailArr" value="${fn:split(vo.getEmail(),'@')}"/>
							<input class="input" type="text" name="email1" maxlength="10" style="width:100px" value="${emailArr[0]}">
							@
							<input class="input" type="text" name="email2" maxlength="20" style="width:100px" value="${emailArr[1]}">
						</td>				
					</tr>
					<tr>
						<th> 핸드폰 번호<span style="color:red;">*</span>
						<td>
							<c:set var="hpArr" value="${fn:split(vo.getHp(),'-')}"/>
								<%-- <c:forTokens items="${vo.getHp()}" var="hpArr" delims="-"/> --%><!-- //테이블 : '010-xxxx-xxxx' / 입력화면 : 쪼개져 있다 (hp1-hp2-hp3) -->
								<input class="input" type="text" name="hp1" maxlength="3" style="width:50px" value="${hpArr[0]}">	
								-
								<input class="input" type="text" name="hp2" maxlength="4" style="width:70px" value="${hpArr[1]}">
								-
								<input class="input" type="text" name="hp3" maxlength="4" style="width:70px" value="${hpArr[2]}">
						</td>
					</tr>
					<tr>
						<th> 배송지&nbsp</th>
						<td>&nbsp&nbsp ${vo.getAddr()}</td>
					</tr>
				</table>
				<table id="modifytb" style="margin-bottom:100px">
					<tr>
						<th rowspan="2" style="width:200px;">배송지수정<span style="color:red;">*</span></th>
						<td style="width:500px;">
							<input type="text" name="postNum" id="sample6_postcode" placeholder="우편번호">
							<input type="button" name="postBt" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						</td>
					</tr>
					<tr>
						<td style="width:1200px;">
							<input type="text" name="addr1" id="sample6_address" placeholder="주소">
							<input type="text" name="addr2" id="sample6_address2" placeholder="상세주소">
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<input type="submit" value="수정">
							<input type="button" value="수정취소" onclick="window.location='main.do'"> <!-- 2페이지 이전(=main.jsp)으로 이동 -->
							<input type="button" value="회원탈퇴" onclick="button_event();">
						</th>
					</tr>
				</table>	
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
				<script>
				    function sample6_execDaumPostcode() {
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var fullAddr = ''; // 최종 주소 변수
				                var extraAddr = ''; // 조합형 주소 변수
				
				                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
				                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
				                    fullAddr = data.roadAddress;
				
				                } else { // 사용자가 지번 주소를 선택했을 경우(J)
				                    fullAddr = data.jibunAddress;
				                }
				
				                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
				                if(data.userSelectedType === 'R'){
				                    //법정동명이 있을 경우 추가한다.
				                    if(data.bname !== ''){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있을 경우 추가한다.
				                    if(data.buildingName !== ''){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
				                document.getElementById('sample6_address').value = fullAddr;
				
				                // 커서를 상세주소 필드로 이동한다.
				                document.getElementById('sample6_address2').focus();
				            }
				        }).open();
				    }
				</script>
			</form>
		</c:if>
		<c:if test="${selectCnt!=1}">
			<script type="text/javascript">
				window.location="modifyForm.do?selectCnt=${selectCnt}";
			</script>
		</c:if>
	</div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>