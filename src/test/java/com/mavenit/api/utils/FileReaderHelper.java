package com.mavenit.api.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileReaderHelper {

    public JsonObject getJsonObject(String file) {
        String path = System.getProperty("user.dir") + "/src/test/resources/" + file + ".json";

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, JsonObject.class);
    }
}
