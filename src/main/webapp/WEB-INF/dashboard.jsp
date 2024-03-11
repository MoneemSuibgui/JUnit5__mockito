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
<title>Read Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container m-5">
		<div class="d-flex justify-content-between">
			<h1>
				Welcome,
				<c:out value="${loggedUser.name}" />
			</h1>
			<div>
				<a href="/bookmarket" class="btn btn-success btn-md"> Book
					Market</a> <a href="/logout" class="btn btn-danger btn-md">Logout</a>
			</div>

		</div>

		<div class="d-flex justify-content-between">
			<p>Books from everyone's shelves:</p>
			<a href="/books/new">+ Add a to my shelf! </a>
		</div>

		<table
			class="table table-hover table-bordered table-striped mt-5 text-center">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<td><c:out value="${book.id}" /></td>
						<td><a href="/books/${book.id}"><c:out
									value="${book.title}" /></a></td>
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.creator.getName()}" /></td>
					</tr>
				</c:forEach>

			</tbody>

		</table>

	</div>

</body>
</html>