<%@page import="java.text.SimpleDateFormat"%>
<%@page import="scheduleDTO.ScheduleDTO"%>
<%@page import="scheduleDTO.ScheduleDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>좌석 리스트</title>


<script>
   function openChild(scCode, pInput)
   {
      let openWin;
      console.log('실행');
      window.name="schedule_list";
      openWin=window.open("selectSeats.jsp?scCode="+scCode+"&pInput="+pInput,"selectSeats","width=570, height=350");
   }
   
</script>

</head>
<body>
<%
   int uNum=0;
   String check=(String)session.getAttribute("check");
   
   if((Integer)session.getAttribute("uNum")!=null){
      uNum=(Integer)session.getAttribute("uNum");//회원이 로그인했을경우
   }else{
         session.setAttribute("uNum",uNum);//비회원으로 스케줄을 조회할 경우
   }
%>
   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expanded="false">
            <span class="icon-bar"></span> <span class="icon-bar"></span> <span
               class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="mainForm.jsp">SIST AIRLINES</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
         <ul class="nav navbar-nav navbar-right">
<%
         
      if(uNum<1){   
         
%>
            <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                     aria-expanded="false">
                     로그인/회원가입
                     <span class="caret">
                     </span>
                  </a>
               <ul class="dropdown-menu">
                  <li><a href="LoginForm.jsp">로그인</a></li>
                  <li><a href="SignUpForm.jsp">회원가입</a></li>
               </ul>
            </li>
<%

            }else{

%>

            <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                     aria-expanded="false">
                     로그아웃/mypage
                     <span class="caret">
                     </span>
                  </a><ul class="dropdown-menu">
                  <li><a href="LogoutProcess.jsp">로그아웃</a></li>
                  <li><a href="Mypage.jsp">마이 페이지</a></li>
               </ul>
            </li>
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

   <div class="container">
      <div class="col-lg-4"></div>
      <div class="col-lg-4"></div>
         <div class="jumbotron" style="padding-top: 20px;">
 <%
   request.setCharacterEncoding("utf-8");
   
   // form으로 넘어온 값을 변수에 저장
   String scDcity = request.getParameter("dCity");
   String scAcity = request.getParameter("aCity");
   String outDate = request.getParameter("outDate");
   String inDate = request.getParameter("inDate");
   
   String adult= request.getParameter("adultCnt");
   String young = request.getParameter("youngCnt");
   String younger = request.getParameter("yongerCnt");
   
   session.setAttribute("adultCnt",adult);
   session.setAttribute("youngCnt",young);
   session.setAttribute("youngerCnt",younger);
   
   // DTO 객체에 값을 저장
   ScheduleDTO data = new ScheduleDTO();
   data.setScDate(outDate);
   data.setScDcity(scDcity);
   data.setScAcity(scAcity);
   
   // DAO의 search 메서드 호출 / DTO 객체(data) 전달
   ScheduleDAO dao =new ScheduleDAO();
   List<ScheduleDTO> list=dao.doRetrive1(data);   
   data=new ScheduleDTO();
   

%>
   
   <form action="InputPassenger.jsp" method="post">
   
      <table border="1">
      <tr>
         <td>출발날짜</td>
         <td>출발시간</td>
         <td>소요시간</td>
         <td>출발도시</td>
         <td>도착도시</td>
         <td>가격</td>
         <td>비행기이름</td>
         <td>남은좌석</td>
         <td>팝업버튼</td>
      </tr>
      <%
         for(int i=0; i < list.size(); i++){
            data = list.get(i); // 0번 인덱스부터 담긴 데이터를 data에 할당
            // list에 저장된 데이터 출력
      %>
      <tr>
         <td><%=data.getScDate() %></td>
         <td><%=data.getScDtime() %></td>
         <td><%=data.getScStime() %></td>
         <td><%=data.getScDcity() %></td>
         <td><%=data.getScAcity() %></td>
         <td><%=data.getScPrice() %></td>
         <td><%=data.getScAname() %></td>
         <td><%=data.getScUnoccupied() %></td>
         <td><input type="radio" name="sc_Code" onclick="this.form.submit();" value="<%=data.getScCode()%>"></td>
      </tr>
      <%      
         } // for문 종료
      %>
      </table>
   
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
   
</body>
</html>