<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Voter Page</title>

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
</style>
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

.dottedborder {
	border: 1px dotted black;
}

h3 {
	background-color: rgb(112, 112, 112);
}

header {
	height: 138px;
	margin: 2px;
	padding: 2px;
	display: block;
	postion: relative;
}

footer {
	
}

div {
	margin: 2px;
	display: block;
	position: relative;
}

.left {
	float: left;
}

.right {
	float: right;
}

.righttopcontent {
	
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

.sideheading {
	text-transform: uppercase;
	font-weight: bold;
	float: bottom;
}

.center {
	text-align: center;
	font-weight: bold;
}

hr {
	height: 5px;
	color: rgb(0, 0, 0);
}

.liststyle {
	list-style-type: none;
}

.subsubhead {
	text-transform: uppercase;
	display: inline-block;
	width: 300px;
	font-weight: 600;
}

.mainheading {
	text-transform: uppercase;
	font-size: x-large;
	text-align: center;
}
</style>





</head>
<body bgcolor="white">
	<nav> <a href="welcome">Welcome page</a> | <a href="login?type=1">Log
		out</a> </nav>
	|
	<h1>Voter Page</h1>
	<h2>
		Name : ${voter.firstName}&nbsp; ${voter.lastName}<br> <i>${errorMessage}</i>
	</h2>
	<h3>
		Things to Remember for Election<br> User Id: ${voter.userId}<br>
		Pass: ${voter.pass }
	</h3>

	<h3>List of Candidates in your Area - ${voter.area.areaName} |
		${voter.area.pin }</h3>
	<table>

		<tr>
			<th>Candidate Name</th>
			<th>Gender</th>
			<th>Party</th>
			<th>Area</th>
			<th>Vote here</th>
		</tr>
		<c:set var="noOfCand" value="${noOfCand }" />
		<%
			int candSize = Integer.valueOf("" + pageContext.getAttribute("noOfCand"));
		%>
		<%
			if (candSize == 0) {
		%>
		<tr>
		<th colspan="5">
		No Candidates Found
		</th>
		</tr>
		<%
			}
		%>

		<c:forEach items="${candidates}" var="candidate">
			<tr>

				<td><c:out
						value="${candidate.firstName} ${candidate.lastName }" /></td>

				<c:set var="gender" value="${candidate.gender }" />
				<%
					int gender = (int) pageContext.getAttribute("gender");
						String genderString = gender == 1 ? "Male" : "Female";
				%>


				<td><%=genderString%></td>

				<td><c:out value="${candidate.party}  " /></td>
				<td><c:out
						value="${candidate.area.areaName} ( ${candidate.area.pin})" /></td>
				<td><a href="vote?vid=${voter.id}&cid=${candidate.id}">Vote</a></td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>
