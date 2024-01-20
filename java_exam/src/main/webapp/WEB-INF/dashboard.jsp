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
<title>TV Show Dashbord</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/mains.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container">
		<div class="d-flex justify-content-between align-items-center">
			<h1>Welcome, ${user.name}</h1>
			<a href="/logout" class="btn btn-danger">Logout</a>
		</div>

		<h4 class="mt-4">Tv Shows</h4>
		<table class="table table-striped table-bordered">
			<thead>
				<tr class="table-secondary">
					<th class="text-center">Show</th>
					<th class="text-center">Network</th>
					<th class="text-center">Average Rating</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="show" items="${shows}">
					<tr>
						<td class="text-center"><a href="/shows/${show.id}" class="text-decoration-none">${show.title}</a></td>
						<td class="text-center"><c:out value="${show.network}"></c:out></td>
						<td class="text-center">
							${show.rating}
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="/shows/new" class="btn btn-primary">Add a Show</a>
	</div>
</body>
</html>