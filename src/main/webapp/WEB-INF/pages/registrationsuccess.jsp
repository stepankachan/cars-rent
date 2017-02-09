<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<title>Registration Confirmation Page</title>
    <%@include file="util/styles.jsp" %>
</head>
<body>
	<div class="generic-container">
		<%@include file="widgets/authheader.jsp" %>
		
		<div class="alert alert-success lead">
	    	${success}
		</div>
		
		<span class="well floatRight">
			Go to <a href="<c:url value='/list' />">Login to application</a>
		</span>
	</div>
    <%@include file="util/scripts.jsp" %>
</body>

</html>