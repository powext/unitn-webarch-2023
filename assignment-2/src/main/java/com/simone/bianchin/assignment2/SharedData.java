package com.simone.bianchin.assignment2;

import com.simone.bianchin.assignment2.controllers.MatchController;
import com.simone.bianchin.assignment2.controllers.SessionsController;
import com.simone.bianchin.assignment2.controllers.UserController;

import java.util.ArrayList;

public class SharedData {
    private UserController userController = new UserController();
    private MatchController matchController = new MatchController();
    private SessionsController sessionsController = new SessionsController();

    // Singleton
    public static SharedData instance;

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public UserController getUserController() {
        return userController;
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }


    public MatchController getMatchController() {
        return matchController;
    }

    public void setMatchController(MatchController matchController) {
        this.matchController = matchController;
    }

    public SessionsController getSessionsController() {
        return sessionsController;
    }

    public void destroy() {
        this.userController.destroy();
        this.matchController.destroy();
    }
}
