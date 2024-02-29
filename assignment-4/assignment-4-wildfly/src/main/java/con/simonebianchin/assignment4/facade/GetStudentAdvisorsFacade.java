package con.simonebianchin.assignment4.facade;

import con.simonebianchin.assignment4.ejb.EnrollmentBean;
import con.simonebianchin.assignment4.ejb.StudentBean;
import con.simonebianchin.assignment4.entity.Enrollment;
import con.simonebianchin.assignment4.entity.Student;
import dto.EntityDTO.TeacherDTO;
import dto.GetStudentAdvisorsDTO;
import facade.GetStudentAdvisorsIF;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Remote(GetStudentAdvisorsIF.class)
public class GetStudentAdvisorsFacade implements GetStudentAdvisorsIF {
    @EJB
    StudentBean userBean;

    @EJB
    EnrollmentBean enrollmentBean;

    public GetStudentAdvisorsDTO getStudentAdvisors(int matriculation) {
        Student student = userBean.getStudent(matriculation);
        if (student == null) return null;

        List<Enrollment> enrollments = enrollmentBean.getStudentEnrollment(student.getMatriculation());
        List<TeacherDTO> teachersDTO = new ArrayList<>();
        enrollments.forEach(enrollment -> teachersDTO.add(enrollment.getCourse().getTeacher().toTDO()));
        return new GetStudentAdvisorsDTO(student.getMatriculation(), student.getFirstName(), student.getLastName(), teachersDTO);
    }
}
