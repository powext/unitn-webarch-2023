package com.simone.bianchin.assignment2.controllers;

import com.simone.bianchin.assignment2.HashGen;
import com.simone.bianchin.assignment2.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionsController {
    private Map<String, Boolean> activeUsers = new HashMap<>();

    public Map<String, Boolean> getActiveUsers() {
        return activeUsers;
    }
}
