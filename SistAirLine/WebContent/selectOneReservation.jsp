<%@page import="Reservation.ReservationVO"%>
<%@page import="member.MemberVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  List<ReservationVO> list=(List<ReservationVO>)request.getAttribute("list");

%>
<!DOCTYPE html>

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
<%
   MemberVO inVO = (MemberVO)session.getAttribute("user");
   String member = null;
   //System.out.println("member"+member);
   if(inVO != null){
      member = inVO.getuID();
      System.out.println("member:"+member);
   }
   
%>
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
						<div class="home_search_title"><a href="schedule.jsp">예약내역 조회</a></div>
						<div class="home_search_content" >
						<form action="/SistAirLine/reservationtest/reservationtest.do" name="reservationFrm" id="reservationFrm" method="post">
						<input type="hidden" id="work_div" name="work_div"/>
                        <input type="hidden" id="resNum" name="resNum"/>
                        <input type="hidden" id="resPassport" name="resPassport"/>
                        <input type="hidden" id="resState" name="resState"/>
                        
					       <table id="div_sche" class="center">
                             <thead>
                                 <th>예약번호</th>
                                 <th>동승자</th>
                                 <th>여권번호</th>
                                 <th>왕복여부</th>
                                 <th>승객구분</th>
                                 <th>성별</th>
                                 <th>예약상태</th>
                                 <th>예약변경</th>
                                 <th></th>
                             </thead>
                                <c:forEach var="i" begin="0" end="${list.size()-1}"> 
	                            	<tr>
								        <td><c:out value="${list.get(i).resNum }"></c:out> </td>
	                                    <td><c:out value="${list.get(i).resEname }"></c:out> </td>
	                                    <td><c:out value="${list.get(i).resPassport}"></c:out></td>
                                       <td class="acenter">
	                                    	<c:if test="${list.get(i).resWay  eq 1 }">
	                                    		<c:out value="편도"></c:out>
	                                    	</c:if>
	                                    	<c:if test="${list.get(i).resWay  eq 2 }">
	                                    		<c:out value="왕복"></c:out>
	                                    	</c:if>
	                                    	<c:if test="${list.get(i).resWay  eq 0 }">
	                                    		<c:out value="고쳐야할것"></c:out>
	                                    	</c:if>
                                   	   </td>
                                   
                                     <td class="money">
                                    	<c:if test="${list.get(i).resPtype eq 1 }">
                                    		<c:out value="성인"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resPtype eq 0.8 }">
                                    		<c:out value="소아"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resPtype eq 0.5 }">
                                    		<c:out value="유아"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resPtype eq 0 }">
                                    		<c:out value="소아"></c:out>
                                    	</c:if>
                                     </td>
                                     <td class="acenter">
                                     <c:if test="${list.get(i).resGender eq 1 }">
                                      	남자
                                     </c:if>
                                     <c:if test="${list.get(i).resGender eq 2 }">
                                      	여자
                                     </c:if>
                                     
                                     </td>
	                                     
	                                <td class="acenter">
                                    	<c:if test="${list.get(i).resState eq 1 }">
                                    		<c:out value="예약대기"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resState eq 2 }">
                                    		<c:out value="취소대기"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resState eq 3 }">
                                    		<c:out value="도착완료"></c:out>
                                    	</c:if>
                                    	<c:if test="${list.get(i).resState eq 0 }">
                                    		<c:out value="고쳐야할것"></c:out>
                                    	</c:if>
                                    </td>
                                    <c:if test="${list.get(i).resState eq 1 }">
                                        <td><input type="button" id="changeStateBtn" name="changeStateBtn" value="상태변경신청" onclick="changeState('${list.get(i).resNum}','${list.get(i).resPassport}','${list.get(i).resState}')"></td>
                                    </c:if>
                                    <c:if test="${list.get(i).resState eq 2 }">
                                        <td><input type="button" id="changeStateBtn" name="changeStateBtn" value="상태변경신청" onclick="changeState('${list.get(i).resNum}','${list.get(i).resPassport}','${list.get(i).resState}')"></td>
                                    </c:if>
                                    <c:if test="${list.get(i).resState eq 3 }">
                                        <td>도착완료</td>
                                    </c:if>
                                 </tr>
                                </c:forEach>
                         </table>
					</form>
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
	
      function changeState(resNum, resPassport, resState)
       	{
    	  var frm = document.reservationFrm;
          
          console.log("resNum:"+resNum);
          alert("예약 상태를 변경합니다.");
          frm.work_div.value = "do_changeReservation";
          frm.resNum.value = resNum;
          frm.resPassport.value = resPassport;
          if(resState==1){
              frm.resState.value = 2;
          }else if(resState==2){
        	  frm.resState.value = 1;
          }else{
         	  frm.resState.value = 3;
               	  
          }
          frm.action = '/SistAirLine/reservationtest/reservationtest.do';
          frm.submit();  	
       	
          $.ajax({
				 type:"POST",
				 url:"/SistAirLine/reservationtest/reservationtest.do",
				 dataType:"html", 
				 data:{"work_div":"do_changeReservation",
				       "resNum":resNum,
				       "resPassport":resPassport,
				       "resState": resState
				 },
				 success:function(data){ //성공
					  var jsonObj = JSON.parse(data);
					  if(null != jsonObj && jsonObj.msgId=="1"){
				 		  alert(jsonObj.msgContents);
				 		 goSelectOneRes(resNum);
				 	  }else{
				 		 alert(jsonObj.msgId+"|"+jsonObj.msgContents);
				 	  }
				 },
				 error:function(xhr,status,error){
				  alert("error:"+error);
				 },
				 complete:function(data){
				 
				 }   
			
			});//--ajax
  		function goSelectOneRes(resNum){
			window.location.href='/SistAirLine/reservationtest/reservationtest.do?work_div=do_member_retrieve&resNum='+resNum;
		}
	
          
       	}
       	
	
	
	
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
	});
	
	
	
	
</script>

</body>
</html>