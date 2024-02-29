package con.simonebianchin.assignment4;

/*
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    @EJB
    GetStudentInfoFacade getStudentInfoFacade;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        GetStudentInfoDTO studentInfo = getStudentInfoFacade.getStudentInfo(1);
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        studentInfo.getEnrollments().forEach(enrollment -> {
            out.println("<h1>" + enrollment.getRating() + "</h1>");
        });
        out.println("</body></html>");
    }

    public void destroy() {
    }
}*/
