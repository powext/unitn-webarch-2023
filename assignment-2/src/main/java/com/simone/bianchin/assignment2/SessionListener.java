package com.simone.bianchin.assignment2;

import com.google.common.base.Strings;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    public SessionListener() {
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = (String) se.getSession().getAttribute("username");
        if (!Strings.isNullOrEmpty(username))
            SharedData.getInstance().getSessionsController().getActiveUsers().remove("username");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        String username = (String) sbe.getSession().getAttribute("username");
        if (!Strings.isNullOrEmpty(username))
            SharedData.getInstance().getSessionsController().getActiveUsers().put(username, true);
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
