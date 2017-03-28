<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Candidate Registration</title>

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
	margin-bottom: 50px;
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
<a href="welcome">Welcome page</a> 
</nav>
	<h2>Candidate Registration</h2>
	<h3>
		<i>${errorMessage}</i><br> <i>${successMessage}</i>
	</h3>
	
	
	<form:form method="get" action="addCand" commandName="candidate"
		enctype="multipart/form-data">

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
				<td height="80px"><form:label path="address">Address</form:label></td>
				<td><form:textarea rows="4" path="address" /></td>
			</tr>
			<tr>
				<td height="100px"><form:label path="aboutCandidate">About You</form:label></td>
				<td><form:textarea rows="8" path="aboutCandidate" height="80px" /></td>
			</tr>

			<tr>
				<td height="100px"><form:label path="previousAchievements">Previous Achievements</form:label></td>
				<td><form:textarea rows="8" path="previousAchievements"
						height="80px" /></td>
			</tr>

			<tr>
				<td><form:label path="maritalStatus">Marital Status</form:label></td>
				<td><form:select path="maritalStatus">

						<form:option value="1">Married</form:option>
						<form:option value="2">Un-Married</form:option>
					</form:select></td>
			</tr>
			<tr>
				<td><form:label path="noOfChildren">No of Children</form:label></td>
				<td><form:input path="noOfChildren" /></td>
			</tr>
			<tr>
				<td><form:label path="party">Party Name</form:label></td>
				<td><form:select name="party" path="party">
						<c:forEach var="partyItem" items="${partyMap}">
							<form:option value="${partyItem.key}"
								>${partyItem.value}</form:option>
						</c:forEach>
				</form:select></td>
			</tr>
			<tr>
				<td><form:label path="profession">Profession</form:label></td>
				<td><form:input path="profession" /></td>
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
				<td><form:label path="iDCard">Upload any Valid Id Card</form:label></td>
				<td><form:input type="file" path="iDCard" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Register" /></td>
			</tr>
		</table>

	</form:form>

</body>
</html>
