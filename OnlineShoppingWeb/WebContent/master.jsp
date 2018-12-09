<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">

<title>Insert title here</title>
</head>
<body>
	<form method="post"
		action="${pageContext.request.contextPath}/processMaster">
		<table
			style="border-width: 0px; margin 0px; width: 100%; height: 50px; padding: 0px; background-color: #232f3e;">
			<tbody
				style="border-width: 0px; margin 0px; width: 100%; height: 100%; padding: 0px; border-collapse: collapse;">
				<tr>
					<td>
					<input type="text" name="keyword"
						style="width: 80%; height: 25px; margin 0px; padding: 0px; border-collapse: collapse;">
						<input type="submit" name="oper" value="SEARCH" size="20">
					</td>
				</tr>
				<tr>
					<td align="right"><c:choose>
							<c:when test="${sessionScope.customer != null}">
								<%-- 								<font color="white">Hi, ${sessionScope.customer.getNickName()}</font> --%>
								<!-- 								<input type="submit" name="oper" value="Logout" size="20"> -->
								<select name="menuList"
									onchange="if (this.options[this.selectedIndex].value != '0') { location = this.options[this.selectedIndex].value;}">

									<option value="0">Hi,
										${sessionScope.customer.getNickName()}</option>
									<c:forEach items="${menu}" var="item" varStatus="theCount">
										<option
											value="${pageContext.request.contextPath}/${item.getLocation()}">
 											${item.getName()}
										</option>
									</c:forEach>
								</select>
							</c:when>
							<c:otherwise>
								<a class="signin"
									href="${pageContext.request.contextPath}/signin.jsp">Hello,
									Sign in</a>
							</c:otherwise>
						</c:choose> <input type="submit" name="oper" value="Orders" size="20">
						<input type="submit" name="oper" value="Cart" size="20"></td>
				</tr>
			</tbody>
		</table>


	</form>
</body>
</html>