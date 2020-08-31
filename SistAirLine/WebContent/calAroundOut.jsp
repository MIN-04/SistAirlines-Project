<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>SIST AIRLINES 날짜 선택</title>
</head>
<style>
	table#calendar
	{
	    border:1px solid #BDBDBD;
	    text-align:center;
	    width:80%;
	    border: 1px solid #111111;
	}
	tr:hover {
	background-color: #ffffff;}
</style>
<body onload="build();">
	<div style="text-align:center;padding:10px;">
		<h2 >날짜를 선택하여 주십시오.</h2>
		<table align="center" id="calendar">
	        <tr>
	            <td><font size=1%; color="#B3B6B3"><label onclick="beforem()" id="before" ></label></font></td>
	            <td colspan="5" align="center" id="yearmonth"></td>
	            <td><font size=1%; color="#B3B6B3"><label onclick="nextm()" id="next"></label></font></td>
	        </tr>
	        <tr>
	            <td align="center"> <font color="#FF9090">일</font></td>
	            <td align="center"> 월 </td>
	            <td align="center"> 화 </td>
	            <td align="center"> 수 </td>
	            <td align="center"> 목 </td>
	            <td align="center"> 금 </td>
	            <td align="center"><font color=#7ED5E4>토</font></td>
	        </tr>
    	</table>
    	<input type="hidden" id="limitDate">
	</div>
	<div style="width:80%; margin-left:50%">
		<table style="text-align:center;">
			<tr>
				<td style="width:20px;height:10px;background-color:#D8D8D8;border: 1px solid #111111;"></td>
				<td>선택 불가능</td>
				<td></td>
				<td style="width:20px;height:10px;background-color:white;border: 1px solid #111111;"></td>
				<td>선택 가능</td>
			</tr>
		</table>
	</div>
	
<script  src="https://code.jquery.com/jquery-2.2.4.js"></script>
<script type="text/javascript">
	var today = new Date(); // 오늘 날짜
	var date = new Date();
	var limitDate = null;

	function beforem() //이전 달을 today에 값을 저장
	{ 
	    today = new Date(today.getFullYear(), today.getMonth() - 1, today.getDate());
	    build(); //만들기
	}
	
	function nextm()  //다음 달을 today에 저장
	{
	    today = new Date(today.getFullYear(), today.getMonth() + 1, today.getDate());
	    build();
	}
	
	function build()
	{
	    var nMonth = new Date(today.getFullYear(), today.getMonth(), 1); //현재달의 첫째 날
	    var lastDate = new Date(today.getFullYear(), today.getMonth() + 1, 0); //현재 달의 마지막 날
	    var tbcal = document.getElementById("calendar"); // 테이블 달력을 만들 테이블
	    var yearmonth = document.getElementById("yearmonth"); //  년도와 월 출력할곳
	    yearmonth.innerHTML = today.getFullYear() + "년 "+ (today.getMonth() + 1) + "월"; //년도와 월 출력
	    
	    if(today.getMonth()+1==12) //  눌렀을 때 월이 넘어가는 곳
	    {
	        before.innerHTML=(today.getMonth())+"월";
	        next.innerHTML="1월";
	    }
	    else if(today.getMonth()+1==1) //  1월 일 때
	    {
	    before.innerHTML="12월";
	    next.innerHTML=(today.getMonth()+2)+"월";
	    }
	    else //   12월 일 때
	    {
	        before.innerHTML=(today.getMonth())+"월";
	        next.innerHTML=(today.getMonth()+2)+"월";
	    }
	   
	    // 남은 테이블 줄 삭제
	    while (tbcal.rows.length > 2) 
	    {
	        tbcal.deleteRow(tbcal.rows.length - 1);
	    }
	    var row = null;
	    row = tbcal.insertRow();
	    var cnt = 0;
	
	    // 1일 시작칸 찾기
	    for (i = 0; i < nMonth.getDay(); i++) 
	    {
	        cell = row.insertCell();
	        cnt = cnt + 1;
	    }
	
	    // 달력 출력
	    for (i = 1; i <= lastDate.getDate(); i++) // 1일부터 마지막 일까지
	    { 
	        cell = row.insertCell();
	        cell.innerHTML = i;
	        cnt = cnt + 1;
	        if (cnt % 7 == 1) {//일요일 계산
	            cell.innerHTML = "<font color=#FF9090>" + i//일요일에 색
	        }
	        if (cnt % 7 == 0) { // 1주일이 7일 이므로 토요일 계산
	            cell.innerHTML = "<font color=#7ED5E4>" + i//토요일에 색
	            row = calendar.insertRow();// 줄 추가
	        }
	        //오늘
	        if(today.getFullYear()==date.getFullYear()&&today.getMonth()==date.getMonth()&&i==date.getDate()) 
	        {
	            cell.bgColor = "#c3e6e5";
	        }
	        //지난 날
	        if((i<date.getDate()&&today.getMonth()==date.getMonth()) || today.getMonth()<date.getMonth()){
	        	cell.bgColor = "#D8D8D8";
	        }
	    }
	    var day = today.getDate();
	    var month = date.getMonth()+1;
	    if(day>=1 && day<=9){
	    	day = "0"+day;
    		//console.log("day:"+month);
    	}
    	if(month>=1 && month<=9){
    		month = "0"+month;
    		//console.log("month:"+month);
    	}
	    limitDate = String(date.getFullYear()) + month + day;
	    document.getElementById("limitDate").value = limitDate;
	}

	$("table").click(function(e) {
    
	    //클릭한 타겟의 오브젝트가 TD일 경우
	    if(e.target.tagName == "TD") {
	    	//클릭한 TD 인덱스 위치 찾기
	    	var idx = $("table td").index($(e.target));
	    	
	    	var tdVal = $("table td").eq(idx).text();
	    	console.log("td.eq(idx).text():"+$("table td").eq(idx).text());
	    	
	        if(idx>9){
	        	
	        	if(tdVal>=1 && tdVal<=9){
	        		tdVal = "0"+tdVal;
	        		console.log("tdVal:"+tdVal);
	        	}
	        	
	        	var month = (today.getMonth() + 1);
	        	if(month>=1 && month<=9){
	        		month = "0"+month;
	        		console.log("month:"+month);
	        	}
	        	console.log("today.getFullYear():"+today.getFullYear());
	        	console.log("(today.getMonth() + 1):"+month);
	        	
	        	var date = String(today.getFullYear()) + month + tdVal;
	        	//document.getElementById("dateTest").value = date;
	        	console.log("date:"+date);
	        	console.log("limitDate:"+limitDate);
	        	if(Number(date)<Number(limitDate)){
	        		alert("지난 날은 선택 불가능합니다.");
		        	return;
		        }else{
		        	opener.document.getElementById("outDateAr").value = date;
		            window.close();
		        	
		        }
	        }
	    }
	});
</script>
</body>
</html>