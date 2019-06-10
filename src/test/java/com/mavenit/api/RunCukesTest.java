package com.mavenit.api;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class RunCukesTest {

    @BeforeClass
    public static void setBaseURI() {
        RestAssured.baseURI = "http://ec2-52-14-141-208.us-east-2.compute.amazonaws.com:9089";
    }
}
