<%@page import="javafx.scene.control.Alert"%>
<%@page import="User.UserDao"%>
<%@page import="User.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<%@page import="java.sql.*"%>

<%
    request.setCharacterEncoding("UTF-8");
    
    
    String userID = request.getParameter("userID");
    String userPassword = request.getParameter("userPassword");
    String userKname = request.getParameter("userKname");
    String userEname = request.getParameter("userEname");
    String year = request.getParameter("year");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String userphone1 = request.getParameter("userphone1");
    String userphone2 = request.getParameter("userphone2");
    String userphone3 = request.getParameter("userphone3");
    String userEmail = request.getParameter("userEmail");
    String userGender= request.getParameter("userGender");
    
    String userAddress = request.getParameter("userAddress");
    String userCountry = request.getParameter("userCountry");
    String userMileage = "0";
    String maindepart = request.getParameter("maindepart");
    int authority = 1;
    
    
    UserVO inVO= new UserVO();
    UserDao userdao = new UserDao();
    int flag = 0;

    
    inVO.setuId(userID);
    inVO.setuPw(userPassword);
    inVO.setuKname(userKname);
    inVO.setuEname(userEname);

    String bir = year + month + day;
    inVO.setuBir(bir);
    
    String userphone = userphone1 +userphone2+userphone3;
    inVO.setuPhone(userphone);
    
    inVO.setuEmail(userEmail);
    inVO.setuGender(userGender);
    inVO.setuAddress(userAddress);
    inVO.setuCountry(userCountry);
    inVO.setuMileage(0);
    inVO.setuMaindepart(maindepart);
    inVO.setAuthority(authority);
    
    flag = userdao.doInsert(inVO);
    
    if (flag == 1) {
    	
    	response.sendRedirect("LoginForm.jsp");
        
    } else {
        response.sendRedirect("SignUpForm.jsp");

    }

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SIST AIRLINES</title>
</head>
<body>

</body>
</html>
