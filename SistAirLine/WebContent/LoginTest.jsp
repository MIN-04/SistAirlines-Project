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
<script>

	$(document).ready(function(){
		
		$("#toLogin").on('click',function(){
			
			var uId=$('#uId').val();
			if(uId==null || uId.trim().length==0){
			   alert('아이디를 입력하세요');
			   $('#uId').focus();
			   return;
			}
			var uPw=$('#uPw').val();
			if(uPw==null || uPw.trim().length==0){
			   alert('비밀번호를 입력하세요');
			   $('#uPw').focus();
			   return;
			}
			
			  if(false==confirm(uId+"로 \n 로그인하시겠습니까?"))return;
		       
		       var frm=document.loginFrm;
				frm.work_div.value="do_Login";
				frm.work_div.action="/SistAirLine/User/user.do";
				frm.submit();
			
			
		});
		
		
		
	});
	
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
						<div class="home_search_title"><a href="schedule.jsp">This is Login</a></div>
						<div class="home_search_content" >
						
					<h2>Login</h2>
					
					  <form name="loginFrm" action="/SistAirLine/User/user.do" method="post">
						    <input type="hidden" name="work_div" id="work_div" value="do_Login">
						        <div class="form-group">
						            <label>아이디</label>
						            <input name="uId" id="uId" type="text" class="form-control" />
						        </div>
						        <div class="form-group">
						            <label>비밀번호</label>
						            <input name="uPw" id="uPw" type="password" class="form-control" />
						        </div>
						        <button class="btn btn-primary">로그인</button>
						        <input type="button" name="toLogin" id="toLogin" value="로그인"/>
						        <table align="center" height="50" style="margin-top: 1%" class="mo">
									<tr>
										<td><a style="text-decoration: none;" href="./2.join.html">회원가입 </td>
						                <td><a style="text-decoration: none;" href="naverLogin.jsp">네이버로 로그인하기 </td>
						                <td><a style="text-decoration: none;" href="SearchIDForm.jsp">아이디 찾기 | </td>
										<td><a style="text-decoration: none;" href="SearchpwForm.jsp">비밀번호 찾기</td>
									</tr>
						
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