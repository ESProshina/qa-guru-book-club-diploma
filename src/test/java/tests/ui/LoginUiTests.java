package tests.ui;

import io.qameta.allure.*;
import models.registration.RegistrationBodyModel;
import org.junit.jupiter.api.*;
import pages.LoginPage;

import static tests.TestData.*;

@Owner("Elena Black")
@Epic("UI: Авторизация")
@Feature("Логин через UI")
public class LoginUiTests extends UiTestBase {

    LoginPage loginPage = new LoginPage();

    String username;
    String password;
    String wrongPassword;

    @BeforeEach
    public void prepareTestData() {
        username = generateUsername();
        password = generatePassword();
        wrongPassword = generateWrongPassword();
    }

    @Test
    @DisplayName("После успешной авторизации отображаются вкладки для авторизованного пользователя")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Severity(SeverityLevel.CRITICAL)
    public void successfulLoginTest() {
        RegistrationBodyModel regBody = new RegistrationBodyModel(username, password);
        api.users.register(regBody);

        loginPage
                .openLoginPage()
                .setUsername(username)
                .setPassword(password)
                .checkMainPageCondition()
                .checkMainPageHaveProfileButton(EXPECTED_PROFILE_BTN_NAME)
                .checkMainPageHaveClubButton(EXPECTED_CLUB_BTN_NAME)
                .checkMainPageHaveCreateClubButton(EXPECTED_CREATE_CLUB_BTN_NAME);
    }

    @Test
    @DisplayName("После ввода некорректного пароля отображается ошибка авторизации")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("negative")})
    @Severity(SeverityLevel.NORMAL)
    public void wrongCredentialsLoginTest() {
        RegistrationBodyModel regBody = new RegistrationBodyModel(username, password);
        api.users.register(regBody);

        loginPage
                .openLoginPage()
                .setUsername(username)
                .setPassword(wrongPassword)
                .checkWrongCredentialsError(EXPECTED_WRONG_CREDENTIALS_ERROR_MESSAGE);
    }
}