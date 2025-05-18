package tests;

import com.aventstack.extentreports.Status;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import listeners.ExtentManager;
import models.Book;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ApiUtils;

import static io.restassured.RestAssured.given;

public class BookApiTests extends ExtentManager {

    private static int createdBookId;

    @Test(priority = 1)
    public void testGetAllBooks() {
        test = extent.createTest("Get All Books");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiUtils.getBaseUrl() + "/books");

        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        test.log(Status.PASS, "Successfully retrieved all books");
    }

    @Test(priority = 2)
    public void testCreateBook() {
        test = extent.createTest("Create Book");

        Book book = new Book("API Testing with RestAssured", "QA Author", 2023);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(book)
                .post(ApiUtils.getBaseUrl() + "/books");

        test.log(Status.INFO, "Payload: " + book.toString());
        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        createdBookId = response.jsonPath().getInt("id");
        test.log(Status.PASS, "Book created with ID: " + createdBookId);
    }

    @Test(priority = 3, dependsOnMethods = "testCreateBook")
    public void testGetBookById() {
        test = extent.createTest("Get Book By ID");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiUtils.getBaseUrl() + "/books/" + createdBookId);

        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "API Testing with RestAssured");
        test.log(Status.PASS, "Book retrieved successfully");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateBook")
    public void testUpdateBook() {
        test = extent.createTest("Update Book");

        Book updatedBook = new Book("Updated Title", "Updated Author", 2024);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatedBook)
                .put(ApiUtils.getBaseUrl() + "/books/" + createdBookId);

        test.log(Status.INFO, "Payload: " + updatedBook.toString());
        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
        test.log(Status.PASS, "Book updated successfully");
    }

    @Test(priority = 5, dependsOnMethods = "testCreateBook")
    public void testDeleteBook() {
        test = extent.createTest("Delete Book");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete(ApiUtils.getBaseUrl() + "/books/" + createdBookId);

        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 200);
        test.log(Status.PASS, "Book deleted successfully");
    }

    @Test(priority = 6)
    public void testGetNonExistentBook() {
        test = extent.createTest("Get Non-existent Book");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(ApiUtils.getBaseUrl() + "/books/9999");

        test.log(Status.INFO, "Response: " + response.asString());

        Assert.assertEquals(response.statusCode(), 404);
        test.log(Status.PASS, "Verified book not found scenario");
    }
}
