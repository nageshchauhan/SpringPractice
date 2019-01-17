<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${pageTitle}</title>
	<c:url var="cssUrl" value="/resources/css/bootstrap.min.css"/>
    <link href="${cssUrl}" rel="stylesheet"/>
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
    </style>
</head>
<body>
<div id="nav-bar" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<c:url var="welcomeUrl" value="/" />
            <a class="brand" href="${welcomeUrl}">HRMS</a>
            
            <div class="nav-collapse">
                <ul class="nav">
                    <li><a id="navWelcomeLink" href="${welcomeUrl}">Welcome</a></li>
                    
                    <c:url var="userUrl" value="/user/" />
                    <li><a id="navEventsLink" href="${userUrl}">View Detail</a></li>
                    
                    <c:url var="updateUserUrl" value="/user/update" />
                    <li><a id="navMyEventsLink" href="${updateUserUrl}">Update user</a></li>
                    
                    <c:url var="createUserUrl" value="/user/create" />
                    <li><a id="navCreateEventLink" href="${createUserUrl}">Create User</a></li>
                    
                    <c:url var="viewallUserUrl" value="/user/viewall" />
                    <li><a id="navCreateEventLink" href="${viewallUserUrl}">View all user</a></li>
                    
                    <c:url var="h2ConsoleUrl" value="/admin/h2" />
                    <li><a id="navH2Link" href="${h2ConsoleUrl}">H2</a></li>
                </ul>
            </div>
            
            <div id="nav-account" class="nav-collapse pull-right">
                <ul class="nav">
					<sec:authorize access="authenticated" var="authenticated" />
					<li><a id="navLogoutLink" href="#"><sec:authentication property="name" /></a></li>
					
					<c:url var="logoutUrl" value="/logout"/>
                    <li><a id="navLogoutLink" href="${logoutUrl}">Logout</a></li>
                </ul>
            </div>
		</div>
	</div>
</div>
<div class="container">
	<c:if test="${message != null}">
	    <div class="alert alert-success" id="message"><c:out value="${message}"/></div>
	</c:if>
	<h1 id="title"><c:out value="${pageTitle}"/></h1>