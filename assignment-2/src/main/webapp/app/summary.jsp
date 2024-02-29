<%@ page import="com.simone.bianchin.assignment2.controllers.MatchController" %>
<%@ page import="com.simone.bianchin.assignment2.SharedData" %>
<%@ page import="com.simone.bianchin.assignment2.model.Match" %>
<%@ page import="java.time.ZoneOffset" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="com.simone.bianchin.assignment2.model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String username = (String) session.getAttribute("username");
    User user = SharedData.getInstance().getUserController().getUser(username);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Summary</title>
    <script type="text/javascript">
    </script>
</head>
</head>
<body>
    <%@include file="../username-header.jsp" %>
    <p>Total points: <%= SharedData.getInstance().getMatchController().getUserPoints(username) %></p>
    <form method="get" action="match.jsp"><input type='submit' value='Play'></form>
    <% if(user.isAdmin()){
        %><a href="../app/admin.jsp">Admin Panel</a><%
    }%>
    <a href="../login.jsp">Login</a>
    <a href="../registration.jsp">Registration</a>
</body>
</html>