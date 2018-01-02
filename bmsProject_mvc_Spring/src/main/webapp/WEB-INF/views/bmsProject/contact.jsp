<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../setting.jsp" %>
<style type="text/css">
@import url('http://fonts.googleapis.com/earlyaccess/nanumgothic.css');
	body{font-family:'Nanum Gothic';}
	table tr th{
		align:left;
	}
</style>
<html>
<body>
<div>
	<!-- header -------------------------------------------------------------------------------------------------------->
	<jsp:include page="../bmsInclude/header.jsp"/>
	<!-- header_end ---------------------------------------------------------------------------------------------------->
	<div style="margin:50px;"></div>
	<div style="margin:0 auto; width:950px;">
		<div style="border:1px solid #cccccc; border-radius:20px; padding:50px;">
	    	<span style="font-size:20px;"><b>한국소프트웨어 인재개발원(COSMO)</b></span><br><br>
		         주소 : 서울특별시 금천구 가산동 가산디지털2로 123<br>
		   	전화 : 02-xxx-xxxx<br>
		   	팩스 : 02-xxx-xxxx<br>
	   	</div>
		
	
		<!-- 구글맵---------------------------------------------------------------------------------------------------- -->
		<!-- 구글맵 키 AIzaSyCPypPlIRfTwGn4AczwErqXHUYZJH0gO6Q -->
		<div style="border:1px solid #cccccc; border-radius:20px; padding:50px;">
			<style>
		      #map {
		        height: 500px;
		        width: 850px;
		        margin:0 auto;
		       }
		    </style>
			<div>
				<table>
					<tr>
						<th><h2 style="color:green" align="left">오시는길</h2></th>
					</tr>
					<tr><td><br></td></tr>
					<tr>
						<th align="left" style="font-size:15">◎ 지하철</th>
					</tr>
					<tr>
						<td align="left" style="font-size:15">6번출구 나오셔서 좌측횡단보도 건너 좌측으로 10m가시면 삼거리가 나옵니다. 삼거리에서 우측 방향으로 직진하셔서 사거리 대각선방향에 우리은행건물 410호 입니다.</td>
					</tr>
					<tr>
						<th align="left" style="font-size:15">◎ 버스</th>
					</tr>
					<tr>
						<td align="left" style="font-size:15">21,571,652,금천 05[디지철3단지 월드벤처센터] 정류장에서 하차</td>
					</tr>
					<tr>
						<th align="left" style="font-size:15">◎ 자가용</th>
					</tr>
					<tr>
						<td align="left" style="font-size:15">서부간선도로를 타고 오다가 광명교를 타고 좌회전 후 첫 사거리에서 우회전</td>
					</tr>
				</table>
				<br>
			</div>
			<div id="map"></div>
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
		<!-- 구글맵---------------------------------------------------------------------------------------------------- -->
	</div>
	
	<div style="margin:100px;"></div>
 	<!-- footer ------------------------------------------------------------------------------------------------------->
	<jsp:include page="../bmsInclude/footer.jsp"/>
	<!-- footer_end --------------------------------------------------------------------------------------------------->
</body>
</html>