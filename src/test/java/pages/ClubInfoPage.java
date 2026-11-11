package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ClubInfoPage {

    private final SelenideElement clubContent = $(".club-content");
    private final SelenideElement leaveBtn = $(".leave-btn");
    private final SelenideElement leaveErrorText = $(".error");

    @Step("Открыть простую страницу")
    public ClubInfoPage openPage() {
        open("/favicon.ico");
        return this;
    }

    @Step("Вставить в Local Storage JSON авторизации")
    public ClubInfoPage putAuthIntoLocalStorage(String localStorageAuthBody) {
        localStorage().setItem("book_club_auth", localStorageAuthBody);
        return this;
    }

    @Step("Открыть страницу клуба с id {clubId}")
    public ClubInfoPage openClubInfoPage(int clubId) {
        open("/clubs/" + clubId);
        return this;
    }

    @Step("Проверка видимости информации о клубе")
    public ClubInfoPage checkClubInfo() {
        clubContent.shouldBe(visible);
        return this;
    }

    @Step("Клик по кнопке Покинуть клуб")
    public ClubInfoPage clickLeaveBtn() {
        leaveBtn.click();
        return this;
    }

    @Step("Подтверждение выхода из клуба")
    public ClubInfoPage confirmLeaveClub() {
        confirm();
        return this;
    }

    @Step("Проверка ошибки при попытке покинуть клуб: {expectedMessage}")
    public ClubInfoPage checkLeaveClubError(String expectedMessage) {
        leaveErrorText.shouldHave(text(expectedMessage));
        return this;
    }
}