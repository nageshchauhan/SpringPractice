<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login in HRMS</title>
<c:url var="cssUrl" value="/resources/css/bootstrap.min.css"/>
<link href="${cssUrl}" rel="stylesheet"/>
</head>
<body>
	<div>
		<c:url value="/login" var="loginUrl"/>
		<form action="${loginUrl}" method="post">
			Login in HRMS <br><br>
			
			<c:if test="${param.error != null}">
				<div class="alert alert-error">
					Failed to login.
					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
              		Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
					</c:if>
				</div>
			</c:if>
			<c:if test="${param.logout != null}">
				<div class="alert alert-success">You have been logged out.</div>
			</c:if>
			
			<div style="color: red">${errorMsg}</div> <br>
			<label for="username">Username</label> 
			<input type="text" id="username" name="username"> <br>
			<label for="password">Password</label>
			<input type="password" id="password" name="password"> <br>
			
			<input type="submit" value="Login"> 
		</form>
	</div>

</body>
</html>