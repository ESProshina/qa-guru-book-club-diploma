package tests.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import tests.TestBase;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class UiTestBase extends TestBase {

    @BeforeAll
    public static void setUpUi() {
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = "https://book-club.qa.guru";
        Configuration.pageLoadTimeout = 60_000;
        Configuration.timeout = 20_000;

        String remote = System.getProperty("remote");
        if (remote != null && !remote.isEmpty()) {
            Configuration.remote = remote;


            Configuration.browserCapabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
        }


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        Configuration.browserCapabilities = options;


        if (remote != null && !remote.isEmpty()) {
            options.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
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


            String remote = System.getProperty("remote");
            if (remote != null && !remote.isEmpty()) {
                Attach.addVideo();
            }

            closeWebDriver();
        }
    }
}