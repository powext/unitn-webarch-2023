package com.example.assignment3;

import com.example.assignment3.engine.Cell;
import com.example.assignment3.engine.SSEngine;
import com.example.assignment3.util.NicknameGenerator;
import com.example.assignment3.util.SessionAttribute;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.HashMap;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "GridServlet", value = "/grid")
public class GridServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        if (request.getSession().getAttribute(SessionAttribute.NICKNAME.getValue()) == null) {
            request.getSession().setAttribute(SessionAttribute.NICKNAME.getValue(), NicknameGenerator.generate());
        }
        String nickname = (String) request.getSession().getAttribute(SessionAttribute.NICKNAME.getValue());
        SSEngine ssEngine = SharedData.getInstance().getSsEngine();
        HashMap<String, Cell> grid = ssEngine.getCellMap();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        JsonElement cellElement = gson.toJsonTree(grid);
        JsonObject responseObject = new JsonObject();
        responseObject.add("cells", cellElement);

        JsonObject gridProprietiesObject = new JsonObject();
        gridProprietiesObject.addProperty("nRows", SSEngine.NROWS);
        gridProprietiesObject.addProperty("nColumns", SSEngine.columns.length);
        responseObject.add("grid", gridProprietiesObject);
        PrintWriter out = response.getWriter();
        out.println(gson.toJson(responseObject));
    }

    public void destroy() {
    }
}