<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mains.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->

<title>Edit a task</title>
</head>
<body>

	<div class="container">
		<h1>${show.title}</h1>


		<form:form action="/shows/edit/${show.id}" method="post"
			modelAttribute="show">

			<table>
				<thead>
					<tr>
						<td class="float-left">Title:</td>
						<td class="float-left"><form:errors path="title"
								class="text-danger" /> <br> <form:input class="input"
								path="title" value="${show.title}" /></td>
					</tr>
					<tr>
						<td class="float-left">Network:</td>
						<td class="float-left"><form:errors path="network"
								class="text-danger" /><br> <form:input class="input"
								path="network" value="${show.network}" /></td>
					</tr>
					<tr>
						<td class="float-left">Description:</td>
						<td class="float-left"><form:errors path="description"
								class="text-danger" /><br> <form:textarea rows="4"
								class="input" path="description" value="${show.description}" /></td>
					</tr>
					<tr>
						<td><a  class="btn btn-primary" href="/shows">Cancel</a></td>
						<br>
						<td><input type="submit" class="btn btn-success" value="Submit" /></td>
					</tr>
				</thead>
			</table>
		</form:form>
		<form action="/shows/delete/${show.id}" method="post">
			<input type="hidden" name="_method" value="delete"> <input
				type="submit" value="Delete Show" class="btn btn-danger">
		</form>
	</div>
</body>
</html>