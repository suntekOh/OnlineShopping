<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<html>
<head>
<title>welcome page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<jsp:include page="master.jsp"/>
	<c:choose>
		<c:when test="${products.size() > 0}">  


	    </c:when>
		<c:otherwise>  
			<h1>There is no product related to the Keyword.</h1>		
       </c:otherwise>
	</c:choose>





</body>
</html>