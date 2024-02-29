package com.simonebianchin.assignment4web.bd;

import com.simonebianchin.assignment4web.ServiceLocator;
import dto.GetStudentInfoDTO;
import facade.GetStudentAdvisorsIF;
import facade.GetStudentInfoIF;

import javax.naming.NamingException;

public class GetStudentInfoBD {
    private GetStudentInfoIF lookupService = null;

    public GetStudentInfoBD() {
        try {
            this.lookupService = (GetStudentInfoIF) ServiceLocator.getService(ServiceLocator.Service.GET_USER_INFO_EJB);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public GetStudentInfoDTO doTask(int matriculation) {
        return this.lookupService.getStudentInfo(matriculation);
    }
}
