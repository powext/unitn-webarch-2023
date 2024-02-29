<%@ page import="com.simone.bianchin.assignment2.controllers.MatchController" %>
<%@ page import="com.simone.bianchin.assignment2.SharedData" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.Instant" %>
<%@ page import="java.time.ZoneId" %>
<%@ page import="com.simone.bianchin.assignment2.model.Match" %>
<%@ page import="org.apache.commons.lang3.tuple.Pair" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Match</title>
</head>
<body>
<%@include file="../username-header.jsp" %>
<form action="${pageContext.request.contextPath}/app/matchValidation" method="post">
<%
    Match match = SharedData.getInstance().getMatchController().generateMatch((String) session.getAttribute("username"));
    int i = 0;
    for (Pair<String, String> country : match.getCountries()) {
        %><p><%=i+1 +". "+ country.getRight()%></p><%
        i++;
    }
    i = 0;
    for (Pair<String, String> country : match.getCountriesToGuess()) {%>
        <img src="${pageContext.request.contextPath}/<%="resources/"+country.getLeft()+".jpg"%>"  alt="nice try"/>
        <select required name="guess<%=i%>">
            <option disabled selected value> -- select an option -- </option>
        <%
        int j =1;
        for (Pair<String, String> item : match.getCountries()) {%>
            <option value="<%=item.getRight()%>"><%=j++ + ". "+item.getRight()%></option>
        <%}
        i++;
        %></select><br /><%
    }
%>
    <input type="hidden" name="localTime" value="<%=match.getLocalDate()%>">
    <input type="submit" >
</form>
</body>
</html>
