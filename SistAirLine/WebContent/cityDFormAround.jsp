<%@page import="java.util.List"%>
<%@page import="cityDTO.CityVO"%>
<%@page import="cityDTO.CityDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	CityDAO cityDao = new CityDAO();
	List<CityVO> list= (List<CityVO>) cityDao.cityList();
%>
<html>
<head>
<meta charset="UTF-8">
<title>SIST AIRLINES 취향지 도시</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {
        'packages':['geochart'],
        // Note: you will need to get a mapsApiKey for your project.
        // See: https://developers.google.com/chart/interactive/docs/basic_load_libs#load-settings
        'mapsApiKey': 'AIzaSyD-9tSrke72PouQMnMX-a7eZSW0jkFMBWY'
      });
      google.charts.setOnLoadCallback(drawRegionsMap);

      function drawRegionsMap() {
        var data = google.visualization.arrayToDataTable([
       	   ['Country', '국가' ,'도시'],
           ['KR', '대한민국' , 1],
           ['Japan', '일본', 3],
           ['China', '중국', 3],
           ['Vietnam', '베트남', 3],
           ['Philippines', '필리핀', 2],
           ['Singapore','싱가폴', 1],
           ['Russia','러시아', 1]
       ]);

        var options = {
                region: '142'
        };

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);
      }
    </script>
  </head>
</head>
<style>
.menubar li ul {
		background: rgb(109,109,109);
		display:none;  /* 평상시에는 서브메뉴가 안보이게 하기 */
		height:auto;
		padding:0px;
		margin:0px;
		border:0px;
		position:absolute;
		width:200px;
		z-index:200;
		}
	.menubar li:hover ul {
		display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
		}A
	table {
    width: 90%;
    margin-left: auto; 
    margin-right: auto;
    align-text:center;
    background-color: #c3e6e5;}
   th, td {
    padding: 7px 10px 10px 10px;
    }
   th {
    text-transform: uppercase;
    letter-spacing: 0.1em;
    font-size: 90%;
    border-bottom: 2px solid #111111;
    border-top: 1px solid #999;
    text-align: center;
    }
   tr{background-color: #c3e6e5;}
   tr.even {
    background-color: #efefef;
    }
   tr:hover {
    background-color: #ffffff;}
</style>
<body>
	<div style="width:100%; margin:auto;">
		<h3 style="text-align:center;">SIST AIRLINES 취향지 도시</h3>
		<hr/>
		<div id="regions_div" style="width: 100%; height: 300px;"></div>
		<table id="listTable" >
	<%
		int i=0;
		for(CityVO outVO : list){
			i=i+1;
	%>
		<tr style="width:100%;">
		<td style="width:75%;"><%=outVO.getContinentKor()%></td>
		<td id="cInput<%=i%>" style="width:50%; text-align:center;"><%=outVO.getCityKor() %></td>
		</tr>
	<%
		}
	%>
		</table>
	</div>
	
	
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript">
	$("#listTable").on("click","tr",function(event){
		
		var tr = $(this);
        var td = tr.children();
        console.log("td.eq(1).text() : "+td.eq(1).text());
        opener.document.getElementById("dCityAr").value = td.eq(1).text();
        window.close()
		
	});
</script>
</body>
</html>