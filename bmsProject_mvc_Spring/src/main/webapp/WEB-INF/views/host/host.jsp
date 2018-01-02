<%@page import="bmsProject.mvc.Spring.vo.DeliveryVo"%>
<%@page import="bmsProject.mvc.Spring.vo.closingVo"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="bmsProject.mvc.Spring.vo.bookListVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./hostSetting.jsp" %>
<html>
<head>
<style type="text/css">

	@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

	ul{list-style:none; margin:0px; padding:0px;}
	.Button{
		width:117px; height:111px;
		cursor:pointer;
	}
	th,td{
		padding:5px;
	}
</style>

</head>
<body>
	<c:if test="${(delivary*1)>0}">
		<script type="text/javascript">
			alert("배송되었습니다.");
		</script>
	</c:if>
	<div style="background-color:black; width:1800px">
		<table style="float:left;">
			<tr>
				<th style="text-size:30px; color:white; margin-top:4px;">BKsT. 판매관리자</th>
			</tr>
		</table>
		<table style="margin-left:auto; margin-right:0">
			<tr>
				<td style="float:right; background-color:green; color:white; margin-top:4px;">관리자</td>
				<th style="float:right; color:white; margin-top:4px;">관리자님 안녕하세요</th>
				<td><input type="button" value="메인으로 가기" onclick="window.location='main.do'" style="background-color:white;border:1px solid gray; padding:5px;"></td>
			</tr>
		</table>
	</div>
	
	<div style="background-color:gray; width:117px; height:444px; float:left">
		<ul>
			<!-- 관리자 메뉴 메인 -->
			<li><input class="Button" onclick="window.location='host.ho'" type="button" style="background:url(/Spring/resources/images/H_main.png) no-repeat;"></li>
			<!-- 회원정보보기 -->
			<li><input class="Button" onclick="window.location='Hmember.ho'" type="button" style="background:url(/Spring/resources/images/H_member.png) no-repeat;"></li>
			<!-- 재고관리
				1. 추가,삭제,수정
			 -->
			<li><input class="Button" onclick="window.location='HostBookList'" type="button" style="background:url(/Spring/resources/images/H_article.png) no-repeat;"></li>
			<!-- 주문관리 
				1. 주문목록
				2. 주문승인
			-->
			<li><input class="Button" onclick="window.location='orderList.ho'" type="button" style="background:url(/Spring/resources/images/H_order.png) no-repeat;"></li>
			<li><input type="button" onclick="" style="width:117px; height:111px;" value="게시판 관리"></li>
			<li></li>
		</ul>
	</div>
	<div style="height:1000px; width:1800px; background-color:#dddddd;">
		<div style="padding:5px"></div>
			<!-- 1번 기본정보 클릭했을때 처리 ------------------------------------------------------------------------------------------ -->
			<c:if test="${(hostMain*1)>0}">
				<table style="float:left;">
					<tr>
						<!-- 구글차트 -->
						<%
							closingVo dto=(closingVo)request.getAttribute("dto");
						%>
						<th>총 판매금액 : </th>
						<td><%=df.format(dto.getTotalSale())%>원</td><!-- ${dto.totalSale} -->
					</tr>
					<tr>
						<th>총 판매 수량:</th>
						<td>${dto.human*1+dto.medical*1+dto.it*1+dto.etc*1+dto.social*1}개
					</tr>
					<tr>
						<th>재고품절 갯수:</th>
						<td>${dto.soltOutCnt}
					</tr>
					<tr>
						<th>환불신청 갯수:</th>
						<td>${dto.refundCnt}</td>
					</tr>
				</table>
				
				<div style="display: -webkit-box; float:left">
					<div id="firstChat"></div>
				</div>
				
				<div>
					<!-- 구글맵 키 AIzaSyCPypPlIRfTwGn4AczwErqXHUYZJH0gO6Q  -->
					<style>
				      #map {
				        height: 360px;
				        width: 400px;
				       }
				    </style>
 					<div>
					    <span style="font-size:20px;"><b>한국소프트웨어 인재개발원(COSMO)</b></span><br>
						주소 : 서울특별시 금천구 가산동 가산디지털2로 123
				   	</div>
				    <div id="map" ></div>
				   
				    <script>
				      function initMap() { 
				        var uluru = {lat: 37.478792, lng: 126.878710}; //경도와 위도
				        var map = new google.maps.Map(document.getElementById('map'), {
				          zoom: 14,
				          center: uluru
				        });
				        var marker = new google.maps.Marker({
				          position: uluru,
				          map: map
				        });
				      }
				    </script>
				    <script async defer
				    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCPypPlIRfTwGn4AczwErqXHUYZJH0gO6Q&callback=initMap">
				    </script>
				</div>
				<c:set var="human" value="${dto.human*1}"></c:set>
				<c:set var="medical" value="${dto.medical*1}"></c:set>
				<c:set var="it" value="${dto.it*1}"></c:set>
				<c:set var="social" value="${dto.social*1}"></c:set>
				<c:set var="etc" value="${dto.etc*1}"></c:set>
				<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
				<script type="text/javascript">
					google.charts.load('current', {'packages':['corechart']});
					google.charts.setOnLoadCallback(drawChartFirst);
					var human='<c:out value="${human}"/>';
					var medical='<c:out value="${medical}"/>';
					var it='<c:out value="${it}"/>';
					var social='<c:out value="${social}"/>';
					var etc='<c:out value="${etc}"/>';
					var firstChart_options={
							title : '구매수량',
							width : 600,
							height: 400,
							bar : {
								groupWidth:'50%'
							},
							legend : {
								position : 'bottom'
							}
					};
					function drawChartFirst(){
						var data=google.visualization.arrayToDataTable([
							['Element','도서별'],
							['인문',human*1],
							['사회',social*1],
							['IT',it*1],
							['의학',medical*1],
							['기타',etc*1]
						]);
						var firstChart=new google.visualization.ColumnChart(document.getElementById('firstChat'));
						firstChart.draw(data,firstChart_options);
					} 
				</script>
			</c:if>
			<!-- 1번 기본정보 클릭했을때 처리 ------------------------------------------------------------------------------------------ -->
			<!-- 3번 상품관리 클릭했을때 처리 ------------------------------------------------------------------------------------------ -->
			<c:if test="${bookList==1}">
					<h2 align="center">책 재고</h2>
					<table border="1" align="center">
					<tr>
						<th>번호</th>
						<th>물류코드</th>
						<th>책 이미지</th>
						<th>책 제목</th>
						<th>저자</th>
						<th>판매가</th>
						<th>재고수량</th>
						<th>등록일</th>						
					</tr>
					<!-- 상품이 있으면 -->
					<c:if test="${cnt>0}">
						<%
							ArrayList<bookListVo> a=(ArrayList<bookListVo>)request.getAttribute("dtos");
						%>
						<%
							for(int n=0;n<a.size();n++){
								int code=a.get(n).getCode();
								String image=a.get(n).getImage();
								String title=a.get(n).getTitle();
								String author=a.get(n).getAuthor();
								int price=a.get(n).getPrice();
								int amount=a.get(n).getAmount();
								Timestamp publisher=a.get(n).getPublisher();
								String[] form={"form1","form2","form3","form4","form5"};
								String[] bookUpdate={"bookUpdate1","bookUpdate2","bookUpdate3","bookUpdate4","bookUpdate5"};
						%>
							<tr>
								<form action="updateBook.ho" method="post" name=<%=form[n]%> onsubmit="return <%=bookUpdate[n]%>();" enctype="multipart/form-data">
									<input type="hidden" name="code" value="<%=code%>">
									<td align="center"><!-- 글번호 -->
										${number}
										<c:set var="number" value="${number-1}"/>
									</td>
									<td align="center"><!-- id-->
										<%=code%>
									</td>
									<td align="center"><!-- name -->
										<input type="file" name="image" value="<%=image%>">
									</td>
									<td align="center"><!-- gender -->
										<input type="text" name="title" value="<%=title%>">
									</td>
									<td align="center"><!-- birth -->
										<input type="text" name="author" value="<%=author%>">
									</td>
									<td align="center"><!-- email -->
										<input type="text" name="price" value="<%=df.format(price)%>">
									</td>
									<td align="center"><!-- hp -->
										<input type="text" name="amount" value="<%=amount%>">
									</td>
									<td align="center"><!-- hp -->
										<%=publisher%>
									</td>
									<td align="center"><!-- 삭제버튼 -->
										<input type="submit" value="수정">
										<input type="button" value="삭제" onclick="window.location='deleteBook.ho?code=<%=code%>'">
									</td>
								</form>
							</tr>
						<%
							}
						%>						
					</c:if>
					<!-- 상품이 없으면 -->
					<c:if test="${cnt==0}">
						<tr>
							<td colspan="8" align="center">
								회원이 없습니다.
							</td>
						</tr>
					</c:if>		
				</table>
				
					<table style="width:1000px" align="center" border="1">
						<th align="center">
							<c:if test="${cnt>0}">
								<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
								<c:if test="${startPage>pageBlock}">
									<a href="HostBookList">[◀◀]</a>
									<a href="HostBookList?pageNum=${startPage-pageBlock}">[◀]</a>
								</c:if>
								
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:if test="${i==currentPage}">
										<span><b>[${i}]</b></span>
									</c:if>
									<c:if test="${i!=currentPage}">
										<a href="HostBookList?pageNum=${i}">[${i}]</a>
									</c:if>					
								</c:forEach>
								
								<!-- 다음블록[▶]/끝[▶▶] -->
								<c:if test="${pageCount > endPage}">
									<a href="HostBookList?pageNum=${startPage+pageBlock}">[▶]</a>
									<a href="HostBookList?pageNum=${pageCount}">[▶▶]</a>
								</c:if>
							</c:if>
						</th>
					</table>
				<!-- 재고추가---------------------------------------------------------------------------- -->
				<div style="margin-top:10px">
					<form action="insertBook.ho" method="post" name="bookInsertForm" onsubmit="return bookInsert();" enctype="multipart/form-data">
						<h2 style="margin-left:25%">재고 추가</h2>
						<table border="1" align="center">
							<tr>
								<th>물류코드</th>
								<th>책 이미지</th>
								<th>책 제목</th>
								<th>저자</th>
								<th>판매가</th>
								<th>책 종류</th>
								<th>재고수량</th>
								
							</tr>
							<tr>
								<td style="width:70"><input type="text" name="code" style="width:100%"></td>
								<td style="width:200"><input type="file" name="image" style="width:100%"></td>
								<td style="width:200"><input type="text" name="title" style="width:100%"></td>
								<td style="width:150"><input type="text" name="author" style="width:100%"></td>
								<td style="width:70"><input type="text" name="price" style="width:100%"></td>
								<td style="width:100">
									<select name="bookChat" style="width:100%">
										<option value="etc">기타</option>
										<option value="medical">의학</option>
										<option value="it">IT</option>
										<option value="human">인문</option>
										<option value="social">사회</option>
									</select>
								</td>
								<td style="width:80"><input type="text" name="amount" style="width:100%"></td>
								<td colspan="2">
									<input type="submit" value="추가">
									<input type="reset" value="취소">
								</td>
							</tr>
						</table>
					</form>
				</div>
				<!-- 재고추가---------------------------------------------------------------------------- -->
			</c:if>
			<!-- 3번 상품관리 클릭했을때 처리end --------------------------------------------------------------------------------------- -->
			<!-- 2번 회원관리 클릭했을때 처리 ------------------------------------------------------------------------------------------ -->
			<c:if test="${Hmember==1}">
				<h2 align="center">회원</h2>
				<table border="1" align="center">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>이름</th>
						<th>성별</th>
						<th>생년월일</th>
						<th>이메일</th>
						<th>연락처</th>
						<th>배송지</th>
						<th>회원삭제</th>
					</tr>
					<!-- 회원이 있으면 -->
					<c:if test="${cnt>0}">
						<c:forEach var="dto" items="${dtos}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
							<tr>
								<td align="center"><!-- 글번호 -->
									${number}
									<c:set var="number" value="${number-1}"/>
								</td>
								<td align="center"><!-- id-->
									${dto.id}
								</td>
								<td align="center"><!-- name -->
									${dto.name}
								</td>
								<td align="center"><!-- gender -->
									${dto.gender}
								</td>
								<td align="center"><!-- birth -->
									${dto.birth}
								</td>
								<td align="center"><!-- email -->
									${dto.email}
								</td>
								<td align="center"><!-- hp -->
									${dto.hp}
								</td>
								<td align="center"><!-- addr -->
									${dto.addr}
								</td>
								
								<td align="center"><!-- 삭제버튼 -->
									<input type="button" id="deleteBtn3" onclick="window.location='deleteMem.ho?id=${dto.id}'" value="삭제">
								</td>
							</tr>
						
						</c:forEach>
						
					</c:if>
					<!-- 회원이 없으면 -->
					<c:if test="${cnt==0}">
						<tr>
							<td colspan="9" align="center">
								회원이 없습니다.
							</td>
						</tr>
					</c:if>		
				</table>
				<!-- 페이지 컨트롤 -->
				<table style="width:1000px" align="center" border="1">
					<th align="center">
						<c:if test="${cnt>0}">
							<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
							<c:if test="${startPage>pageBlock}">
								<a href="Hmember.ho">[◀◀]</a>
								<a href="Hmember.ho?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i==currentPage}">
									<span><b>[${i}]</b></span>
								</c:if>
								<c:if test="${i!=currentPage}">
									<a href="Hmember.ho?pageNum=${i}">[${i}]</a>
								</c:if>					
							</c:forEach>
							
							<!-- 다음블록[▶]/끝[▶▶] -->
							<c:if test="${pageCount > endPage}">
								<a href="Hmember.ho?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="Hmember.ho?pageNum=${pageCount}">[▶▶]</a>
							</c:if>
						</c:if>
					</th>
				</table>
			</c:if>
			<!-- 2번 회원정보 클릭했을때 처리end ------------------------------------------------------------------------------------------ -->
			<!-- 4번 주문관리 클릭했을때 처리end ------------------------------------------------------------------------------------------ -->
			<c:if test="${orderList==1}">
				<!-- 구매 테이블 ------------------------------------------------------------------------------------- -->
				<c:if test="${(del*1)>0}">
					<script type="text/javascript">
						alert("삭제되었습니다.");
					</script>
				</c:if>
				<c:if test="${(refundCnt*1)>0}">
					<script type="text/javascript">
						alert("환불하였습니다.");
					</script>
				</c:if>
				
				<h2 align="center">구매</h2>
				<table border="1" align="center">
					<tr>
						<th>번호</th>
						<th>아이디</th>
						<th>고객명</th>
						<th>연락처</th>
						<th>이메일</th>
						<th>배송지 주소</th>
						<th>책제목</th>
						<th>구매수량</th>
						<th>구매날짜</th>
						<th>결제금액</th>
						<th>환불여부</th>
					</tr>
					<!-- 회원이 있으면 -->
					<c:if test="${cnt1>0}">
						<%
							int i=0;
						%>
						<c:forEach var="dto" items="${dtos1}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
						<%
							ArrayList<DeliveryVo> a=(ArrayList<DeliveryVo>)request.getAttribute("dtos1");
							
							int price=a.get(i).getPrice();
							i++;
						%>
							<tr>
								<td align="center"><!-- 글번호 -->
									${number1}
									<c:set var="number1" value="${number1-1}"/>
								</td>
								<td align="center"><!-- id-->
									${dto.id}
								</td>
								<td align="center"><!-- name -->
									${dto.name}
								</td>
								<td align="center"><!-- hp -->
									${dto.hp}
								</td>
								<td align="center"><!-- email -->
									${dto.email}
								</td>
								<td align="center"><!-- addr -->
									${dto.addr}
								</td>
								<td align="center"><!-- title -->
									${dto.title}
								</td>
								<td align="center"><!-- amount -->
									${dto.amount}
								</td>
								<td align="center"><!-- publisher -->
									${dto.publisher}
								</td>
								<td align="center"><!-- price -->
									<%=df.format(price)%>
								</td>
								<td align="center"><!-- price -->
									<c:if test="${dto.refund.equals('구매확정')}">
										<span style="color:green">${dto.refund}</span>
									</c:if>
									<c:if test="${dto.refund.equals('환불신청')}">
										<span style="color:red">${dto.refund}</span>
									</c:if>
									<c:if test="${dto.refund.equals('판매')}">
										<span style="color:blue">${dto.refund}</span>
									</c:if>
									<c:if test="${dto.refund.equals('환불')}">
										<span style="text-decoration:line-through">${dto.refund}</span>
									</c:if>
									<c:if test="${dto.refund.equals('배송대기중')}">
										<b>${dto.refund}</b>
									</c:if>		
								</td>
								<c:if test="${dto.refund.equals('환불')}">
									<td>
										<input type="button" onclick="window.location='deleteDel.ho?num=${dto.num}'" value="삭제">
									</td>
								</c:if>
								
								<c:if test="${dto.refund.equals('환불신청')}">
									<td align="center"><!-- 환불하기 -->
										<input type="button" onclick="window.location='hostRefund?price=${dto.price}&title=${dto.title}&amount=${dto.amount}&num=${dto.num}'" value="환불하기">
									</td>
								</c:if>
								<c:if test="${dto.refund.equals('배송대기중')}">
									<td align="center"><!-- 배송하기 -->
										<input type="button" onclick="window.location='Delivery.ho?num=${dto.num}&price=${dto.price}&amount=${dto.amount}&title=${dto.title}'" value="배송하기">
									</td>
								</c:if>
							</tr>
						</c:forEach>
						
					</c:if>
					<!-- 회원이 없으면 -->
					<c:if test="${cnt1==0}">
						<tr>
							<td colspan="10" align="center">
								주문이 없습니다.
							</td>
						</tr>
					</c:if>		
				</table>
				<!-- 페이지 컨트롤 -->
				<table style="width:1000px" align="center" border="1">
					<th align="center">
						<c:if test="${cnt1>0}">
							<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
							<c:if test="${startPage1>pageBlock1}">
								<a href="orderList.ho">[◀◀]</a>
								<a href="orderList.ho?pageNum1=${startPage1-pageBlock1}">[◀]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage1}" end="${endPage1}">
								<c:if test="${i==currentPage1}">
									<span><b>[${i}]</b></span>
								</c:if>
								<c:if test="${i!=currentPage1}">
									<a href="orderList.ho?pageNum1=${i}">[${i}]</a>
								</c:if>					
							</c:forEach>
							
							<!-- 다음블록[▶]/끝[▶▶] -->
							<c:if test="${pageCount1 > endPage1}">
								<a href="orderList.ho?pageNum1=${startPage1+pageBlock1}">[▶]</a>
								<a href="orderList.ho?pageNum1=${pageCount1}">[▶▶]</a>
							</c:if>
						</c:if>
					</th>
				</table>
				<!-- 구매 테이블 ------------------------------------------------------------------------------------- -->
			</c:if>
			<!-- 4번 주문관리 클릭했을때 처리end ------------------------------------------------------------------------------------------ -->
	</div>
</body>
</html>