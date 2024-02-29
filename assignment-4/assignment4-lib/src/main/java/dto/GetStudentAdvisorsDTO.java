package dto;

import dto.EntityDTO.EnrollmentDTO;
import dto.EntityDTO.TeacherDTO;

import java.io.Serializable;
import java.util.List;

public class GetStudentAdvisorsDTO implements Serializable {
    int matriculation;
    String studentFirstName;
    String studentLastName;
    List<TeacherDTO> teachers;

    public GetStudentAdvisorsDTO(int matriculation, String studentFirstName, String studentLastName, List<TeacherDTO> teachers) {
        this.matriculation = matriculation;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.teachers = teachers;
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

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }
}
