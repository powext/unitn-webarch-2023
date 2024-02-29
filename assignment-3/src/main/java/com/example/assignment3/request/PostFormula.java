package com.example.assignment3.request;

import com.google.gson.annotations.Expose;

public class PostFormula {
    @Expose
    String cellId;
    @Expose
    String formula;

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    public String getCellId() {
        return cellId;
    }

    public String getFormula() {
        return formula;
    }
}
