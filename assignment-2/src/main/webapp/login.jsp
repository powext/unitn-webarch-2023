<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1><%= "Login" %></h1>
    <form action="login" method="post">
        Username
        <input type="text" name="username" required/><br>

        Password
        <input type="password" name="password" required/><br>

        <input type="submit" value="submit">
        <a href="registration.jsp">Registration</a>
    </form>
</body>
</html>