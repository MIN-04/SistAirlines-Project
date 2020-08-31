<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>좌석조회 폼</title>
</head>
<body>
	<form action="seatJoin.jsp" method="post">
	
		출발날짜: <input type="text" name="e_date" size="10"><br>	
		출발지: <input type="text" name="depart_city" size="10"><br>
		도착지: <input type="text" name="arrive_city" size="10"><br>
		출발시간: <input type="text" name="arrive_time" size="10"><br>
		<input type="submit" value="확인">
		
		
	</form>
</body>
</html>