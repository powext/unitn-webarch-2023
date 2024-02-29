package com.example.assignment3;

import com.example.assignment3.engine.Cell;
import com.example.assignment3.engine.SSEngine;
import com.example.assignment3.model.FormulaRecord;
import com.example.assignment3.request.GetUpdate;
import com.example.assignment3.request.PostFormula;
import com.example.assignment3.util.NicknameGenerator;
import com.example.assignment3.util.SessionAttribute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        if (request.getSession().getAttribute(SessionAttribute.NICKNAME.getValue()) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        String timestamp = request.getParameter("timestamp");
        if (timestamp == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        List<FormulaRecord> records = null;
        try {
            records = SharedData.getInstance().getControllerFormulaRecords().getNewRecords(timestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        JsonElement cellElement = gson.toJsonTree(records);

        PrintWriter out = response.getWriter();
        out.println(gson.toJson(cellElement));
    }

    public void destroy() {
    }
}