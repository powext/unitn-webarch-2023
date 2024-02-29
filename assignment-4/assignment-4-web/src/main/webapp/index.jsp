<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Assignment 4 - Simone Bianchin</title>
</head>
<body>
<h1><%= "Assignment 4 - Simone Bianchin" %>
</h1>
<br/>
<h3>Get Student Info</h3>
Matriculation: <form method="GET" action="student-info"><input name="matriculation" type="number" /><input type="submit" value="Get"></form>

<h3>Get Student Advisors</h3>
Matriculation: <form method="GET" action="student-advisors"><input name="matriculation" type="number" /><input type="submit" value="Get"></form>
</body>
</html>