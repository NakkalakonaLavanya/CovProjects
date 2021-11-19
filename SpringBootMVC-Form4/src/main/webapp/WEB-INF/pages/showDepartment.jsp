
<%@page import="java.util.List"%>
<%@page import="com.cov.beans.Department"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2 align="center">Employee Details</h2>
	<table border="3" bgcolor="pink">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Edit</th>
			
		</tr>
		<%
		List<Department> depts = (List<Department>) request.getAttribute("depts");
		for (Department dept : depts) {
		%>
		<tr>
			<td><%=dept.getId()%></td>
			<td><%=dept.getName()%></td>
			<td><a href="editDept?id=<%=dept.getId()%>">Edit</a></td>
			
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<br>
	<a href="regDept">Ragistraion department</a>
	<a href="/">Home</a>

</body>
</html>