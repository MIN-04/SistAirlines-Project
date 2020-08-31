<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>SIST AIRLINES</title>

<script type="text/javascript">
    
        var openWin;
    
        function openDCityForm()
        {
            // window.name = "부모창 이름"; 
            window.name = "mainForm.jsp";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("cityDForm.jsp",
                    "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
        }
        function openACityForm()
        {
            // window.name = "부모창 이름"; 
            window.name = "mainForm.jsp";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("cityAForm.jsp",
                    "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
        }
</script>

</head>
<body>
<%
   int uNum=0;
   String check=(String)session.getAttribute("check");
   if(check!=null){
      uNum=(Integer)session.getAttribute("uNum");
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

%>            <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                     aria-expanded="false">
                     로그아웃/mypage
                     <span class="caret">
                     </span>
                  </a><ul class="dropdown-menu">
                  <li><a href="LogoutProcess.jsp">로그아웃</a></li>
                  <li><a href="MypageForm.jsp">마이 페이지</a></li>
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
      <div class="jumbotron" style="padding-top: 20px">
      
      <form method="post" action="Schedule_list.jsp">
            <h2 style="text-align: center;">항공권</h2>
            <h4>
               <a href="mainFrom.jsp">예매</a>
            </h4>
            <div class="trip-type option-type">
               <input type="radio" name="trip-type" value="편도" /> 편도 <input
                  type="radio" name="trip-type" value="왕복" checked="checked" /> 왕복
               <input type="radio" name="trip-type" value="다구간" /> 다구간
            </div>
            <table>
               <tr>
                  <td>
                     <input type="text" id="sc_dcity" class="form-control" placeholder="출발지"
                        name="dCity" maxlength="20" autofocus required >
                  </td>
                  <td>
                     <input type="text" id="sc_acity" class="form-control" placeholder="목적지"
                        name="aCity" maxlength="20" autofocus required>
                  </td>
                  <td><input type="text" name="outDate" maxlength="20"></td>
                  <td><input type="text" name="inDate" maxlength="20"></td>
               </tr>
               <script>
                  document.getElementById('currentDate1').value = new Date()
                        .toISOString().substring(0, 10);
                  document.getElementById('currentDate2').value = new Date()
                        .toISOString().substring(0, 10);
                  ;
               </script>
            </table>
            <input type="submit" class="btn btn-primary form-control"
               value="항공권 조회">
            <input type="text" name="adultCnt" placeholder="성인">
         <input type="text" name="youngCnt" placeholder="소아">
         <input type="text" name="yongerCnt" placeholder="유아">
               
         </form>
         <button
            id="btnDCity" onclick="openDCityForm()" value="출발지">
            출발지<img src="img/pin.png"
               style="height: 20px; width: 20px;">
         </button>
         <button
            id="btnACity" onclick="openACityForm()" value="도착지">
            도착지<img src="img/pin.png"
               style="height: 20px; width: 20px;">
         </button>
      </div>
   </div>
   <div class="col-lg-4"></div>
   </div>
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="js/bootstrap.js"></script>
</body>
</html>