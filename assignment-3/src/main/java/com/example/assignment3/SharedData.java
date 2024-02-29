package com.example.assignment3;

import com.example.assignment3.controller.ControllerFormulaRecords;
import com.example.assignment3.engine.SSEngine;

public class SharedData {
    private final SSEngine ssEngine = SSEngine.getSSEngine();
    private final ControllerFormulaRecords controllerFormulaRecords = ControllerFormulaRecords.getInstance();

    // Singleton
    public static SharedData instance;

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public SSEngine getSsEngine() {
        return ssEngine;
    }

    public ControllerFormulaRecords getControllerFormulaRecords() {
        return controllerFormulaRecords;
    }

    public void destroy() {
        // TODO destroy
    }
}
