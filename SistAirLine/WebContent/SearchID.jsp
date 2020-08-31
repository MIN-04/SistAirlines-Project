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
	String uPhone = request.getParameter("uPhone");
	
	UserDao dao =new UserDao();
    UserVO inVO = new UserVO();

	inVO.setuKname(uKname);
	inVO.setuPhone(uPhone);
	//dao.idLookup(inVO);
	
	UserVO outVO = dao.idLookup(inVO);
	//outVO.getuId();
	System.out.println(outVO);
	
	String uID = outVO.getuId();
	String uemail = outVO.getuEmail();
		if(uID !=null){
			MailMainEx01.naverMailSendid(uID,uemail); 

			
	


			
			
		  %>
<script>
            alert('ID'+"아이디를 이메일로 전송했습니다.");
            location.href="mainForm.jsp";
</script>
   
<%     
    }else{
%>
<script>
            alert("조회하고자 하는 아이디가 없습니다.");
            history.back(1);
</script>   
	
	
	<%
    }
%>
	
	
		

		

	
	</HEAD>
<BODY>
	</BODY>
</HTML>