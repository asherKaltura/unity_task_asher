package tests;

import org.testng.annotations.*;
import pages.LoginPage;
import pages.PublisherPage;
import pages.PostPage;
import utils.DriverManager;
import utils.ConfigReader;
import utils.FakerUtils;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static utils.DriverManager.quitDriver;

public class UITests {

    @BeforeClass
    public void setup() {

        DriverManager.getDriver().get(ConfigReader.get("base.url") + "/admin");
    }

    @Test
    public void testCreatePublisherAndPostUI() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.login(ConfigReader.get("login.email"), ConfigReader.get("login.password"));
        String email=FakerUtils.generateEmail();
        String content=FakerUtils.someMessage();
        PublisherPage publisherPage = new PublisherPage();
        publisherPage.createPublisher(FakerUtils.generateFullName() ,email);

        String title = FakerUtils.someMessage();
        PostPage postPage = new PostPage();
        postPage.createPost(title,content, email);

        postPage.filter().open();
        postPage.filter().setTitleFilter(title);
        postPage.filter().setContentFilter(content);
        postPage.filter().apply();
        assertEquals(postPage.getValueByHeader("Status"), "ACTIVE");
        postPage.clickAction(PostPage.PostAction.EDIT);
        postPage.changeStatus(PostPage.PostStatus.REMOVED);
        postPage.clickSave();

        postPage.filter().open();
        postPage.filter().setTitleFilter(title);
        postPage.filter().setContentFilter(content);
        postPage.filter().apply();
        assertEquals(postPage.getValueByHeader("Status"), PostPage.PostStatus.REMOVED.name());

    }

    @AfterClass
    public void tearDown() {

         quitDriver();
    }
}
