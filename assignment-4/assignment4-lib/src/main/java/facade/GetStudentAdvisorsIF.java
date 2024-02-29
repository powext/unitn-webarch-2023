package facade;

import dto.GetStudentAdvisorsDTO;

public interface GetStudentAdvisorsIF {
    GetStudentAdvisorsDTO getStudentAdvisors(int matriculation);
}
