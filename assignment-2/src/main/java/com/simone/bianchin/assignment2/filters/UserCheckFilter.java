package com.simone.bianchin.assignment2.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/app/*")
public class UserCheckFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();

        Object isLogged = session.getAttribute("logged");
        if (isLogged != null && (boolean) isLogged) {
            chain.doFilter(request, response);
            return;
        }
        response.sendRedirect("../login.jsp");
    }
}
