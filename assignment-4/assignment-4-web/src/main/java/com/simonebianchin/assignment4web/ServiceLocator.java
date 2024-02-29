package com.simonebianchin.assignment4web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Properties;

public class ServiceLocator {
    public enum Service {
        GET_USER_INFO_EJB("ejb:/WildflyDemo-1.0-SNAPSHOT/GetStudentInfoFacade!facade.GetStudentInfoIF"),
        GET_USER_ADVISORS_EJB("ejb:/WildflyDemo-1.0-SNAPSHOT/GetStudentAdvisorsFacade!facade.GetStudentAdvisorsIF");

        private final String text;

        Service(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
    private static HashMap<String, Object> cache;

    static {
        cache = new HashMap<String, Object>();
    }

    public static Object getService(Service serviceUrl) throws NamingException {
        Properties p = new Properties();
        p.put(InitialContext.PROVIDER_URL, "http-remoting://localhost:8888");
        p.put(InitialContext.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        Object service = cache.get(serviceUrl);
        if (service == null) {
            InitialContext context = new InitialContext(p);
            service = (Object) context.lookup(serviceUrl.toString());
            cache.put(serviceUrl.toString(), service);
        }
        return service;
    }
}
