<%@page import="java.text.DecimalFormat"%>
<%@page import="member.MemberVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="Reservation.ReservationDao"%>
<%@page import="Reservation.ReservationVO"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.List"%>
<%@page import="User.UserVO"%>
<%@page import="User.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>
    
<!DOCTYPE html>
<html>
<head>

<title>결제정보 페이지</title>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" type="text/css" href="./css/bootstrap4/bootstrap.min.css">
<link href="./plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="./plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="./css/main_styles.css">
<link rel="stylesheet" type="text/css" href="./css/responsive.css">
<link rel="stylesheet" href="./css/bootstrap.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">


<script>
    function openChild(scCode, passport)
    {
        let openWin;
        console.log('실행');
        window.name="schedule_list";
        openWin=window.open("selectSeats.jsp?scCode="+scCode+"&seatNum="+passport,"selectSeats","width=670, height=350");
    }
        
    //달력 javascript dateSelect
    function dateSelect(docForm,selectIndex) {
           watch = new Date(docForm.year.options[docForm.year.selectedIndex].value, docForm.month.options[docForm.month.selectedIndex].value,1);
           hourDiffer = watch - 86400000;
           calendar = new Date(hourDiffer);

           var daysInMonth = calendar.getDate();
              for (var i = 0; i < docForm.day.length; i++) {
                 docForm.day.options[0] = null;
              }
              for (var i = 0; i < daysInMonth; i++) {
                 docForm.day.options[i] = new Option(i+1);
           }
           document.inputPassenger.day.options[0].selected = true;
        }
    
    //달력 javascript
    function Today(year,mon,day, pNum,inYear,inMonth,inDay,idYear,idMonth,idDay){
           //alert(pNum);
             if(year == null && mon == null && day == null){       
                today = new Date();
                this_year=today.getFullYear();
                this_month=today.getMonth();
                this_month+=1;
                if(this_month <10){
                    this_month="0" + this_month;
                }
                this_day=today.getDate();
                if(this_day<10) {
                    this_day="0" + this_day;
                }   
            }
            else{  
                var this_year = eval(year);
                var this_month = eval(mon); 
                var this_day = eval(day);
             }
         
          montharray=new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 
          maxdays = montharray[this_month-1]; 
        //아래는 윤달을 구하는 것
          if (this_month==2) { 
              if ((this_year/4) != (this_year/4))
                 maxdays=28; 
              else
                 maxdays=29; 
          } 
         
          document.writeln("<select name='"+inYear+"' id='"+ idYear+ pNum +"' size=1 onChange='dateSelect(this.form.year"+ pNum +", this.form.month"+ pNum +", this.selectedIndex);'>");
          for(i=this_year-100; i < this_year+1; i++){//현재 년도에서 과거로 5년까지 미래로 5년까지를 표시함
              if(i==this_year) 
                 document.writeln("<OPTION VALUE="+i+ " selected >" +i); 
              else 
                 document.writeln("<OPTION VALUE="+i+ ">" +i); 
          }    
          document.writeln("</select>년");      


        document.writeln("<select name='"+inMonth+"' id='"+idMonth+pNum +"' size=1 onChange='dateSelect(this.form.year"+ pNum +", this.form.month"+ pNum +", this.selectedIndex);'>");
          for(i=1;i<=12;i++){ 
              if(i<10){
                  if(i==this_month) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i); 
                  else document.writeln("<OPTION VALUE=0" +i+ ">0"+i);
              }         
              else{
                  if(i==this_month) document.writeln("<OPTION VALUE=" +i+ " selected >" +i);  
                  else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
              }                     
         }         
        document.writeln("</select>월");

        document.writeln("<select name='"+inDay+"' id='"+idDay+ pNum +"' size=1>");
          for(i=1;i<=maxdays;i++){ 
              if(i<10){
                  if(i==this_day) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i); 
                  else document.writeln("<OPTION VALUE=0" +i+ ">0"+i); 
              }
              
              else{
                  if(i==this_day) document.writeln("<OPTION VALUE=" +i+ " selected } >"+i); 
                  else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
              }                     
         }         
        document.writeln("</select>일"); 

        }
</script>

</head>
<style>
    .grid_4{width:300px;display:inline;float:right;margin-left:10px;margin-right:10px;padding:10px;color:#fff;background-color:#337AB7}

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
</style>
<body>
    <div class="super_container">
    
<!-------------------------------------------- Header -------------------------------------------------------->

    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="header_content d-flex flex-row align-items-center justify-content-start">
                        <div class="header_content_inner d-flex flex-row align-items-end justify-content-start">
                            <div class="logo"><a href="index.jsp">SIST AIRLINES</a></div>
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
       
    </header>

    <!-- Home -->

    <div class="home">
        
        <!-- Home Slider -->
        <div class="home_slider_container" style="background-image:url(img/home_slider.jpg)">
            <div class="owl-carousel owl-theme home_slider">
                
           
            </div>

          </div>
    </div>
<!-------------------------------------------- Header 끝 -------------------------------------------------------->   
    
    <form action='<%=HR_PATH %>/reservationtest/reservationtest.do' name="inputPassenger" method="post">
        <%

            request.setCharacterEncoding("utf-8");
            HttpSession httpSession = request.getSession(true);
        
            DecimalFormat formatter=new DecimalFormat("###,###,###");
            int uNum = 0;
            String userEngName = "";
            int userGender = 0;
            String userBirth = "";

            String year = "";
            String month = "";
            String day = "";        
            
            if(null != session.getAttribute("user")){
                
                MemberVO inVO =(MemberVO)session.getAttribute("user");
                uNum=inVO.getuNum();
                userEngName = inVO.getuEname();
                userGender = inVO.getuGender();
                userBirth = inVO.getuBir();

                year = userBirth.substring(0, 4);
                month = userBirth.substring(4, 6);
                day = userBirth.substring(6, 8);
                

            } else {
                uNum = 0;
            }
            
            String adultCnt= (String)httpSession.getAttribute("inputAdult");
            String childCnt= (String)httpSession.getAttribute("inputChild");
            String toddCnt= (String)httpSession.getAttribute("inputTodd");
              
            int adult=Integer.parseInt(adultCnt);
            int child=Integer.parseInt(childCnt);
            int todd=Integer.parseInt(toddCnt);
            
            
        
            // **************form으로 넘어온 값을 변수에 저장***********
            String scCode = null;
            String scCodeR = null;
            
            String scPrice=null;
            String scPriceR=null;
            
            String scCodeAdd = null;
            String scCodeRAdd = null;
            String scPriceAdd=null;
            String scPriceRAdd=null;
            
            String scAcity=null;
            String scAcityR=null;
            String scDcity=null;
            String scDcityR=null;
            
            String scMileage=null;
            String scMileageR=null;
        
            String tripType=request.getParameter("tripType");
            String notIC=(String)request.getParameter("notIC");
            //notIC: 인천이 없을때 true
            String radioNum1=null;
            String radioNum2=null;
            int rNum1=0;
            int rNum2=0;
            
            int price=0;
            int priceAdd=0;
            int priceR=0;
            int priceRAdd=0;
            
            String resultPrice=null;
            
            double totalPrice=0;
            if(tripType.equals("1")&&notIC.equals("false")){//편도 인천있음
                   radioNum1=(String)request.getParameter("radioNum1");
                   scCode=request.getParameter("scCode"+radioNum1);
                   scPrice=request.getParameter("scPrice"+radioNum1);
                    
                   rNum1=Integer.parseInt(radioNum1);
                   
                   scAcity=request.getParameter("scAcity"+rNum1);
                   scDcity=request.getParameter("scDcity"+rNum1);
                   scMileage=request.getParameter("scMileage"+rNum1);
                   System.out.println("scPrice"+radioNum1+":"+scPrice);
                   
                   price=Integer.parseInt(scPrice.replace(",","").trim()); 
                       
                   totalPrice=((double)(price*adult)*1)+((double)(price*child)*0.8)+((double)(price*todd)*0.5);
                   resultPrice=String.format("%,", (int)totalPrice);
                   
                   
                 }else if(tripType.equals("1")&&notIC.equals("true")){//편도 인천없음
                 
                	 System.out.println("여기왔닌?");
                 radioNum1=(String)request.getParameter("radioNum1");
                 rNum1=Integer.parseInt(radioNum1);
                
                 scCode=request.getParameter("scCode"+rNum1);
            
                 scCodeR=request.getParameter("scCode"+(rNum1-1));
                 
                 scPrice=request.getParameter("scPrice"+rNum1);
                 scPriceR=request.getParameter("scPrice"+(rNum1-1));
                 
                 scAcity=request.getParameter("scAcity"+rNum1);//오사카
                 scDcity=request.getParameter("scDcity"+rNum1);//인천
                 scAcityR=request.getParameter("scAcity"+(rNum1-1));//인천
                 scDcityR=request.getParameter("scDcity"+(rNum1-1));//우한
                    
                 scMileage=request.getParameter("scMileage"+rNum1);//오사카 인천
                 scMileageR=request.getParameter("scMileage"+(rNum1-1));//인천 우한
         
                 price=Integer.parseInt(scPrice.replace(",","").trim()); 
                 priceAdd=Integer.parseInt(scPriceR.replace(",","").trim()); 
                 
                      
                 totalPrice=((double)(price*adult)*1)+((double)(price*child)*0.8)+((double)(price*todd)*0.5)+((double)(priceAdd*adult)*1)+((double)(priceAdd*child)*0.8)+((double)(priceAdd*todd)*0.5);
         
                 resultPrice=String.format("%,d", (int)totalPrice);
                 
                 
                 
                }else if(tripType.equals("2")&&notIC.equals("false")){//왕복 인천있음
                radioNum1=(String)request.getParameter("radioNum1");
                radioNum2=(String)request.getParameter("radioNum2");        
                
                rNum1=Integer.parseInt(radioNum1);
                rNum2=Integer.parseInt(radioNum2);
                
                scCode=request.getParameter("scCode"+rNum1);
                scCodeR=request.getParameter("scCodeR"+rNum2);
                scPrice=request.getParameter("scPrice"+rNum1);
                scPriceR=request.getParameter("scPriceR"+rNum2);
                
                scAcity=request.getParameter("scAcity"+rNum1);
                scDcity=request.getParameter("scDcity"+rNum1);
               
                scMileage=request.getParameter("scMileage"+rNum1);//오사카 인천
                scMileageR=request.getParameter("scMileageR"+rNum2);//인천 우한
                      
                
                price=Integer.parseInt(scPrice.replace(",","").trim()); 
                priceAdd=Integer.parseInt(scPriceR.replace(",","").trim()); 
               
                totalPrice=((double)(price*adult)*1)+((double)(price*child)*0.8)+((double)(price*todd)*0.5)+((double)(priceAdd*adult)*1)+((double)(priceAdd*child)*0.8)+((double)(priceAdd*todd)*0.5);
                resultPrice=String.format("%,d", (int)totalPrice);
                
            }else if(tripType.equals("2")&&notIC.equals("true")){//왕복 인천없음
                
                radioNum1=(String)request.getParameter("radioNum1");
                radioNum2=(String)request.getParameter("radioNum2");        
                
                rNum1=Integer.parseInt(radioNum1);
                rNum2=Integer.parseInt(radioNum2);
                
                
                scCodeAdd=request.getParameter("scCode"+(rNum1));//오사카 인천
                scCode=request.getParameter("scCode"+(rNum1-1));//인천 우한 
                               
                scCodeR=request.getParameter("scCodeR"+rNum2);//우한 인천
                scCodeRAdd=request.getParameter("scCodeR"+(rNum2-1));//인천 오사카
                
                scPrice=request.getParameter("scPrice"+rNum1);//오사카 인천
                scPriceR=request.getParameter("scPrice"+(rNum1-1));//인천 우한
                 
                scDcity=request.getParameter("scDcity"+rNum1);//오사카
                scAcity=request.getParameter("scAcity"+rNum1);//인천
                
                scDcityR=request.getParameter("scDcity"+(rNum1-1));//인천
                scAcityR=request.getParameter("scAcity"+(rNum1-1));//우한
                
                scMileage=request.getParameter("scMileage"+rNum1);//오사카 인천
                scMileageR=request.getParameter("scMileage"+(rNum1-1));//인천 우한
               
                
                price=Integer.parseInt(scPrice.replace(",","").trim()); 
                priceAdd=Integer.parseInt(scPriceR.replace(",","").trim()); 
               
               
                totalPrice=(((double)(price*adult)*1)+((double)(price*child)*0.8)+((double)(price*todd)*0.5)+((double)(priceAdd*adult)*1)+((double)(priceAdd*child)*0.8)+((double)(priceAdd*todd)*0.5))*2;
                resultPrice = String.format("%,d", (int)(totalPrice));
          }
            
            
        %>

<!---------------------------------------- 메뉴바 끝 ---------------------------------------------------->
        
<!---------------------------------------- 사이드 바 ---------------------------------------------------->
        <div id="sidebar" class=" grid_4"  style="clear: both; position: relative;">
            		결제정보
            <hr/> 
            
            <%=StringUtil.sideBar(scDcity, scAcity, scPrice, tripType, scMileage, adult, child, todd) %>
            
            <% if(tripType.equals("1")&&notIC.equals("true")){//인천없고 편도 %>
            
            <%=StringUtil.sideBar(scDcityR, scAcityR, scPrice, tripType, scMileage, adult, child, todd) %>
            
            <%
                }
            %>
            
            <% if(scDcityR==null && tripType.equals("2")){//인천있고 왕복 %>
            
            <%=StringUtil.sideBar(scAcity, scDcity, scPrice, tripType, scMileage, adult, child, todd) %>
            
            <%
                }
            %>
            
            <% if(scDcityR!=null && tripType.equals("2")){//인천 없고 왕복 
            		
            
            %>
            
            <%=StringUtil.sideBar(scDcityR, scAcityR, scPriceR, tripType, scMileageR, adult, child, todd) %>
            
            <%
                }
            %>
            
            <LABEL>총가격: &nbsp;&nbsp;</LABEL><label><%=resultPrice%>원</label><br/>
            <input type="hidden" id="totalPrice" name="totalPrice" value="<%=resultPrice%>">
        </div>
<!------------------------------------------- 사이드 바 끝 ---------------------------------------------------->

<!-------------------------------------------- hidden ----------------------------------------------------->        
        <input type="hidden" name="tripType"  id="tripType"  value="<%=tripType%>">
        <input type="hidden" name="notIC"  id="notIC"  value=<%=notIC %>>
        <input type="hidden" id="scPrice" name="scPrice" value=<%=scPrice %> />
        <input type="hidden" id="scMileage" name="scMileage" value=<%=scMileage %> />
        <input type="hidden" name="uNum"  id="uNum"  value=<%=uNum %>>
        <input type="hidden" name="adult" id="adult" value=<%=adult %>>
        <input type="hidden" name="child" id="child" value=<%=child %>>
        <input type="hidden" name="todd"  id="todd"  value=<%=todd %>>
        <input type="hidden" name="work_div" value="do_insert" id="work_div" />
<!------------------------------------------- hidden 끝 ----------------------------------------------------->       

        
        <%
            if (uNum != 0) {
        %>
            
<!--------------------------------------------  탑승객 구분   (성인/회원) ----------------------------------------->

        <%=StringUtil.memberInput(userGender, userEngName, year, month, day, scCode,scCodeR,scCodeAdd,
                                  scCodeRAdd,scDcity,scAcity,scDcityR,scAcityR,"gender_M","ename_M",
                                  "scode_M","MemberseatNum","passport_M","phone_M","email_M") %>
        
<!--------------------------------------------- 탑승객 구분   (성인/비회원) ----------------------------------------->

        <%
            } else {
        %>
        
        <%=StringUtil.nonMemberInput(scCode, scCodeR, scCodeAdd, scCodeRAdd, scDcity, scAcity, scDcityR,
                                     scAcityR,"gender_N","ename_N","scode_N","nMemberseatNum","passport_N",
                                     "phone_N","email_N") %>
        
        <%
                }
        %>
<!-----------------------------------------------  동행자(성인,소아,유아)  ---------------------------------------->

        <%=StringUtil.divPassenger(adult, child, todd, scCode,scCodeR,scCodeAdd,scCodeRAdd,scDcity,scAcity,scDcityR,scAcityR) %>
            
     
<!----------------------------------------------------탑승객 정보 끝 --------------------------------------------->
        <div class="container mt-4"   style="max-width: 800px; padding:5px; text-align:right;">
        <input type="button" value="결제하기" id="insert_btn" class="home_search_button" style="width:150px;"/>
        </div>
        
    </div>  
            
    </form>
<!------------------------------------------------------ footer ------------------------------------------------>   
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
                        <div class="col-xl-4 footer_contact_col">
                            <div class="footer_contact_item d-flex flex-column align-items-center justify-content-start text-center">
                                <div class="footer_contact_icon"><img src="img/around.svg" alt=""></div>
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
Copyright &copy;
<script>
document.write(new Date().getFullYear());
</script> 
All rights reserved | This template is made with 
<i class="fa fa-heart-o" aria-hidden="true"></i>
 by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --> </div>
    </footer>
<!------------------------------------------------------ footer 끝 ----------------------------------------------->

<script src="js/jquery-3.2.1.min.js"></script>
<script src="./js/custom.js"></script>
<script src="css/bootstrap4/popper.js"></script>
<script src="css/bootstrap4/bootstrap.min.js"></script>
<script src="plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="plugins/scrollTo/jquery.scrollTo.min.js"></script>
<script src="plugins/easing/easing.js"></script>
<script src="plugins/parallax-js-master/parallax.min.js"></script>
<script src="js/bootstrap.js"></script>

<script src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript" src="jquery-contained-sticky-scroll.js"></script>

<!--------------------------------------------- jquery  -------------------------------------------------->

<script>
        $('#sidebar').containedStickyScroll();

        $("#insert_btn").on('click', function(){
            var work_div = $("#work_div").val();
             
            if(null == work_div || work_div.trim().length == 0){
                alert("작업구분을 확인 하세요.");
                return;
            }
                       
           //회원 입력란
           //var ename    = $("#ename").val();
           
           //if(null == ename || ename.trim().length == 0){
              //$("#ename").focus();//focus
              //alert("이름을 확인 하세요.");
               //return;  
           //}
                    
           //var userseatNum   = $("#userseatNum").val();
           
           //if(null == userseatNum || userseatNum.trim().length == 0){
              // alert("좌석을 선택 하세요");
               //return;
           //}
           
           //var passport = $("#passport").val();
           
           //if(null == passport || passport.trim().length == 0){
               //alert("여권번호를 입력 하세요.");
               //return;
           //}
           
           //var year = $("#year option:selected").val();
               
           //if(null == year || year.trim().length == 0){
                //alert("년도가 입력이 안됬대...");
                //return;   
           //}  
           //var month = $("#month option:selected").val();
               
           //if(null == month || month.trim().length == 0){
                //alert("월이 입력이 안됬대...");
                //return;
           //}      
           //var day = $("#day option:selected").val();         
           
           //if(null == day || day.trim().length == 0){
                //alert("일이 입력이 안됬대...");
                //return;
           //}  
           
           //탑승객 구별
                 var uNum  = $("#uNum").val();  //회원
                 var adult = $("#adult").val(); //성인
                 var child = $("#child").val(); //소아
                 var todd  = $("#todd").val();  //유아
                 var tripType = $("#tripType").val();  //왕복 , 편도 구분
                 var notIC = $("#notIC").val();  //왕복 , 편도 구분
                 var scPrice=$('#scPrice').val();
                 var scMileage=$('#scMileage').val();
                 
           //회원  
                 var gender_M = [];   
                 var ename_M = [];
                 var passport_M = [];
                 var scode_M = [];
                 var year_M = [];
                 var month_M = [];
                 var day_M = [];
                 var phone_M = [];
                 var email_M = [];
           
           //비회원
                 var gender_N = [];   
                 var ename_N = [];
                 var passport_N = [];
                 var scode_N = [];
                 var year_N = [];
                 var month_N = [];
                 var day_N = [];
                 var phone_N = [];
                 var email_N = [];
  
           //성인
                 var gender_A = [];   
                 var ename_A = [];
                 var passport_A = [];
                 var scode_A = [];
                 var scode_A2 = [];
                 var scode_A3 = [];
                 var scode_A4 = [];
                 var year_A = [];
                 var month_A = [];
                 var day_A = [];
                 
           //소아
                 var gender_C = [];
                 var ename_C = [];
                 var passport_C = [];
                 var scode_C = [];
                 var year_C = [];
                 var month_C = [];
                 var day_C = [];
                 
           //유아
                 var gender_T = [];
                 var ename_T = [];
                 var passport_T = [];
                 var scode_T = [];
                 var year_T = [];
                 var month_T = [];
                 var day_T = [];
                 
                 
                var uNum = $("input[name='uNum']").val();
                var tripType = $("input[name='tripType']").val();
                var size01 = $("input[name='scode_M']").length;
                var size02 = $("input[name='scode_N']").length;
                var totalPrice=$('#totalPrice').val();
               
                 //회원
                   if(uNum != 0){
                    for(i=0;i<size01; i++){     
                        gender_M.push($("input:radio[name='gender_M']:checked").val());
                        ename_M.push($("input[name='ename_M']").val());
                        passport_M.push($("input[name='passport_M']").val());
                        scode_M.push($("input[name='scode_M']").eq(i).val());
                        year_M.push($("select[name='year_M']").val());
                        month_M.push($("select[name='month_M']").val());
                        day_M.push($("select[name='day_M']").val());
                        phone_M.push($("input[name='phone_M']").val());
                        email_M.push($("input[name='email_M']").val())  
                    }   
                    if(adult > 0){  
                        for(i=0;i<size01;i++){
                          gender_A.push($("input:radio[id='gender_A0']:checked").val());
                          ename_A.push($("input[name='ename_A']").val());
                          passport_A.push($("input[name='passport_A']").val());
                          scode_A.push($("input[name='scode_A']").eq(i).val());
                          year_A.push($("select[name='year_A']").val());
                          month_A.push($("select[name='month_A']").val());
                          day_A.push($("select[name='day_A']").val());              
                        }
                     
                     }
                     
                   //소아  
                   var child = $("input[name='child']").val();
                     if(child > 0){  
                       for(i=0; i<size01; i++){
                          gender_C.push($("input:radio[name='gender_C0']:checked").val());
                          ename_C.push($("input[name='ename_C']").val());
                          passport_C.push($("input[name='passport_C']").val());
                          scode_C.push($("input[name='scode_C']").eq(i).val());
                          year_C.push($("select[name='year_C']").val());
                          month_C.push($("select[name='month_C']").val());
                          day_C.push($("select[name='day_C']").val());              
                       }
                     }  
                    //유아  
                    var todd = $("input[name='todd']").val();
                      if(todd > 0){  
                        for(i=0; i<size01; i++){
                           gender_T.push($("input:radio[name='gender_T0']:checked").val()); 
                           ename_T.push($("input[name='ename_T']").val());
                           passport_T.push($("input[name='passport_T']").val());
                           scode_T.push($("input[name='scode_T']").eq(i).val());
                           year_T.push($("select[name='year_T']").val());
                           month_T.push($("select[name='month_T']").val());
                           day_T.push($("select[name='day_T']").val());              
                        }
                      }
                    
                    
                    
                    
                    
                   }else{//비회원
                    for(i=0;i<size02; i++){
                       gender_N.push($("input:radio[name='gender_N']:checked").val());
                       ename_N.push($("input[name='ename_N']").val());
                       passport_N.push($("input[name='passport_N']").val());
                       scode_N.push($("input[name='scode_N']").eq(i).val());
                       year_N.push($("select[name='year_N']").val());
                       month_N.push($("select[name='month_N']").val());
                       day_N.push($("select[name='day_N']").val());
                       phone_N.push($("input[name='phone_N']").val());
                       email_N.push($("input[name='email_N']").val())  
                     }
                   
                    if(adult > 0){  
                        for(i=0;i<size02;i++){
                          gender_A.push($("input:radio[id='gender_A0']:checked").val());
                          ename_A.push($("input[name='ename_A']").val());
                          passport_A.push($("input[name='passport_A']").val());
                          scode_A.push($("input[name='scode_A']").eq(i).val());
                          year_A.push($("select[name='year_A']").val());
                          month_A.push($("select[name='month_A']").val());
                          day_A.push($("select[name='day_A']").val());              
                        }
                     
                     }
                     
                   //소아  
                   var child = $("input[name='child']").val();
                     if(child > 0){  
                       for(i=0; i<size02; i++){
                          gender_C.push($("input:radio[name='gender_C0']:checked").val());
                          ename_C.push($("input[name='ename_C']").val());
                          passport_C.push($("input[name='passport_C']").val());
                          scode_C.push($("input[name='scode_C']").eq(i).val());
                          year_C.push($("select[name='year_C']").val());
                          month_C.push($("select[name='month_C']").val());
                          day_C.push($("select[name='day_C']").val());              
                       }
                     }  
                    //유아  
                    var todd = $("input[name='todd']").val();
                      if(todd > 0){  
                        for(i=0; i<size02; i++){
                           gender_T.push($("input:radio[name='gender_T0']:checked").val()); 
                           ename_T.push($("input[name='ename_T']").val());
                           passport_T.push($("input[name='passport_T']").val());
                           scode_T.push($("input[name='scode_T']").eq(i).val());
                           year_T.push($("select[name='year_T']").val());
                           month_T.push($("select[name='month_T']").val());
                           day_T.push($("select[name='day_T']").val());              
                        }
                      }
                   }
                   
               
                   
       
          function goPayment(){
          	  window.location.href = '/SistAirLine/Payment.jsp?totalPrice='+totalPrice;
          }
           $.ajax({
               type:"POST",
               url:"/SistAirLine/reservationtest/reservationtest.do", 
               dataType:"html",
               traditional : true,
               data:{"work_div":work_div,
                     
                     //회원 
                     "gender_M":gender_M,
                     "ename_M":ename_M,
                     "scode_M":scode_M,
                     "passport_M":passport_M,
                     "year_M" :year_M,
                     "month_M" :month_M,
                     "day_M" :day_M,
                     "phone_M" :phone_M,
                     "email_M" :email_M, 
                     
                     //비회원
                     "gender_N":gender_N,
                     "ename_N":ename_N,
                     "scode_N":scode_N,
                     "passport_N":passport_N,
                     "year_N" :year_N,
                     "month_N" :month_N,
                     "day_N" :day_N,
                     "phone_N" :phone_N,
                     "email_N" :email_N,
                     
                     //성인
                     "gender_A":gender_A,
                     "ename_A":ename_A,
                     "scode_A":scode_A,
                     "passport_A":passport_A,
                     "year_A" :year_A,
                     "month_A" :month_A,
                     "day_A" :day_A,
                     
                     //소아
                     "gender_C":gender_C,
                     "ename_C":ename_C,
                     "scode_C":scode_C,
                     "passport_C":passport_C,
                     "year_C" :year_C,
                     "month_C" :month_C,
                     "day_C" :day_C,
                     
                     //유아
                     "gender_T":gender_T,
                     "ename_T":ename_T,
                     "scode_T":scode_T,
                     "passport_T":passport_T,
                     "year_T" :year_T,
                     "month_T" :month_T,
                     "day_T" :day_T,
                     
                     //구별
                     "uNum" :uNum,
                     "adult" :adult,
                     "child" : child,
                     "todd" : todd,
                     
                     //왕복-편도구분
                     "triptype" : tripType,
                     "notIC":notIC,
                     "scPrice":scPrice,
                     "scMileage":scMileage
            
               }, 
                success:function(data){ //성공                  
                    var jsonObj = JSON.parse(data);                  
                    //if(null != jsonObj && jsonObj.msgID == "1"){
            	    	alert('결제페이지로 이동합니다.');
            	    	goPayment();
            	    //}else{
            	    //	alert('This is What!!?');
            	   // }            
                },
                error:function(xhr,status,error){
                 alert("error:"+error);
                },
                complete:function(data){               
                }
              });//--ajaxd  
              
               //}else
            //}
           
          });//--#insert_btn

</script>

</body>

</html>
