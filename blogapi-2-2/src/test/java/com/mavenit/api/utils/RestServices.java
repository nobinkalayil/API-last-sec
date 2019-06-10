package com.mavenit.api.utils;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class RestServices {

    public Response postService(JsonObject jsonObject, String uri) {
        return given().contentType(ContentType.JSON).body(jsonObject).when().post(uri);
    }

    public Response getServices(String uri) {
        return given().contentType("application/json").when().get(uri);
    }

    public Response getByIdServices(String uri, int id) {
        return given().contentType("application/json").when().get(uri + id);
    }

    public Response deleteByIdServices(String uri, int id) {
        return given().contentType("application/json").when().delete(uri + id);
    }




}
