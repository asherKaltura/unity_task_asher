package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.FakerUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiTests {

    private String adminjsCookie;
    private String email = FakerUtils.generateEmail();
    private String content = FakerUtils.someMessage();
    private String publisherId = "";
    private String postId = "";
    @BeforeClass
    public void login() {
        RestAssured.baseURI = "http://localhost:3000";


        Response loginResponse = given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigReader.get("login.email"))
                .formParam("password", ConfigReader.get("login.password"))
                .post("/admin/login")
                .then()
                .statusCode(302)
                .extract()
                .response();

        adminjsCookie = loginResponse.getCookie("adminjs");
        System.out.println("Logged in, adminjs Cookie: " + adminjsCookie);
    }

  @Test(priority = 1)
    public void createPublisher() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .formParam("name", "Test Publisher")
                .formParam("email", email)
                .post("/admin/api/resources/Publisher/actions/new")
                .then()
                .statusCode(200)
                .extract()
                .response();
        publisherId = response.jsonPath().getString("record.params.id");

        System.out.println("Publisher created id : " + publisherId);
    }

    @Test(priority = 2)
    public void createPost() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .formParam("title", FakerUtils.someMessage())
                .formParam("content", content)
                .formParam("published", "true")
                .formParam("publisher", publisherId)
                .formParam("someJson.0.number", FakerUtils.getNumber())
                .post("/admin/api/resources/Post/actions/new")
                .then()
                .statusCode(200)
                .extract()
                .response();
        postId= response.jsonPath().getString("record.params.id");

        System.out.println("Post created id: " +postId);
    }

    @Test(priority = 4)
    public void removePost() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .formParam("status", "REMOVED")
                .formParam("title", FakerUtils.someMessage())
                .formParam("content", content)
                .formParam("published", "true")
                .formParam("publisher", publisherId)
                .post(String.format("/admin/api/resources/Post/records/%s/edit",postId))
                .then()
               // .statusCode(200)
                .extract()
                .response();

        System.out.println("Post status changed: " + response.asString());
    }

    @Test(priority = 5)
    public void validatePostStatus() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .get(String.format("/admin/api/resources/Post/records/%s/show",postId))
                .then()
                .statusCode(200)
                .extract()
                .response();

        String status = response.jsonPath().getString("record.params.status");
        System.out.println("Post current status: " + status);
        assertEquals(status, "REMOVED", "Post status should be REMOVED");
    }
}
