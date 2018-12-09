<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>bye</title>
</head>
<body>
	<p>
		Go to <a
			href="${pageContext.request.contextPath}/searchProduct.jsp">Search
			Product</a> or 
		Go to <a
			href="${pageContext.request.contextPath}/manageProduct.jsp">Manage
			Product</a>
or
		Go to <a
			href="${pageContext.request.contextPath}/manageInventory">Manage
			Inventory</a>
	</p>

	<c:choose>
		<c:when test="${requestScope.from=='mangeProduct'}">
			<h1>A new product has been successfully created.</h1>
		</c:when>
		<c:when test="${requestScope.from=='productDetail'}">
			<h1>Your purchase has been successfully processed.</h1>
		</c:when>		
		<c:otherwise>
			<h1>Products has been successfully added.</h1>
		</c:otherwise>
	</c:choose>



</body>
</html>