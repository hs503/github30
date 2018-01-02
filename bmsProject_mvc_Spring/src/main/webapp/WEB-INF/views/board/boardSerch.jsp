<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="setting.jsp" %>

<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}
	tr,th,td{
		padding:10px 0;
	}
	#title th{
		background-color:#eeeeee;
		border-bottom:2px solid gray;
	}
	#boardbody td{
		border-bottom:1px solid #bbbbbb;
	}
	a{
		color:black;
	}
</style>
</head>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<h2 style="width:1000px; margin:0 auto;">게시판</h2>
	<br>
	<table style="width:1000px; border-bottom:1px solid #bbbbbb; border-collapse: collapse; border-spacing: 0" align="center">
		<tr>
			<th colspan="6" align="center" style="height:25px; border-bottom:2px solid gray;">
				<div style="width:1000px; margin:0 auto;" align="right">
					<c:if test="${sessionScope.memId==null}">
						<span style="margin-right:200px;">글목록(글갯수 : ${cntB})&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</c:if>
					<c:if test="${sessionScope.memId!=null}"><!-- 로그인 상태 -->
						<span style="margin-right:150px;">글목록(글갯수 : ${cntB})&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<a href="writeForm.bd?pageNum=${pageNumB}">글쓰기</a>
					</c:if>
					
					<input type="text" name="serch">
					<input type="button" value="검색" onclick="boardSerch(serch.value);">
				</div>
			</th>
		</tr>
		<tr id="title">
			<th style="width:15%; border-left:1px solid #bbbbbb;">글번호</th>
			<th style="width:25%">글제목</th>
			<th style="width:10%">작성자</th>
			<th style="width:15%">작성일</th>
			<th style="width:5%">조회수</th>
			<th style="width:10%; border-right:1px solid #bbbbbb;">id</th>
		</tr>
		<!-- 게시글이 있으면 -->
		<c:if test="${cntB>0}">
			<c:forEach var="dto" items="${dtosB}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
				<tr id="boardbody">
					<td align="center"><!-- 글번호 -->
						${numberB}
						<c:set var="number" value="${numberB-1}"/>
					</td>
					<td align="center"><!-- 글제목 -->
						<!-- 추가 -->
						<c:if test="${dto.ref_level>1}"><!-- 들여쓰기 > 1 답변글 --->
							<c:set var="wid" value="${(dto.ref_level-1)*10}" />
							<img src="${project}images/level.gif" border="0" width="${wid}" height="15">
						</c:if>
						<!-- 들여쓰기 >0 : 답변글 -->
						<c:if test="${dto.ref_level>0}"><!-- 들여쓰기 > 1 -->
							<img src="${project}images/re.gif" border="0" width="20" height="15">
						</c:if>	
						<!-- 추가 end -->
						<a href="contentForm.bd?num=${dto.num}&pageNum=${pageNumB}&number=${numberB+1}">${dto.subject}</a>
						
						<!-- hot 이미지 -->
						<c:if test="${dto.readCnt>10}">
							<img src="${project}images/hot.gif" border="0" width="20" height="15">
						
						</c:if>
					</td>
					<td align="center"><!-- 작성자 -->
						${dto.writer}
					</td>
					<td align="center"><!-- 작성일 -->
						<fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${dto.req_date}"/>
					</td>
					<td align="center"><!-- 조회수 -->
						${dto.readCnt}
					</td>
					<td align="center"><!-- id -->
						${dto.id}
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<!-- 게시글이 없으면 -->
		<c:if test="${cntB==0}">
			<tr>
				<td colspan="6" align="center">
					게시글이 없습니다. 글을 작성해주세요.!!
				</td>
			</tr>
		</c:if>		
	</table>
	<!-- 페이지 컨트롤 -->
	<table style="width:1000px" align="center">
		<th align="center">
			<c:if test="${cntB>0}">
				<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
				<c:if test="${startPageB>pageBlockB}">
					<a href="list.bd">[◀◀]</a>
					<a href="list.bd?pageNum=${startPageB-pageBlockB}">[◀]</a>
				</c:if>
				
				<c:forEach var="i" begin="${startPageB}" end="${endPageB}">
					<c:if test="${i==currentPageB}">
						<span><b style="font-size:18px">[${i}]</b></span>
					</c:if>
					<c:if test="${i!=currentPageB}">
						<a href="list.bd?pageNum=${i}">[${i}]</a>
					</c:if>					
				</c:forEach>
				
				<!-- 다음블록[▶]/끝[▶▶] -->
				<c:if test="${pageCountB > endPageB}">
					<a href="list.bd?pageNum=${startPageB+pageBlockB}">[▶]</a>
					<a href="list.bd?pageNum=${pageCountB}">[▶▶]</a>
				</c:if>
			</c:if>
		</th>
	</table>
	<div style="margin:100px;"></div>
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>