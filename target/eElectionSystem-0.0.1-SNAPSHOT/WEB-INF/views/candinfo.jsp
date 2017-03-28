<%@page import="com.rakmo.ees.service.EESServiceImpl"%>
<%@page import="com.rakmo.ees.service.EESService"%>
<%@page import="com.rakmo.ees.entity.Candidate"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>candidate Information Page</title>
<style>
html {
	background-color: rgb(256, 256, 256);
}

body {
	background-color: rgb(232, 232, 232);
	margin: 10% 20%;
	border: 2px solid black;
	font-family: "Times New Roman";
	position: relative;
}

h3 {
	background-color: rgb(112, 112, 112);
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
<a href="welcome">Welcome page</a>
</nav>|
	<h1>Candidate Information</h1>
	<h2>
		<i>${errorMessage}</i>
	</h2>
	<div style="left">
<table>
	<tr>
		<td>First Name</td>
		<td>${candidate.firstName}</td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td>${candidate.lastName}</td>
	</tr>

	<c:set var="gender" value="${candidate.gender }" />
	<%
		int gender = Integer.valueOf(""+pageContext.getAttribute("gender"));
		String genderString = gender == 1 ? "Male" : "Female";
	%>
	<tr>
		<td>Gender</td>
		<td><%=genderString%></td>
	</tr>
	<tr>
		<td>Email Id</td>
		<td>${candidate.emailId}</td>
	</tr>
	<tr>
		<td>Mobile No</td>
		<td>${candidate.mobileNo}</td>
	</tr>
	<tr>
		<td>Father's Name</td>
		<td>${candidate.fatherName}</td>
	</tr>
	<tr>
		<td>Address</td>
		<td>${candidate.address}</td>
	</tr>
	<tr>
		<td>About You</td>
		<td>${candidate.aboutCandidate}</td>
	</tr>
	<tr>
		<td>Party</td>
		<td>${candidate.party}</td>
	</tr>
	<tr>
		<td>Marital Status</td>
		<td>${candidate.maritalStatus}
	</tr>
	<tr>
		<td>No of Children</td>
		<td>${candidate.noOfChildren}</td>
	</tr>
	<tr>
		<td>Party Name</td>
		<td>${candidate.party}</td>
	</tr>
	<tr>
		<td>Profession</td>
		<td>${candidate.profession}</td>
	</tr>
	<tr>
		<td>Area</td>
		<td>${candidate.area.areaName} | ${candidate.area.pin} </td>
	</tr>
	<c:set var="status" value="${candidate.status }" />
	<%
		int status = Integer.valueOf(""+pageContext.getAttribute("status"));
		String statusString = (status == 1) ? "Active" : "Inactive";
	%>
	<tr>
	<td>Status</td>
	<td><%=statusString%></td>
	</tr>
	<%
		if (status == 1) {
	%>
	<tr>
		<td colspan="2">Below two Fields are important to contest in Election</td>
	</tr>
	<tr>
		<td>User Id</td>
		<td>${candidate.userId}</td>
	</tr>
	<tr>
		<td>Pass</td>
		<td>${candidate.pass}</td>
	</tr>
	<%
		}
	
	%>
	</table>
	</div>
	
	
	<div style="float:right">
	<img src="${imageText}" width="100%" height="100%"/> 
	</div>


</body>
</html>
