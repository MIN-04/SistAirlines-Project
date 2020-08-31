<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<%
	String tripType = (String)request.getAttribute("radioValue");
	boolean notIC = (boolean)request.getAttribute("notIC");
	String dCity = (String)request.getAttribute("dCity");
	String aCity = (String)request.getAttribute("aCity");
	
	List<ScheduleVO> listOneWay=(List<ScheduleVO>)request.getAttribute("listOneWay"); // 편도 인천있을때
	List<ScheduleVO> listOneWay2=(List<ScheduleVO>)request.getAttribute("listOneWay2");// 편도 인천없을때
	
	//왕복
	List<ScheduleVO> listAround1=(List<ScheduleVO>)request.getAttribute("listAround1");//왕복-인천있을때 가는거
	List<ScheduleVO> listAround2=(List<ScheduleVO>)request.getAttribute("listAround2");//왕복-인천있을때 오는거
	
	List<ScheduleVO> listAround3=(List<ScheduleVO>)request.getAttribute("listAround3");//왕복-인천없을때 오는거
	List<ScheduleVO> listAround4=(List<ScheduleVO>)request.getAttribute("listAround4");//왕복-인천없을때 오는거
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>항공편 조회</title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Travello template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../css/bootstrap4/bootstrap.min.css">
<link href="../plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="../plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="../css/main_styles.css">
<link rel="stylesheet" type="text/css" href="../css/responsive.css">
<link rel="stylesheet" href="../css/bootstrap.css">
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
    width: 80%;
    margin-left: auto; 
    margin-right: auto;}
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
    background-color: #e2e2e2; }
   tr{
    border: 1px solid #bcbcbc;}
   tr.even {
    background-color: #efefef;}
   tr:hover {
    background-color: #c3e6e5;}
   div#div_table{
   	display:block;
   	margin:auto;
   	margin-top:10px;
   	margin-bottom:10px;
   	border: solid 1px gray;
   	overflow: auto; 
   	width:82%; 
   	height:300px; 
   	padding:20px; 
   	text-align:center;"
   }
   div#div_sche{
   	width:82%; 
   	height:40px; 
   	text-align:center;
   	background-color:gray;
   	margin:auto;
   	padding:10px;
   }
   .center {
			position: relative;
	} 
	.center span {
		position: absolute;
		left: 50%; top:50%;
		transform: translate(-50%,-50%);
		text-align: center;
		line-height: 100px;
	}
</style>
<body>
	<div class="super_container">
	
	<!-- Header -->

	<header class="header">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="header_content d-flex flex-row align-items-center justify-content-start">
						<div class="header_content_inner d-flex flex-row align-items-end justify-content-start">
							<div class="logo"><a href="/SistAirLine/index.jsp">SIST AIRLINES</a></div>
							<nav class="main_nav">
								<ul class="d-flex flex-row align-items-start justify-content-start">
									<% if(null != session.getAttribute("user")){%>
									<%=StringUtil.loginNav() %>
									<% }else{%>
									<%=StringUtil.noLoginNav() %>
									<%}%>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">항공권 예매</a>
										<ul class="dropdown-menu">
											<li><a href="login.jsp">일반 예매</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">스카이패스</a>
										<ul class="dropdown-menu">
											<li><a href="join.jsp">회원 혜택</a></li>
											<li><a href="join.jsp">마일리지 적립</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">서비스 안내</a>
										<ul class="dropdown-menu">
											<li><a href="join.jsp">탑승수속</a></li>
											<li><a href="join.jsp">수하물</a></li>
										</ul></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">프로모션/여행상품</a>
										<ul class="dropdown-menu">
											<li><a href="join.jsp">이벤트</a></li>
											<li><a href="join.jsp">특가항공</a></li>
										</ul>
									</li>
								</ul>
							</nav>

							<!-- Hamburger -->

							<div class="hamburger ml-auto">
								<i class="fa fa-bars" aria-hidden="true"></i>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="header_social d-flex flex-row align-items-center justify-content-start">
		</div>
	</header>

	<!-- Home -->

	<div class="home">
		
		<!-- Home Slider -->
		<div class="home_slider_container">
			<div class="owl-carousel owl-theme home_slider">
				
				<!-- Slide -->
				<div class="owl-item">
					<div class="background_image" style="background-image:url(../img/home_slider.jpg)"></div>
					<div class="home_slider_content_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_slider_content">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Slide -->
				<div class="owl-item">
					<div class="background_image" style="background-image:url(../img/home_slider.jpg)"></div>
					<div class="home_slider_content_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_slider_content">
										<div class="home_title"><h2>Let us take you away</h2></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Slide -->
				<div class="owl-item">
					<div class="background_image" style="background-image:url(../img/home_slider.jpg)"></div>
					<div class="home_slider_content_container">
						<div class="container">
							<div class="row">
								<div class="col">
									<div class="home_slider_content">
										<div class="home_title"><h2>Let us take you away</h2></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

			<div class="home_page_nav">
			</div>
		</div>
	</div>

	<!-- Search -->

	<div class="home_search">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="home_search_container">
						<div class="home_search_title"><a href="schedule.jsp">항공편 선택</a></div>
						<div class="home_search_content" >
						<div id="div_sche" >
							<span style="color:white;"><%=dCity %> → <%=aCity %></span>
						</div>
						<form action="../InputPassenger.jsp" method="POST" id="scheduleChoise_frm" name="scheduleChoise_frm">
							<div id="div_table" class="center"> <!-- 갈때 div -->
							<input type="hidden" name="tripType" id="tripType" value="<%=tripType%>">
							<input type="hidden" name="notIC" id="notIC" value="<%=notIC%>">
							<input type="hidden" name="radioNum1" id="radioNum1" value="">
							<input type="hidden" name="radioNum2" id="radioNum2" value="">
							<c:choose>
								<c:when test="${radioValue.equals('1') }"><!-- 편도 -->
									<c:if test="${listOneWay.size()>0}"> <!-- 인천 있을 때 -->
										<table >
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
								                <th>잔여좌석</th>
								                <th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listOneWay.size()-1 }" step="1"> <!-- listOneWay : 인천있는 편도 -->
												<tr>
					                            <td><c:out value="${listOneWay.get(i).scDate }"></c:out> </td>
					                            <td><c:out value="${listOneWay.get(i).scDtime }"></c:out> </td>
					                            <td><c:out value="${listOneWay.get(i).scStime }"></c:out> </td>
					                            <td><c:out value="${listOneWay.get(i).scDcity }"></c:out> </td>
					                            <td><c:out value="${listOneWay.get(i).scAcity }"></c:out> </td>
					                            <td class="money"><c:out value="${listOneWay.get(i).scPrice } 원"></c:out> </td>
					                            <td class="money"><c:out value="${listOneWay.get(i).scMileage }"></c:out> </td>
					                            <td class="acenter"><c:out value="${listOneWay.get(i).scCapacity }"></c:out> </td>
					                            <td class="acenter"><c:out value="${listOneWay.get(i).scUnoccupied }"></c:out> </td>
					                            <td style="display:none;"><c:out value="${listOneWay.get(i).scCode }"></c:out> </td>
					                            <td style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice" value="${i }" /></td>
					                            </tr>
					                            <input type="hidden" name="scCode${i}"  id="scCode${i}" value="${listOneWay.get(i).scCode}" />
					                            <input type="hidden" name="scPrice${i}" id="scPrice${i}" value="${listOneWay.get(i).scPrice}" />
					                            <input type="hidden" name="scDcity${i}"  id="scDcity${i}" value="${listOneWay.get(i).scDcity}" />
					                            <input type="hidden" name="scAcity${i}"  id="scAcity${i}" value="${listOneWay.get(i).scAcity}" />
					                            <input type="hidden" name="scMileage${i}"  id="scMileage${i}" value="${listOneWay.get(i).scMileage}" />
	 										</c:forEach>
	 										</table>
									</c:if>
									<c:if test="${listOneWay2.size()>0}"> <!-- 인천 없을 때 -->
										<table>
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
								                <th>잔여좌석</th>
								                <th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listOneWay2.size()-1 }" step="1"> <!-- listOneWay2 : 인천없는 편도 -->
												<tr >
				                                <td><c:out value="${listOneWay2.get(i).scDate }"></c:out> </td>
				                                <td><c:out value="${listOneWay2.get(i).scDtime }"></c:out> </td>
				                                <td><c:out value="${listOneWay2.get(i).scStime }"></c:out> </td>
				                                <td><c:out value="${listOneWay2.get(i).scDcity }"></c:out> </td>
				                                <td><c:out value="${listOneWay2.get(i).scAcity }"></c:out> </td>
				                                <td class="money"><c:out value="${listOneWay2.get(i).scPrice } 원"></c:out> </td>
				                                <td class="money"><c:out value="${listOneWay2.get(i).scMileage }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listOneWay2.get(i).scCapacity }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listOneWay2.get(i).scUnoccupied }"></c:out> </td>
				                                <td style="display:none;"><c:out value="${listOneWay2.get(i).scCode }"></c:out> </td>
				                                <c:if test="${i%2 ==0}">
				                                	<td rowspan="2" style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice" value="${i }" > </td>
				                                </c:if>
				                               </tr>
				                               <input type="hidden" name="scCodeR${i}" id="scCodeR${i}" value="${listOneWay2.get(i).scCode}" />
				                               <input type="hidden" name="scPriceR${i}" id="scPriceR${i}" value="${listOneWay2.get(i).scPrice}" />
				                               <input type="hidden" name="scDcityR${i}"  id="scDcityR${i}" value="${listOneWay2.get(i).scDcity}" />
				                               <input type="hidden" name="scAcityR${i}"  id="scAcityR${i}" value="${listOneWay2.get(i).scAcity}" />
				                               <input type="hidden" name="scMileageR${i}"  id="scMileageR${i}" value="${listOneWay2.get(i).scMileage}" />
	 										</c:forEach>
	 										</table>
									</c:if>
								</c:when>
								<c:when test="${radioValue.equals('2') }"><!-- 왕복 -->
									<c:if test="${listAround1.size()>0}" ><!-- 인천 있을 때 -->
										<table>
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
								                <th>잔여좌석</th>
								                <th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listAround1.size()-1}"> <!-- listAround1 : 인천 있을때 가는거 (왕복) -->
	          									<tr>
				                                <td><c:out value="${listAround1.get(i).scDate }"></c:out> </td>
				                                <td><c:out value="${listAround1.get(i).scDtime }"></c:out> </td>
				                                <td><c:out value="${listAround1.get(i).scStime }"></c:out> </td>
				                                <td><c:out value="${listAround1.get(i).scDcity }"></c:out> </td>
				                                <td><c:out value="${listAround1.get(i).scAcity }"></c:out> </td>
				                                <td class="money"><c:out value="${listAround1.get(i).scPrice } 원"></c:out> </td>
				                                <td class="money"><c:out value="${listAround1.get(i).scMileage }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listAround1.get(i).scCapacity }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listAround1.get(i).scUnoccupied }"></c:out> </td>
				                                <td style="display:none;"><c:out value="${listAround1.get(i).scCode }"></c:out> </td>
				                               	<td style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice" value="${i }"> </td>
				                               </tr>
				                               <input type="hidden" name="scCode${i}" id="scCode${i}" value="${listAround1.get(i).scCode}" />
				                               <input type="hidden" name="scPrice${i}" id="scPrice${i}" value="${listAround1.get(i).scPrice}" />
				                               <input type="hidden" name="scDcity${i}"  id="scDcity${i}" value="${listAround1.get(i).scDcity}" />
				                               <input type="hidden" name="scAcity${i}"  id="scAcity${i}" value="${listAround1.get(i).scAcity}" />
				                               <input type="hidden" name="scMileage${i}"  id="scMileage${i}" value="${listAround1.get(i).scMileage}" />
	                      					</c:forEach>
	                      				</table>
									</c:if>
									<c:if test="${listAround3.size()>0}"> <!-- 인천 없을 때 -->
										<table>
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
								                <th>잔여좌석</th>
								                <th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listAround3.size()-1}"> <!-- listAround3 : 인천 없을때 가는거 (왕복) -->
	          									<tr>
				                                <td><c:out value="${listAround3.get(i).scDate }"></c:out> </td>
				                                <td><c:out value="${listAround3.get(i).scDtime }"></c:out> </td>
				                                <td><c:out value="${listAround3.get(i).scStime }"></c:out> </td>
				                                <td><c:out value="${listAround3.get(i).scDcity }"></c:out> </td>
				                                <td><c:out value="${listAround3.get(i).scAcity }"></c:out> </td>
				                                <td class="money"><c:out value="${listAround3.get(i).scPrice } 원"></c:out> </td>
				                                <td class="money"><c:out value="${listAround3.get(i).scMileage }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listAround3.get(i).scCapacity }"></c:out> </td>
				                                <td class="acenter"><c:out value="${listAround3.get(i).scUnoccupied }"></c:out> </td>
				                                <td style="display:none;"><c:out value="${listAround3.get(i).scCode }"></c:out> </td>
				                                <c:if test="${i%2 ==0}">
				                                	<td rowspan="2" style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice" value="${i }"> </td>
				                                </c:if>
				                               </tr>
				                               <input type="hidden" name="scCode${i}" id="scCode${i}" value="${listAround3.get(i).scCode}" />
				                               <input type="hidden" name="scPrice${i}" id="scPrice${i}" value="${listAround3.get(i).scPrice}" />
				                               <input type="hidden" name="scDcity${i}"  id="scDcity${i}" value="${listAround3.get(i).scDcity}" />
				                               <input type="hidden" name="scAcity${i}"  id="scAcity${i}" value="${listAround3.get(i).scAcity}" />
				                               <input type="hidden" name="scMileage${i}"  id="scMileage${i}" value="${listAround3.get(i).scMileage}" />
	                      					</c:forEach>
			                      		</table>
									</c:if>
								</c:when>
							</c:choose>
						</div>
						<div id="div_sche">
							<c:choose>
								<c:when test="${radioValue.equals('2') }"><!-- 왕복 -->
									<span style="color:white;"><%=aCity %> → <%=dCity %></span>
								</c:when>
							</c:choose>
						</div>
						<div id="div_table" class="center"><!-- 왕복일때만 -->
							<c:choose>
								<c:when test="${radioValue.equals('1') }"><!-- 편도 -->
									<span style="color:gray;">편도 항공권을 검색하셨습니다. &nbsp;&nbsp; 왕복 항공권 검색 시 표시됩니다.</span>
								</c:when>
								<c:when test="${radioValue.equals('2') }"><!-- 왕복 -->
									<c:if test="${listAround1.size()>0}" ><!-- 인천 있을 때 -->
										<table>
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
										        <th>잔여좌석</th>
										        <th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listAround2.size()-1}"> <!-- listAround2 : 인천 있을때 오는거 (왕복) -->
						          			   <tr>
							                       <td><c:out value="${listAround2.get(i).scDate }"></c:out> </td>
							                       <td><c:out value="${listAround2.get(i).scDtime }"></c:out> </td>
							                       <td><c:out value="${listAround2.get(i).scStime }"></c:out> </td>
							                       <td><c:out value="${listAround2.get(i).scDcity }"></c:out> </td>
							                       <td><c:out value="${listAround2.get(i).scAcity }"></c:out> </td>
							                       <td class="money"><c:out value="${listAround2.get(i).scPrice } 원"></c:out> </td>
							                       <td class="money"><c:out value="${listAround2.get(i).scMileage }"></c:out> </td>
							                       <td class="acenter"><c:out value="${listAround2.get(i).scCapacity }"></c:out> </td>
							                       <td class="acenter"><c:out value="${listAround2.get(i).scUnoccupied }"></c:out> </td>
							                       <td style="display:none;"><c:out value="${listAround2.get(i).scCode }"></c:out> </td>
							                       <td style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice2" value="${i }"> </td>
							                       </tr>
							                       <input type="hidden" name="scCodeR${i}" id="scCodeR${i}" value="${listAround2.get(i).scCode}" />
							                       <input type="hidden" name="scPriceR${i}" id="scPriceR${i}" value="${listAround2.get(i).scPrice}" />
							                       <input type="hidden" name="scDcityR${i}"  id="scDcityR${i}" value="${listAround2.get(i).scDcity}" />
							                       <input type="hidden" name="scAcityR${i}"  id="scAcityR${i}" value="${listAround2.get(i).scAcity}" />
							                       <input type="hidden" name="scMileage${i}"  id="scMileage${i}" value="${listAround2.get(i).scMileage}" />
			                      			</c:forEach> 
										</table>
									</c:if>
									<c:if test="${listAround3.size()>0}"> <!-- 인천 없을 때 -->
										<table>
											<thead>
												<th>출발 날짜</th>
												<th>출발 시간</th>
												<th>소요 시간</th>
												<th>출발 도시</th>
												<th>도착 도시</th>
												<th>가격</th>
												<th>마일리지</th>
												<th>수용인원</th>
												<th>잔여좌석</th>
												<th></th>
											</thead>
											<c:forEach var="i" begin="0" end="${listAround4.size()-1}"> <!-- listAround4 : 인천 없을때 오는거 (왕복) -->
						          			    <tr>
						                            <td><c:out value="${listAround4.get(i).scDate }"></c:out> </td>
						                            <td><c:out value="${listAround4.get(i).scDtime }"></c:out> </td>
						                            <td><c:out value="${listAround4.get(i).scStime }"></c:out> </td>
						                            <td><c:out value="${listAround4.get(i).scDcity }"></c:out> </td>
						                            <td><c:out value="${listAround4.get(i).scAcity }"></c:out> </td>
						                            <td class="money"><c:out value="${listAround4.get(i).scPrice } 원"></c:out> </td>
						                            <td class="money"><c:out value="${listAround4.get(i).scMileage }"></c:out> </td>
						                            <td class="acenter"><c:out value="${listAround4.get(i).scCapacity }"></c:out> </td>
						                            <td class="acenter"><c:out value="${listAround4.get(i).scUnoccupied }"></c:out> </td>
						                            <td style="display:none;"><c:out value="${listAround4.get(i).scCode }"></c:out> </td>
						                            <c:if test="${i%2 ==0}">
						                            	<td rowspan="2" style="border-left: 1px solid #bcbcbc;"><input type="radio" name="choice2" value="${i }"> </td>
						                            </c:if>
					                            </tr>
					                            <input type="hidden" name="scCodeR${i}" id="scCodeR${i}" value="${listAround4.get(i).scCode}" />
					                            <input type="hidden" name="scPriceR${i}" id="scPriceR${i}" value="${listAround4.get(i).scPrice}" />
					                            <input type="hidden" name="scDcityR${i}"  id="scDcityR${i}" value="${listAround4.get(i).scDcity}" />
					                            <input type="hidden" name="scAcityR${i}"  id="scAcityR${i}" value="${listAround4.get(i).scAcity}" />
					                            <input type="hidden" name="scMileage${i}"  id="scMileage${i}" value="${listAround4.get(i).scMileage}" />
					                      	</c:forEach>
										</table>
									</c:if>
								</c:when>
							</c:choose>
						</div>
						</form>
						<div style="width:82%; height:40px; text-align:right; margin:auto;">
							<input type="button" id="to_reservation_btn" value="예매하기" class="home_search_button" >
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="../img/footer_1.jpg" data-speed="0.8"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter">
						<div class="newsletter_title_container text-center">
							<div class="newsletter_title">Subscribe to our newsletter to get the latest trends & news</div>
							<div class="newsletter_subtitle">Join our database NOW!</div>
						</div>
						<div class="newsletter_form_container">
							<form action="#" class="newsletter_form d-flex flex-md-row flex-column align-items-start justify-content-between" id="newsletter_form">
								<div class="d-flex flex-md-row flex-column align-items-start justify-content-between">
									<div><input type="text" class="newsletter_input newsletter_input_name" id="newsletter_input_name" placeholder="Name" required="required"><div class="input_border"></div></div>
									<div><input type="email" class="newsletter_input newsletter_input_email" id="newsletter_input_email" placeholder="Your e-mail" required="required"><div class="input_border"></div></div>
								</div>
								<div><button class="newsletter_button">subscribe</button></div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="row footer_contact_row">
				<div class="col-xl-10 offset-xl-1">
					<div class="row">
						<div class="col-xl-4 footer_contact_col">
							<div class="footer_contact_item d-flex flex-column align-items-center justify-content-start text-center">
								<div class="footer_contact_icon"><img src="../img/around.svg" alt=""></div>
								<div class="footer_contact_title"></div>
								<div class="footer_contact_list">
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="col text-center"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --> </div>
	</footer>
</div>

<script src="../js/jquery-3.2.1.min.js"></script>
<script src="../css/bootstrap4/popper.js"></script>
<script src="../css/bootstrap4/bootstrap.min.js"></script>
<script src="../plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="../plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="../plugins/scrollTo/jquery.scrollTo.min.js"></script>
<script src="../plugins/easing/easing.js"></script>
<script src="../plugins/parallax-js-master/parallax.min.js"></script>
<script src="../js/custom.js"></script>
<script src="../js/bootstrap.js"></script>

<script type="text/javascript">

	var tripType = $("#tripType").val();
	var notIC = $("#notIC").val();
	var num = null;
	
	$("#to_reservation_btn").on('click',function(){
		console.log("tripType : "+tripType);
		console.log("document.getElementById(radioNum1).value : "+document.getElementById("radioNum1").value);
		console.log("document.getElementById(radioNum2).value : "+document.getElementById("radioNum2").value);
		
		var chk_radio = document.getElementsByName('choice');
		
		for(var i=0;i<chk_radio.length;i++){
			if(chk_radio[i].checked == true){ 
				num = chk_radio[i].value;
				console.log("num : "+num);
			}
		}
		document.getElementById("radioNum1").value = num;
		console.log("radioNum1 : "+document.getElementById("radioNum1").value);
		
		if(tripType == "2"){
			var chk_radio = document.getElementsByName('choice2');
			num = null;
			
			for(var i=0;i<chk_radio.length;i++){
				if(chk_radio[i].checked == true){ 
					num = chk_radio[i].value;
					//console.log("i : "+i);
					console.log("num : "+num);
				}
			}
			document.getElementById("radioNum2").value = num;
			console.log("radioNum2 : "+document.getElementById("radioNum2").value);
			
		}//tripType if
		
		//라디오버튼 선택 유효검사
		if(tripType == "1"){
			if(document.getElementById("radioNum1").value == ""){
				alert("항공권을 선택해주세요.");
			}else{
				if(confirm("선택하신 항공권을 예약하시겠습니까?") == true){//확인
					var frm=document.scheduleChoise_frm;
					frm.action="../InputPassenger.jsp";
					frm.submit();
				}else{   //취소
				    return false;
				}
			}
		}else if(tripType == "2"){
			if(document.getElementById("radioNum1").value == "" || document.getElementById("radioNum2").value == ""){
				alert("항공권을 선택해주세요.");
				return;
			}else if(document.getElementById("radioNum1").value == "" && document.getElementById("radioNum2").value == ""){
				alert("항공권을 선택해주세요.");
				return;
			}else{
				 if(confirm("선택하신 항공권을 예약하시겠습니까?") == true){//확인
					var frm=document.scheduleChoise_frm;
					frm.action="../InputPassenger.jsp";
					frm.submit();
				 }else{   //취소
				    return false;
				 }
			}
		}
		//var frm=document.scheduleChoise_frm;
		//frm.action="../InputPassenger.jsp";
		//frm.submit();
		
	});
</script>

</body>
</html>