<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>

<html>
<head>
</head>
<body>
<header>
		<c:if test="${sessionScope.memId==null}">
			<div id="user_in">
				<ul id="user_id1">
					<li><a href="#" onclick="window.location='inputForm.do'">회원가입</a></li>
					<li>&nbsp|&nbsp</li>
					<li><a href="#" onclick="window.location='login.do'">로그인</a></li>
				</ul>
			</div>
		</c:if>
		<c:if test="${sessionScope.memId!=null}"><!-- 로그인 상태 -->
			<div id="user_in">
				<ul id="user_id1">
					<c:if test="${!sessionScope.memId.equals('host')}">
						<li>&nbsp]&nbsp</li>
						<li><a href="logout.do">로그아웃</a>
						<li>&nbsp|&nbsp</li>
						<li><a href="modifyForm.do">내정보수정</a></li>
						<li>&nbsp|&nbsp</li>
						<li><a href="cart.do">장바구니</a></li>
						<li>&nbsp[&nbsp</li>
						<li>${sessionScope.memName}님 안녕하세요.</li>
					</c:if>
					<c:if test="${sessionScope.memId.equals('host')}">
						<li>&nbsp]&nbsp</li>
						<li><a href="logout.do">로그아웃</a>
						<li>&nbsp|&nbsp</li>
						<li><a href="host.ho">관리자메뉴</a></li>
						<li>&nbsp[&nbsp</li>
						<li>${sessionScope.memName}님 안녕하세요.</li>
					</c:if>
				</ul>
			</div>
		</c:if>
		<div id="tytle_serch">
			<form action="serch.do" method="post">
				<a href="main.do">BKsT.</a>
				<input type="text" name="serch">
				<input type="submit" value="검색">
			</form>
		</div>
		<hr>
		<div id="menu">
			<ul>
				<li class="books"><a href="bookList.bo">BOOKS</a></li>
				<c:if test="${!sessionScope.memId.equals('host')}">
					<li class="cart"><a href="cart.do">CART</a></li>
				</c:if>
				
				<li class="q&a"><a href="" onclick="return developing();">SUPPORT</a></li>
				<li class="event"><a href="" onclick="return developing();">EVENT</a></li>
			</ul>		
		</div>
		<hr>
	</header>
</body>
</html>