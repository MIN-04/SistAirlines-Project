<%@page import="member.MemberVO"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>



   
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" href="./css/custom.css">
<title>JSP 게시판 웹사이트</title>
</head>

<body>
<%
       request.setCharacterEncoding("utf-8");
       

        //String userNumSeq = "USERS_SEQ.NEXTVAL";
        //int userNum=0;
        //try{
        //   userNum=Integer.parseInt(userNumSeq);
        //}catch(NumberFormatException e){
        //    e.printStackTrace();
        //}
        
        
        //String i= request.getParameter("uNum");
        //int number = Integer.parseInt(i);
       String uNum=request.getParameter("uNum");
       
		MemberVO inVO=(MemberVO)session.getAttribute("user");
       inVO.setuNum(inVO.getuNum());
       MemberDAO dao=new MemberDAO();
      
       int flag=0;
     
      String uId = request.getParameter("userID");
      String uPw = request.getParameter("userPassword");
      String uKname = request.getParameter("userKname");
      String uEname = request.getParameter("userEname");
      String year= request.getParameter("year");
      String month= request.getParameter("month");
      String day= request.getParameter("day");
      
      MemberVO outVO=(MemberVO)dao.doSelectOne(inVO);
      String uBir = outVO.getuBir();
      
      String uPhone1 = request.getParameter("userphone1");
      String uPhone2 = request.getParameter("userphone2");
      String uPhone3 = request.getParameter("userphone3");
      
      String uPhone = uPhone1+uPhone2+uPhone3;
      //String uPhone1= uPhone.substring(0,3);
      //String uPhone2= uPhone.substring(3,7);
      //String uPhone3= uPhone.substring(7,11);
      
      
      
      String uEmail = request.getParameter("userEmail");
      String uGender = request.getParameter("userGender");
      String uAddress = request.getParameter("userAddress");
      String uCountry =request.getParameter("userCountry");
     // String uMileage = request.getParameter("userMileage");
      String uMaindepart = request.getParameter("maindepart");
      int authority = 1;
      
      inVO.setuNum(inVO.getuNum());
      //inVO.setuID(uId);
      inVO.setuPW(uPw);
      inVO.setuKname(uKname);
      inVO.setuEname(uEname);
      inVO.setuBir(uBir);
      inVO.setuPhone(uPhone);
      inVO.setuEmail(uEmail);
      inVO.setuGender(Integer.parseInt(uGender));
      inVO.setuAddress(uAddress);
      inVO.setuCountry(uCountry);
     // inVO.setuMileage(Integer.parseInt(uMileage));
      inVO.setuMaindepart(uMaindepart);
      inVO.setAuthority(authority);

      flag= dao.doUpdate(inVO);
      
      if (flag == 1) {
          
          %>
          <script>
                      alert("회원정보를 수정하였습니다.");
                      location.href="index.jsp";
          </script>

          <%  
          
          
      } else {
    	  %>
          <script>
                      alert("회원정보 수정에 실패하였습니다.");
                      location.href="LoginForm.jsp";
          </script>

    <%   
      }
      
    %>
