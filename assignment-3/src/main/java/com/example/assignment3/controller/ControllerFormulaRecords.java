package com.example.assignment3.controller;

import com.example.assignment3.engine.Cell;
import com.example.assignment3.model.FormulaRecord;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ControllerFormulaRecords {
    private static ControllerFormulaRecords instance = null;
    private final ArrayList<FormulaRecord> formulaChangeRecords = new ArrayList<>();

    public synchronized void addChangeRecord(Set<Cell> cells) {
        this.formulaChangeRecords.add(new FormulaRecord(cells));
    }

    public synchronized List<FormulaRecord> getNewRecords(String requestTimestamp) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        List<FormulaRecord> records = this.formulaChangeRecords
                .stream()
                .filter(e -> {
                    try {
                        return sdf.parse(e.getTimestamp()).compareTo(sdf.parse(requestTimestamp)) > 0;
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }
                })
                .toList();

        return records;
    }

    public static ControllerFormulaRecords getInstance() {
        if (instance == null) {
            instance = new ControllerFormulaRecords();
            return instance;
        }
        return instance;
    }
}
