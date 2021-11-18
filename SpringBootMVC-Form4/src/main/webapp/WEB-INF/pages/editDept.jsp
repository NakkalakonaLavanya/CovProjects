<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <h1>Edit Department</h1>
	<form:form action="updateDept"  modelAttribute="empToEdit">
		<table>
			<tr>
				<form:label path="id">ID</form:label>
				<form:input path="id" readonly="true" />
				<br>
				<br>
				<form:label path="name">NAME</form:label>
				<form:input path="name" />
					<form:input type="text" name="name" />
				<br>
				<br>
				
				<input type="submit" value="Update"/>
			</tr>
		</table>
	</form:form>
</body>
</html>