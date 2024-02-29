package com.simone.bianchin.assignment2.controllers;

import com.simone.bianchin.assignment2.HashGen;
import com.simone.bianchin.assignment2.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserController {
    private ArrayList<User> users = new ArrayList<>();

    public UserController() {
        this.deserializeState();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public synchronized User registerUser(User user) {
        boolean userExists = users.stream().anyMatch(item -> item.getUsername().equals(user.getUsername()));
        if (userExists) return null;

        users.add(user);
        return user;
    }

    public User loginUser(String username, String hashedPassword) {
        User foundUser =  users.stream()
                .filter(item -> item.getUsername().equals(username))
                .findFirst()
                .orElse(null);

        if (foundUser == null) return null;
        if (!foundUser.getHashedPassword().equals(hashedPassword)) return null;

        return foundUser;
    }

    public User getUser(String username) {
        return users.stream()
                .filter(item -> item.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    private void deserializeState() {
        try
        {
            User adminUser = User.create("admin", HashGen.SHA1("admin"));
            adminUser.setAdmin(true);

            File f = new File("userData");
            if (!f.isFile() || !f.canRead()) {
                this.users.add(adminUser);
                return;
            }

            FileInputStream fis = new FileInputStream("userData");
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.users = (ArrayList<User>) ois.readObject();
            this.users.add(adminUser);

            System.out.println("===");
            System.out.println("Loaded users from file:");
            for (User user : users) {
                System.out.println(user.getUsername());
            }

            ois.close();
            fis.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void serializeState() {
        try
        {
            this.users = (ArrayList<User>) users.stream().filter(item -> !item.getUsername().equals("admin")).collect(Collectors.toList());
            FileOutputStream fos = new FileOutputStream("userData");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this.users);
            oos.close();
            fos.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void destroy() {
        this.serializeState();
    }
}
