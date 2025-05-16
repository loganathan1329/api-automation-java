package com.company.api.utils;

import com.company.api.models.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {

    public static Response createUser(User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .post("/users")
                .then()
                .extract()
                .response();
    }

    public static Response getUserById(String id) {
        return given()
                .contentType("application/json")
                .when()
                .get("/users/" + id)
                .then()
                .extract()
                .response();
    }

    public static Response updateUser(String id, User user) {
        return given()
                .contentType("application/json")
                .body(user)
                .when()
                .put("/users/" + id)
                .then()
                .extract()
                .response();
    }

    public static Response deleteUser(String id) {
        return given()
                .contentType("application/json")
                .when()
                .delete("/users/" + id)
                .then()
                .extract()
                .response();
    }
}

