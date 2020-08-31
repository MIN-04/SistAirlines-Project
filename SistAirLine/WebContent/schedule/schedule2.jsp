<%--
	���ϸ�: 
	�ۼ���: 
	�ۼ���:
--%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<%
//req.setAttribute("list", list);//목록 
//req.setAttribute("paramVO", inVO);//PARAM
//req.setAttribute("totalCnt", totalCnt);//총글수
    //param
    
    ScheduleVO inVO=(ScheduleVO)request.getAttribute("paramVO");
  
    
	List<ScheduleVO> listOneWay=(List<ScheduleVO>)request.getAttribute("listOneWay"); // 편도 인천없을때
    List<ScheduleVO> listOneWay2=(List<ScheduleVO>)request.getAttribute("listOneWay2");// 편도 인천있을때
    
    //왕복
    List<ScheduleVO> listAround1=(List<ScheduleVO>)request.getAttribute("listAround1");//왕복-인천없을때
    List<ScheduleVO> listAround2=(List<ScheduleVO>)request.getAttribute("listAround2");//왕복-인천없을때
    List<ScheduleVO> listAround3=(List<ScheduleVO>)request.getAttribute("listAround3");//왕복-인천있을때
    List<ScheduleVO> listAround4=(List<ScheduleVO>)request.getAttribute("listAround4");//왕복-인천있을때
    
 
 //paging
    
    String url ="/SistAirLine/scheduleDTO/schedule.do";//호출 URL
    String scriptName="doSearchPage";//자바스크립트 함수(url, no)
   
%>  
<html>
<head>

<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>


  <style type="text/css">
   body {
    font-family: Arial, Verdana, sans-serif;
    color: #111111;}
   table {
    width: 90%;}
   th, td {
    padding: 7px 10px 10px 10px;}
   th {
    text-transform: uppercase;
    letter-spacing: 0.1em;
    font-size: 90%;
    border-bottom: 2px solid #111111;
    border-top: 1px solid #999;
    text-align: left;}
   tr.even {
    background-color: #efefef;}
   tr:hover {
    background-color: #c3e6e5;}
   .money {
    text-align: right;}
    .acenter {
    text-align: center;}
    
     select { width: 200px; /* 원하는 너비설정 */
  padding: .5em .3em; /* 여백으로 높이 설정 */ 
  font-family: inherit; /* 폰트 상속 */ 
  border: 1px solid #999; 
  border-radius: 0px; /* iOS 둥근모서리 제거 */  
  appearance: none; }

 input[type="text"], input[type="password"] { 
 height: auto; /* 높이 초기화 */ 
 line-height: normal; /* line-height 초기화 */
  padding: .5em .3em; /* 여백 설정 */ }
  </style>
    
  </style>
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
<title>Insert title here</title>
</head>
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
									<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown" role="button" aria-haspopup="true"
									aria-expanded="false">로그인/회원가입</a>
										<ul class="dropdown-menu">
											<li><a href="login.jsp">로그인</a></li>
											<li><a href="join2.jsp">회원가입</a></li>
											<li><a href="mypage.jsp">마이 페이지</a></li>
										</ul></li>
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
			<ul class="d-flex flex-row align-items-start justify-content-start">
				<li><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-dribbble" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-behance" aria-hidden="true"></i></a></li>
				<li><a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
			</ul>
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
				<ul class="d-flex flex-column align-items-end justify-content-end">
					<li><a href="#" data-scroll-to="#destinations">Offers<span>01</span></a></li>
					<li><a href="#" data-scroll-to="#testimonials">Testimonials<span>02</span></a></li>
					<li><a href="#" data-scroll-to="#news">Latest<span>03</span></a></li>
				</ul>
			</div>
		</div>
	</div>

	<!-- Search -->

	<div class="home_search">
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="home_search_container">
						<div class="home_search_title"><a href="index.jsp">항공편 선택</a></div>
						<div class="home_search_content" >
	<form action="../InputPassenger.jsp" name="searchfrm" id="searchfrm" method="post">
	<input type="hidden" name="work_div"/>
    <input type="hidden" name="page_num"/>
    <input type="hidden" name="seq"/>
    
                    
    <!-- //검색 -->
	     <table id="listTable">
	        <thead>
	            <tr>
	                <th>날짜</th>
	                <th>출발시간</th>
	                <th>소요시간</th>
	                <th>출발지</th>
	                <th>도착지</th>
	                <th>가격</th>
	                <th>적립마일리지</th>
	                <th>수용인원</th>
	                <th>잔여좌석</th>
	                <th style="display:none;">scCode</th>
	            </tr>
	        </thead>
	         <tbody>
	         	<form action="" method="post">
	             <c:choose >
                       <c:when test="${listOneWay.size()>0 && listOneWay2.size()==0}"><!-- 인천없을때 -->
                        <c:forEach var="i" begin="0" end="${listOneWay.size()-1}">

                               <tr>
                                <td><c:out value="${listOneWay.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listOneWay.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listOneWay.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listOneWay.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listOneWay.get(i).scAcity }"></c:out> </td>
                                <td class="money"><c:out value="${listOneWay.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listOneWay.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listOneWay.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listOneWay.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listOneWay.get(i).scCode }"></c:out> </td>
                               </tr>
          					</c:forEach>
                 		</c:when>
                       <c:when test="${listOneWay2.size() >0 }"><!-- 인천있을때 -->
                        <c:forEach var="i" begin="0" end="${listOneWay2.size()-1}">
                               
          					   <tr>
                                <td><c:out value="${listOneWay2.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listOneWay2.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listOneWay2.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listOneWay2.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listOneWay2.get(i).scAcity }"></c:out> </td>
                                <td class="money"><c:out value="${listOneWay2.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listOneWay2.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listOneWay2.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listOneWay2.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listOneWay2.get(i).scCode }"></c:out> </td>
                                <c:if test="${i%2 ==0}">
                                <td rowspan="2"><input type="radio" name="scCode" value="${listOneWay2.get(i).scCode }"> </td>
                                </c:if>
                               </tr>
          						
                      	</c:forEach> 
                       </c:when>
                        <c:when test="${listAround1.size() >0 && listAround3.size()==0}"><!--왕복 인천없을때 -->
                      	<tr><td colspan="10"><b>출발 스케줄</b></td></tr>

                        <c:forEach var="i" begin="0" end="${listAround1.size()-1}">
                               
          					   <tr>
                                <td><c:out value="${listAround1.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listAround1.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listAround1.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listAround1.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listAround1.get(i).scAcity }"></c:out> </td>
                                <td class="money"><c:out value="${listAround1.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listAround1.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround1.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround1.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listAround1.get(i).scCode }"></c:out> </td>
                                <td><input type="radio" name="scCode" onclick="this.form.submit();" value="${listAround1.get(i).scCode}"></td>
                                </tr>
          						
                      	</c:forEach> 
                      	<tr><td colspan="10"><b>도착 스케줄</b></td></tr>
                        <c:forEach var="i" begin="0" end="${listAround2.size()-1}">
                               
          					   <tr>
                                <td><c:out value="${listAround2.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listAround2.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listAround2.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listAround2.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listAround2.get(i).scAcity }"></c:out> </td>
                                <td class="money"><c:out value="${listAround2.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listAround2.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround2.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround2.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listAround2.get(i).scCode }"></c:out> </td>
                              	<td><input type="radio" name="scCode" onclick="this.form.submit();" value="${listAround2.get(i).scCode}"></td>
                                 </tr>
          						
                      	</c:forEach> 
                       
                       </c:when>
                       <c:when test="${listAround3.size() >0 }">
                       
                      	<tr><td colspan="10"><b>출발 스케줄</b></td></tr>

                        <c:forEach var="i" begin="0" end="${listAround3.size()-1}">
                               
          					   <tr>
                                <td><c:out value="${listAround3.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listAround3.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listAround3.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listAround3.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listAround3.get(i).scAcity }"></c:out> </td>
                                <input type="hidden" name="scPrice" value="${listAround3.get(i).scPrice }"/>
                                <td class="money"><c:out value="${listAround3.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listAround3.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround3.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround3.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listAround3.get(i).scCode }"></c:out> </td>
                                <c:if test="${i%2 ==0}">
                            	<td rowspan="2"><input type="radio" name="scCode" onclick="this.form.submit();" value="${listAround3.get(i).scCode}"></td>
                                   </c:if>
                               </tr>
          						
                      	</c:forEach> 
                       
                       <tr><td colspan="10"><hr/></td></tr>
                        <tr><td colspan="10"><b>도착 스케줄</b></td></tr>
                        
                        <c:forEach var="i" begin="0" end="${listAround4.size()-1}">
                               
          					   <tr>
                                <td><c:out value="${listAround4.get(i).scDate }"></c:out> </td>
                                <td><c:out value="${listAround4.get(i).scDtime }"></c:out> </td>
                                <td><c:out value="${listAround4.get(i).scStime }"></c:out> </td>
                                <td><c:out value="${listAround4.get(i).scDcity }"></c:out> </td>
                                <td><c:out value="${listAround4.get(i).scAcity }"></c:out> </td>
                                <input type="hidden" name="scPrice" value="${listAround4.get(i).scPrice }"/>
                                <td class="money"><c:out value="${listAround4.get(i).scPrice }"></c:out> </td>
                                <td class="money"><c:out value="${listAround4.get(i).scMileage }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround4.get(i).scCapacity }"></c:out> </td>
                                <td class="acenter"><c:out value="${listAround4.get(i).scUnoccupied }"></c:out> </td>
                                <td style="display:none;"><c:out value="${listAround4.get(i).scCode }"></c:out> </td>
                                <c:if test="${i%2 ==0}">
                                <td rowspan="2"><input type="radio" name="scCode" onclick="this.form.submit();" value="${listAround4.get(i).scCode}"></td>
                                </c:if>
                               </tr>
          						
                      	</c:forEach> 
                       
                       </c:when>
                              
                       <c:otherwise>
                           <tr>
                                <td colspan="99">No data found</td> 
                           </tr> 
                       
                       </c:otherwise>
                   </c:choose>
	       	</form>
	        </tbody>
	    </table>
	    
	    <!-- paging -->
            
            <div>
            	<input type="button" id="toInputPsg" value="TO Passenger">
            </div>
        
        <!-- paging -->
        
    </form>
    
    
 <script type="text/javascript">
 
 
 
 function doSearchPage(url,no){
	 //console.log("url:"+ url);
	// console.log("no:"+ no);
     var frm=document.searchfrm;
     frm.work_div.value="do_retrieve";
      frm.page_num.value=no;
      frm.action=url;
      frm.submit();
  }
 
 $("#listTable>tbody").on("dblclick","tr",function(){
	console.log("event"+event); 
	var pTR=$(this);//tr
	var pTR_Children=pTR.children();//td
	var scCode=pTR_Children.eq(9).text();
	console.log("scCode"+scCode);
	
    var frm=document.searchfrm;
    frm.work_div.value="do_selectOne";
    frm.seq.value=scCode; 
    frm.action='/SistAirLine/scheduleDTO/schedule.do?scCode='+scCode;
    frm.submit();
     
	
	   });
    function doRetrieve(){
	   var frm=document.searchfrm;
       frm.work_div.value="do_retrieve";
        frm.page_num.value="1";
        frm.action='/DaoWeb/board/board.do';
        frm.submit();
        
    }
 
    $("#toInputPsg").on('click',function(e){
    	goInputPassenger();
	 });
    function goInputPassenger(){
 	   window.location.href='/SistAirLine/InputPassenger.jsp';
 }
 
    $("#search_word").keypress(function(e){
    		if(e.which==13){//ajax에서 13은 Enter
    		doRetrieve();
    	}
    });
</script>
</body>
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

</html>