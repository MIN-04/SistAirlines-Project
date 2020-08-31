<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Hello JSP</title>
</head>
<body class="container">
    <div class="jumbotron">
        <h1>비밀번호 찾기</h1>
        <p>★이름,이메일을 입력하세요.★</p>
    </div>


    <form action="Searchpw.jsp" method="post">
        <div class="form-group">
            <label>이름</label>
            <input name="uKname" type="text" class="form-control" />
        </div>
        <div class="form-group">
            <label>이메일</label>
            <input name="uEmail" type="text" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary">비번찾기</button>
        <table align="center" height="50" style="margin-top: 1%" class="mo">
			<tr>
				<td><a style="text-decoration: none;" href="./2.join.html">회원가입
						| </td>
				<td><a style="text-decoration: none;" href="SearchIDForm.jsp">아이디 찾기 | </td>
				<td><a style="text-decoration: none;" href="SearchpwForm.jsp">비밀번호 찾기</td>
			</tr>

		</table>
    </form>

    <!-- Optional JavaScript -->
    <Script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <Script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
