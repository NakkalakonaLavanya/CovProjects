<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DEPARTMENT</title>
</head>
<body>
	<h2>Department Registration</h2>
	<form:form action="regDept" method="post" modelAttribute="department">

		<form:label path="name">Name</form:label>
		<form:input path="name" />
		<br>
		<br>
		<input type="submit" value="SUBMIT">
	</form:form>

</body>
</html>
</body>
</html>