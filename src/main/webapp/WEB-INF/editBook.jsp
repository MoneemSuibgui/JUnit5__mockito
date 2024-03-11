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
<title>Book Share</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<%-- adding some styling using bootstrap library --%>
	<div class="container mt-5">
		<div class="col-8 bg-info border border-3 p-4">
			<div class="d-flex justify-content-between ">
				<h2 class="mx-5 mb-3">Change your Entry</h2>
				<a href="/books">Back to the shelves</a>
			</div>

			<form:form action="/update/book/${updatedBook.id}" method="put"
				modelAttribute="updatedBook">


				<form:label path="title">Title : </form:label>
				<p>
					<form:errors path="title" class="text-danger" />
				</p>
				<form:input path="title" class="form-control mb-3" />

				<form:label path="author">Author : </form:label>
				<p>
					<form:errors path="author" class="text-danger" />
				</p>
				<form:input path="author" class="form-control mb-3" />

				<form:label path="thoughts">My thoughts : </form:label>
				<p>
					<form:errors path="thoughts" class="text-danger" />
				</p>
				<form:textarea path="thoughts" class="form-control mb-3" />

				<button class="btn btn-primary btn-lg mt-3">Submit</button>
			</form:form>

		</div>
	</div>
</body>
</html>