<%@page import="member.MemberVO"%>
<%@page import="com.hr.cmn.CodeVO"%>
<%@page import="com.hr.cmn.SearchVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="Reservation.ResVO"%>
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
     request.getAttribute("list");//목록
//req.setAttribute("paramVO", inVO);//param
//req.setAttribute("totalCnt", totalCnt);
    //param
    String pageNum    ="1";//페이지 넘버
    String res_state  = "";//예약상태 (int형)
    String res_num ="";// 예약번호 (String)
    String pageSize   ="10";//페이지 사이즈
    
    ResVO resVO = (ResVO) request.getAttribute("paramVO");
    
    SearchVO inVO = new SearchVO();
    
    int res_state01 =resVO.getResState();
        LOG.debug("===========");
        LOG.debug("=res_state11111111="+res_state01); //3333333을 다시 받아온다.
        LOG.debug("===========");
   
   String res_num01 =resVO.getResNum();
        LOG.debug("===========");
        LOG.debug("=res_num11111111="+res_num01); //res_num 을 다시 받아온다.
        LOG.debug("===========");
    
    
    
    
    inVO.setPageNum(resVO.getPageNum());
    inVO.setPageSize(resVO.getPageSize());
    
    if(null !=inVO){
        LOG.debug("===========");
        LOG.debug("=inVO="+inVO);
        LOG.debug("===========");
        pageNum    = String.valueOf(inVO.getPageNum());
        pageSize   = String.valueOf(inVO.getPageSize());
       
        res_state  = String.valueOf(resVO.getResState());
        res_num = resVO.getResNum();
    } 
    
    //code
    List<CodeVO> searchList = 
                      (List<CodeVO>)request.getAttribute("searchList");

    
    List<CodeVO> pageSizeList = 
            (List<CodeVO>)request.getAttribute("pageSizeList");
    
    
    List<ResVO> list = (List<ResVO>)request.getAttribute("list");
    
    //paging
    int maxNum     = 0;//총글수 
    int currPageNo = 1;//현재 페이지 
    int rowsPerPage= 10;// page_size 
    int bottomCount= 5;// 바닥 page_cnt
    
    String url = HR_PATH+"/Reservation/res.do";//호출 URL 
        String scriptName = "doSearchPage";//JavaScript함수: doSearchPage(url,no) // do_retriev: 조건 목록조회
        String scriptName01 = "doSearchPage01";//JavaScript함수: doSearchPage(url,no) // do_retrieve: 조건 없는 목록조회
    
    
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
                <h3>예약 조회</h3>
            </div>
        </div>
        <div class="row" style="margin-top:30px;">
            <div class="col-md-12" align="center">
                <form class="form-inline" name="searchFrm" id="searchFrm" action='<%=HR_PATH%>/Reservation/res.do' method="get">
                  <input type="hidden" name="work_div" value="do_retriev" id="work_div" />
                  <input type="hidden" name="page_num"   />  
                  <input type="hidden" name="resNum"/>
                   <input type="hidden" name="resPassport"/>
                  <div class="form-group">
                  <label for="res_state">예약 상태</label>
                   <select name="res_state" id="res_state" class="form-control" value="<%= res_state %>"  > 
                       <option value=''>전체</option>
                        <option value='1'>예약완료</option>
                        <option value='2'>예약취소</option>
                        <option value='3'>도착완료</option>
                    </select>
                   </div>
                  <div class="form-group" style="margin-left:10px;">
                    <label for="res_num">예약 일자</label>
                    <input type="text" class="form-control" name="res_num" id="res_num" placeholder="예약 일자" maxlength="8" >
                  </div>
                  <div class="col-md-12" style="margin-top:30px;">
                     <button type="submit" class="btn btn-primary btn-lg" style="width:467px;" id="to_list_btn">예약 조회</button>
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
        </div>
        <br>
        <br>
   </div>
   <div class="col-md-12" align="center">
    <div class="col-md-10 col-md-offset-1">
        <table class="table table-hover"   border="1" id="listTable" >
            <thead>
                <tr style="font-size:15px;">
                    <th>예약번호</th>
                    <th>여권번호</th>
                    <th>예약자이름</th>
                    <th>생년월일</th>
                    <th>전화번호</th>
                    <th>이메일</th>
                    <th>예약자구분</th>
                    <th>성별</th>
                    <th>예약상태</th>
                    <th>예약지불구분</th>
                    <th>마일리지</th>
                    <th>좌석코드</th>
                    <th>회원번호</th>
                </tr>
            </thead>
            <tbody>
                <%
                    if(null !=list && list.size()>0){
                        for(ResVO vo:list){
                %>      
                        <tr>
                            <td><%=vo.getResNum() %></td>
                            <td><%=vo.getResPassport()%></td>
                            <td><%=vo.getResEname() %></td>
                            <td><%=vo.getResBir() %></td>
                            <td><%=vo.getResphone() %></td>
                            <td><%=vo.getResEmail() %></td>
                            <td><%=vo.getResPtype() %></td>
                            <td><%=vo.getResGender()%></td>
                            <td><%=vo.getResState() %></td>
                            <td><%=vo.getResPay() %></td>
                            <td><%=vo.getResMileage()%></td>
                            <td><%=vo.getResScode() %></td>
                            <td><%=vo.getuNum() %></td>
                        </tr>
                <%
                        }//for
                    }else{  
                %>
                        <tr>
                            <td colspan="13">조회 결과가 없습니다.</td>
                        </tr>
                <%
                    }
                %>
    
            </tbody>
        </table>
        
        <!-- paging 조건검색할때 bar -->
        <div   >
               <%=StringUtil.renderPaging(maxNum, currPageNo, rowsPerPage, bottomCount, url, scriptName)%>
        </div>
        <div style="margin-bottom:1px;    display:none;">
               <%=StringUtil.renderPaging(maxNum, currPageNo, rowsPerPage, bottomCount, url, scriptName01)%>
        </div>
        <!--// paging -->
    </div>
   </div>
        
    
    <script type="text/javascript">
        function doSearchPage(url,no){
            console.log("url:"+url);
            console.log("no:"+no);
            var frm = document.searchFrm;
            frm.work_div.value = "do_retriev";
            frm.page_num.value = no;
            <%-- frm.res_num.value = <%= resVO.getResNum() %> --%>
        frm.res_state.value = <%= resVO.getResState() %> 
            frm.action = url;   
            frm.submit();           
            
        }
        
        function doSearchPage01(url,no){
            console.log("url:"+url);
            console.log("no:"+no);
            var frm = document.searchFrm;
            frm.work_div.value = "do_retrieve";
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
            var resNum = pTR_Children.eq(0).text();
            var resPassport = pTR_Children.eq(1).text();
            console.log("resNum:"+resNum);
            
            alert("resNum:"+resNum+"\n"+"resPassport"+resPassport);
            
            var frm = document.searchFrm;
            frm.work_div.value = "do_selectOne";
            frm.resNum.value = resNum;   // form에  input type hidden으로  seq를 만들어준다.
            frm.resPassport.value = resPassport; 
            frm.action = '/SistAirLine/Reservation/res.do';
            frm.submit();           
            
        });
    
    

    
        function moveToSave(){
            //alert('moveToSave');
            //http://localhost:8080/DaoWEB/board/board.do?work_div=move_to_save&page_num=
            if(false==confirm('등록 화면으로 이동 하시겠습니까?'))return;
            var frm = document.searchFrm;
            frm.work_div.value = "move_to_save";
            frm.action = '/SistAirLine/Reservation/res.do';
            frm.submit();
        };
        
        //위에서 아래로 해석(인터프리터 방식)

        $("#to_list_btn").on('click',function(){
            //console.log("#insert_btn");

            var res_state = $("#res_state").val();
            var res_num = $("#res_num").val();
            
            
            if( "" != res_state || "" != res_num  ){
                //param 있을때
                var frm = document.searchFrm;
                frm.work_div.value = "do_retriev";
                frm.action = "/SistAirLine/Reservation/res.do";
                frm.submit();
                //  goRetrieve();
                ///return;
                
            }else{
            
            //동기통신(서블릿으로 보냄)
                    var frm = document.searchFrm;
                    frm.work_div.value = "do_retrieve";
                    frm.action = "/SistAirLine/Reservation/res.do"; //서블릿으로 보낸다.
                    frm.submit();
        }
            
            
        });//--#insert_btn  

        //목록호출
        function goRetrieve(){
             window.location.href='/SistAirLine/Reservation/res.do?work_div=do_retrieve';
        }


        
        
    </script>
   
   
   
</body>
</html>