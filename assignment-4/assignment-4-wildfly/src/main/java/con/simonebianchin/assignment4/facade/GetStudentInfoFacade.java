package con.simonebianchin.assignment4.facade;

import con.simonebianchin.assignment4.ejb.EnrollmentBean;
import con.simonebianchin.assignment4.ejb.StudentBean;
import con.simonebianchin.assignment4.entity.Enrollment;
import con.simonebianchin.assignment4.entity.Student;
import dto.EntityDTO.EnrollmentDTO;
import dto.GetStudentInfoDTO;
import facade.GetStudentInfoIF;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(GetStudentInfoIF.class)
public class GetStudentInfoFacade implements GetStudentInfoIF {
    @EJB
    StudentBean userBean;

    @EJB
    EnrollmentBean enrollmentBean;

    public GetStudentInfoDTO getStudentInfo(int matriculation) {
        Student student = userBean.getStudent(matriculation);
        if (student == null) return null;

        List<Enrollment> enrollments = enrollmentBean.getStudentEnrollment(student.getMatriculation());
        List<EnrollmentDTO> enrollmentDTOS = enrollments.stream()
                .map(Enrollment::toDTO)
                .collect(Collectors.toList());
        return new GetStudentInfoDTO(student.getMatriculation(), student.getFirstName(), student.getLastName(), enrollmentDTOS);
    }
}
