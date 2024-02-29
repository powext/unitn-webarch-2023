package com.simonebianchin.assignment4web;

import com.simonebianchin.assignment4web.bd.GetStudentInfoBD;
import dto.EntityDTO.EnrollmentDTO;
import dto.GetStudentInfoDTO;
import facade.GetStudentInfoIF;

import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "studentInfo", value = "/student-info")
public class GetStudentInfoServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        GetStudentInfoBD getStudentInfoBD = new GetStudentInfoBD();
        GetStudentInfoDTO studentInfoDTO = getStudentInfoBD.doTask(Integer.parseInt(request.getParameter("matriculation")));

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        if (studentInfoDTO == null) {
            out.println("No student with such matriculation found.");
            out.println("</body></html>");
            return;
        }

        out.println("<b>Name</b> "+studentInfoDTO.getStudentFirstName()+"<br />");
        out.println("<b>Surname</b> "+studentInfoDTO.getStudentLastName()+"<br />");
        out.println("<b>Matriculation</b> "+studentInfoDTO.getMatriculation()+"<br />");
        out.println("<b>Enrollments:</b>"+"<br />");
        for (EnrollmentDTO enrollment: studentInfoDTO.getEnrollments()) {
            out.println(""+enrollment.getCourseName() + " - Grade: "+enrollment.getRating()+"<br />");
        }
        out.println("</body></html>");
    }

    public void destroy() {
    }
}