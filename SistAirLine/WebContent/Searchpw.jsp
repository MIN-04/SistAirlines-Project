<%@page import="mail.MailMainEx01"%>
<%@page import="User.UserVO"%>
<%@page import="User.UserDao"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<%
	request.setCharacterEncoding("UTF-8");

	String uKname = request.getParameter("uKname");
	String uEmail = request.getParameter("uEmail");
	
	UserDao dao=new UserDao();
	UserVO inVO = new UserVO();
	inVO.setuKname(uKname);
	inVO.setuEmail(uEmail);
	//dao.idLookup(inVO);
	
	UserVO outVO = dao.passLookup(inVO);
	//outVO.getuId();
	System.out.println(outVO);
	
	String uPw = outVO.getuPw();
	String uemail = outVO.getuEmail();
		if(uPw !=null){
			MailMainEx01.naverMailSendpw(uPw,uemail); 
			
			
			
			
    //out.println("나의 비밀번호는:"+  uPw);
%>
		<script>
	 		alert("비밀번호를 이메일로 전송했습니다.");
        	location.href="mainForm.jsp";       
    	</script>
	<% 
		}else{
	%>
		<script>
             alert("조회하고자 하는 비밀번호가 없습니다,.");
             history.back(1); 
         //    location.href="Searchpw.jsp";
        </script>
	<% 
		}
	%>
	
	</HEAD>
<BODY>
	</BODY>
</HTML>