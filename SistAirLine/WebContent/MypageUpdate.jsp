<%@page import="com.hr.cmn.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberVO"%>

   
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/custom.css">
<title>SIST AIRLINES</title>
</head>

<body>
<%
	request.setCharacterEncoding("utf-8");
	String uNum=request.getParameter("uNum");
	
		MemberVO inVO=(MemberVO)session.getAttribute("user");
		 String member = null;
		   //System.out.println("member"+member);
		   if(inVO != null){
		      member = inVO.getuID();
		      System.out.println("member:"+member);
		   }
		      
		      
	inVO.setuNum(inVO.getuNum());
	
	MemberDAO dao=new MemberDAO();
	MemberVO outVO=(MemberVO)dao.doSelectOne(inVO);

      
      

      String uId = outVO.getuID();
      String uPw = outVO.getuPW();
      String uKname = outVO.getuKname();
      String uEname = outVO.getuEname();
      String uBir = outVO.getuBir();
      String year= uBir.substring(0,4);
      String month= uBir.substring(4,6);
      String day= uBir.substring(6,8);
      
      String uPhone = outVO.getuPhone();
      String uPhone1= uPhone.substring(0,3);
      String uPhone2= uPhone.substring(3,7);
      String uPhone3= uPhone.substring(7,11);
      
      
      
      String uEmail = outVO.getuEmail();
      int uGender = outVO.getuGender();
      String uAddress = outVO.getuAddress();
      String uCountry = outVO.getuCountry();
      int uMileage = outVO.getuMileage();
      String uMaindepart = outVO.getuMaindepart();
      int authority = outVO.getAuthority();
      

      
      

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Travello template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/SistAirLine/css/bootstrap4/bootstrap.min.css">
<link href="/SistAirLine/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="/SistAirLine/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
<link rel="stylesheet" type="text/css" href="/SistAirLine/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
<link rel="stylesheet" type="text/css" href="/SistAirLine/plugins/OwlCarousel2-2.2.1/animate.css">
<link rel="stylesheet" type="text/css" href="/SistAirLine/css/main_styles.css">
<link rel="stylesheet" type="text/css" href="/SistAirLine/css/responsive.css">
<link rel="stylesheet" href="/SistAirLine/css/bootstrap.css">
</head>

<script type="text/javascript">
			
			
			function Cancel() {
				location.href = "/SistAirLine/MypageForm.jsp";
			}
		    
		    function dateSelect(docForm,selectIndex) {
		    	   watch = new Date(docForm.year.options[docForm.year.selectedIndex].text, docForm.month.options[docForm.month.selectedIndex].value,1);
		    	   hourDiffer = watch - 86400000;
		    	   calendar = new Date(hourDiffer);
	
		    	   var daysInMonth = calendar.getDate();
		    	      for (var i = 0; i < docForm.day.length; i++) {
		    	         docForm.day.options[0] = null;
		    	      }
		    	      for (var i = 0; i < daysInMonth; i++) {
		    	         docForm.day.options[i] = new Option(i+1);
		    	   }
		    	   document.form1.day.options[0].selected = true;
		    	}
		    
		    function Today(year,mon,day){
		        if(year == "null" && mon == "null" && day == "null"){       
		        today = new Date();
		        this_year=today.getFullYear();
		        this_month=today.getMonth();
		        this_month+=1;
		        if(this_month <10) this_month="0" + this_month;
		    
		        this_day=today.getDate();
		        if(this_day<10) this_day="0" + this_day;     
		    }else{  
		        var this_year = eval(year);
		        var this_month = eval(mon); 
		        var this_day = eval(day);
		     }
		    
		     montharray=new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 
		     maxdays = montharray[this_month-1]; 
		   //아래는 윤달을 구하는 것
		     if (this_month==2) { 
		         if ((this_year/4)!=parseInt(this_year/4)) maxdays=28; 
		         else maxdays=29; 
		     } 
		   
		   
		     document.writeln("<select name='year' size=1 onChange='dateSelect(this.form,this.form.month.selectedIndex);'>");
		     for(i=this_year-100;i<this_year+1;i++){//현재 년도에서 과거로 5년까지 미래로 5년까지를 표시함
		         if(i==this_year) document.writeln("<OPTION VALUE="+i+ " selected >" +i); 
		         else document.writeln("<OPTION VALUE="+i+ ">" +i); 
		     }    
			
		   document.writeln("</select>년");      
	
		 
		document.writeln("<select name='month' size=1 onChange='dateSelect(this.form,this.selectedIndex);'>");
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
		 
		 document.writeln("<select name='day' size=1>");
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
   
    text-align: center;
    background-color: #e2e2e2; }
   
   tr.even {
    background-color: #efefef;
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
	
	.centerdiv {
			align:center;
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
					<div class="background_image" style="background-image:url(/SistAirLine/img/home_slider.jpg)"></div>
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
					<div class="background_image" style="background-image:url(/SistAirLine/img/home_slider.jpg)"></div>
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
					<div class="background_image" style="background-image:url(/SistAirLine/img/home_slider.jpg)"></div>
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
						<div class="home_search_title">회원정보 수정</a></div>
						<div class="home_search_content" style="margin: auto;"  >
						
						
						<form method="post" action="MypageUpdateProcess.jsp">
	      						
						       <tr>
				               		<td>아이디:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				               		<td>
				               		<input type="text" class="search_input search_input_1" placeholder="아이디" name="userID" value=<%= uId %> maxlength="10" style="width:90%; border-bottom:1px solid #ffffff"  required="required" readonly="readonly" onkeydown="checkSpacebar(); inputIdChk();"></td>   
				               		
	           					</tr></br>
	           					<tr>
			                        <td>비밀번호 :&nbsp;</td>
			                        <td><input type="password" class="search_input search_input_1" placeholder="비밀번호" name="userPassword" value=<%= uPw %> maxlength="15" style="width:90%; border-bottom:1px solid #ffffff"  required="required" onkeydown="checkSpacebar();"></td>
			                    </tr></br>
			                    <tr>
			                        <td>한글 이름 : </td>
			                        <td><input type="text" class="search_input search_input_1" placeholder="한글 이름" name="userKname" value=<%= uKname %> maxlength="7" style="width:90%; border-bottom:1px solid #ffffff" required="required" onkeydown="checkSpacebar();"></td>
			                    </tr></br>
			                    <tr>
			                        <td>영어 이름 : </td>
			                        <td><input type="text" class="search_input search_input_1" placeholder="영어 이름" name="userEname" value=<%= uEname %> maxlength="40" style="width:90%; border-bottom:1px solid #ffffff"  required="required" onkeydown="checkSpacebar();"></td>
			                    </tr></br>
			                    <tr>
			                        <td>생년월일:&nbsp;&nbsp; </td>
			                        <td><input type="text" class="search_input search_input_1" placeholder="" name="userEname" value="<%=year %>년&nbsp;<%=month %>월&nbsp;<%=day %>일"  readonly="readonly" maxlength="40" style="width:90%; border-bottom:1px solid #ffffff"  required="required" onkeydown="checkSpacebar();"></td>
			                    </tr></br>
			                    
	                   			<tr>
			                       <td>
			                       <td >휴대폰번호&nbsp;</td>
			                       <select name="userphone1">
			                           <optgroup label="010">
			                           	<option value=<%=uPhone1 %>><%=uPhone1 %></option>
			                               <option value="010">010</option>
			                               <option value="011">011</option>
			                               <option value="012">012</option>                                
			                           </optgroup>
			                        </select>
			                        </td>
			                           <td><input type="text" placeholder=" " name="userphone2" value=<%=uPhone2 %> maxlength="4" style="width:180px;" required="required" onkeydown="checkSpacebar();"></td>
			                           <td><input type="text"  placeholder=" " name="userphone3" value=<%=uPhone3 %> maxlength="4" style="width:180px;" required="required" onkeydown="checkSpacebar();"></td>
			                     </tr></br>
			                     <tr>
			                     	<td><td>이메일 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>
                        			<td><input type="email" class="search_input search_input_1" placeholder="이메일" name="userEmail" value=<%= uEmail %> maxlength="23" style="width:90%; border-bottom:1px solid #ffffff" required="required" onkeydown="checkSpacebar();"></td>
			                     </tr></br>
			                     
			                     <div class="form-group" style="text-align: left; vertical-align: middle; padding-top:20px;">
			                        <div class="btn-group" >
			                        	<td>성별&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			                            <label >
			                           		<input type="radio" name="userGender"  value="1" <%if(uGender==1)out.print("checked='checked'"); %> >남자
			                            </label>
			                            <label>
			                                <input type="radio" name="userGender"  value="2" <%if(uGender==2)out.print("checked='checked'"); %> >여자
			                            </label>
			                        </div>
			                    </div>
			                    
			                     <tr>
			                     	<td>주소 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                        			<td><input type="text" class="search_input search_input_1" placeholder="주소" name="userAddress" value=<%= uAddress %> maxlength="100" style="width:90%; border-bottom:3px solid #ffffff"  required="required"></td>
			                     </tr></br>
			                     
			                     
			                     
			                      
			                        <td >회원국가&nbsp;&nbsp;&nbsp;</td>
			                        <td>
			                        <select name="userCountry" value=<%= uCountry %>>
			                            <optgroup label="회원국가">
			                            	 <option value=<%= uCountry %>><%= uCountry %></option>
			                                <option value="Korea">Korea</option>
			                                <option value="China">China</option>
			                                <option value="Japan">Japan</option>                              
			                                <option value="Vietnam">Vietnam</option>                              
			                                <option value="Guam">Guam</option>                              
			                                <option value="Philippines">Philippines</option>
			                                <option value="Singapore">Singapore</option> 
			                                <option value="Russia">Russia</option>                               
			                            </optgroup>
			                        </select>
			                        </td></br>
			                        
                        
                        
			                        <td>주요출발지</td>
			                        <select name="maindepart" value=<%= uMaindepart %>>
			                            <optgroup label="회원국가">
                                			<option value=<%= uMaindepart %>><%= uMaindepart %></option>			                            
			                                <option value="Korea">Korea</option>
			                                <option value="China">China</option>
			                                <option value="Japan">Japan</option>                              
			                                <option value="Vietnam">Vietnam</option>                              
			                                <option value="Guam">Guam</option>                              
			                                <option value="Philippines">Philippines</option>
			                                <option value="Singapore">Singapore</option> 
			                                <option value="Russia">Russia</option>                               
			                            </optgroup>
			                        </select></br>
						           
								<div class="centerdiv" style=" height:40px;  text-align:right; " >
									<input type="submit" class="home_search_button" value="수정하기"   style="width: 110px;">
									<input type="button"  class="home_search_button"   value="취소"  style="width: 110px;" onClick="Cancel()">
								</div>
						    </form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->

	<footer class="footer">
		<div class="parallax_background parallax-window" data-parallax="scroll" data-image-src="/SistAirLine/img/footer_1.jpg" data-speed="0.8"></div>
		<div class="container">
			<div class="row">
				<div class="col">
					<div class="newsletter">
						<div class="newsletter_title_container text-center">
							<div class="newsletter_title">Subscribe to our newsletter to get the latest trends & news</div>
							
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

<script src="/SistAirLine/js/jquery-3.2.1.min.js"></script>
<script src="/SistAirLine/css/bootstrap4/popper.js"></script>
<script src="/SistAirLine/css/bootstrap4/bootstrap.min.js"></script>
<script src="/SistAirLine/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="/SistAirLine/plugins/Isotope/isotope.pkgd.min.js"></script>
<script src="/SistAirLine/plugins/scrollTo/jquery.scrollTo.min.js"></script>
<script src="/SistAirLine/plugins/easing/easing.js"></script>
<script src="/SistAirLine/plugins/parallax-js-master/parallax.min.js"></script>
<script src="/SistAirLine/js/custom.js"></script>
<script src="/SistAirLine/js/bootstrap.js"></script>



</body>
</html>