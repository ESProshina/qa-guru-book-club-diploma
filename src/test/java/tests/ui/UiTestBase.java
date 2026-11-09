package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.bookclub.TestBase;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static io.restassured.RestAssured.sessionId;

public class UiTestBase extends TestBase {

    @BeforeAll
    public static void setUpUi() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://book-club.qa.guru";
        Configuration.pageLoadTimeout = 60_000;
        Configuration.timeout = 20_000;

                ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;

        String remote = System.getProperty("remote", "");
        if (!remote.isEmpty()) {
            Configuration.remote = remote;
        }
    }

    @BeforeEach
    void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @AfterEach
    void tearDown() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            Attach.browserConsoleLogs();
        if (System.getProperty("remote") != null) {
                Attach.addVideo(sessionId);
            }
            closeWebDriver();
        }
    }
}