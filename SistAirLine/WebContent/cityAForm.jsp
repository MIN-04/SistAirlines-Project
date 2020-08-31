<%@page import="java.util.List"%>
<%@page import="cityDTO.CityVO"%>
<%@page import="cityDTO.CityDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	CityDAO cityDao = new CityDAO();
	List<CityVO> list= (List<CityVO>) cityDao.cityList();
%>
<html>
<head>
<meta charset="EUC-KR">
<title>SIST AIRLINES 취향지 도시</title>
<script type="text/javascript">
        function setParentText(cInput){
        opener.document.getElementById("sc_acity").value = document.getElementById(cInput).value
        window.close()
        }
</script>
</head>
<body>
	<h3>SIST AIRLINES 취향지 도시</h3>
	<hr/>
	<table border="1">
		<%
			int i=0;
			for(CityVO outVO : list){
				i=i+1;
		%>
		<tr>
			<td><%=outVO.getContinent() %></td>
			<td><input type="button" id="cInput<%=i%>" onClick="setParentText(id)" value="<%=outVO.getCityKor() %>"></td>
			
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>