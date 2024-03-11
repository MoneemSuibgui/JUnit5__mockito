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
<!-- adding some bootstrap styling -->
</head>
<body>
	<div class="container mt-5">
		<div class="d-flex justify-content-between ">
			<h5>
				Hello,
				<c:out value="${user.getName()}" />
				. Welcome to..
			</h5>
			<a href="/books">back to the shelves</a>
		</div>
		<h1 class="mb-4 mt-2">The Book Broker!</h1>
		<h5>Available Books to Borrow</h5>
		<table
			class="table table-stripped table-bordered table-hovered text-center border border-3 border-dark mb-5 bg-danger">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Owner</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="book" items="${books}">
					<%-- Condition to check & Display all books dosen't borrowed by the logged in user  --%>
					<c:if test="${book.getBorrwed().getId() != user.id}">
						<tr>
							<td><c:out value="${book.getId()}" /></td>
							<td><c:out value="${book.getTitle()}" /></td>
							<td><c:out value="${book.getAuthor()}" /></td>
							<td><c:out value="${book.creator.getName()}" /></td>
							<td><c:choose>
									<c:when test="${book.creator.getId() == user.getId() }">
										<a href="/edit/books/${book.id}"
											class="btn btn-secondary mx-2">edit</a>
										<a href="/delete/books/${book.id }" class="btn btn-danger">delete</a>
									</c:when>
									<c:otherwise>
										<a href="/borrow/books/${book.id}">borrow</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:if>

				</c:forEach>

			</tbody>
		</table>
		<h5>Books I'am Borrowing..</h5>
		<table
			class="table table-stripped table-bordered table-hovered border border-3 border-dark mb-5 bg-warning text-center">
			<thead>
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Owner</th>
					<th>Actions</th>

				</tr>
			</thead>
			<tbody>


				<c:forEach var="borrowedBook" items="${books}">
					<c:if test="${borrowedBook.getBorrwed().getId() == user.id }">
						<tr>
							<th><c:out value="${borrowedBook.getId()}" /></th>
							<th><c:out value="${borrowedBook.getTitle()}" /></th>
							<th><c:out value="${borrowedBook.getAuthor()}" /></th>
							<th><c:out value="${borrowedBook.getCreator().getName()}" /></th>
							<th><a
								href="/return/books/${borrowedBook.getId()}/${user.getId()}">return</a></th>
						</tr>
					</c:if>


				</c:forEach>



			</tbody>
		</table>



	</div>


</body>
</html>