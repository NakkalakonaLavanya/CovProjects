
<%@page import="java.util.List"%>
<%@page import="com.cov.service.DepartmentService"%>
<%@page import="com.cov.beans.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="2">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Department Name</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
		List<Employee> emps = (List<Employee>) request.getAttribute("employees");
		for (Employee emp : emps) {
		%>
		<tr>
			<td><%=emp.getId()%></td>
			<td><%=emp.getName()%></td>
			<td><%=emp.getDepartment().getName()%></td>
			<td><a href="editEmp?id=<%=emp.getId()%>">Edit</a></td>
			<td><a href="deleteEmp?id=<%=emp.getId()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<br>
	<br>
	<br>
</body>
</html>

