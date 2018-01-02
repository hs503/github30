<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<html>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}

</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	
	<style>
		body { margin: 0; padding:0; }
		#layout {position: relative; maring: auto; width: 820px; height:260px; display: flex; flex-warp: wrap; align-items: center; }
		#layout b {width: 10px; position: relative; z-index: 1; cursor: pointer;  }
		#banner  { position:relative; z-index:0; width:820px; height:260px;overflow:hidden; margin:auto; }
		#banner img {position: absolute; width: 200px; height: 260px; }
		#banner img:nth-child(1) { left:-200px; z-index:2; }
		#banner img:nth-child(2) { left:0px; }
		#banner img:nth-child(3) { left:200px; }
		#banner img:nth-child(4) { left:400px; }
		#banner img:nth-child(5) { left:600px; }
		#banner img:last-child { left:800px; }
	</style>
	
	<script>
	$(document).ready(function(){			
			var i=$("#banner img:first-child").attr('src').substr(-5,1);

			var rolling=setInterval(frame, 3000);
			function frame() {
					
					$("#banner img").removeClass("firstImg").animate({left:'-=200px'});		
					i++;
					if( i==5) {i=1}		
					var appendImg="<img src='/Spring/resources/saveImg/book"+i+".jpg'>";
					$("#banner img:nth-child(3)").addClass("firstImg");
					$("#banner").append(appendImg);
					if( $("#banner img:first-child").position().left<=-400 ) {  $("#banner img:first-child").remove()  }
			}
			

			$("#right").click(function(){
				clearInterval();
				$("#banner img").removeClass("firstImg").animate({left:'-=200px'});
				i++;
				if( i==5) {i=1}		
				var appendImg="<img src='/Spring/resources/saveImg/book"+i+".jpg'>";
				$("#banner img:nth-child(3)").addClass("firstImg");
				$("#banner").append(appendImg);
				if( $("#banner img:first-child").position().left<=-400 ) {  $("#banner img:first-child").remove()  }
			});
			
		
			$("#left").click(function(){				
				$("#banner img").removeClass("firstImg").animate({left:'+=200px'});	
				i=i-1;			
				if( i==0) {i=4}
				var prependImg="<img src='/Spring/resources/saveImg/book"+i+".jpg'>";
				$("#banner img:nth-child(2)").addClass("firstImg");
				$("#banner").prepend(prependImg);
				if( $("#banner img:last-child").position().left>=800 ) {  $("#banner img:last-child").remove()}
			});
	});
	</script>
<body>
	<!-- header -->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end -->
	<!-- main -->
	<div id="best_book">
		<c:set var="image12" value="book12.jpg"/>
		<a href="detailPage.bo?image=${image12}"><img src="/Spring/resources/images/K-011.png" width=1000 height=400></a>
	</div>

	<hr>
		<div id="blank">
			베스트 셀러
		</div>
	<hr>
	<div id="book_list">
			<div>&nbsp</div>
			<div id="layout" style="margin:0 auto;">
			<b id="left">&lt;</b>
			<div id="banner" onclick="window.location='bookList.bo'">
				<c:if test="${cnt>0}">
					<c:forEach var="dto" items="${dtos}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
						<img src="/Spring/resources/saveImg/${dto.image}" width="200" height="260">
					</c:forEach>
				</c:if>
			</div>
			<b id="right">&gt;</b>
			</div>
	</div>
	<div id="notice">
		<div style="float:left; margin-top:40px; padding:0 20px; width:450px; margin-right:10px;">
			<table style="width:440px;">
				<tr>
					<th align="left">게시판</th>
					<td style="color:#cccccc;" align="right"><a href="list.bd"><span style="font-size:15px; color:black;">더보기 ▶</span></a>
				</tr>
			</table>
			<br>
				<!-- 게시글이 있으면 -->
				<c:if test="${cntB>0}">
					<div style="width:440px; height:155px;">
					<c:forEach var="dto" items="${dtosB}"><!-- items : dtos(ArrayList)의 배열만큼 반복된다. -->
						<span style="color:#cccccc; font-size:1px;">■</span>&nbsp<a style="color:black" href="contentForm.bd?num=${dto.num}&pageNum=${pageNumB}&number=${numberB+1}">${dto.subject}</a><br>
						<hr style="border:1px solid #cccccc;">
					</c:forEach>
					</div>
				</c:if>
				<c:if test="${cntB==0}">
					<div style="padding:20px 25px; width:440px; height:150px;">
					<span style="color:#cccccc; font-size:1px;">■</span><a href="list.bd" style=" color:black">&nbsp게시글이 없습니다</a>
					</div>		
				</c:if>
		</div>
		<div>
			<img src="/Spring/resources/images/notice3.png" style="height:285px;">
		</div>
	</div>
	<div style="height:20px;">&nbsp</div>
	<!-- main_end -->	
 	<!-- footer -->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end -->
</body>
</html>