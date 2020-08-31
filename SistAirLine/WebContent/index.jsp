<%@page import="member.MemberVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>SIST 항공</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Travello template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/bootstrap4/bootstrap.min.css">
<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="css/main_styles.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="shortcut icon" type="image/x-icon" href='/SistAirLine/favicon.ico' >
</head>
<%
   MemberVO inVO = (MemberVO)session.getAttribute("user");
   String member = null;
   //System.out.println("member"+member);
   if(inVO != null){
      member = inVO.getuID();
      System.out.println("member:"+member);
   }
   
%>
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript">
$(document).ready(function(){      
      // http://localhost:8080/SistAirLine/scheduleDTO/schedule.do
      var member = '<%=member%>';
      var chk_radio = document.getElementsByName('trip-type');
      var sel_type = null;
      for(var i=0;i<chk_radio.length;i++){
         if(chk_radio[i].checked == true){ 
            sel_type = chk_radio[i].value;
            console.log(sel_type);
         }
      }
      $("#radioOneWay").on('click',function() {
         for(var i=0;i<chk_radio.length;i++){
               sel_type = $("#radioOneWay").val();
               console.log("Oneway:"+sel_type);
            }
      });
      $("#radioRoundWay").on('click',function() {
         for(var i=0;i<chk_radio.length;i++){
               sel_type = $("#radioRoundWay").val();
               console.log("Roundway:"+sel_type);
            }
      });
      $("#radioMulti").on('click',function() {
         for(var i=0;i<chk_radio.length;i++){
               sel_type = $("#radioRoundWay").val();
               console.log("Roundway:"+sel_type);
            }
      });
      
      //스케줄 조회 버튼
      
      $("#to_schedule_btn").on('click',function(){
         if(sel_type=='1'){
              var work_div=$("#work_div").val();
               if(work_div==null || work_div.trim().length==0){
                  alert('작업구분을 확인하세요');
                  return;
               }
                var dCityOne=$("#dCityOne").val();
               if(dCityOne==null ||dCityOne.trim().length==0){
                    alert('출발지를 확인하세요');
                    $("#title").focus();
                    return;
                }
                var aCityOne=$("#aCityOne").val();
                if(aCityOne==null ||aCityOne.trim().length==0){
                    alert('도착지를 확인하세요');
               $("#reg_id").focus();
                    return;
                }
             var outDateOne=$("#outDateOne").val();
             if(outDateOne==null ||outDateOne.trim().length==0){
                 alert('출발날짜를 확인하세요');
                 $("#outDateOne").focus();
                 return;
             }
            
            //if(dCityOne == aCityOne){
         //   alert('출발지와 도착지가 같습니다. 다시 선택해주세요.');
         //   document.schedule_frm.reset();
         //   return;
            //} 
             
             if(false==confirm(dCityOne+" - "+aCityOne+"행 편도 항공권을(를) \n 조회하시겠습니까?"))return;
             console.log(member);
             //회원/ 비회원 확인
             if(member != 'null'){
                 var frm=document.schedule_frm;
               frm.work_div.value="do_retrieve";
               frm.work_div.action="/SistAirLine/scheduleDTO/schedule.do";
               frm.submit(); 
             }else{
                if(confirm("회원이신가요? \n확인 버튼 클릭시 로그인 페이지로 넘어갑니다.")){
                   location.href="/SistAirLine/LoginForm.jsp";   
                }else{
                    var frm=document.schedule_frm;
                  frm.work_div.value="do_retrieve";
                  frm.work_div.action="/SistAirLine/scheduleDTO/schedule.do";
                  frm.submit();
                }
             }
             
            
         }else if(sel_type=='2'){
            //      goRetrieve();
               var work_div=$("#work_div").val();
               if(work_div==null || work_div.trim().length==0){
                  alert('작업구분을 확인하세요');
                  return;
               }
                var dCityAr=$("#dCityAr").val();
                console.log(dCityAr);
                if(dCityAr==null ||dCityAr.trim().length==0){
                    alert('왕복 출발지를 확인하세요');
                    $("#dCityAr").focus();
                    return;
                    
                }
                var aCityAr=$("#aCityAr").val();
                console.log(aCityAr);
                if(aCityAr==null ||aCityAr.trim().length==0){
                    alert('왕복 도착지를 확인하세요');
                    $("#aCityAr").focus();
                    
                    return;
                    
                }
                var outDateAr=$("#outDateAr").val();
                if(outDateAr==null ||outDateAr.trim().length==0){
                    alert('출발날짜를 확인하세요');
                    $("#outDateAr").focus();
                    return;
                    
                }
                var inDateAr=$("#inDateAr").val();
		        if(inDateAr==null ||inDateAr.trim().length==0){
		            alert('도착날짜를 확인하세요');
		            $("#inDateAr").focus();
		            return;
		            
		        }
                
                //if(dCityAr == aCityAr){
               //   alert('출발지와 도착지가 같습니다. 다시 선택해주세요.');
               //   document.schedule_frm.reset();
               //   return;
               //} 
                
                if(false==confirm(dCityAr+" - "+aCityAr+"행 왕복 항공권을(를) \n 조회하시겠습니까?"))return;
                
                //회원/비회원 확인
                if(member != 'null'){
                    var frm=document.schedule_frm;
                  frm.work_div.value="do_retrieve";
                  frm.work_div.action="/SistAirLine/scheduleDTO/schedule.do";
                  frm.submit(); 
                }else{
                   if(confirm("회원이신가요? \n확인 버튼 클릭시 로그인 페이지로 넘어갑니다.")){
                      location.href="/SistAirLine/LoginForm.jsp";   
                   }else{
                       var frm=document.schedule_frm;
                     frm.work_div.value="do_retrieve";
                     frm.work_div.action="/SistAirLine/scheduleDTO/schedule.do";
                     frm.submit();
                   }
                }
            }//radioButtonif
         });
      
       //출발지 도착지가 같을 때
       $("#dCityOne").on("propertychange change keyup paste", function() {
         var dCityOne = $(this).val();
         var aCityOne = document.getElementById("aCityOne").value; 
         
         if(dCityOne != "" && aCityOne != ""){
            if(dCityOne == aCityOne){
               alert("출발지와 도착지가 같을 수 없습니다. 다시 선택해주세요.");
               document.schedule_frm.reset();
            }
         }
      });
      $("#aCityOne").on("propertychange change keyup paste", function() {
         var dCityOne = document.getElementById("dCityOne").value; 
         var aCityOne = $(this).val();
         if(dCityOne != "" && aCityOne != ""){
            if(dCityOne == aCityOne){
               alert("출발지와 도착지가 같을 수 없습니다. 다시 선택해주세요.");
               document.schedule_frm.reset();
            }
         }
      });
      $("#dCityAr").on("propertychange change keyup paste", function() {
         var dCityAr = $(this).val();
         var aCityAr = document.getElementById("aCityAr").value; 
         
         if(dCityAr != "" && aCityAr != ""){
            if(dCityAr == aCityAr){
               alert("출발지와 도착지가 같을 수 없습니다. 다시 선택해주세요.");
               document.schedule_frm.reset();
            }
         }
      });
      $("#aCityAr").on("propertychange change keyup paste", function() {
         var dCityAr = document.getElementById("dCityAr").value; 
         var aCityAr = $(this).val();
         if(dCityAr != "" && aCityAr != ""){
            if(dCityAr == aCityAr){
               alert("출발지와 도착지가 같을 수 없습니다. 다시 선택해주세요.");
               document.schedule_frm.reset();
            }
         }
      });

});//document.open

   //index내의 버튼 이벤트들
    var openWin;
   
    function clickPlus(i){ //인원수 증가
          //var val = document.getElementById(i).value;
          var val = Number(document.getElementById(i).value);
          val += 1;
          document.getElementById(i).value = val;
    }
       
    function clickMinus(i){ //인원수 감소
          var val = Number(document.getElementById(i).value);
          if(val != 0){
             val -= 1;
              document.getElementById(i).value = val;
          }else{
             alert("더 이상 줄일 수 없습니다.")
          }
          
    }
 
    function openCalOne()  //편도 날짜 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("calOne.jsp",
                "출발일 선택", "width=570, height=350, resizable = no, scrollbars = no");    
    }
    function openCalAroundOut()  //왕복 날짜(Out) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("calAroundOut.jsp",
                "출발일 선택", "width=570, height=350, resizable = no, scrollbars = no");    
    }
    
    function openCalAroundIn()  //왕복 날짜(In) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("calAroundIn.jsp",
                "출발일 선택", "width=570, height=350, resizable = no, scrollbars = no");
    }
    
    function openDCityFormOne()  //출발지(편도) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("cityDFormOne.jsp",
                "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
    }
    function openACityFormOne() //도착지(편도) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("cityAFormOne.jsp",
                "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
    }
    function openDCityFormAround() //출발지(왕복) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("cityDFormAround.jsp",
                "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
    }
    function openACityFormAround() //도착지(편도) 선택 팝업창
    {
        // window.name = "부모창 이름"; 
        window.name = "index.jsp";
        // window.open("open할 window", "자식창 이름", "팝업창 옵션");
        openWin = window.open("cityAFormAround.jsp",
                "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
    }
    function scnOnOff(v,id)
    {
       //라디오 버튼 value
       if(v=="1"){
          document.getElementById("con1").style.display=""; //보여줌
          document.getElementById("con2").style.display="none";//숨김
       }else if(v=="2"){
          document.getElementById("con2").style.display=""; //보여줌
          document.getElementById("con1").style.display="none";//숨김
       }
    }
</script>

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
                     <div class="logo"><a href="index.jsp">SIST AIRLINES</a></div>
                     <nav class="main_nav">
                        <ul class="d-flex flex-row align-items-start justify-content-start">
                        <% if(null != member){%>
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
   </header>

   <!-- Menu -->

   <div class="menu">
      <div class="menu_header d-flex flex-row align-items-center justify-content-start">
         <div class="menu_logo"><a href="index.html">Travello</a></div>
         <div class="menu_close_container ml-auto"><div class="menu_close"><div></div><div></div></div></div>
      </div>
      <div class="menu_content">
         <ul>
            <li><a href="inex.html">Home</a></li>
            <li><a href="about.html">About us</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="news.html">News</a></li>
            <li><a href="contact.html">Contact</a></li>
         </ul>
      </div>
      <div class="menu_social">
         <div class="menu_phone ml-auto">Call us: 00-56 445 678 33</div>
         <ul class="d-flex flex-row align-items-start justify-content-start">
            <li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
            <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
            <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
            <li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
            <li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
            <li><a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
         </ul>
      </div>
   </div>
   
   <!-- Home -->

   <div class="home" id="home">
      
      <!-- Home Slider -->
      <div class="home_slider_container">
         <div class="owl-carousel owl-theme home_slider">
            
            <!-- Slide -->
            <div class="owl-item">
               <div class="background_image" style="background-image:url(img/home_slider.jpg)"></div>
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
               <div class="background_image" style="background-image:url(img/home_slider.jpg)"></div>
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
               <div class="background_image" style="background-image:url(img/home_slider.jpg)"></div>
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
            <ul class="d-flex flex-column align-items-end justify-content-end">
               <li><a href="#" data-scroll-to="#home">항공권 예매<span>01</span></a></li>
               <li><a href="#" data-scroll-to="#destinations">항공편<span>02</span></a></li>
            </ul>
         </div>
      </div>
   </div>

   <!-- Search -->

   <div class="home_search" >
      <div class="container">
         <div class="row">
            <div class="col">
               <div class="home_search_container">
                  <div class="home_search_title"><a href="index.jsp">항공권 예매</a></div>
                  <div class="home_search_content">
                         <form action="/SistAirLine/scheduleDTO/schedule.do" method="get" name="schedule_frm" accept-charset="utf-8">
                        <input type="hidden" name="work_div" value="do_retrieve" id="work_div"/>
                        <div style="padding-bottom:20px">
                           <label><input type="radio" name="trip-type" id="radioOneWay" value="1" onclick="scnOnOff(this.value,con1)"/> 편도</label>
                           <label><input type="radio" name="trip-type" id="radioRoundWay"  value="2" checked="checked" onclick="scnOnOff(this.value,con2)"/> 왕복</label>
                        </div>
                        <div id="con1" style="display:none;"> <!-- 편도 display -->
                           <div class="d-flex flex-lg-row flex-column align-items-start justify-content-lg-between justify-content-start" style="padding-bottom:20px" >
                              <input type="text" name="dCityOne" id="dCityOne" class="search_input search_input_1" placeholder="출발지" required="required" style="height: 40px; width: 300px;">
                              <img src="img\pin1.png" id="btnDCity" onclick="openDCityFormOne()" style="height: 40px; width: 40px;">
                              <input type="text" name="aCityOne" id="aCityOne" class="search_input search_input_1" placeholder="도착지" required="required" style="height: 40px; width: 300px;">
                              <img src="img\pin1.png" id="btnACity" onclick="openACityFormOne()" style="height: 40px; width: 40px;">
                              <input type="text" name="outDateOne" id="outDateOne" readonly class="search_input search_input_1" id="dateOne" placeholder="출발일 (YYYY-MM-DD)" required="required" style="height: 40px; width: 300px;">
                              <img src="img\calendar.png" id="btnCalOne" onclick="openCalOne()" style="height: 45px; width: 45px; padding:5px;">
                           </div>
                        </div>
                        <div id="con2"> <!-- 왕복 display -->
                           <div class="d-flex flex-lg-row flex-column align-items-start justify-content-lg-between justify-content-start" style="padding-bottom:20px">
                              <input type="text" name="dCityAr" id="dCityAr" class="search_input search_input_1" placeholder="출발지" required="required" style="height: 40px; width: 300px;">
                              <img src="img\pin1.png" id="btnDCity" onclick="openDCityFormAround()" style="height: 40px; width: 40px;">
                              <input type="text" name="aCityAr" id="aCityAr" class="search_input search_input_2" placeholder="도착지" required="required" style="height: 40px; width: 300px;">
                              <img src="img\pin1.png" id="btnACity" onclick="openACityFormAround()" style="height: 40px; width: 40px;">
                              <input type="text" name="outDateAr" id="outDateAr" readonly class="search_input search_input_3" id="dateA1" placeholder="가는 날" required="required" style="height: 40px; width: 140px;">
                              <img src="img\calendar.png" id="btnCalArOut" onclick="openCalAroundOut()" style="height: 45px; width: 45px; padding:5px;">
                              <input type="text" name="inDateAr"  id="inDateAr" readonly class="search_input search_input_3" id="dateA2" placeholder="오는 날 " required="required" style="height: 40px; width: 140px; padding-left:20px;">
                              <img src="img\calendar.png" id="btnCalArIn" onclick="openCalAroundIn()" style="height: 45px; width: 45px; padding:5px;">
                           </div>
                        </div>
                        <div style="padding-left:30px;">
                           <label style="width:300px;">성인</label>
                           <label style="width:300px;">소아</label>
                           <label style="width:300px;">유아</label>
                        </div>
                     
                        <div class="d-flex flex-lg-row flex-column align-items-start justify-content-lg-between justify-content-start" style="padding-bottom:20px">
                           <img src="img\minus.png" id="btnAminus" onclick="clickMinus('inputAdult')" style="height: 45px; width: 45px; padding:5px;">
                           <input type="text" class="search_input search_input_1" name="inputAdult" id="inputAdult" value="1" style="height: 40px; width: 200px; text-align:center;">
                           <img src="img\plus.png" id="btnAplus" onclick="clickPlus('inputAdult')" style="height: 45px; width: 45px; padding:5px;">
                           <img src="img\minus.png" id="btnCminus" onclick="clickMinus('inputChild')" style="height: 45px; width: 45px; padding:5px;">
                           <input type="text" class="search_input search_input_2" name="inputChild" id="inputChild" value="0" style="height: 40px; width: 200px; text-align:center;">
                           <img src="img\plus.png" id="btnCplus" onclick="clickPlus('inputChild')" style="height: 45px; width: 45px; padding:5px;">
                           <img src="img\minus.png" id="btnTminus" onclick="clickMinus('inputTodd')" style="height: 45px; width: 45px; padding:5px;">
                           <input type="text" class="search_input search_input_3" name="inputTodd" id="inputTodd" value="0" style="height: 40px; width: 200px; text-align:center;">
                           <img src="img\plus.png" id="btnTplus" onclick="clickPlus('inputTodd')" style="height: 45px; width: 45px; padding:5px;">
                           <input type="button" class="home_search_button" id="to_schedule_btn" value="항공편 조회"/>
                        </div>
                     </form>
                           
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Destinations -->

   <div class="destinations" id="destinations">
      <div class="container">
         <div class="row">
            <div class="col text-center">
               <div class="section_subtitle">SIST AIRLINES</div>
               <div class="section_title"><h2>인기있는 항공편</h2></div>
            </div>
         </div>
         <div class="row destinations_row">
            <div class="col">
               <div class="destinations_container item_grid">

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_1.jpg" alt="">
                        <div class="spec_offer text-center"><a href="#">특가</a></div>
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Da Nang</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 베트남 항공편</p></div>
                        <div class="destination_price">220,000 원</div>
                     </div>
                  </div>

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_12.png" alt="">
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Vladivostok</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 러시아 항공편</p></div>
                        <div class="destination_price">200,000 원</div>
                     </div>
                  </div>

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_11.png" alt="">
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Singapore</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 싱가폴 항공편</p></div>
                        <div class="destination_price">225,000 원</div>
                     </div>
                  </div>

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_10.png" alt="">
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Shanghai</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 중국 항공편</p></div>
                        <div class="destination_price">185,000 원</div>
                     </div>
                  </div>

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_5.jpg" alt="">
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Cebu</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 필리핀 항공편</p></div>
                        <div class="destination_price">235,000 원</div>
                     </div>
                  </div>

                  <!-- Destination -->
                  <div class="destination item">
                     <div class="destination_image">
                        <img src="img/destination_13.png" alt="">
                     </div>
                     <div class="destination_content">
                        <div class="destination_title"><a href="destinations.html">Osaka</a></div>
                        <div class="destination_subtitle"><p>SIST AIRLINES가 제공하는 일본 항공편</p></div>
                        <div class="destination_price">150,000 원</div>
                     </div>
                  </div>

               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Testimonials -->

   <div class="testimonials" id="testimonials">
      <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="img/testimonials.jpg" data-speed="0.8"></div>
      <div class="container">
         <div class="row">
            <div class="col text-center">
               <div class="section_subtitle">simply amazing places</div>
               <div class="section_title"><h2>Testimonials</h2></div>
            </div>
         </div>
         <div class="row testimonials_row">
            <div class="col">

               <!-- Testimonials Slider -->
               <div class="testimonials_slider_container">
                  <div class="owl-carousel owl-theme testimonials_slider">
                     
                     <!-- Slide -->
                     <div class="owl-item text-center">
                        <div class="testimonial">Lorem ipsum dolor sit amet, consectetur adipiscing elit. lobortis dolor. Cras placerat lectus a posuere aliquet. Curabitur quis vehicula odio.</div>
                        <div class="testimonial_author">
                           <div class="testimonial_author_content d-flex flex-row align-items-end justify-content-start">
                              <div>john turner,</div>
                              <div>client</div>
                           </div>
                        </div>
                     </div>

                     <!-- Slide -->
                     <div class="owl-item text-center">
                        <div class="testimonial">Lorem ipsum dolor sit amet, consectetur adipiscing elit. lobortis dolor. Cras placerat lectus a posuere aliquet. Curabitur quis vehicula odio.</div>
                        <div class="testimonial_author">
                           <div class="testimonial_author_content d-flex flex-row align-items-end justify-content-start">
                              <div>john turner,</div>
                              <div>client</div>
                           </div>
                        </div>
                     </div>

                     <!-- Slide -->
                     <div class="owl-item text-center">
                        <div class="testimonial">Lorem ipsum dolor sit amet, consectetur adipiscing elit. lobortis dolor. Cras placerat lectus a posuere aliquet. Curabitur quis vehicula odio.</div>
                        <div class="testimonial_author">
                           <div class="testimonial_author_content d-flex flex-row align-items-end justify-content-start">
                              <div>john turner,</div>
                              <div>client</div>
                           </div>
                        </div>
                     </div>

                  </div>
               </div>
            </div>
         </div>
      </div>
      <div class="test_nav">
         <ul class="d-flex flex-column align-items-end justify-content-end">
            <li><a href="#">City Breaks Clients<span>01</span></a></li>
            <li><a href="#">Cruises Clients<span>02</span></a></li>
            <li><a href="#">All Inclusive Clients<span>03</span></a></li>
         </ul>
      </div>
   </div>

   <!-- News -->

   <div class="news" id="news">
      <div class="container">
         <div class="row">
            <div class="col-xl-8">
               <div class="news_container">
                  
                  <!-- News Post -->
                  <div class="news_post d-flex flex-md-row flex-column align-items-start justify-content-start">
                     <div class="news_post_image"><img src="img/news_1.jpg" alt=""></div>
                     <div class="news_post_content">
                        <div class="news_post_date d-flex flex-row align-items-end justify-content-start">
                           <div>02</div>
                           <div>june</div>
                        </div>
                        <div class="news_post_title"><a href="#">Best tips to travel light</a></div>
                        <div class="news_post_category">
                           <ul>
                              <li><a href="#">lifestyle & travel</a></li>
                           </ul>
                        </div>
                        <div class="news_post_text">
                           <p>Pellentesque sit amet elementum ccumsan sit amet mattis eget, tristique at leo. Vivamus massa.Tempor massa et laoreet.</p>
                        </div>
                     </div>
                  </div>

                  <!-- News Post -->
                  <div class="news_post d-flex flex-md-row flex-column align-items-start justify-content-start">
                     <div class="news_post_image"><img src="img/news_2.jpg" alt=""></div>
                     <div class="news_post_content">
                        <div class="news_post_date d-flex flex-row align-items-end justify-content-start">
                           <div>01</div>
                           <div>june</div>
                        </div>
                        <div class="news_post_title"><a href="#">Best tips to travel light</a></div>
                        <div class="news_post_category">
                           <ul>
                              <li><a href="#">lifestyle & travel</a></li>
                           </ul>
                        </div>
                        <div class="news_post_text">
                           <p>Tempor massa et laoreet malesuada. Pellentesque sit amet elementum ccumsan sit amet mattis eget, tristique at leo.</p>
                        </div>
                     </div>
                  </div>

                  <!-- News Post -->
                  <div class="news_post d-flex flex-md-row flex-column align-items-start justify-content-start">
                     <div class="news_post_image"><img src="img/news_3.jpg" alt=""></div>
                     <div class="news_post_content">
                        <div class="news_post_date d-flex flex-row align-items-end justify-content-start">
                           <div>29</div>
                           <div>may</div>
                        </div>
                        <div class="news_post_title"><a href="#">Best tips to travel light</a></div>
                        <div class="news_post_category">
                           <ul>
                              <li><a href="#">lifestyle & travel</a></li>
                           </ul>
                        </div>
                        <div class="news_post_text">
                           <p>Vivamus massa.Tempor massa et laoreet malesuada. Aliquam nulla nisl, accumsan sit amet mattis.</p>
                        </div>
                     </div>
                  </div>

               </div>
            </div>

            <!-- News Sidebar -->
            <div class="col-xl-4">
               <div class="travello">
                  <div class="background_image" style="background-image:url(img/travello.jpg)"></div>
                  <div class="travello_content">
                     <div class="travello_content_inner">
                        <div></div>
                        <div></div>
                     </div>
                  </div>
                  <div class="travello_container">
                     <a href="#">
                        <div class="d-flex flex-column align-items-center justify-content-end">
                           <span class="travello_title">Get a 20% Discount</span>
                           <span class="travello_subtitle">Buy Your Vacation Online Now</span>
                        </div>
                     </a>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>

   <!-- Footer -->

   <footer class="footer">
      <div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="img/footer_1.jpg" data-speed="0.8"></div>
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

                  <!-- Footer Contact Item -->
                  <div class="col-xl-4 footer_contact_col">
                     <div class="footer_contact_item d-flex flex-column align-items-center justify-content-start text-center">
                        <div class="footer_contact_icon"><img src="img/sign.svg" alt=""></div>
                        <div class="footer_contact_title">give us a call</div>
                        <div class="footer_contact_list">
                           <ul>
                              <li>Office Landline: +44 5567 32 664 567</li>
                              <li>Mobile: +44 5567 89 3322 332</li>
                           </ul>
                        </div>
                     </div>
                  </div>

                  <!-- Footer Contact Item -->
                  <div class="col-xl-4 footer_contact_col">
                     <div class="footer_contact_item d-flex flex-column align-items-center justify-content-start text-center">
                        <div class="footer_contact_icon"><img src="img/trekking.svg" alt=""></div>
                        <div class="footer_contact_title">come & drop by</div>
                        <div class="footer_contact_list">
                           <ul style="max-width:190px">
                              <li>4124 Barnes Street, Sanford, FL 32771</li>
                           </ul>
                        </div>
                     </div>
                  </div>

                  <!-- Footer Contact Item -->
                  <div class="col-xl-4 footer_contact_col">
                     <div class="footer_contact_item d-flex flex-column align-items-center justify-content-start text-center">
                        <div class="footer_contact_icon"><img src="img/around.svg" alt=""></div>
                        <div class="footer_contact_title">send us a message</div>
                        <div class="footer_contact_list">
                           <ul>
                              <li>youremail@gmail.com</li>
                              <li>Office@yourbusinessname.com</li>
                           </ul>
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

<script src="js/jquery-3.2.1.min.js"></script>
<script src="css/bootstrap4/popper.js"></script>
<script src="css/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/scrollTo/jquery.scrollTo.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/custom.js"></script>
<script src="js/bootstrap.js"></script>

</body>
</html>