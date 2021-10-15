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
<body style="padding:150px">

<div class="d-flex justify-content-between">
<h1><c:out value="${ course.name }"/> with <c:out value="${ course.instructor.userName }"/></h1>
</br>
</div>

<br/>
<h2>Day: <c:out value="${ course.dayOfWeek }"/></h2>
<h2>Cost: $<c:out value="${ course.price }"/></h2>
<h2>Time: <c:out value="${ course.time }"/></h2>
<br/>
<br/>

<c:out value="${ course.description }"/>
<br/>
<hr/>

<h1>Students</h1>
<c:forEach items="${course.students }" var="student">
	<li><c:out value="${ student.name }"/> - <c:out value="${ student.email }"/></li>
</c:forEach>
<br/>

<h1>Add students to Class</h1>

<div class="d-flex justify-content-around">
<div><h3>New student</h3>
<form:form action="/students/create" method="post" modelAttribute="student">
		<p>
			<form:label path="name">Name:</form:label>
			<form:input type="text" path="name" />
			<form:errors path="name" class="text-danger"/>
		</p>
		<p>
			<form:label path="email">Email:</form:label>
			<form:input type="text" path="email" />
			<form:errors path="email" class="text-danger"/>
		</p>
		<button class="btn btn-success">Create</button>
</form:form>
</div>

<div><h3>Existing student</h3>
<form action="/courses/${course.id}/addStudent" method="post">
			<select name="studentId" id="">
				<c:forEach items="${allStudents}" var="student">
					<option value="${student.id}"><c:out value="${student.name}"/>- <c:out value="${student.email}"/></option>	
				</c:forEach>
			</select>
			<div><button class="btn btn-danger mt-3">Add Student</button></div>
</form>
</div>
</div>
<a href="/courses/${course.id}/edit">Edit</a>
</body>
</html>