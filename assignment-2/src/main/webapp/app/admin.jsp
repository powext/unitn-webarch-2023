<%@ page import="com.simone.bianchin.assignment2.SharedData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.simone.bianchin.assignment2.model.User" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String username = (String) session.getAttribute("username");
    ArrayList<User> users = SharedData.getInstance().getUserController().getUsers();
    int[] points = new int[users.size()];
    for (int i = 0; i < users.size(); i++) {
        points[i] = SharedData.getInstance().getMatchController().getUserPoints(users.get(i).getUsername());
    }
    Map<String, Boolean> activeUsers = SharedData.getInstance().getSessionsController().getActiveUsers();
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
    <h3>Users points</h3>
    <%for (int i = 0; i < users.size(); i++) {%>
        <p><%=users.get(i).getUsername() + " points: " + points[i] + " " + (activeUsers.get(users.get(i).getUsername()) != null ? "Currently active" : "") %></p>
    <%}%>
    <form method="get" action="match.jsp"><input type='submit' value='Play'></form>
    <a href="../login.jsp">Login Servlet</a>
    <a href="../registration.jsp">Registration Servlet</a>
</body>
</html>