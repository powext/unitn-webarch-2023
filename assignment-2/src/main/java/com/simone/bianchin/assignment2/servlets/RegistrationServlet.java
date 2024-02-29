package com.simone.bianchin.assignment2.servlets;

import com.google.common.base.Strings;
import com.simone.bianchin.assignment2.HashGen;
import com.simone.bianchin.assignment2.SharedData;
import com.simone.bianchin.assignment2.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {

    public void init() {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if (Strings.isNullOrEmpty(request.getParameter("username"))
        || Strings.isNullOrEmpty(request.getParameter("password"))) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user parameter.");
            return;
        }

        User user = User.create(request.getParameter("username"), HashGen.SHA1(request.getParameter("password")));
        user = SharedData.getInstance().getUserController().registerUser(user);
        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User already registered.");
            return;
        }
        response.sendRedirect("login.jsp");
    }

    public void destroy() {
    }
}