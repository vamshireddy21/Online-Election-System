<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>eElection System</title>
<link rel="stylesheet" type="text/css">
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

.
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
	position: relative;
}

.liststyle {
	list-style-type: none;
}

li {
	border-radius: 2px;
	border: solid 1px #ccc;
	padding: 0.4em;
	background-color: #f5f5f5;
	box-shadow: inset 2 3px 3px rgba(0, 0, 5, 0.2);
	width: 70%;
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

.divstyle {
	margin:0%;
	margin-left:10%;
	padding: 10% 10% 10% 10%;
	position: relative;
	font-size: x-large;
	text-align: center;
}
</style>
</head>

<body style="border: 1px" class="center">

	<h1>Election System</h1>
	<h2>
		<i>${errorMessage}</i><br> <i>${successMessage}</i>
	</h2>
	<div class="divstyle">
		<ul class="liststyle">
			<li><a href="login?type=1">Enter as Voter</a></li>
			<li><a href="login?type=2">Enter as Candidate</a></li>
			<li><a href="login?type=3">Enter as Admin</a></li>
		</ul>


	</div>
</body>
</html>