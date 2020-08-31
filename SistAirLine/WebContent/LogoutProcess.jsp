<%@page import="User.UserDao"%>
<%@page import="com.hr.cmn.JDBCResClose"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.hr.cmn.ConnectionMaker"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="User.UserVO"%>
<%@page import="java.sql.Connection"%>
<%@page session="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
		session.invalidate();
	
%>
<script>
         	alert("로그아웃되었습니다.");
         	location.href="mainForm.jsp";
</script>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>

