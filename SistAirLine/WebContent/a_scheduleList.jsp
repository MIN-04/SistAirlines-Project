<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="java.util.List"%>

<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>

   
<!DOCTYPE html>
<%
   request.setCharacterEncoding("utf-8");
   
	//param
	String pageNum="1";//페이지넘버
	String dCity="";//검색구분
	String outDate="";//검색어
	String pageSize="10";//페이지사이즈
	
 
 
  
   
   ScheduleVO inVO = (ScheduleVO)request.getAttribute("paramVO");
   if(null != inVO){
		LOG.debug("=============");
		LOG.debug("=inVO="+inVO);
		LOG.debug("=============");
		pageNum = String.valueOf(inVO.getPageNum());
		dCity = inVO.getScDcity();
		outDate= inVO.getScDate();
		pageSize = String.valueOf(inVO.getPageSize());
  
		//inVO.setPageNum(1);
		//inVO.setPageSize(10);
		}
   //data.setNum(10); dao switch문으로바꾸면 사용하기
   
   // DAO의 search 메서드 호출 / DTO 객체(data) 전달
//   ScheduleDAO dao =new ScheduleDAO();
//   List<?> list=dao.adminRetrive(inVO);
   
   List<ScheduleVO> sclist = (List<ScheduleVO>)request.getAttribute("sclist");
	for(ScheduleVO vo :sclist) {
	//	out.print(vo+"<br>");
	}
   
 	//paging
 	int maxNum =	0; //총글수
 	int currPageNo    =1;//현재페이지
 	int rowsPerPage   =10;//page_size
 	int bottomCount   =10;// 바닥 page_cnt
 	
 	
 	String url        =HR_PATH + "/scheduleDTO/schedule.do";//호출 URL
	String scriptName ="doSearchPage"; //JavaScript 함수: doSearchPage(url,no)
	if(null != inVO){
		maxNum = (Integer)request.getAttribute("totalCnt");
		currPageNo = inVO.getPageNum();
		rowsPerPage = inVO.getPageSize();
		//bottomCount = String.valueOf(inVO.getPageSize());
		LOG.debug("======================");
		LOG.debug("url="+url);
		LOG.debug("scriptName="+scriptName);
		LOG.debug("maxNum="+maxNum);
		LOG.debug("currPageNo="+currPageNo);
		LOG.debug("rowsPerPage="+rowsPerPage);
		LOG.debug("======================");
	}
   
 
   %> 
   
<script>
   function openChild(scCode, pInput)
   {
      let openWin;
      console.log('실행');
      window.name="schedule_list";
      openWin=window.open("selectSeats.jsp?scCode="+scCode+"&pInput="+pInput,"selectSeats","width=570, height=350");
   }
   
</script> 

<html>
<head>

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
    width: 100%; text-align: center;}
   th, td {
    padding: 7px 10px 10px 10px; text-align: center; height:40px; width:110px;}
   th {
    text-transform: uppercase;
    letter-spacing: 0.1em;
    font-size: 100%;
    border-bottom: 2px solid #111111;
    border-top: 1px solid #999;
    height:40px;
    text-align: center;}
   tr.even {
    background-color: #efefef;}
    
    .centered { display: table; margin-left: auto; margin-right: auto; } 
   
</style>


<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/SistAirLine/css/bootstrap.min.css">
<link rel="stylesheet" href="/SistAirLine/css/custom.css">
<title>JSP 게시판 웹사이트</title>
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
         <a class="navbar-brand" href="administer.jsp">SIST AIRLINES</a>
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
               aria-expanded="false">예약관리<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="login.jsp">일반 예매</a></li>
               </ul></li>
            <li class="dropdown"><a href="#" class="dropdown-toggle"
               data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">스케줄관리<span class="caret"></span></a>
               <ul class="dropdown-menu">
                  <li><a href="join.jsp">회원 혜택</a></li>
                  <li><a href="join.jsp">마일리지 적립</a></li>
               </ul></li>
            <li class="dropdown"><a href="#" class="dropdown-toggle"
               data-toggle="dropdown" role="button" aria-haspopup="true"
               aria-expanded="false">직원관리<span class="caret"></span></a>
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
      
            		<h4 align="center" style="padding-top: 70px;" >항공스케줄 조회</h4></br></br></br></br>
                	<form action="/SistAirLine/scheduleDTO/schedule.do" name="searchFrm" id="searchFrm" method="get">
							<input type="hidden" name="work_div"	/>
							<input type="hidden" name="page_num"	/>
							<input type="hidden" name="scCode" 	/>
							<input type="hidden" name="dCity" value="<%=dCity%>"	/>
							<input type="hidden" name="outDate"  value="<%=outDate%>" />
						
						<div class="centered">
						      <table id="listTable" border="1" style="text-align: center;">
						      <thead>
						      <tr>
						         <th style="text-align: center;">출발날짜</<th>
						         <th style="text-align: center;">출발시간</th>
						         <th style="text-align: center;">소요시간</th>
						         <th style="text-align: center;">출발도시</th>
						         <th style="text-align: center;">도착도시</th>
						         <th style="text-align: center;">가격</th>
						         <th style="text-align: center;">마일리지</th>
						         <th style="text-align: center;">비행기이름</th>
						         <th style="text-align: center;">수용좌석</th>
						         <th style="text-align: center;">UNOCCUPIED</th>
						         <th style="text-align: center;">sccode</th>
						         <th style="text-align: center;">scstate</th>
						      </tr>
						      </thead>
						      <tbody>
									<c:choose>
										<c:when test="${sclist.size()>0 }">
											<c:forEach var="vo" items="${sclist }">
												<tr>
												<td><c:out value="${vo.scDate }"></c:out></td>
												<td><c:out value="${vo.scDtime }"></c:out></td>
												<td><c:out value="${vo.scStime }"></c:out></td>
												<td><c:out value="${vo.scDcity }"></c:out></td>
												<td><c:out value="${vo.scAcity }"></c:out></td>
												<td><c:out value="${vo.scPrice }"></c:out></td>
												<td><c:out value="${vo.scMileage }"></c:out></td>
												<td><c:out value="${vo.scAname }"></c:out></td>
												<td><c:out value="${vo.scCapacity }"></c:out></td>
												<td><c:out value="${vo.scUnoccupied }"></c:out></td>
												<td><c:out value="${vo.scCode }"></c:out></td>
												<td><c:out value="${vo.scState }"></c:out></td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan="99">no data found</td>
											</tr>
											
										</c:otherwise>
									</c:choose>
								</tbody>
						      </table>
						      </div>
						      	<!-- paging -->
								<div>
									<%=StringUtil.renderPaging(maxNum, currPageNo, rowsPerPage, bottomCount, url, scriptName) %>
								</div>
						</form>
    </div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="/SistAirLine/js/bootstrap.js"></script>

<script type="text/javascript">
	
		function doSearchPage(url,no){
			//console.log("url:"+url);
			//console.log("no:"+no);
			
			var frm = document.searchFrm;
			frm.work_div.value="do_adminretrieve";
			frm.page_num.value=no; //param pageNum을 받는다.

			frm.action=url;
			frm.submit();
			
		}
	
		//listTable의 자식:doubleClick=dblclick
		$("#listTable>tbody").on("click","tr",function(event){
			//console.log('#listTable>tbody>tr');
			//alert("event:"+event);
			var pTR = $(this);//tr
			var pTR_Children = pTR.children();
			var scCode= pTR_Children.eq(10).text();
			console.log("sccode:"+scCode);
			alert(scCode+" 편을 조회합니다.");
			
			var frm = document.searchFrm;
			frm.work_div.value="do_selectOne";
			frm.scCode.value = scCode;
			frm.action='/SistAirLine/scheduleDTO/schedule.do?scCode='+scCode;
			frm.submit();
			
			
		});
		
		function doRetrieve(){
			//alert('doRetrieve');
			var frm = document.searchFrm;
			frm.work_div.value="do_adminretrieve";
			frm.page_num.value="1";
			frm.action='/SistAirLine/scheduleDTO/schedule.do';
			frm.submit();
		}
		
		
		
		$("#search_word").keypress(function(e){
			//console.log('#search_word')
			//alert('#search_word')
			//Enter Key 13번
			if(e.which ==13){
				doRetrieve();
			}
		});
		
		$("#to_list_btn").on('click',function(){
			console.log("#to_list_btn");
			//goRetrieve();
			
			var frm= document.searchFrm;
			frm.action = '/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve';
			frm.submit();
		});
		
		//목록호출
		function goRetrieve(){
			 window.location.href='/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve';
		}
		
		
		$(document).ready(function(){
				
				//console.log("document ready");
		
			
			});
		
		
		</script>

</body>
</html>







