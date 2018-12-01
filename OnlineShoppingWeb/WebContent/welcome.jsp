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
		<table border=1 width="100%">		
			<tr>
				<th>Products List</th>
			</tr>
		<c:forEach items="${products}" var="item"
			varStatus="theCount">
				<tr>
					<td>
						<table width="100%">
						<tr>
							<td rowspan="4">
								<img src="${item.getImgLoc()}" style='height:100px'>
							</td>
							<td colspan="4" width="80%">${item.getTitle()}</td>
						</tr>
						<tr>
							<td colspan="4" width="80%">${item.getPrice()}</td>
						</tr>
						<tr>
							<td height="30"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td height="30" ></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>		
						</tr>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>			
			


	    </c:when>
		<c:otherwise>  
			<h1>There is no product related to the Keyword.</h1>		
       </c:otherwise>
	</c:choose>





</body>
</html>