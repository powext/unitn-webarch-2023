package com.example.assignment3;

import com.example.assignment3.engine.Cell;
import com.example.assignment3.engine.SSEngine;
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
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet(name = "FormulaServlet", value = "/formula")
public class FormulaServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        if (request.getSession().getAttribute(SessionAttribute.NICKNAME.getValue()) == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        SSEngine ssEngine = SharedData.getInstance().getSsEngine();
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        PostFormula postFormula = null;
        try {
            postFormula = gson.fromJson(requestBody, PostFormula.class);
        } catch (Exception e ){
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        if (postFormula == null) return;

        Set<Cell> changedCells = ssEngine.modifyCell(postFormula.getCellId(), postFormula.getFormula());
        SharedData.getInstance().getControllerFormulaRecords().addChangeRecord(changedCells);

        JsonElement cellElement = gson.toJsonTree(changedCells);
        JsonObject responseObject = new JsonObject();
        responseObject.add("changedCells", cellElement);

        PrintWriter out = response.getWriter();
        out.println(gson.toJson(responseObject));
    }

    public void destroy() {
    }
}