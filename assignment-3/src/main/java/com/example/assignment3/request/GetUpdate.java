package com.example.assignment3.request;

import com.google.gson.annotations.Expose;

import java.sql.Timestamp;

public class GetUpdate {
    @Expose
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
