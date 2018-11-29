<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/processMaster">
<table style="border-width: 0px; margin 0px; width:100%; height: 50px; padding:0px; background-color:#04cf5d;">
	<tbody style="border-width: 0px; margin 0px; width:100%; height: 100%; padding:0px; border-collapse: collapse;">
		<tr>
			<td>
			<input type="text" name="keyword" style="width:80%;height:25px;margin 0px; padding:0px; border-collapse: collapse;">
		    <input type="submit" name="oper" value="SEARCH" size="20">
			</td>
		</tr>
		<tr>
			<td align="right">
				<input type="submit" name="oper" value="Hello, Sign in" size="20">			
				<input type="submit" name="oper" value="ORDERS" size="20">
				<input type="submit" name="oper" value="CART" size="20">
			</td>
		</tr>
	</tbody>
</table>


</form>
</body>
</html>