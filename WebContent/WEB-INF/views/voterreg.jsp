<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Voter Registration</title>

<style>
html {
	background-color: rgb(256, 256, 256);
}

body {
	background-color: rgb(232, 232, 232);
	margin: 10% 20% 5%;
	border: 2px solid black;
	font-family: "Times New Roman";
	position: relative;
}



h3 {
	background-color: rgb(112, 112, 112);
}



div {
	margin: 2px;
	display: block;
	position: relative;
}



table {
	margin-left: 50px;
	border: 1px solid black;
}

th {
	text-align: left;
	background-color: rgb(200, 200, 200);
	text-transform: uppercase;
}

table, th, tr, td {
	border: 1px solid black;
}

td {
	padding: 10px;
}


.center {
	text-align: center;
	font-weight: bold;
}



.liststyle {
	list-style-type: none;
}
h1,h2 {
	font-family: 'Trocchi', serif;
	font-weight: bold;
	color: #6c7854;
	margin: 0;
	line-height: 52px;
}
a
{
font-weight: bold;
	color: #6c7854;
}

</style>
</head>
<body>
<nav>
<a href="welcome">Welcome page</a> |
</nav>
	<h2>Voter Registration</h2>
	<h3>
				<i>${errorMessage}</i><br>
				<i>${successMessage}</i>
			</h3>
	<form:form method="get" action="addVoter"
		commandName="voter">

		<table>
			
			<tr>
				<td><form:label path="firstName">First Name</form:label></td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td><form:label path="lastName">Last Name</form:label></td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td><form:label path="gender">Gender</form:label></td>
				<td><form:select name="gender" path="gender">
						<form:option value="1">Male</form:option>
						<form:option value="2">Female</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="emailId">Email Id</form:label></td>
				<td><form:input path="emailId" /></td>
			</tr>
			<tr>
				<td><form:label path="mobileNo">Mobile No</form:label></td>
				<td><form:input path="mobileNo" /></td>
			</tr>
			<tr>
				<td><form:label path="fatherName">Father's Name</form:label></td>
				<td><form:input path="fatherName" /></td>
			</tr>
			<tr>
				<td><form:label path="address">Address</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
		<tr>
				<td><form:label path="areaId">Area</form:label></td>
				<td><form:select name="areaId" path="areaId">
						<c:forEach var="areaItem" items="${areaMap}">
							<form:option value="${areaItem.key}"
								>${areaItem.value}</form:option>
						</c:forEach>
				</form:select>
			</tr>
			<tr>
				<td><form:label path="credentials.userName">User Name</form:label></td>
				<td><form:input path="credentials.userName" /></td>
			</tr>
			<tr>
				<td><form:label path="credentials.password">Password</form:label></td>
				<td><form:input path="credentials.password" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form:form>
	
</body>
</html>
