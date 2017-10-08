package com.kobbikobb.mygoals;

import io.restassured.RestAssured;
import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GoalsResourceTest {

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        RestAssured.baseURI = Main.BASE_URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void shouldGetGoals() {
        given()
                .when()
                .get( "/goals")
                .then()
                .statusCode(200)
                .log().all()
                .body("[0].description", containsString("Learn how to relax with my children"));
    }
}
