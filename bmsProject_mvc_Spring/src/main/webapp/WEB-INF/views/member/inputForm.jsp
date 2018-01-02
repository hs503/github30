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
	<div style="width:800px; margin:0 auto; margin-top:60px">
		<h2>회원정보 입력</h2><hr><br>
		<form action="inputPro.do" method="post" name="inputForm" onsubmit="return inputCheck();">
			<!-- hiddenId : 중복확인 버튼 클릭 여부 체크(0:클릭안함, 1:클릭함) -->
			<input type="hidden" name="hiddenId" value="0">
			<input type="hidden" name="EmailCheck" value="0">
			<table id="signUptb">
				<tr>
					<th>아이디<span style="color:red; text-align:right;" >*</span>
					<td><input type="text" name="id" style="width:220px" maxlength="10">
					<td><input type="button"  name="dupChk" value="아이디 중복확인" onclick="confirmId();">
				</tr>
				<tr>
					<th>비밀번호<span style="color:red;">*</span>
					<td><input type="password" name="pwd" style="width:220px" maxlength="20"> 			
				</tr>
				<tr>
					<th>비밀번호 확인<span style="color:red;">*</span>
					<td><input type="password" name="repwd" style="width:220px" maxlength="20">	
				</tr>
				<tr>
					<th>이름<span style="color:red;">*</span>
					<td><input type="text" name="name" style="width:220px" maxlength="10">			
				</tr>
				<tr>
					<th>성별<span style="color:red;">*</span>
					<td><input type="radio" name="gender" value="1">남자
						<input type="radio" name="gender" value="2">여자</td>	
				</tr>
				<tr>
					<th>생년월일<span style="color:red;">*</span>
					<td><input type="text" name="birth" style="width:220px" value="ex)920503" onClick="this.value='';" maxlength="6"></td>
					<td><input type="radio" name="birth1" value="1">양력
						<input type="radio" name="birth1" value="2">음력</td>
				</tr>
				<tr>
					<th>이메일<span style="color:red;">*</span>
					<td>
						<input type="text" name="email1" style="width:80px">
						@
						<input type="text" name="email2" style="width:100px">
					</td>
					<td>
						<select name="email3" onchange="emailCheck(this.form)">
							<option value="0">직접입력</option>
							<option value="naver.com">네이버</option>
							<option value="gmail.com">구글</option>
							<option value="daum.net">다음</option>				
						</select>
						<input type="button" value="이메일확인" name="checkEmail" onclick="confirmEmail();">
					</td>
				</tr>
				<tr>
					<th>휴대전화<span style="color:red;">*</span>
					<td>
						<input type="text" name="hp1" style="width:45px;" maxlength="3">
						-
						<input type="text" name="hp2" style="width:55px;" maxlength="4">
						-
						<input type="text" name="hp3" style="width:55px;" maxlength="4">
					</td>
				</tr>
			</table>
			<table id="signUptb" style="margin-bottom:100px">
				<tr>
					<th rowspan="2">배송지 주소<span style="color:red;">*</span></th>
					<td>
						<input type="text" name="postNum" id="sample6_postcode" placeholder="우편번호">
						<input type="button" name="postBt" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					</td>
				</tr>
				<tr>
					<td>
						<input type="text" name="addr1" id="sample6_address" placeholder="주소">
						<input type="text" name="addr2" id="sample6_address2" placeholder="상세주소">
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="가입하기">
						<input type="reset" value="취소">
					</td>
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
	</div>
	
	<!-- main_end -->	
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>