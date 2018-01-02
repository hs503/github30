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
	<c:if test="${cnt1>0}">
		<script type="text/javascript">
			alert("장바구니에 담겼습니다.");
		</script>
	</c:if>
	<c:if test="${DirectByCnt>0}">
		<script type="text/javascript">
			alert("구매하였습니다.");
		</script>
	</c:if>
	<div style="margin:100px;"></div>
	<div style="margin:0 auto; width:950px;">
	<form name="detailForm" action="cartInsert.do" method="post" onsubmit="return amountCheck();">
	<input type="hidden" name="title" value="${dto.title}">
	<input type="hidden" name="code" value="${dto.code}">
	<input type="hidden" name="amount1" value="${dto.amount}">
	<input type="hidden" name="price" value="${dto.price}">
	<input type="hidden" name="image" value="${dto.image}">
		<table style="border-spacing:0px; border:0.1px solid #dddddd;">
		<tr>
			<td><img src="/Spring/resources/saveImg/${dto.image}" style="width:500px; height:670px;"></td>
			<td style="width:500px; padding:30px;">
				<h2 style="text-align:center">${dto.title}</h2>
				<hr style="border:0.5px solid #dddddd;"><br><br>
				<table>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp저자 :</td>
						<td>${dto.author}
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp출간일 :</td>
						<td><fmt:formatDate type="both" pattern="yyyy-MM-dd" value="${dto.publisher}"/>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp판매가격 :</td>
						<td>${dto.price}원</td>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp물류코드 :</td>
						<td>${dto.code}
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp재고수량 :</td>
						<c:if test="${(dto.amount*1)>0}">
							<td>${dto.amount}
						</c:if>
						<c:if test="${(dto.amount*1)<=0}">
							<td><span style="color:red;">품절</span>
						</c:if>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<td><span style="color:#cccccc; font-size:10px;">●</span>&nbsp구매수량 :</td>
						<td>
							<input type="text" name="amount" style="size:30px;" value="1">
							<select name="buyCntSelect" onchange="buyCntCheck(this.form)">
							<option value="0">직접입력</option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
						</select>
						</td>
					</tr>
				</table>
				<br><br>
				<hr style="border:0.5px solid #dddddd;"><br><br>
				<c:if test="${(dto.amount*1)>0}">
					<c:if test="${!sessionScope.memId.equals('host')}">
						<h2 style="align:center">
							<input type="submit" value="" style="float:left; background:url(/bmsProject/images/cartBt.png) no-repeat; width:105px;height:87px; border:0.9px solid #dddddd; margin:0 10px; border-radius:20px;">
							<input type="button" onclick="return DirectPro();" value="구매하기" style="width:120px; height:87px; background-color:black; border-radius:20px; color:white; margin:0 10px;">
						</h2>
					</c:if>
				</c:if>
			</td>
		</tr>
	</table>
	</form>


	</div>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>