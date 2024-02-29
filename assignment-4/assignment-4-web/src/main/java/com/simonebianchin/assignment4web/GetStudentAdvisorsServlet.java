package com.simonebianchin.assignment4web;

import com.simonebianchin.assignment4web.bd.GetStudentAdvisorsBD;
import com.simonebianchin.assignment4web.bd.GetStudentInfoBD;
import dto.EntityDTO.TeacherDTO;
import dto.GetStudentAdvisorsDTO;
import dto.GetStudentInfoDTO;
import facade.GetStudentAdvisorsIF;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "studentAdvisors", value = "/student-advisors")
public class GetStudentAdvisorsServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        GetStudentAdvisorsBD getStudentAdvisorsBD = new GetStudentAdvisorsBD();
        GetStudentAdvisorsDTO studentAdvisorsDTO = getStudentAdvisorsBD.doTask(Integer.parseInt(request.getParameter("matriculation")));

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (studentAdvisorsDTO == null) {
            out.println("No student with such matriculation found.");
            out.println("</body></html>");
            return;
        }

        out.println("<b>Name</b> "+studentAdvisorsDTO.getStudentFirstName()+"<br />");
        out.println("<b>Surname</b> "+studentAdvisorsDTO.getStudentLastName()+"<br />");
        out.println("<b>Matriculation</b> "+studentAdvisorsDTO.getMatriculation()+"<br />");
        out.println("<b>Advisors:</b>"+"<br />");
        for (TeacherDTO teacher: studentAdvisorsDTO.getTeachers()) {
            out.println(""+teacher.getFirstName()+" "+teacher.getLastName()+"<br />");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}