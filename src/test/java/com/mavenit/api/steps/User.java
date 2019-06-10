package com.mavenit.api.steps;

import com.google.gson.JsonObject;
import com.mavenit.api.utils.FileReaderHelper;
import com.mavenit.api.utils.RestServices;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class User {

    public Response response;

    private RestServices services;

    public User(RestServices diServices) {
        this.services = diServices;
    }

    @Then("^I should see status code as \"([^\"]*)\"$")
    public void i_should_see_status_code_as(int expected) {
        response.then().statusCode(expected);
    }

    @When("^I execute post request on endpoint \"([^\"]*)\" with payload \"([^\"]*)\"$")
    public void iExecutePostRequestOnEndpointWithPayload(String endpoint, String payloadFile) {

        JsonObject payload = new FileReaderHelper().getJsonObject(payloadFile);
        response = services.postService(payload, endpoint);
    }
}
