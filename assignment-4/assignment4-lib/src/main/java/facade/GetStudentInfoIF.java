package facade;

import dto.GetStudentInfoDTO;

public interface GetStudentInfoIF {
    GetStudentInfoDTO getStudentInfo(int matriculation);
}
