package dto;

import dto.EntityDTO.EnrollmentDTO;

import java.io.Serializable;
import java.util.List;

public class GetStudentInfoDTO implements Serializable {
    int matriculation;
    String studentFirstName;
    String studentLastName;
    List<EnrollmentDTO> enrollments;

    public GetStudentInfoDTO(int matriculation, String studentFirstName, String studentLastName, List<EnrollmentDTO> enrollments) {
        this.matriculation = matriculation;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.enrollments = enrollments;
    }

    public int getMatriculation() {
        return matriculation;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public List<EnrollmentDTO> getEnrollments() {
        return enrollments;
    }
}
