<%@page import="member.MemberVO"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>항공편 조회</title>
<meta charset="UTF-8">
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
   tr.even {
    background-color: #efefef;}
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
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>
$(document).ready(function(){
		$("#toLogin").on("click",function(){
			
			console.log("do_login");
			//id입력 체크
			var uId = $("#uId").val();
			if(null ==uId || uId.trim().length ==0 ){
				$("#uId").focus();
				alert("ID를 입력 하세요.");
				return;
			}
			//비번 체크
			var uPw = $("#uPw").val();
			if(null ==uPw || uPw.trim().length ==0 ){
				$("#uPw").focus();
				alert("비번를 입력 하세요.");
				return;
			}    		
			
			console.log("uId:"+uId);
			console.log("uPw:"+uPw);
			//ajax
		    $.ajax({
		       type:"POST",
		       url:"/SistAirLine/member/member.do",
		       dataType:"html",
		       data:{
		       "work_div":"do_login",
		       "uId":uId,
		       "uPw":uPw
		    }, 
		    success: function(data){
		       var jData = JSON.parse(data);
		       if(null != jData && jData.msgId=="40"){//일반회원
		          alert(jData.msgContents);
		          window.location.href="/SistAirLine/index.jsp";
		       }else if(null != jData && jData.msgId=="30"){//관리자
			          alert(jData.msgContents);
			          window.location.href="/SistAirLine/administer.jsp";
			   }else{
		          alert(jData.msgId+"|"+jData.msgContents);
		       }
		    },
		    complete:function(data){
		     
		    },
		    error:function(xhr,status,error){
		        alert("error:"+error);
		    }
		   }); //--ajax
		  
			
		}); //loginBtn

});//--main
		
	
		
		

	
</script>
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
		</div>
	</header>

	<!-- Home -->

	<div class="home">
		
		<!-- Home Slider -->
		<div class="home_slider_container">
			<div class="owl-carousel owl-theme home_slider">
				
				<!-- Slide -->
				<div class="owl-item">
					<div class="background_image" style="background-image:url(img/loginBack.jpg)"></div>
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
					<div class="background_image" style="background-image:url(img/loginBack.jpg)"></div>
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
					<div class="background_image" style="background-image:url(img/loginBack.jpg)"></div>
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
						<div class="home_search_title"><a href="schedule.jsp">로그인</a></div>
						<div class="home_search_content" >
					  	<div style="width:80%;margin:auto;padding:20px;">
					  		<form name="loginFrm" action="/SistAirLine/member/member.do" method="post">
						    <input type="hidden" name="work_div" id="work_div" value="do_login">
						        <div class="form-group" style="padding:10px;">
						            <label >아이디</label>
						            <input name="uId" id="uId" type="text" class="form-control" />
						        </div>
						        <div class="form-group" style="padding:10px;">
						            <label>비밀번호</label>
						            <input name="uPw" id="uPw" type="password" class="form-control" />
						        </div>
						    </form>
						    <div style="width:100%;text-align:center;padding:20px;">
						    	<button class="btn btn-primary" name="toLogin" id="toLogin" style="width:37%">로그인</button>
						    	<table   style="margin:auto;text-align:center;height:50;margin-top: 1%;" class="mo">
								<tr>
									<td><a style="text-decoration: none;" href="./2.join.html">회원가입 </td>
					                <td><a style="text-decoration: none;" href="SearchIDForm.jsp">아이디 찾기 | </td>
									<td><a style="text-decoration: none;" href="SearchpwForm.jsp">비밀번호 찾기</td>
								</tr>
							</table>
						    </div>
					  	</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="img/loginBack.jpg" data-speed="0.8"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter">
						<div class="newsletter_title_container text-center">
							<div class="newsletter_title"></div>
							<div class="newsletter_subtitle"></div>
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
								<div class="footer_contact_icon"><img src="" alt=""></div>
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