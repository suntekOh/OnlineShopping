<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Suntek's Welcome Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h3>Register</h3>

<fieldset>
<p>If you don't have an account yet, complete the form below and click <b>Register</b> to create an account</p>
<p>Members should sign in on the <a href="${pageContext.request.contextPath}/singin.jsp">log in</a> page.</p>
<form method="post" action="${pageContext.request.contextPath}/signin">

Registration form<br>
<font color="red">${requestScope.message}</font>
<table border="0">
	<tbody>
		<tr>
			<td>E-mail address </td>
			<td><input type="text" name="email" size="30"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="password" size="30"></td>
		</tr>
		<tr>
			<td>Your name</td>
			<td><input type="text" name="nickName" size="30"></td>
		</tr>		
	</tbody>
</table>
<input type="submit" name="oper" value="register"/> 
</form>
</body>
</html>