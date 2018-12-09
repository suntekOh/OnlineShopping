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
	<form action="${pageContext.request.contextPath}/productDetail"
		method="post">
						<c:forEach items="${item.entrySet()}" var="entry"
							varStatus="theCount">			
		<table border=1 width="100%">		
			<tr>
				<th>Product Detail</th>
			</tr>
				<tr>
					<td>
						<table width="100%">
					
						<tr>
							<td rowspan="4">
								<img src="${entry.getKey().getImgLoc()}" style='height:100px'>
							</td>
							<td colspan="4" width="80%">
								${entry.getKey().getTitle()}					
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
							<td colspan="4" width="80%">
		       					Stock: ${entry.getValue()}								
							</td>
						</tr>						
						<tr>
							<td colspan="4" rowspan="2" width="80%">
		       					${entry.getKey().getDescription()}								
							</td>
						</tr>
					
						</table>
					</td>
				</tr>
		</table>	
<font color="red">${requestScope.message}</font>
<br/>
		<input type="hidden" value="${entry.getKey().getId()}" name="productId">		
		Quantity <input type="text" value="" name="numberOfItems" class="productDetail" size="5" >	
		<input type="submit" value="Checkout" name="decision">	
					</c:forEach>			
</form>
</body>
</html>