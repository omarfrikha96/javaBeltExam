<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TV Show Manager</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/mains.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
<div class="container text-center">
	<h1 class="text-primary">TV Shows Database</h1>
    <div class="row">
        <div class="col-md-6">
            <h1 class="mt-4">Register</h1>
            <form:form action="/register" method="post" modelAttribute="newUser" class="mt-4">

                <table class="table">
            <tbody>
            <tr>
                <td class="float-left">Name:</td>
                <td class="float-left">
                    <form:errors path="name" class="text-danger"/>
                    <form:input class="form-control" path="name"/>
                </td>
            </tr>
            <tr>
                <td class="float-left">Email:</td>
                <td class="float-left">
                    <form:errors path="email" class="text-danger"/>
                    <form:input class="form-control" path="email"/>
                </td>
            </tr>
            <tr>
                <td class="float-left">Password:</td>
                <td class="float-left">
                    <form:errors path="password" class="text-danger"/>
                    <form:input type="password" class="form-control" path="password"/>
                </td>
            </tr>
            <tr>
                <td class="float-left">Confirm PW:</td>
                <td class="float-left">
                    <form:errors path="confirm" class="text-danger"/>
                    <form:input type="password" class="form-control" path="confirm"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input class="btn btn-primary" type="submit" value="Register"/></td>
            </tr>
            </tbody>
                </table>
            </form:form>
        </div>
        <div class="col-md-6">
            <h1 class="mt-4">Log In</h1>
            <form:form action="/login" method="post" modelAttribute="newLogin" class="mt-4">

                <table class="table">
            <tbody>
            <tr>
                <td class="float-left">Email:</td>
                <td class="float-left">
                    <form:errors path="email" class="text-danger"/>
                    <form:input class="form-control" path="email"/>
                </td>
            </tr>
            <tr>
                <td class="float-left">Password:</td>
                <td class="float-left">
                    <form:errors path="password" class="text-danger"/>
                    <form:input type="password" class="form-control" path="password"/>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input class="btn btn-primary" type="submit" value="Login"/></td>
            </tr>
            </tbody>
                </table>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>