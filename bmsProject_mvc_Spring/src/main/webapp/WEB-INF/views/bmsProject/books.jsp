<%@page import="java.sql.Timestamp"%>
<%@page import="bmsProject.mvc.Spring.vo.bookListVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
<html>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<div style="margin:100px;"></div>
	<div style="margin:0 auto; width:1100px;">
		<table style="margin-left:5%;">
			<tr>
				<th> <h3>새로나온 책</h3> </th>
				<th> <span style="font-size:15px;">&nbsp&nbsp|&nbsp&nbsp</span> </th>
				<th> <h3>베스트셀러</h3> </th>
			</tr>		
		</table>
		<br><br><br>
		<table style="margin-left:3.5%;" align="cneter">
					<!-- 상품이 있으면 -->
					<c:if test="${cnt>0}">
						<%
							ArrayList<bookListVo> a=(ArrayList<bookListVo>)request.getAttribute("dtos");
						%>
						<tr align="center">
						<%
							if(a.size()>5){
								for(int n=0;n<5;n++){
									int code=a.get(n).getCode();
									String image=a.get(n).getImage();
									String title=a.get(n).getTitle();
									String author=a.get(n).getAuthor();
									int amount=a.get(n).getAmount();
						%>	
									<td align="center" style="height:300px; vertical-align:top;">
										<ul >
											<li style="border:1px solid gray;"><a href="detailPage.bo?image=<%=image%>"><img src="/Spring/resources/saveImg/<%=image%>" style="width:150px; height:200px;"></a></li><!-- 책 이미지 -->
											<li>&nbsp</li>
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=title%></a></li><!-- 책 제목 -->
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=author%></a></li><!-- 저자 -->
						<%
												if(amount<=0){
						%>
												<li style="color:red;">품절</li>
						<%
												}
						%>
										</ul>
									</td>
						<%
								}
						%>	
								</tr>
								<tr align="center">
						<%
								for(int n=5;n<a.size();n++){
									int code=a.get(n).getCode();
									String image=a.get(n).getImage();
									String title=a.get(n).getTitle();
									String author=a.get(n).getAuthor();
						%>	
									<td align="center" style="height:300px; vertical-align:top;">
										<ul>
											<li style="border:1px solid gray;"><a href="detailPage.bo?image=<%=image%>"><img src="/Spring/resources/saveImg/<%=image%>" style="width:150px; height:200px;"></a> </li><!-- 책 이미지 -->
											<li>&nbsp</li>
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=title%></a></li><!-- 책 제목 -->
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=author%></a></li><!-- 저자 -->
										</ul>
									</td>
						<%
								}
							}else{
								for(int n=0;n<a.size();n++){
									int code=a.get(n).getCode();
									String image=a.get(n).getImage();
									String title=a.get(n).getTitle();
									String author=a.get(n).getAuthor();
									int amount=a.get(n).getAmount();
						%>	
									<td align="center" style="height:300px; vertical-align:top;">
										<ul >
											<li style="border:1px solid gray;"><a href="detailPage.bo?image=<%=image%>"><img src="/Spring/resources/saveImg/<%=image%>" style="width:150px; height:200px;"></a> </li><!-- 책 이미지 -->
											<li>&nbsp</li>
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=title%></a></li><!-- 책 제목 -->
											<li style="font-size:11px; width:140px;"><a href="detailPage.bo?image=<%=image%>"><%=author%></a></li><!-- 저자 -->
						<%
												if(amount<=0){
						%>
												<li style="color:red;">품절</li>
						<%
												}
						%>
										</ul>
									</td>
						<%
								}
						
							}
						%>	
						</tr>					
					</c:if>
					<!-- 상품이 없으면 -->
					<c:if test="${cnt==0}">
						<tr>
							<td colspan="6" align="center">
								회원이 없습니다.
							</td>
						</tr>
					</c:if>		
				</table>
				
					<table style="width:1000px" align="center">
						<th align="center">
							<c:if test="${cnt>0}">
								<!-- 처음[◀◀]/이전블록[◀] 특수문자 :ㅁ한자키 -->
								<c:if test="${startPage>pageBlock}">
									<a href="bookList.bo">[◀◀]</a>
									<a href="bookList.bo?pageNum=${startPage-pageBlock}">[◀]</a>
								</c:if>
								
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<c:if test="${i==currentPage}">
										<span><b>[${i}]</b></span>
									</c:if>
									<c:if test="${i!=currentPage}">
										<a href="bookList.bo?pageNum=${i}">[${i}]</a>
									</c:if>					
								</c:forEach>
								
								<!-- 다음블록[▶]/끝[▶▶] -->
								<c:if test="${pageCount > endPage}">
									<a href="bookList.bo?pageNum=${startPage+pageBlock}">[▶]</a>
									<a href="bookList.bo?pageNum=${pageCount}">[▶▶]</a>
								</c:if>
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