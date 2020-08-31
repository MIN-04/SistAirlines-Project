<%@page import="User.UserVO"%>
<%@page import="User.UserDao"%>
<!DOCTYPE html>
<html lang="kr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>NaverLoginSDK</title>
</head>

<body>

<%

	String uId = request.getParameter("uId");
	String uPw = request.getParameter("uPw");
	
	UserVO inVO = new UserVO();
	inVO.setuId(uId);
	inVO.setuPw(uPw);
	UserDao dao = new UserDao();
	UserVO outVO=dao.loginMethod(inVO);
	
	int userNum=outVO.getuNum();
	int authority=outVO.getAuthority();
	if(userNum >0 && authority==1){
		session.setAttribute("uNum",userNum);
		session.setAttribute("check","go");
		session.setAttribute("auth",1);	
%>
<script>
         	alert("로그인 성공");
         	location.href="index.jsp";
</script>

<%		
	}else if(userNum >0 &&authority==9){
				session.setAttribute("auth",9);	
				session.setAttribute("check","go");
				session.setAttribute("uNum",9999);
		
%>

<script>
         	alert("administer.");
         	location.href="administer.jsp";
</script>	
	
<%
	}else{
%>

<script>
         	alert("Fail!");
			history.back(1);
</script>
<%
	}
%>


</body>
</html>


</body>

</html>