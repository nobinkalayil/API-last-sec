package com.mavenit.api;

import com.google.gson.JsonObject;
import com.mavenit.api.utils.FileReaderHelper;
import com.mavenit.api.utils.RestServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class RegressionTestSuites extends RestServices {


    @Test
    public void getUserTest() {
        Response response = given()
                .when()
                .get("http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register");
        List<Integer> ids = response.then().extract().path("id");

        //Hamcreset style
        assertThat(ids, contains(8));

        //restassured
        response.then().body("id", contains(8))
                .body("user", contains(""))
                .body("user", contains(""));
    }


    @Test
    public void userRegisterTest() {
        Response response = null;
        JsonObject payload = new JsonObject();
        payload.addProperty("username", "jygyghjffhf");
        payload.addProperty("passwordConfirmation", "rakesh");
        payload.addProperty("password", "rakesh");


        response = postService(payload, "http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register");

        response.prettyPrint();

        int id = response.then().extract().path("id");
        //complete hamcrest
        assertThat(response.then().extract().path("successful").toString(), is(equalToIgnoringCase("successful")));
        assertThat(response.getStatusCode(), is(equalTo(200)));


        response = deleteByIdServices("http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089/register/", id);

        //Rest assured +hamcrest
        response.then()
                .body("message", is(equalToIgnoringCase("User has been removed")))
                .statusCode(200);

    }


    @BeforeClass
    public static void setBaseURI() {
        RestAssured.baseURI = "http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089";
    }

    @Test
    public void blogTest() {
        Response response = null;
        String token = getAccessToken("admin", "admin");

        JsonObject payload= new FileReaderHelper().getJsonObject("user");


        response = given().contentType(ContentType.JSON).log().all()
                .auth().basic("my-trusted-client", "secret")
                .queryParam("access_token", token)
                .when().body(payload)
                .post("post");
        response.then().body("message",
                is(equalTo("Post was published"))).statusCode(200);



        response = given().auth().basic("my-trusted-client", "secret")
                .queryParam("access_token", token).when()
                .delete("post/31");



        response.then().body("deleted",
                is(equalTo(true))).body("id",
                is(equalTo(31))).statusCode(200);
    }

    @Test
    public void blogTestWithExtFiles() {
        Response response = null;
        String token = getAccessToken("admin", "admin");


        JsonObject payload= new FileReaderHelper().getJsonObject("blog");




        response = given().contentType(ContentType.JSON).log().all()
                .auth().basic("my-trusted-client", "secret")
                .queryParam("access_token", token)
                .when().body(payload)
                .post("post");
        response.then().body("message",
                is(equalTo("Post was published"))).statusCode(200);



        response = given().auth().basic("my-trusted-client", "secret")
                .queryParam("access_token", token).when()
                .delete("post/31");



        response.then().body("deleted",
                is(equalTo(true))).body("id",
                is(equalTo(31))).statusCode(200);
    }

    public String getAccessToken(String username, String password) {
        Response response = given()
                .auth().basic("my-trusted-client", "secret")
                .queryParam("grant_type", "password")
                .queryParam("username", username)
                .queryParam("password", password)
                .post("/oauth/token");

        response.then().statusCode(200);
        return response.then().extract().path("access_token").toString();

    }



}
