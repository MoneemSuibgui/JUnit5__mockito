<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login_Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container mt-5">
		<div class="d-flex justify-content-between">
			<h1 class="mb-3">
				<c:out value="${book.title }" />
			</h1>
			<a href="/books">back to the shelves</a>
		</div>
		<c:if test="${book.creator.id == user_id }">
			<h3>
				<span class="text-danger"><c:out
						value="${book.creator.getName()}" /></span> read <span
					class="text-primary"><c:out value="${book.title }" /></span> by <span
					class="text-success"><c:out value="${book.author}" /></span>.
			</h3>
		</c:if>

		<h4>
			Here are <span><c:out value="${book.author}" /></span>'s thoughts:
		</h4>
		<hr>
		<p>
			<c:out value="${book.thoughts}"></c:out>
		</p>

		<hr>
		<c:if test="${book.creator.id == user_id }">
			<a href="/edit/books/${book.id }" class="btn btn-success">Edit</a>
			<a href="/delete/books/${book.id }" class="btn btn-danger">delete</a>
		</c:if>

	</div>
</body>
</html>