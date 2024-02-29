package com.simone.bianchin.assignment2.filters;

import com.simone.bianchin.assignment2.SharedData;
import com.simone.bianchin.assignment2.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/app/admin.jsp")
public class AdminCheckFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        String username = (String) request.getSession().getAttribute("username");
        User user = SharedData.getInstance().getUserController().getUser(username);

        if (user != null && user.isAdmin()) {
            chain.doFilter(request, response);
            return;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
