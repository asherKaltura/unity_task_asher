package tests;

import assertion.AssertTrue;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.PublisherPage;
import pages.PostPage;
import utils.DriverManager;
import utils.ConfigReader;
import utils.FakerUtils;
import org.testng.annotations.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.testng.Assert.assertEquals;
import static utils.DriverManager.quitDriver;

public class UITests extends AbstractTestCase {


    @BeforeClass
    public void setup() {
        DriverManager.getDriver().get(ConfigReader.get("base.url") + "/admin");
    }

    @Test
    public void testCreatePublisherAndPostUI() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.get("login.email"), ConfigReader.get("login.password"));
        String email = FakerUtils.generateEmail();
        String content = FakerUtils.someMessage();
        PublisherPage publisherPage = new PublisherPage();
        publisherPage.createPublisher(FakerUtils.generateFullName(), email);
        String title = FakerUtils.someMessage();
        PostPage postPage = new PostPage();
        postPage.createPost(title, content, email);
        postPage.filter().applyFilter(title, content);
        assertEquals(postPage.getValueByHeader("Status"), "ACTIVE");
        postPage.clickAction(PostPage.PostAction.EDIT);
        postPage.changeStatus(PostPage.PostStatus.REMOVED);
        postPage.clickSave();
        postPage.filter().applyFilter(title, content);
        report.step("Post status should be REMOVED");
        assertion.verify(new AssertTrue(postPage.getValueByHeader("Status").equals(PostPage.PostStatus.REMOVED.name()), "Status == REMOVED"), false);
        assertEquals(postPage.getValueByHeader("Status"), PostPage.PostStatus.REMOVED.name());
    }

    private void deletePost(int id) {

        System.out.println("delete post" + id);

    }

    private void deletePublisher(int id) {

        System.out.println("delete publisher" + id);
    }

    @AfterClass
    public void tearDown() {

        quitDriver();
    }
}
