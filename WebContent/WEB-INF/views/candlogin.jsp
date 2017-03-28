<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Candidate Login</title>
<style>
html
{
background-color:rgb(256,256,256);
}
body
{
background-color:rgb(232,232,232);
margin:10% 20%;
border:2px solid black;
font-family:"Times New Roman";
position:relative;
}

h3
{
background-color:rgb(112,112,112);
}

div
{
margin:2px;
display:block;
position:relative;
}

table
{
margin-left:50px;
border:1px solid black;
}
th
{
text-align:left;
background-color:rgb(200,200,200);
text-transform:uppercase;
}
table,th,tr,td
{
border:1px solid black;
}
td { 
    padding: 10px;
} 

.center
{
text-align:center;
font-weight:bold;
}

.liststyle
{
list-style-type:none;
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
	<h2>Candidate Login</h2>
	<h3>
				<i>${errorMessage}</i><br>
				<i>${successMessage}</i>
			</h3>
	<form:form method="post" action="candLogin" commandName="credentials">

		<table>
			
			<tr>
				<td><form:label path="userName">User Name</form:label></td>
				<td><form:input path="userName" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Login" /></td>
			</tr>
			<tr>
			<td><a href="reg?type=2" >Not a Candidate yet! Sign up? </a></td>
			</tr>
		</table>

	</form:form>
</body>
</html>
