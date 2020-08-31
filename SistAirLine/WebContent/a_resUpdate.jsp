<%@page import="org.apache.log4j.Logger"%>
<%@page import="scheduleDTO.ScheduleVO"%>
<%@page import="com.hr.cmn.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/cmn/common.jsp" %>


<!DOCTYPE html>
<html lang="ko">
   

 
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>항공편 조회</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="description" content="Travello template project">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>

<!-- 부트스트랩 -->
<link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
      .form-group { font-size: 16px; }
      .title { font-size: 32px; }
      
    </style>
</head>




<body>
    <!-- div container -->
    <div class="container">  
         <!-- div title --> 
        <div class="page-header">
           <h2 style="text-align: center;" >항공예약 수정</h2>
        </div>
        <div style="width:100%;align:center;padding:10px;">
            <div class="row text-right" style="width:100%;">
                <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label"></label>
                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">         
                   <input  type="button"  class="btn btn-primary" value="목록" id="to_list_btn" />
                   <button type="submit"  class="btn btn-primary" id="update_btn">수정</button>
                </div>
            </div>
			
		 <form style="padding:10px;"  name="insert_frm" id="insert_frm" class="form-horizontal" action='<%=HR_PATH%>/Reservation/res.do'  method="post">
			    <input type="hidden" name="seq"  id="seq" value="${vo.resNum}" />
						<input type="hidden" name="res_num" id="res_num" value="${vo.resNum}"/>
						<input type="hidden"  name="res_passport" id="res_passport" value="${vo.resPassport}"/>
						
					    <!-- 영문이름 -->
			            <div class="form-group">
			                <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">영문이름</label>
			                <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
			                    <input type="text" placeholder="영문이름" class="form-control" value="${vo.resEname}" name="res_ename" id="res_ename" size="20" maxlength="12"  />
			                </div>
			            </div>
			            <!--// 영문이름 -->
									
						<!-- 생일 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">생일</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="생일" class="form-control" value="${vo.resBir}" name="res_bir" id="res_bir" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 생일 -->
                                    
						
						<!-- 핸드폰번호 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">핸드폰번호</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="핸드폰번호" class="form-control" value="${vo.resphone}" name="res_phone" id="res_phone" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 핸드폰번호 -->
						
						
						<!-- 이메일 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">이메일</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="이메일" class="form-control" value="${vo.resEmail}" name="res_email" id="res_email" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 이메일 -->
						
						 <!-- 성별 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">성별</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="성별" class="form-control" value="${vo.resGender}" name="res_gender" id="res_gender" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 성별 -->	      
							 
						 <!-- 예약상태 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">예약상태</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="예약상태" class="form-control" value="${vo.resState}" name="res_state" id="res_state" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 예약상태 -->         	    
							     
						 <!-- 지불 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">지불</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="지불" class="form-control" value="${vo.resPay}" name="res_pay" id="res_pay" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 지불 -->   
                        
                         <!-- 마일리지 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">마일리지</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" placeholder="마일리지" class="form-control" value="${vo.resMileage}" name="res_mileage" id="res_mileage" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 마일리지 -->   	   
							  
						  <!-- 예약코드 -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">예약코드</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" readonly="readonly" placeholder="예약코드" class="form-control" value="${vo.resScode}" name="res_scode" id="res_scode" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// 예약코드 -->        
						
						 <!-- uNum -->
                        <div class="form-group">
                            <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">uNum</label>
                            <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
                                <input type="text" readonly="readonly" placeholder="uNum" class="form-control" value="${vo.uNum}" name="u_num" id="u_num" size="20" maxlength="12"  />
                            </div>
                        </div>
                        <!--// uNum --> 	     
							  
							
						</form>
							</div>
	



	
		




    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
 <script src="<c:url  value='../js/bootstrap.min.js' />"> </script>
    <script type="text/javascript">
	//수정

		
$("#update_btn").on('click',function(){
			console.log("#update_btn");
		
		var res_num = $("#res_num").val();
		if(null == res_num || res_num.trim().length == 0){
			alert("res_num 확인하세요.");
			return;
		}
		var res_passport = $("#res_passport").val();
		if(null == res_passport || res_passport.trim().length == 0){
			alert("res_passport 확인하세요.");
			return;
		}
		
		var res_ename = $("#res_ename").val();
		if(null == res_ename || res_ename.trim().length == 0){
			alert("res_ename 확인하세요.");
			return;
		}
		
		var res_bir = $("#res_bir").val();
		if(null == res_bir || res_bir.trim().length == 0){
			alert("res_bir 확인하세요.");
			return;
		}
		
		var res_phone = $("#res_phone").val();
		if(null == res_phone || res_phone.trim().length == 0){
			alert("res_phone 확인하세요.");
			return;
		}
		
		var res_email = $("#res_email").val();
		if(null == res_email || res_email.trim().length == 0){
			alert("res_email 확인하세요.");
			return;
		}
		
		var res_gender = $("#res_gender").val();
		if(null == res_gender || res_gender.trim().length == 0){
			alert("res_gender 확인하세요.");
			return;
		}
		
		var res_state = $("#res_state").val();
		if(null == res_state || res_state.trim().length == 0){
			alert("res_state 확인하세요.");
			return;
		}
		var res_pay = $("#res_pay").val();
		if(null == res_pay || res_pay.trim().length == 0){
			alert("res_pay 확인하세요.");
			return;
		}
		var res_mileage = $("#res_mileage").val();
		if(null == res_mileage || res_mileage.trim().length == 0){
			alert("res_mileage 확인하세요.");
			return;
		}
		var res_scode = $("#res_scode").val();
		if(null == res_scode || res_scode.trim().length == 0){
			alert("res_scode 확인하세요.");
			return;
		}
		
		
		//ajax (Post방식)
		$.ajax({
			type:"POST",
			url:"/SistAirLine/Reservation/res.do",
			dataType:"html", 
			data:{"work_div":"do_update",
				  "res_num":$("#res_num").val(),
				  "res_passport":$("#res_passport").val(),
				  "res_ename":$("#res_ename").val(),
				  "res_bir":$("#res_bir").val(),
				  "res_phone":$("#res_phone").val(),
				  "res_email":$("#res_email").val(),
				  "res_gender":$("#res_gender").val(),
				  "res_state":$("#res_state").val(),
				  "res_pay":$("#res_pay").val(),
				  "res_mileage":$("#res_mileage").val(),
				  "res_scode":$("#res_scode").val(),
				  "u_num":$("#u_num").val()
				 
			},
			success:function(data){ //성공
				   console.log("data="+data);
				   alert("data:"+data);
				   var jData = JSON.parse(data);
				   if(null != jData && jData.msgId =="1"){
					   alert(jData.msgContents);
					   goRetrieve();
				   }else{
					   alert(jData.msgId+ "||" + jData.msgContents);
				   }
			   
			},
			error:function(xhr,status,error){
			 alert("error:"+error);
			},
			complete:function(data){
	
			}   
		});     //--ajax	
	
	});
	
	
	$("#to_list_btn").on('click',function(){
		//console.log("#to_list_btn");
		goRetrieve();
	});
	
	//목록호출
	function goRetrieve(){
		 window.location.href="/SistAirLine/Reservation/res.do?work_div=do_retrieve";
	}
	
	
	
	
	
	$(document).ready(function(){
			
			//console.log("document ready");
	
		
		});
	
 </script>

    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="../js/bootstrap.min.js"></script>


</body>
</html>