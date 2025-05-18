package stepdefs;

import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import models.Book;
import org.testng.Assert;
import utils.ApiUtils;
import utils.Context;
import utils.TestContext;

import static io.restassured.RestAssured.given;

public class BookStepDefs {

    private final TestContext testContext;
    private Response response;
    private Book requestBook;

    public BookStepDefs(TestContext context) {
        this.testContext = context;
    }

    @Given("a new book with title {string}, author {string}, and year {int}")
    public void a_new_book_with_details(String title, String author, Integer year) {
        requestBook = new Book(title, author, year);
        testContext.getExtentTest().log(Status.INFO, "Creating book: " + requestBook.toString());
    }

    @When("I send a POST request to create the book")
    public void i_send_post_request_to_create_book() {
        response = given()
                .contentType("application/json")
                .body(requestBook)
                .when()
                .post(ApiUtils.getBaseUrl() + "/books");

        testContext.setContext(Context.BOOK_ID, response.jsonPath().getInt("id"));
        testContext.getExtentTest().log(Status.INFO, "Response: " + response.asString());
    }

    @Then("the book should be created with status code {int}")
    public void book_should_be_created_with_status_code(Integer statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode.intValue());
        testContext.getExtentTest().log(Status.PASS, "Book created successfully");
    }

    @When("I retrieve the book by ID")
    public void i_retrieve_the_book_by_id() {
        int bookId = testContext.getContext(Context.BOOK_ID);
        response = given()
                .when()
                .get(ApiUtils.getBaseUrl() + "/books/" + bookId);

        testContext.getExtentTest().log(Status.INFO, "Fetched book by ID: " + bookId);
    }

    @Then("the response should contain the title {string}")
    public void response_should_contain_title(String expectedTitle) {
        String actualTitle = response.jsonPath().getString("title");
        Assert.assertEquals(actualTitle, expectedTitle);
        testContext.getExtentTest().log(Status.PASS, "Book title matched: " + actualTitle);
    }

    @When("I update the book title to {string}")
    public void i_update_the_book_title(String newTitle) {
        int bookId = testContext.getContext(Context.BOOK_ID);
        requestBook.setTitle(newTitle);

        response = given()
                .contentType("application/json")
                .body(requestBook)
                .when()
                .put(ApiUtils.getBaseUrl() + "/books/" + bookId);

        testContext.getExtentTest().log(Status.INFO, "Updated book with new title: " + newTitle);
    }

    @Then("the book should be updated with status code {int}")
    public void book_should_be_updated(Integer statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode.intValue());
        testContext.getExtentTest().log(Status.PASS, "Book updated successfully");
    }

    @When("I delete the book by ID")
    public void i_delete_book_by_id() {
        int bookId = testContext.getContext(Context.BOOK_ID);
        response = given()
                .when()
                .delete(ApiUtils.getBaseUrl() + "/books/" + bookId);

        testContext.getExtentTest().log(Status.INFO, "Deleted book ID: " + bookId);
    }

    @Then("the book should be deleted with status code {int}")
    public void book_should_be_deleted(Integer statusCode) {
        Assert.assertEquals(response.statusCode(), statusCode.intValue());
        testContext.getExtentTest().log(Status.PASS, "Book deleted successfully");
    }

    @When("I try to fetch a non-existent book ID")
    public void i_try_to_fetch_nonexistent_book() {
        response = given()
                .when()
                .get(ApiUtils.getBaseUrl() + "/books/9999");

        testContext.getExtentTest().log(Status.INFO, "Attempted to fetch non-existent book ID 999
