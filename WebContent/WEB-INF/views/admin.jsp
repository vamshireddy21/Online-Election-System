<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
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

footer {
	
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

hr {
	height: 5px;
	color: rgb(0, 0, 0);
}

.liststyle {
	list-style-type: none;
}

h1, h2 {
	font-family: 'Trocchi', serif;
	font-weight: bold;
	color: #6c7854;
	margin: 0;
	line-height: 52px;
}

a {
	font-weight: bold;
	color: #6c7854;
}
</style>



</head>
<body bgcolor="white">
	<nav> <a href="welcome">Welcome page</a> | 
	<a href="login?type=3">Log out</a>
	<a href="progress">Election Count</a>
		 </nav>
	|
	<h1>Admin</h1>
	<h2>
		<i>${confirmMessage}</i>
	</h2>
	<c:set var="size" value="${noOfCand}" />

	<h3>Pending Candidates Waiting for Approval</h3>


	<table>

		<tr>
			<th>Candidate Name</th>
			<th>Party</th>
			<th>Area</th>
			<th>Status</th>
			<th>Approve/Reject</th>
		</tr>
			<c:set var="noOfCand" value="${noOfCand }" />
		<%
			int candSize = Integer.valueOf("" + pageContext.getAttribute("noOfCand"));
		%>
		<%
			if (candSize == 0) {
		%>
		<tr>
			<th colspan="4">No Candidate Approvals Pending</th>
		</tr>
		<%
			}
		%>
		<c:forEach items="${candidates}" var="candidate">
			<tr>
				<td><c:out
						value="${candidate.firstName} ${candidate.lastName }" /></td>
				<td><c:out value="${candidate.party}  " /></td>
				<td><c:out
						value="${candidate.area.areaName} ${candidate.area.pin}" /></td>

				<c:set var="status" value="${candidate.status }" />
				<%
					int status = Integer.valueOf("" + pageContext.getAttribute("status"));
						String statusString = (status == 1) ? "Active" : "Inactive";
				%>
				<td><%=statusString%></td>
				<td><a href="candApproval?id=${candidate.id}">Approve/Reject</a></td>


			</tr>
		</c:forEach>
	</table>
</body>
</html>
