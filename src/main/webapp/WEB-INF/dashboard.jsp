
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) --> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- Functions --> 
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <!-- for Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/css/style.css"/>
</head>
<body style="padding:150px;">
<div class="d-flex justify-content-between">
<div><h1>Welcome, <c:out value="${user.userName }"/></h1><br/>
<h5>Class schedule</h5>
</div>
<div>
<a href="/logout">Logout</a><br/>

</div>
</div>

			<table class="table border border-black">
				<thead>
					<tr>
						<th>Class Name</th>
						<th>Instructor</th>
						<th>Weekday</th>
						<th>Price</th>
						<th>Time</th>
					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ courses }" var="course">
						<tr>
							
							<td>
							<c:choose>
							<c:when test="${ user.id == course.instructor.id}"><a href="/courses/${ course.id }"><c:out value="${ course.name }"/></a>
							 &nbsp;&nbsp;<a class="btn btn-warning" href="/courses/${ course.id }/edit">Edit</a>
							</c:when>
							<c:otherwise>
							<c:out value="${ course.name }"/>
							</c:otherwise>
							</c:choose>
							</td>
							<td><c:out value="${ course.instructor.userName }"/></td>
							<td><c:out value="${ course.dayOfWeek }"/></td>
							<td>	
							<c:out value="${ course.price }"/>
							</td>
							<td>	
							<c:out value="${ course.time }"/>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

<a href="/courses/new">+ New Class</a>
</body>
</html>