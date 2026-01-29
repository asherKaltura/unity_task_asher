package tests;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigReader;
import utils.FakerUtils;

import static org.testng.Assert.assertEquals;

public class UITestsPlaywright {

    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(ConfigReader.get("base.url") + "/admin");
    }

    @Test
    public void testCreatePublisherAndPostUI() {

        LoginPagePlaywright loginPage = new LoginPagePlaywright(page);
        loginPage.login(
                ConfigReader.get("login.email"),
                ConfigReader.get("login.password")
        );

        String email = FakerUtils.generateEmail();
        String content = FakerUtils.someMessage();
        String title = FakerUtils.someMessage();

        PublisherPagePlaywright publisherPage = new PublisherPagePlaywright(page);
        publisherPage.createPublisher(FakerUtils.generateFullName(), email);

       PostPagePlaywright postPage = new PostPagePlaywright(page);
       postPage.createPost(title, content, email);

        //postPage.filter().applyFilter(title, content);
       // assertEquals(postPage.getPostStatus(), "ACTIVE");

     //   postPage.changeStatus(PostPagePlaywright.PostStatus.REMOVED);
      //  postPage.filter().applyFilter(title, content);
       // assertEquals(postPage.getPostStatus(), "REMOVED");
    }

    @AfterClass
    public void tearDown() {
        page.close();
        browser.close();
        playwright.close();
    }
}
