package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import models.login.LoginBodyModel;
import models.login.SuccessfulLoginResponseModel;
import models.login.WrongCredentialsLoginResponseModel;
import models.logout.LogoutBodyModel;
import specs.BaseSpec;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.baseRequestSpec;
import static specs.login.LoginSpec.successfulLoginResponseSpec;

public class AuthApiClient {

    @Step("Авторизация")
    public SuccessfulLoginResponseModel login(LoginBodyModel loginBody) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post("/auth/token/")
                .then()
                .spec(successfulLoginResponseSpec)
                .extract()
                .as(SuccessfulLoginResponseModel.class);
    }

    @Step("Авторизация и получение refresh-токена")
    public String loginAndGetRefreshToken(LoginBodyModel loginBody) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post("/auth/token/")
                .then()
                .spec(successfulLoginResponseSpec)
                .extract()
                .path("refresh");
    }

    @Step("Авторизация и получение access-токена")
    public String loginAndGetAccessToken(LoginBodyModel loginBody) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post("/auth/token/")
                .then()
                .spec(successfulLoginResponseSpec)
                .extract()
                .path("access");
    }

    @Step("Авторизация с неверными данными")
    public WrongCredentialsLoginResponseModel loginWrongCredentials(LoginBodyModel loginBody) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post("/auth/token/")
                .then()
                .spec(BaseSpec.responseSpec(401)) // <--- ИСПРАВЛЕНО
                .extract()
                .as(WrongCredentialsLoginResponseModel.class);
    }

    @Step("Авторизация со спецификацией")
    public Response loginWithSpec(LoginBodyModel loginBody, ResponseSpecification spec) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post("/auth/token/")
                .then()
                .spec(spec)
                .extract()
                .response();
    }

    @Step("Выход из системы")
    public void logout(LogoutBodyModel logoutBody) {
        given(baseRequestSpec)
                .body(logoutBody)
                .when()
                .post("/auth/logout/")
                .then()
                .spec(BaseSpec.responseSpec(200)); // <--- ИСПРАВЛЕНО
    }

    @Step("Выход из системы со спецификацией")
    public Response logoutWithSpec(LogoutBodyModel logoutBody, ResponseSpecification spec) {
        return given(baseRequestSpec)
                .body(logoutBody)
                .when()
                .post("/auth/logout/")
                .then()
                .spec(spec)
                .extract()
                .response();
    }
}