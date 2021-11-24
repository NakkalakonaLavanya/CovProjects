<%@page import="com.cov.service.EmployeeService"%>
<%@page import="ch.qos.logback.core.net.SyslogOutputStream"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="com.cov.beans.Employee"%>
<%@page import="com.cov.beans.Department"%>
<%@page import="com.cov.service.DepartmentService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees List</title>
</head>
<body>
	<form:form modelAttribute="department" method="GET">
		<form:label path="id">Choose a Department</form:label>
		<form:select path="id">
			<form:option value="0">--Select--</form:option>
			<%
			DepartmentService departmentService = (DepartmentService) request.getAttribute("departmentService");
			List<Department> depts = departmentService.findAll();
			for (Department department : depts) {
			%>



			<form:option value="<%=department.getId()%>"><%=department.getName()%></form:option>
			<%
			}
			%>
		</form:select>
		<input type="submit" value="Filter">
	</form:form>



	<h2>Employee Details</h2>

    <table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Department</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>

		<%
		if (request.getParameter("id") == null) {
		%>

		<%
		List<Employee> emps = (List<Employee>) request.getAttribute("emps");
		for (Employee employee : emps) {
		%>
		<tr>
			<td><%=employee.getId()%></td>
			<td><%=employee.getName()%></td>
			<td><%=employee.getDepartment().getName()%></td>
			<td><a href="editEmp?id=<%=employee.getId()%>">Edit</a></td>
			<td><a href="deleteEmp?id=<%=employee.getId()%>">Delete</a></td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else {
	%>
	<%
	int id = Integer.parseInt(request.getParameter("id"));
	DepartmentService departmentService = (DepartmentService) request.getAttribute("departmentService");
	Department department = departmentService.findById(id);
	EmployeeService employeeService = (EmployeeService) request.getAttribute("employeeService");
	%>
	<br>
	<%
	List<Employee> emps = employeeService.findAllByDeptno(id);
	for (Employee employee : emps) {
	%>
	<tr>
		<td><%=employee.getId()%></td>
		<td><%=employee.getName()%></td>
		<td><%=employee.getDepartment().getName()%></td>
		<td><a href="editEmp?id=<%=employee.getId()%>">Edit</a></td>
		<td><a href="deleteEmp?id=<%=employee.getId()%>">Delete</a></td>
	</tr>
	<%
	}
	%>
	</table>
	<%
	}
	%>
   <br>
	<br>
	<a href="/">Home</a>
	<br>
	<br>
	<a href="regemp">Register Employees</a>
	<br>
	<br>
</body>
</html>

