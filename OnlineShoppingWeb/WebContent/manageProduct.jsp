<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>The Best Online Shopping site</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1>Manage Product</h1>

<fieldset>
<h3>You can create a new product here.</h3>
<p>Go to <a href="${pageContext.request.contextPath}/searchProduct.jsp">Search Product</a> or 
		Go to <a
			href="${pageContext.request.contextPath}/manageProduct.jsp">Manage
			Product</a>
</p>
<form method="post" action="${pageContext.request.contextPath}/manageProduct"  enctype = "multipart/form-data">

<font color="red">${requestScope.message}</font>
<table border="0">
	<tbody>
		<tr>
			<td>Product Code </td>
			<td><input type="text" name="productCode" size="30"></td>
		</tr>
		<tr>
			<td>Title</td>
			<td><textarea rows="2" cols="70" name="title"></textarea></td>
		</tr>
		<tr>
			<td>Description</td>
			<td><textarea rows="10" cols="70" name="description"></textarea></td>
		</tr>		
		<tr>
			<td>Price</td>
			<td><input type="text" name="price" size="30"></td>
		</tr>			
		<tr>
			<td>Image</td>
			<td><input type = "file" name = "file" size = "30" /></td>
		</tr>	
				</tbody>
</table>
<input type="submit" name="oper" value="create"/> 

</form>
</fieldset>
</body>
</html>