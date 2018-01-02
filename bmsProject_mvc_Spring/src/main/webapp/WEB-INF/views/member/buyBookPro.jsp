<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>

<html>
<head>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<c:if test="${sessionScope.memId!=null}">
		<div style="margin:0 auto; width:800px;">
			<form action="buyBook.do" method="post" name="buyForm">
				<input type="hidden" name="num"	value="${num}">
				<input type="hidden" name="code" value="${code}">
				<input type="hidden" name="amount" value="${amount}">
				<input type="hidden" name="title" value="${title}">
				<input type="hidden" name="price" value="${(amount*1)*(price*1)}">
				<input type="hidden" name="addrChange" value="${param.addrChange}">
				<h4>◎ 결제 정보 (Guest)</h4>
				<table>
					<tr>
						<td>구매수량</td>
						<td>${amount}
					</tr>
					<tr>
						<td>총 결제금액</td>
						<td>${(amount*1)*(price*1)}
					</tr>
					<tr>
						<td>결제수단</td>
						<td>
							<input type="radio" name="payment" value="1">신용카드결제
							<input type="radio" name="payment" value="2">실시간 계좌이체
						</td>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="확인">
							<input type="button" value="배송지 변경" onclick="window.location='buyBookPro.do?num=${num}&code=${code}&amount=${amount}&price=${price}&title=${title}&addrChange=1'">
						</td>
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
					</tr>
					<c:if test="${param.addrChange==1}">
						
					<tr>
						<td rowspan="2">배송지 변경</td>
						<td>
							<input type="text" name="postNum" id="sample6_postcode" placeholder="우편번호">
							<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" name="addr1" id="sample6_address" placeholder="주소">
							<input type="text" name="addr2" id="sample6_address2" placeholder="상세주소">
						</td>
					</tr>
					</c:if>
				</table>
			</form>
		</div>
	</c:if>
	<c:if test="${sessionScope.memId==null}">
		<div style="margin:0 auto; width:800px;">
		<form action="buyBook.do" method="post" name="gestAddrForm">
			<input type="hidden" name="num"	value="${num}">
			<input type="hidden" name="code" value="${code}">
			<input type="hidden" name="title" value="${title}">
			<input type="hidden" name="amount" value="${amount}">
			<input type="hidden" name="price" value="${(amount*1)*(price*1)}">
		<h4>◎ 주문자 정보 (Guest)</h4>
		<table>
			<tr>
				<td>주문자 이름</td>
				<td><input type="text" name="name">
			</tr>
			<tr>
				<td>핸드폰 연락처</td>
				<td>
					<input type="text" name="hp1">
					-
					<input type="text" name="hp2">
					-
					<input type="text" name="hp3">
				</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>
					<input type="text" name="email1">
					@
					<input type="text" name="email2">
					<select name="email3">
						<option value="0">직접입력</option>
						<option value="naver.com">네이버</option>
						<option value="gmail.com">구글</option>
						<option value="daum.net">다음</option>
					</select>
				</td>
			</tr>
			<tr>
				<td rowspan="2">배송지 주소</td>
				<td>
					<input type="text" name="postNum" id="sample6_postcode" placeholder="우편번호">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
				</td>
			</tr>
			<tr>
				<td>
					<input type="text" name="addr1" id="sample6_address" placeholder="주소">
					<input type="text" name="addr2" id="sample6_address2" placeholder="상세주소">
				</td>
			</tr>
		</table>
		<h4>◎ 결제 정보 (Guest)</h4>
		<table>
			<tr>
				<td>구매수량</td>
				<td>${amount}
			</tr>
			<tr>
				<td>총 결제금액</td>
				<td>${(amount*1)*(price*1)}
			</tr>
			<tr>
				<td>결제수단</td>
				<td>
					<input type="radio" name="payment" value="1">신용카드결제
					<input type="radio" name="payment" value="2">실시간 계좌이체
				</td>
			</tr>
			<tr><td><br></td></tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인">
					
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
	</c:if>
	
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>