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
<link href="/SistAirLine/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
    <!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
      .form-group { font-size: 16px; padding: 20px;}
      .title { font-size: 32px; }
      .pad{padding: 20px;}
      .col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label{
      padding 20px;}
    </style>
</head>




<body>
    <!-- div container -->
    <div class="container">  
         <!-- div title --> 
        <div class="page-header">
           <h2 style="text-align: center;">항공스케줄 수정</h2>
        </div>    
        <div style="width:100%;align:center;padding:10px;">
        	<div class="row text-right" style="width:100%;">
             	<label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label"></label>
             	<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">			
				   <input  type="button"  class="btn btn-primary" value="목록" id="to_list_btn" />
				   <button type="submit"  class="btn btn-primary" id="update_btn">수정</button>
				</div>
			</div>
			
			 <form style="padding:10px;" name="insert_frm" id="insert_frm" class="form-horizontal" action='<%=HR_PATH%>/scheduleDTO/schedule.do'  method="post">
				<input type="hidden" name="work_div"  id="work_div" />
			    <input type="hidden" name="seq"  id="seq" value="${vo.scCode}" />
		         <div >
		             <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">출발날짜</label>
		             <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
		                 <input type="text"  class="form-control" value="${vo.scDate}" name="scdate" id="scdate" size="20" maxlength="12"  />
		             </div>
		         </div>
		         
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">출발시간</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scDtime}" name="scdtime" id="scdtime" size="20" maxlength="12"/>
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">소요시간</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scStime}" name="scstime" id="scstime" size="20" />
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">출발도시</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scDcity}" name="scdcity" id="scdcity" size="20" />
	                    </div>
	                </div>
	                
	
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">도착도시</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scAcity}" name="scacity" id="scacity" size="20" />
	                    </div>
	                </div>
	                	      
	
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">가격</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text"class="form-control" value="${vo.scPrice}" name="scprice" id="scprice" size="20" />
	                    </div>
	                </div>
	                       	    
	
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">마일리지</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scMileage}" name="scmileage" id="scmileage" size="20" />
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">비행기이름</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scAname}" name="scaname" id="scaname" size="20"/>
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">수용좌석</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scCapacity}" name="sccapcacity" id="sccapcacity" size="20"  />
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">UNOCCUPIED</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scUnoccupied}" name="scunoccupied" id="scunoccupied" size="20" />
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">비행기코드</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scCode}" name="sccode" id="sccode" readonly="readonly" size="20" />
	                    </div>
	                </div>
	                
	                <div>
	                    <label for="title" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 control-label" style="padding-top:15px;">상태</label>
	                    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10" style="padding:10px;">
	                        <input type="text" class="form-control" value="${vo.scState}" name="scstate" id="scstate"  size="20"/>
	                    </div>
	                </div>
				</form>
        </div>         
		
		</div>
	



	
		




    <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
 <script type="text/javascript">
	//수정
	$("#update_btn").on("click",function(){
		console.log("#update_btn");
		
		var scdate = $("#scdate").val();
		if(null == scdate || scdate.trim().length == 0){
			alert("scdate 확인하세요.");
			return;
		}
		var scdtime = $("#scdtime").val();
		if(null == scdtime || scdtime.trim().length == 0){
			alert("scdtime 확인하세요.");
			return;
		}
		
		var scstime = $("#scstime").val();
		if(null == scstime || scstime.trim().length == 0){
			alert("scstime 확인하세요.");
			return;
		}
		
		var scdcity = $("#scdcity").val();
		if(null == scdcity || scdcity.trim().length == 0){
			alert("scdcity 확인하세요.");
			return;
		}
		
		var scacity = $("#scacity").val();
		if(null == scacity || scacity.trim().length == 0){
			alert("scacity 확인하세요.");
			return;
		}
		
		var scprice = $("#scprice").val();
		if(null == scprice || scprice.trim().length == 0){
			alert("scprice 확인하세요.");
			return;
		}
		
		var scmileage = $("#scmileage").val();
		if(null == scmileage || scmileage.trim().length == 0){
			alert("scmileage 확인하세요.");
			return;
		}
		
		var scaname = $("#scaname").val();
		var sccapcacity = $("#sccapcacity").val();
		if(null == sccapcacity || sccapcacity.trim().length == 0){
			alert("sccapcacity 확인하세요.");
			return;
		}
		var scunoccupied = $("#scunoccupied").val();
		if(null == scunoccupied || scunoccupied.trim().length == 0){
			alert("scunoccupied 확인하세요.");
			return;
		}
		var sccode = $("#sccode").val();
		
		var scstate = $("#scstate").val();
		if(null == scstate || scstate.trim().length == 0){
			alert("scstate 확인하세요.");
			return;
		}
		
		
		//ajax
		$.ajax({
			type:"POST",
			url:"/SistAirLine/scheduleDTO/schedule.do",
			dataType:"html", 
			data:{"work_div":"do_update",
				  "scdate":$("#scdate").val(),
				  "scdtime":$("#scdtime").val(),
				  "scstime":$("#scstime").val(),
				  "scdcity":$("#scdcity").val(),
				  "scacity":$("#scacity").val(),
				  "scprice":$("#scprice").val(),
				  "scmileage":$("#scmileage").val(),
				  "scaname":$("#scaname").val(),
				  "sccapcacity":$("#sccapcacity").val(),
				  "scunoccupied":$("#scunoccupied").val(),
				  "sccode":$("#sccode").val(),
				  "scstate":$("#scstate").val()
				 
			},
			success:function(data){ //성공
				   //console.log("data="+data);
				   //alert("data:"+data);
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
		 window.location.href='/SistAirLine/scheduleDTO/schedule.do?work_div=do_adminretrieve';
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