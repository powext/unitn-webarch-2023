package com.simone.bianchin.assignment2.servlets;

import com.simone.bianchin.assignment2.SharedData;
import com.simone.bianchin.assignment2.controllers.MatchController;
import com.simone.bianchin.assignment2.model.Match;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@WebServlet(name = "summeryServlet", value = "/app/summary_old")
public class SummaryServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Login";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        MatchController matchController = SharedData.getInstance().getMatchController();
        Match match = matchController.generateMatch(username);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + username + "</h1>");
        out.println("<p> Punti " + matchController.getUserPoints(username) + "</p>");
        out.println("<input type='button' value='Play'>");
        request.getRequestDispatcher("WEB-INFO/summary.jsp");
    }

    public void destroy() {
    }
}