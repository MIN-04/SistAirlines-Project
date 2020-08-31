<%--
	작성자:
	작성일:
	파일명:
--%>
<%@page import="User.UserDao"%>
<%@page import="User.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>중복확인</title>


</head>
<body>
	 <%
	    request.setCharacterEncoding("UTF-8");

        String userID = request.getParameter("userID");
       // UserDao userdao = 	UserDao.getInstance();
       // int doublecheck = userdao.confirmId(userID);
        
        
        UserVO inVO= new UserVO();
        inVO.setuId(userID);
        
        UserDao userdao = new UserDao();
        int doublecheck = userdao.confirmId(inVO);

        
        
        if(doublecheck == 1){%>
        <div align="center">
        <b><font color="red"><%=userID %></font>는 이미 사용중인 아이디입니다.</b>
        <form name="checkForm" method="post" action="SignUpConfirmId.jsp">
        <b>다른아이디를 선택하세요</b><br/></br>
        <input type="text" name="userID"/>
        
        <input type="submit" value="ID중복확인" onclick="idCheck"/>
        </form>
        </div>
        <%
        }else{
        %><center>
        <div align="center">
        </br></br>
        <b>입력하신</b><font color="red"><%=userID %></font>는 사용가능합니다.</b><br/>
        </div>
        </br></br></br>
        <input type="button" value="닫기" onclick="setid()"/></center>
        <%} %>
        
        
        
        <script type="text/javascript">
        function setid()
        {
        	
        	opener.document.f.idDuplication.value="idCheck";
        	opener.document.f.userID.value="<%=userID %>";
        	
        	
        	if(opener != null){
        		opener.chkForm = null;
        		self.close();
        	}
        	
        	
        
        }
        
        
        </script>
        
        
</body>
</html>