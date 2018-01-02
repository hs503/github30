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

	<h1 align="center">검색 </h1>
	<div style="margin:0 auto; width:1200px">
			<table style="border-spacing:0px;" align="center">
					<tr>
						<th style="width:80px; padding:10px 0;">번호</th>
						<th style="width:150px; padding:10px 0;">책 이미지</th>
						<th style="width:250px; padding:10px 0;">책 제목</th>
						<th style="width:200px; padding:10px 0;">저자</th>
						<th style="width:80px; padding:10px 0;">판매가</th>
						<th style="width:80px; padding:10px 0;">재고수량</th>
						<th style="width:100px; padding:10px 0;">출간일</th>
					</tr>
					<!-- 회원이 있으면 -->
					<c:if test="${cnt>0}">
						<c:forEach var="dto" items="${dtos}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
							<tr>
								<td align="center"><!-- 글번호 -->
									${number}
									<c:set var="number" value="${number-1}"/>
								</td>
								<td align="center"><!-- 책 이미지 -->
									<a href="detailPage.bo?image=${dto.image}" style="color:black;"><img src="/Spring/resources/saveImg/${dto.image}" style="width:150px; height:195px;"></a>
								</td>
								<td align="center"><!-- 책 제목 -->
									<a href="detailPage.bo?image=${dto.image}" style="color:black;">${dto.title}</a>
								</td>
								<td align="center"><!-- 책 저자 -->
									${dto.author}
								</td>
								<td align="center"><!-- 판매가 -->
									${dto.price}
								</td>
								<td align="center"><!-- 재고 수량 -->
									${dto.amount}
								</td>
								<td align="center"><!-- 출간일 -->
								 	${dto.publisher}	
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
								검색값이 없습니다.
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
								<a href="serch.do?&serch=${serch}">[◀◀]</a>
								<a href="serch.do?pageNum=${startPage-pageBlock}&serch=${serch}">[◀]</a>
							</c:if>
							
							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<c:if test="${i==currentPage}">
									<span><b>[${i}]</b></span>
								</c:if>
								<c:if test="${i!=currentPage}">
									<a href="serch.do?pageNum=${i}&serch=${serch}">[${i}]</a>
								</c:if>					
							</c:forEach>
							
							<!-- 다음블록[▶]/끝[▶▶] -->
							<c:if test="${pageCount > endPage}">
								<a href="serch.do?pageNum=${startPage+pageBlock}&serch=${serch}">[▶]</a>
								<a href="serch.do?pageNum=${pageCount}&serch=${serch}">[▶▶]</a>
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