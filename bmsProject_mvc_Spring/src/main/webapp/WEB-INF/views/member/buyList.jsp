<%@page import="bmsProject.mvc.Spring.vo.buyListVo"%>
<%@page import="bmsProject.mvc.Spring.vo.DeliveryVo"%>
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
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:50px;"></div>
	<c:if test="${(refundCnt*1)>0}">
		<script type="text/javascript">
			alert("환불신청 되었습니다.");
		</script>
	</c:if>
	<c:if test="${(ConfirmationCnt*1)>0}">
		<script type="text/javascript">
			alert("구매확정 되었습니다.");
		</script>
	</c:if>
	
	<h1 align="center">구매목록 </h1>
	<div style="margin:0 auto; width:1200px">
				<div style="margin:0 auto; width:1020px">
					<h3 align="right"><a href="cart.do">장바구니보기▶</a></h3>
				</div>
				<table style="border-spacing:0px;" align="center">
					<tr>
						<th style="width:80px; padding:10px 0;">번호</th>
						<th style="width:150px; padding:10px 0;">책 이미지</th>
						<th style="width:250px; padding:10px 0;">책 제목</th>
						<th style="width:200px; padding:10px 0;">저자</th>
						<th style="width:130px; padding:10px 0;">최종 구매가</th>
						<th style="width:80px; padding:10px 0;">구매수량</th>
						<th style="width:130px; padding:10px 0;">환불/구매확정</th>
					</tr>
					<!-- 회원이 있으면 -->
					<c:if test="${cnt>0}">
						<%
							int i=0;
						%>
						<c:forEach var="dto" items="${dtos}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
							<%
								ArrayList<buyListVo> a=(ArrayList<buyListVo>)request.getAttribute("dtos");
								int price=a.get(i).getPrice();
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
								<td align="center"><!-- 최종 구매가 -->
									<%=df.format(price)%>원
								</td>
								<td align="center"><!-- 구매 수량 -->
									${dto.amount}
								</td>
								<td align="center"><!-- 환불/구매확정 버튼 -->
									<c:if test="${dto.refund.equals('배송대기중')}">
										배송대기중..
									</c:if>
									<c:if test="${!dto.refund.equals('배송대기중')}">
										<ul>
											<li><input type="button" onclick="window.location='refund.do?num=${dto.num}'" value="환불">
											<li><input type="button" onclick="window.location='Confirmation.do?num=${dto.num}'" value="구매확정">
										</ul>
									</c:if>
								</td>
							</tr>
							<tr>
								<th colspan="8" style="font-size:10px;">&nbsp</th>
							</tr>
						</c:forEach>
						
					</c:if>
					<!-- 구매목록이 비었으면 -->
					<c:if test="${cnt==0}">
						<tr>
							<td colspan="8" align="center">
								구매목록에 담긴것이 없습니다.
							</td>
						</tr>
						<tr>
							<th colspan="8" style="font-size:10px;">&nbsp</th>
						</tr>
					</c:if>		
				</table>
				<!-- 페이지 컨트롤 -->
				<table style="width:1020px;" align="center">
					<th align="center">
						<c:if test="${cnt>0}">
							<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
							<c:if test="${startPage>pageBlock}">
								<a href="buyList.do">[◀◀]</a>
								<a href="buyList.do?pageNum=${startPage-pageBlock}">[◀]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i==currentPage}">
									<span><b>[${i}]</b></span>
								</c:if>
								<c:if test="${i!=currentPage}">
									<a href="buyList.do?pageNum=${i}">[${i}]</a>
								</c:if>					
							</c:forEach>
							
							<!-- 다음블록[▶]/끝[▶▶] -->
							<c:if test="${pageCount > endPage}">
								<a href="buyList.do?pageNum=${startPage+pageBlock}">[▶]</a>
								<a href="buyList.do?pageNum=${pageCount}">[▶▶]</a>
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