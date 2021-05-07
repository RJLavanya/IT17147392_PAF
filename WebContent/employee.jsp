<%@page import="com.employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script type="text/javascript" src="Components/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Components/employee.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-7">
				<h1 class="m-3">Employee Management</h1>

				<form id="formEmployee" name="formEmployee" method="post"
					action="employee.jsp">

					Employee Name: <input id="employeeName" name="employeeName"
						type="text" class="form-control form-control-sm"> <br>
					Employee Address: <input id="employeeAddress"
						name="employeeAddress" type="text"
						class="form-control form-control-sm"> <br> Employee
					Contact: <input id="employeeContact" name="employeeContact"
						type="text" class="form-control form-control-sm"> <br>
					Employee Date of Birth: <input id="employeeDob" name="employeeDob"
						type="date" value="yyyy-mm-dd" class="form-control form-control-sm"><br>
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidEmployeeIDSave" name="hidEmployeeIDSave" value="">
				</form>
<br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

			</div>
		</div>




		<br>
		<div id="divEmployeeGrid">

			<%
			employee employeeObj = new employee();
			out.print(employeeObj.readEmployee());
			%>
		</div>
</body>
</html>