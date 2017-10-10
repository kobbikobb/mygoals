package com.kobbikobb.mygoals.rest;

import com.kobbikobb.mygoals.Main;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import org.glassfish.grizzly.http.server.HttpServer;

import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GoalsResourceTest {

    private final int OK = 200;

    private HttpServer server;

    @Before
    public void setUp() throws Exception {
        server = Main.startServer();
        RestAssured.baseURI = Main.BASE_URI;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.authentication = createPreemtiveAuthentication();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void shouldCreateGoal() throws JSONException {
        String goal = new JSONObject()
                .put("description", "Clean all my clothes")
                .put("targetDate", "2008-09-12")
                .toString();

        given()
                .body(goal)
                .given()
                .contentType(ContentType.JSON)
                .when()
                .post("/goals")
                .then()
                .statusCode(OK)
                .body("description", Matchers.equalTo("Clean all my clothes"))
                .body("targetDate", Matchers.equalTo("2008-09-12"));
    }

    @Test
    public void shouldGetGoals() throws JSONException {
        String goal = new JSONObject()
                .put("description", "Clean all my clothes")
                .put("targetDate", "2008-09-12")
                .toString();

        given()
                .body(goal)
                .contentType(ContentType.JSON)
                .when()
                .post("/goals")
                .then()
                .statusCode(OK);

        given()
                .when()
                .get( "/goals")
                .then()
                .statusCode(OK)
                .body("[0].description", Matchers.equalTo("Clean all my clothes"))
                .body("[0].targetDate", Matchers.equalTo("2008-09-12"));
    }

    private PreemptiveBasicAuthScheme createPreemtiveAuthentication() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("1234");
        return authScheme;
    }
}
