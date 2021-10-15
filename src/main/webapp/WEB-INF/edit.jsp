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

<h1>Edit a Course</h1>


	<form:form action="/courses/${course.id}/update" method="put" modelAttribute="course">
		<p>
			<form:hidden path="instructor" value="${course.instructor.id}"/>
			<form:label path="name">Name:</form:label>
			<form:input type="text" path="name" />
			<form:errors path="name" class="text-danger"/>
		</p>
  			

		<p>
			<form:label path="dayOfWeek">Day of Week:</form:label>
			<form:input type="text" path="dayOfWeek" />
			<form:errors path="dayOfWeek" class="text-danger"/>
		</p>	
			
		
		<p>
			<form:label path="price">Drop-in Price:</form:label>
			<form:input type="float" path="price" min="0" max="1000" steps="1.0"/>
			<form:errors path="price" class="text-danger"/>
			
		</p>	
		
		
		<p>
			<form:label path="time">Time:</form:label>
			<form:input type="time" path="time" value="${localtime}"/>
			<form:errors path="time" class="text-danger"/>
			
		</p>	
		<p>
			<form:label path="description">Description:</form:label>
			<form:textarea path="description" rows = "4" cols = "30"/>
			<form:errors path="description" class="text-danger"/>
		</p>
		
			<button class="btn btn-success">Submit</button>
	</form:form>
	<br/>
	
		<form action="/courses/${ course.id }/delete" method="post">
						    <input type="hidden" name="_method" value="delete">
						    <input class="btn btn-danger" type="submit" value="Delete">
		</form><br/>
	<a href="/home"><button class="btn btn-secondary">Cancel</button></a>

</body>
</html>