package com.simone.bianchin.assignment2.servlets;

import com.simone.bianchin.assignment2.SharedData;
import com.simone.bianchin.assignment2.controllers.MatchController;
import com.simone.bianchin.assignment2.model.Match;
import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.checker.units.qual.A;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

@WebServlet(name = "matchValidation", value = "/app/matchValidation")
public class MatchValidationServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Login";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        MatchController matchController = SharedData.getInstance().getMatchController();
        Match match = matchController.getMatch(LocalDateTime.parse(request.getParameter("localTime")), username);
        int i = 0;
        int points = 0;
        for (Pair<String, String> result : match.getCountriesToGuess()) {
            String guess = request.getParameter("guess"+i);
            if (guess.equals(result.getRight())) points++;
            i++;
        }
        match.setPoints(points);

        response.sendRedirect(request.getServletContext().getContextPath()+"/app/summary.jsp");
    }

    public void destroy() {
    }
}