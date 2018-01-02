<%@page import="java.util.ArrayList"%>
<%@page import="bmsProject.mvc.Spring.vo.cartVo"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<html>
<head>
<style type="text/css">
	table,tr,td{
		border:0.1px solid #bbbbbb;
	}
	@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}
	#td1 tr td{
		text-align:center
	}
</style>
</head>
<c:if test="${(amountChangeCnt*1)>0}">
	<script type="text/javascript">
		alert("수량이 변경되었습니다.");
	</script>
</c:if>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:50px;"></div>
	<c:if test="${cartCnt>0}">
		<script type="text/javascript">
			alert("삭제되었습니다.");
		</script>
	</c:if>
	<c:if test="${buyBookCnt>0}">
		<script type="text/javascript">
			alert("구매되었습니다.");
		</script>
	</c:if>
	<c:if test="${AmountTest>0}">
		<script type="text/javascript">
			alert("재고가 부족하여 구매하실수 없습니다.");
		</script>	
	</c:if>
	<h1 align="center">장바구니 </h1>
	<div style="margin:0 auto; width:1200px">
				<div style="margin:0 auto; width:1100px">
				<h3 align="right"><a href="buyList.do">구매목록보기▶</a></h3>
				</div>
				<table style="border-spacing:0px;" align="center">
					<tr>
						<th style="width:80px; padding:10px 0;">번호</th>
						<th style="width:150px; padding:10px 0;">책 이미지</th>
						<th style="width:250px; padding:10px 0;">책 제목</th>
						<th style="width:200px; padding:10px 0;">저자</th>
						<th style="width:80px; padding:10px 0;">판매가</th>
						<th style="width:100px; padding:10px 0;">구매수량</th>
						<th style="width:170px; padding:10px 0;">합계</th>
						<th style="width:100px; padding:10px 0;">삭제/구매</th>
					</tr>
					<!-- 회원이 있으면 -->
					<c:if test="${cnt>0}">
						<%
							int i=0;
							int j=1;
						%>
						
						<c:forEach var="dto" items="${dtos}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
							<%
								ArrayList<cartVo> a=(ArrayList<cartVo>)request.getAttribute("dtos");
								int price=a.get(i).getPrice();
								int amount=a.get(i).getAmount();
								int sum=price*amount;
								i++;
							%>
							<tr>
								<td align="center"><!-- 글번호 -->
									${number}
									<c:set var="number" value="${number-1}"/>
								</td>
								<td align="center"><!-- 책 이미지 -->
									<img src="/Spring/resources/saveImg/${dto.image}" style="width:150px; height:195px;">
								</td>
								<td align="center"><!-- 책 제목 -->
									${dto.title}
								</td>
								<td align="center"><!-- 책 저자 -->
									${dto.author}
								</td>
								<td align="center"><!-- 책 가격 -->
									<%=df.format(price)%>원
								</td>
								<td align="center"><!-- 구매 수량 -->
									<div>
										${dto.amount}
										<form action="" name="amountForm"+<%=j%>>
											<input type="hidden" name="num1" value="${dto.num}">
											<select name="selectAmount" onchange="amountChange(this.form)" style="width:20px">
												<option value=""></option>
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
											</select>
										</form>
										<%
											j++;
										%>
									</div>
									
								</td>
								<td align="center"><!-- 합계 -->
								 	<%=df.format(sum)%>원
								</td>
								<td align="center"><!-- 삭제/구매 버튼 -->
									<ul>
										<li><input type="button" onclick="window.location='cartDelete.do?num=${dto.num}'" value="삭제">
										<li><input type="button" onclick="window.location='buyBookPro.do?num=${dto.num}&title=${dto.title}&price=${(dto.price*1)*(dto.amount)}&amount=${dto.amount}&code=${dto.code}'" value="구매">
									</ul>
								</td>
							</tr>
							<tr>
								<th colspan="8" style="font-size:10px;">&nbsp</th>
							</tr>
						</c:forEach>
						
					</c:if>
					<!-- 장바구니가 비었으면 -->
					<c:if test="${cnt==0}">
						<tr>
							<td colspan="8" align="center">
								장바구니에 담긴것이 없습니다.
							</td>
						</tr>
						<tr>
							<th colspan="8" style="font-size:10px;">&nbsp</th>
						</tr>
					</c:if>		
				</table>
				<!-- 페이지 컨트롤 -->
				<table style="width:1110px;" align="center">
					<th align="center">
						<c:if test="${cnt>0}">
							<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
							<c:if test="${startPage>pageBlock}">
								<a href="cart.do">[◀◀]</a>
								<a href="cart.do?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i==currentPage}">
									<span><b>[${i}]</b></span>
								</c:if>
								<c:if test="${i!=currentPage}">
									<a href="cart.do?pageNum=${i}">[${i}]</a>
								</c:if>					
							</c:forEach>
							
							<!-- 다음블록[▶]/끝[▶▶] -->
							<c:if test="${pageCount > endPage}">
								<a href="cart.do?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="cart.do?pageNum=${pageCount}">[▶▶]</a>
							</c:if>
						</c:if>
						<c:if test="${cnt==0}">
							[0]
						</c:if>
					</th>
				</table>
	</div>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
			
</body>
</html>