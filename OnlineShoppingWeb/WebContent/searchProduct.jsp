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
<jsp:include page="master.jsp"/>
	<c:choose>
		<c:when test="${requestScope.products.size() > 0}">  
		<table border=1 width="100%">		
			<tr>
				<th>Products List</th>
			</tr>
		<c:forEach items="${requestScope.products.entrySet()}" var="entry"
			varStatus="theCount">
				<tr>
					<td>
						<table width="100%">
						<tr>
							<td rowspan="4">
								<img src="${entry.getKey().getImgLoc()}" style='height:100px'>
							</td>
							<td colspan="4" width="80%">
								<a href="${pageContext.request.contextPath}/productDetail?productId=${entry.getKey().getId()}" class="detail">${entry.getKey().getTitle()}</a>							
							</td>
						</tr>
						<tr>
							<td colspan="4" width="80%">
							<font color="red">
		       					<fmt:setLocale value = "en_US"/>					
								<fmt:formatNumber type="currency" value="${entry.getKey().getPrice()}"  />								
							</font>
							</td>
						</tr>
						<tr>
							<td colspan="4" width="80%"> Stock: ${entry.getValue()} 								
							</td>
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
		<c:when test="${requestScope.searchYN =='Y'}">  
			<h1>There is no product related to the Keyword.</h1>		
		</c:when>	    
		<c:otherwise>  

       </c:otherwise>
	</c:choose>





</body>
</html>