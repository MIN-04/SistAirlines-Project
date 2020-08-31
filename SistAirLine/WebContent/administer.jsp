<%@page import="member.MemberVO"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.CodeVO"%>
<%@page import="com.hr.cmn.SearchVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/cmn/common.jsp"%>
<%@page session="true"%>




<!DOCTYPE html>

<html>
<head>
<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/SistAirLine/js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="/SistAirLine/css/bootstrap.css">
<title>SIST AIRLINES</title>
<style>
.table-hover tbody tr:hover td {
	background: #337ab7;
	color: #fff;
}
</style>
<script type="text/javascript">
	function goSchedule() {
		location.href = "/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve";
	}
	function goReservation() {

		location.href = "/SistAirLine/Reservation/res.do?work_div=do_retrieve";

	}
</script>
</head>


<%
	MemberVO inVO = (MemberVO) session.getAttribute("user");
	String member = null;
	//System.out.println("member"+member);
	if (inVO != null) {
		member = inVO.getuID();
		System.out.println("member:" + member);
	}
%>

<body style="background-image:url(img/loginBack.jpg); background-size: cover;">
	<!-- Header menu  -->

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/SistAirLine/index.jsp">SIST
				AIRLINES</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">

				<%
					if (null != member) {
				%>
				<%=StringUtil.loginNav()%>
				<%
					} else {
				%>
				<%=StringUtil.noLoginNav()%>
				<%
					}
				%>


				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">항공권 예매<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="login.jsp">일반 예매</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">스카이패스<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="join.jsp">회원 혜택</a></li>
						<li><a href="join.jsp">마일리지 적립</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">서비스 안내<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="join.jsp">탑승수속</a></li>
						<li><a href="join.jsp">수하물</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">프로모션/여행상품<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="join.jsp">이벤트</a></li>
						<li><a href="join.jsp">특가항공</a></li>
					</ul></li>
			</ul>
		</div>
	</nav>
	<!-- Header menu  -->


	<div class="container" style="padding-top: 200px;" >
		<div>
			<div>
				<div class="col-md-12" align="center">
				</div>
			</div>
			<div class="row"
				style="margin-top: 30px; border: 1px solid black; border-radius: 30px 30px 30px 30px; padding: 120px; background-color: white;">
				<div class="col-md-12" align="center">
					<form name="searchFrm" id="searchFrm"
						action='<%=HR_PATH%>/scheduleDTO/schedule.do' method="get">

						<table style="align: center; margin: auto; padding: 200px">
							<tr>
								<td style="padding-bottom: 110px;"><img
									src="/SistAirLine/img/airplane3.png" width="400" height="300">
								</td>
								<td style="padding-bottom: 110px;"><img
									src="/SistAirLine/img/a_res.png" width="400" height="300">
								</td>
							</tr>
							<tr
								style="text-align: center; padding-right: 120px; margin: auto; align: center;">
								<td><input type="button" class="btn btn-primary"
									value="스케줄 조회" onClick="goSchedule()"
									style="width: 200px; height: 50px;"></td>
								<td><input type="button" class="btn btn-primary"
									value="예약 조회" onClick="goReservation()"
									style="width: 200px; height: 50px;"></td>
							</tr>

						</table>


					</form>
				</div>
			</div>
		</div>

	</div>

	
</body>
</html>