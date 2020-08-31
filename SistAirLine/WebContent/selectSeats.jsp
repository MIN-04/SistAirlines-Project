<%@page import="airplanes.airplanesDAO"%>
<%@page import="airplanes.airplanesVO"%>
<%@page import="scheduleDTO.ScheduleDAO"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="seatDTO.SeatDTO"%>
<%@page import="seatDTO.SeatDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html> 
<head>
<style type="text/css">
   table{
   		width:70%;
   		height:100%;
      	margin :auto;
      	text-align:center;
      	margin-left:130px;
   }
   #airplaneBack {
   		background-image: url("img/airplaneback.png");
		background-repeat:no-repeat;
		background-size:cover;
	}
   
</style>
<script>
      function setParentPk(seCode,seatNum){
    	  opener.document.getElementById(seatNum).value =seCode;
    	  opener.document.getElementById(totalPrice).value ='100000원';
          
   }
</script>
<%
      request.setCharacterEncoding("utf-8");
      
      // form으로 넘어온 값을 변수에 저장
      String scCode = request.getParameter("scCode");
      String seatNum = request.getParameter("seatNum");
      int uNum=0;
      String check=(String)session.getAttribute("check");
      if(check!=null){
         uNum=(Integer)session.getAttribute("uNum");
      }
      
      ScheduleVO inVO =new ScheduleVO();
       inVO.setScCode(scCode);
      ScheduleDAO scDao=new ScheduleDAO();
      ScheduleVO outVO=(ScheduleVO)scDao.doSelectOne(inVO);
      
 //     String dCity=outVO.getScDcity();
 //     String aCity=outVO.getScAcity();
      
//      String toAir;
//      int aCheck=0;
//      if(aCity.equals("INCHEON")){
//           toAir=dCity;
//      }else {
//          toAir=aCity;
//      }
//      
//      airplanesVO aInVO=new airplanesVO();
//      aInVO.setCity("OSAKA");
//      
//      airplanesDAO aDao=new airplanesDAO();
//      airplanesVO aOutVO=(airplanesVO)aDao.doSelectOne(aInVO);
//      if(aOutVO.getaType().equals("BOEING")){
//          aCheck=1;//BOEING
//      }else{
//          aCheck=0;//AirBus
//      }
      
      
      // 좌석
      SeatDTO data = new SeatDTO();
      data.setScCode(scCode);
      
      // DAO의 search 메서드 호출 / DTO 객체(data) 전달
      SeatDAO dao =new SeatDAO();
      List<SeatDTO> list=dao.doRetrieve(data);   
      data=new SeatDTO();
 %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>좌석 리스트</title>
</head>
<body>

   <nav class = "navbar navbar-default">
      <div class="navbar-header">
      <button type = "button" class="navbar-togle collapsed"
         data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
         aria-expanded="false">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         </button>   
         <a class="navbar-brand" href="main.jsp">JSP 게시판 웹사이트</a>      
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
         <ul class ="nav navbar-nav"> 
            <li><a href="seatList.jsp">메인</a></li>
            <li><a href="seatList.jsp">게시판</a></li>
         </ul>
         <ul class ="nav navbar-nav navbar-right"> 
            <li class = "dropdown">
               <a href="#" class="dropdown-toggle"
                  data-toggle="dropdown" role="button" aria-haspopup="true"
                  aria-expanded="false">접속하기<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li class= "active"><a href="LoginForm.jsp">로그인</a></li>
                  <li><a href="SignUpForm.jsp">회원가입</a></li>
               </ul>
            </li>
         </ul>
      </div>
   </nav>
   <div class="container">
      <div class="col-lg-4"></div>
      <div class="col-lg-4"></div>
         <div class="jumbotron" style="padding-top: 20px;" id="airplaneBack">

   <form action="schedule2.jsp" method="post">
      <table cellpadding="20px" >
      <tr>
         <th>1열</th>
         <th>2열</th>
         <th>3열</th>
         <th>4열</th>
         <th>5열</th>
         <th>6열</th>
         <th>7열</th>
         <th>8열</th>
         <th>9열</th>
         <th>10열</th>
      </tr>
      <%
     // if(aCheck==1){
         for(int i=0; i < 5; i++){         
      %>
   <tr>
      <%
         
         for(int j=(i*10); j<10*(i+1); j++){
            data = list.get(j); // 0번 인덱스부터 담긴 데이터를 data에 할당   
      if(data.getSeOccupied().equals("0")){
      
      %>
      <td style="width:20px; height:20px;" >
      		<img  onmouseout="this.src='img/occupiedSeats.png';" src="img/occupiedSeats.png" style="height:100%;">
      </td>
      <%
         }else{
      %>
      <td style="width:20px; height:20px;">
      		<a href="SeatProcess.jsp?seCode=<%=data.getSeCode()%>&scCode=<%=data.getScCode()%>" onclick="setParentPk('<%=data.getSeCode()%>','<%=seatNum%>')">
      			<img onmouseover="this.src='img/whiteSeat.png';" onmouseout="this.src='img/unoccupiedSeats.png';" src="img/unoccupiedSeats.png" style="height:100%;">
      		</a>
      </td>
      
      
      <%
            }
         }
      %>
   </tr>
   <%
         }//for
    
     %>
       </table>
   </form>

   <script src="https://code.jqeury.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
   
</body>
</html>