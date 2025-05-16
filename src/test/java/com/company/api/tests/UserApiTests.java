package com.company.api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserApiTests {

    private static final String BASE_URL = "https://api.example.com"; // Update this to your actual base URL

    private String userId;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(priority = 1)
    public void testCreateUser() {
        String requestBody = """
            {
                "name": "John Doe",
                "email": "john@example.com"
            }
            """;

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .post("/users");

        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201 for user creation");
        userId = response.jsonPath().getString("id");
        Assert.assertNotNull(userId, "User ID should not be null after creation");
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUser() {
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .get("/users/" + userId);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 for getting user");
        Assert.assertEquals(response.jsonPath().getString("name"), "John Doe", "User name should be John Doe");
        Assert.assertEquals(response.jsonPath().getString("email"), "john@example.com", "User email should match");
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        String updatedName = "Johnathan Doe";
        String requestBody = String.format("""
            {
                "name": "%s",
                "email": "john@example.com"
            }
            """, updatedName);

        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .put("/users/" + userId);

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200 for updating user");
        Assert.assertEquals(response.jsonPath().getString("name"), updatedName, "User name should be updated");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .delete("/users/" + userId);

        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204 for deleting user");
    }

    @Test(priority = 5)
    public void testGetDeletedUser() {
        Response response = RestAssured.given()
            .header("Content-Type", "application/json")
            .get("/users/" + userId);

        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404 for non-existent user");
    }
}

