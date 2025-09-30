package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.CleanupManager;
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
    private CleanupManager cleanupManager = new CleanupManager();

    @BeforeClass
    public void login() {
        RestAssured.baseURI = ConfigReader.get("base.url");


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

    @Test
    public void addPublisherAndAddModifyPost() {

        createPublisher();
        createPost();
        removePost();
        validatePostStatus();
    }


    private void createPublisher() {
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
        cleanupManager.register(() -> deleteApi(publisherId, EntityType.Publisher));
    }


    private void createPost() {
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
        postId = response.jsonPath().getString("record.params.id");
        System.out.println("Post created id: " + postId);
        cleanupManager.register(() -> deleteApi(postId, EntityType.Post));

    }


    private void removePost() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .formParam("status", "REMOVED")
                .formParam("title", FakerUtils.someMessage())
                .formParam("content", content)
                .formParam("published", "true")
                .formParam("publisher", publisherId)
                .post(String.format("/admin/api/resources/Post/records/%s/edit", postId))
                .then()
                // .statusCode(200)
                .extract()
                .response();

        System.out.println("Post status changed: " + response.asString());
    }


    private void validatePostStatus() {
        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .get(String.format("/admin/api/resources/Post/records/%s/show", postId))
                .then()
                .statusCode(200)
                .extract()
                .response();

        String status = response.jsonPath().getString("record.params.status");
        System.out.println("Post current status: " + status);
        assertEquals(status, "REMOVED", "Post status should be REMOVED");
    }

    private void deleteApi(String id, EntityType entityType) {

        Response response = given()
                .cookie("adminjs", adminjsCookie)
                .post(String.format("/admin/api/resources/%s/records/%s/delete", entityType, id))
                .then()
                .statusCode(200)
                .extract()
                .response();

        String message = response.jsonPath().getString("notice.message");
        System.out.println(String.format("%s %s %s", entityType, id, message));
        assertEquals(message, "successfullyDeleted", String.format("%s %s %s", entityType, id, message));


    }

    enum EntityType {
        Publisher, Post;


    }
    @AfterClass
    private void tearDown(){

        cleanupManager.cleanup();

    }
}
