package com.simonebianchin.assignment4web.bd;

import com.simonebianchin.assignment4web.ServiceLocator;
import dto.GetStudentAdvisorsDTO;
import dto.GetStudentInfoDTO;
import facade.GetStudentAdvisorsIF;
import facade.GetStudentInfoIF;

import javax.naming.NamingException;

public class GetStudentAdvisorsBD {
    private GetStudentAdvisorsIF lookupService = null;

    public GetStudentAdvisorsBD() {
        try {
            this.lookupService = (GetStudentAdvisorsIF) ServiceLocator.getService(ServiceLocator.Service.GET_USER_ADVISORS_EJB);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public GetStudentAdvisorsDTO doTask(int matriculation) {
        return this.lookupService.getStudentAdvisors(matriculation);
    }
}
