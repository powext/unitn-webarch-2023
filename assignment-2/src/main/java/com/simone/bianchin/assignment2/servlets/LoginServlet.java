package com.simone.bianchin.assignment2.servlets;

import com.google.common.base.Strings;
import com.simone.bianchin.assignment2.HashGen;
import com.simone.bianchin.assignment2.SharedData;
import com.simone.bianchin.assignment2.model.User;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Login";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        if (Strings.isNullOrEmpty(request.getParameter("username"))
                || Strings.isNullOrEmpty(request.getParameter("password"))) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing user parameter.");
            return;
        }


        User user = SharedData.getInstance().getUserController().loginUser(
                request.getParameter("username"),
                HashGen.SHA1(request.getParameter("password"))
        );
        if (user == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials.");
            return;
        }

        HttpSession session = request.getSession();
        session.setAttribute("logged", true);
        session.setAttribute("username", user.getUsername());
        if (user.isAdmin())
            response.sendRedirect("app/admin.jsp");
        else
            response.sendRedirect("app/summary.jsp");
    }

    public void destroy() {
        SharedData.getInstance().destroy();
    }
}