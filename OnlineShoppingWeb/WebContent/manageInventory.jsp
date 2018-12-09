<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>welcome page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<h1>Manage Inventory</h1>
	<p>
		Go to <a
			href="${pageContext.request.contextPath}/searchProduct.jsp">Search
			Product</a> or 
		Go to <a
			href="${pageContext.request.contextPath}/manageProduct.jsp">Manage
			Product</a>
	</p>	
	please enter the quantity of each item you want to add to the inventory, and then
	click Confirm.
	<br />
	<font color="red">${requestScope.message}</font>	
	<br />


	<form action="${pageContext.request.contextPath}/manageInventory"
		method="post">
		<table border=1>
			<tr>
				<th>ProductCode</th>
				<th>Price</th>
				<th>Quantity in stock</th>
				<th>Quantity to add</th>
				<th>Title</th>				
			</tr>

			<c:forEach items="${inventory.entrySet()}" var="entry">
				<tr>
					<td align="center">${entry.getKey().getProductCode()}</td>				
					<td align="right">
       					<fmt:setLocale value = "en_US"/>					
						<fmt:formatNumber type="currency" value="${entry.getKey().getPrice()}"  />						
					</td>
					<td align="right">${entry.getValue()}</td>
					<td>
					<input type="text" size="20" name="${entry.getKey().getId()}" style="text-align:right;color:red;"/>
					</td>
					<td>${entry.getKey().getTitle()}</td>				
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Confirm" name="decision">	
	</form>



</body>
</html>