<%@page import="member.MemberVO"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.CodeVO"%>
<%@page import="com.hr.cmn.SearchVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %> 
<%@page session="true"%>


<%
   MemberVO inVOo = (MemberVO)session.getAttribute("user");
   String member = null;
   //System.out.println("member"+member);
   if(inVOo != null){
      member = inVOo.getuID();
      System.out.println("member:"+member);
   }
   
%>
    
<%


	

     request.getAttribute("sclist");//목록
//req.setAttribute("paramVO", inVO);//param
//req.setAttribute("totalCnt", totalCnt);



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
   
    
    //code
    List<CodeVO> searchList = 
                      (List<CodeVO>)request.getAttribute("searchList");

    
    List<CodeVO> pageSizeList = 
            (List<CodeVO>)request.getAttribute("pageSizeList");
    
    
    List<ScheduleVO> sclist = (List<ScheduleVO>)request.getAttribute("sclist");
	for(ScheduleVO vo :sclist) {
	//	out.print(vo+"<br>");
	}
    
    //paging
    int maxNum     = 0;//총글수 
    int currPageNo = 1;//현재 페이지 
    int rowsPerPage= 10;// page_size 
    int bottomCount= 10;// 바닥 page_cnt
    
    String url = HR_PATH+"/scheduleDTO/schedule.do";//호출 URL 
    String scriptName = "doSearchPage";//JavaScript함수: doSearchPage(url,no)
    if(null !=inVO){
        maxNum     = (Integer)request.getAttribute("totalCnt");
        currPageNo = inVO.getPageNum();
        rowsPerPage= inVO.getPageSize();
        
        LOG.debug("=======================");
        LOG.debug("url="+url);
        LOG.debug("scriptName="+scriptName);
        LOG.debug("maxNum="+maxNum);
        LOG.debug("currPageNo="+currPageNo);
        LOG.debug("rowsPerPage="+rowsPerPage);
        LOG.debug("=======================");
    }
    //--paging
%>    

<!DOCTYPE html>
 
<html>
<head>
    <script  src="https://code.jquery.com/jquery-2.2.4.js"></script> 
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
     <script src="../js/bootstrap.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1">
    <link rel="stylesheet" href="../css/bootstrap.css">
<title>SIST AIRLINES</title>
<style>
.table-hover tbody tr:hover td {
    background: #337ab7;
    color:#fff;
}
</style>
<script type="text/javascript">
    
        var openWin;
    
        function openDCityForm()
        {
            // window.name = "부모창 이름"; 
            window.name = "administer_schedule.jsp";
            // window.open("open할 window", "자식창 이름", "팝업창 옵션");
            openWin = window.open("cityDFormOne.jsp",
                    "도시 선택", "width=570, height=350, resizable = no, scrollbars = yes");    
        }
        
        
     
</script>

</head>

<body>
<!-- Header menu  -->

   <nav class="navbar navbar-default">
      <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed"
            data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
            aria-expanded="false">
            <span class="icon-bar"></span> <span class="icon-bar"></span> <span
               class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="/SistAirLine/index.jsp">SIST AIRLINES</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
         <ul class="nav navbar-nav navbar-right">

			<% if(null != member){%>
                <%=StringUtil.loginNav() %>
                <% }else{%>
                <%=StringUtil.noLoginNav() %>
                   <%}%>
           
           

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
   <!-- Header menu  -->
   
   
   <div class="container">
      <div class="well">
        <div class="row">
            <div class="col-md-12" align="center">
                <h3>스케줄 조회</h3>
            </div>
        </div>
        <div class="row" style="margin-top:30px;">
            <div class="col-md-12" align="center">
                <form class="form-inline" name="searchFrm" id="searchFrm" action='<%=HR_PATH%>/scheduleDTO/schedule.do' method="get">
                  <input type="hidden" name="work_div" value="do_adminretrieve" id="work_div" />
                  <input type="hidden" name="page_num"   />  
                  <input type="hidden" name="scCode" 	/>
                 
                  <div class="form-group">
                    <label>출발 도시</label>
                    <input type="text" class="form-control" id="dCity" name="dCity" placeholder="도시 " maxlength="8" value="<%=dCity%>">
                    
                  </div>
                  <div class="form-group" style="margin-left:10px;">
                    <label >출발 일자</label>
                    <input type="text" class="form-control" name="outDate" id="outDate" placeholder="출발 일자" maxlength="8" value="<%=outDate%>">
                  </div>
                  <div class="col-md-12" style="margin-top:30px;">
                     <button type="submit" class="btn btn-primary btn-lg" style="width:467px;" id="to_list_btn">스케줄 조회</button>
                  </div>
                </form>
            </div>
        </div>
     </div>
      
   </div>
   <div class="col-lg-4"></div>
   
 
   <!-- 검색 폼 -->
   
   <div class="row">
        <div class="col-md-12" align="center">
            <h2></h2>
        </div>
   </div>
   <div class="col-md-12" align="center">
    <div class="col-md-10 col-md-offset-1">
        <table class="table table-hover"   border="1" id="listTable" >
            <thead>
                <tr style="font-size:15px;">
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
        
        <!-- paging -->
        <div style="margin-bottom:50px;">
            <%=StringUtil.renderPaging(maxNum, currPageNo, rowsPerPage, bottomCount, url, scriptName)%>
        </div>
        <!--// paging -->
    </div>
   </div>
  
    <script type="text/javascript">
        function doSearchPage(url,no){
            console.log("url:"+url);
            console.log("no:"+no);
            var frm = document.searchFrm;
            frm.work_div.value = "do_adminretrieve";
            frm.page_num.value = no;
            frm.action = url;
            frm.submit();           
            
        }
        
        //listTable자식:dbclick(double click)
        $("#listTable>tbody").on("dblclick","tr",function(event){
            //console.log("event:"+event);
            //console.log("#listTable>tbody>tr");
            var pTR = $(this);//tr
            var pTR_Children = pTR.children();
            var scCode= pTR_Children.eq(10).text();
            console.log("sccode:"+scCode);
			alert(scCode+" 편을 조회합니다.");
            
            
            var frm = document.searchFrm;
            frm.work_div.value = "do_selectOne";
            frm.scCode.value = scCode;
			frm.action='/SistAirLine/scheduleDTO/schedule.do?scCode='+scCode;
			frm.submit();
			
            
        });
    
    
        function doRetrieve(){
            //alert('doRetrieve');
            var frm = document.searchFrm;
            frm.work_div.value = "do_adminretrieve";
            frm.page_num.value = "1";
            frm.action='/SistAirLine/scheduleDTO/schedule.do';
            frm.submit();
        }
    
        function moveToSave(){
            //alert('moveToSave');
            //http://localhost:8080/DaoWEB/board/board.do?work_div=move_to_save&page_num=
            if(false==confirm('등록 화면으로 이동 하시겠습니까?'))return;
            var frm = document.searchFrm;
            frm.work_div.value = "move_to_save";
            frm.action='/SistAirLine/scheduleDTO/schedule.do';
            frm.submit();
        }
        
        $("#search_word").keypress(function(e){
            //console.log('#search_word');
            //alert('#search_word');
            //Enter Keycode 13
            if(e.which ==13){
                doRetrieve();
            }
            
        });
        
        //위에서 아래로 해석(인터프리터 방식)
        
       /* $("#to_list_btn").on('click',function(){
			console.log("#to_list_btn");
			//goRetrieve();
			
			var frm= document.searchFrm;
			frm.action = '/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve';
			frm.submit();
		});*/

        $("#to_list_btn").on('click',function(){
            //console.log("#insert_btn");

            var dCity = $("#dCity").val();
            var outDate = $("#outDate").val();
            
            
            if( "" != dCity || "" != outDate  ){
                //param 있을때
                var frm = document.searchFrm;
                frm.work_div.value = "do_adminretrieve";
                frm.action = "/SistAirLine/scheduleDTO/schedule.do";
                frm.submit();
                //  goRetrieve();
                ///return;
                
            }else{
            
            //동기통신(서블릿으로 보냄)
                    var frm = document.searchFrm;
                    frm.work_div.value = "do_adminretrieve";
                    frm.action = "/SistAirLine/scheduleDTO/schedule.do"; //서블릿으로 보낸다.
                    frm.submit();
        	}
            
            
        }); 

        //목록호출
        function goRetrieve(){
             window.location.href='/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve';
        }


        
        
    </script>
   
   
   
</body>
</html>