<%@page import="java.text.SimpleDateFormat"%>
<%@page import="seatDTO.SeatDTO"%>
<%@page import="seatDTO.SeatDAO"%>
<%@page import="scheduleDTO.ScheduleDAO"%>
<%@page import="scheduleDTO.ScheduleDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<title>좌석 리스트</title>
</head>
<body>

<%
	request.setCharacterEncoding("utf-8");
	
	// form으로 넘어온 값을 변수에 저장
	String seCode = request.getParameter("seCode");
	String scCode = request.getParameter("scCode");
	
	SeatDAO sDao=new SeatDAO();	
	SeatDTO seatVO=new SeatDTO();
	seatVO.setSeCode(seCode);
	int seatflag=sDao.doOccupied(seatVO);
	
	ScheduleDAO scDao=new ScheduleDAO();
	ScheduleDTO scVO=new ScheduleDTO();
	scVO.setScCode(scCode);
	int scheduleFlag=scDao.doUpdateSeat(scVO);
	
	
		
	if(seatflag==1 && scheduleFlag==1){
%>
<script>
   alert("좌석이 선택되었습니다.");
	self.close();
</script>
<%
	}else{
%>
<script>
	alert("좌석이 변경되지 않았습니다.");
	history.back();
</script>
<%
	}
%>
</body>
</html>
