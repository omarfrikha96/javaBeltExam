<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/mains.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<title>Show Details</title>
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1>${show.title}</h1>
			<a href="/shows">Back to Dashboard</a>
		</div>

		<table style="width: 400px" class="table">
			<tbody>
				<tr>
					<td><h4>Posted by:</h4></td>
					<td><h4>${show.owner.name}</h4></td>
				</tr>

				<tr>
					<td>Network:</td>
					<td><c:out value="${show.network}"></c:out></td>
				</tr>

				<tr>
					<td>Description:</td>
					<td><c:out value="${show.description}"></c:out></td>
				</tr>
			</tbody>
		</table>

		<c:if test="${show.owner.id==userId}">
			<a href="/shows/${show.id}/edit" class="btn btn-secondary">Edit</a>
		</c:if>
		<hr>

		<table class="table table-striped table-bordered text-center">
			<thead>
				<tr class="table-secondary">
					<th>Name</th>
					<th>Rating</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="review" items="${reviews}">
					<tr>
						<td>${review.user.name}</td>
						<td>${review.rating}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<hr>
		<c:if test="${not empty error}">
			<div style="color: red;">${error}</div>
		</c:if>
		<form:form action="/shows/${show.id}/review" method="post"
			modelAttribute="review">
			<table>
				<tr>
					<td>Leave a rating:</td>
				</tr>
				<tr>
					<td><form:errors path="rating" class="text-danger" /></td>
				</tr>
				<tr>
					<td><form:input path="rating" type="number" /></td>
					<td><input class="input" type="submit" value="Rate !" /></td>
				</tr>


			</table>
		</form:form>
	</div>


</body>
</html>