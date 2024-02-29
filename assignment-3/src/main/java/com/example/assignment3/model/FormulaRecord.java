package com.example.assignment3.model;

import com.example.assignment3.engine.Cell;
import com.google.gson.annotations.Expose;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

public class FormulaRecord {
    @Expose
    Set<Cell> cells = null;
    @Expose
    String timestamp = null;

    public FormulaRecord(Set<Cell> cells) {
        this.cells = cells;
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); // Quoted "Z" to indicate UTC, no timezone offset
        df.setTimeZone(tz);
        timestamp = df.format(new Date());
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return timestamp;
    }
}
