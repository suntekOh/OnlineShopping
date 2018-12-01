<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Signin page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

<h1>Sign in</h1>
<fieldset>

<form method="post" action="${pageContext.request.contextPath}/signin">
	<font color="red">${requestScope.message}</font>	
<table border="0">
		<tr>
			<td>E-MAIL</td>
			<td><input type="text" name="email" size="30" maxlength="30"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="30" maxlength="30"></td>
		</tr>
</table>
<input type="submit" name="oper" value="Sign in">
</form>
</fieldset>
<fieldset>
<p>If you are new to this site, <a href="${pageContext.request.contextPath}/register.jsp">Create your account</a>.</p>
</fieldset>
</body>
</html>