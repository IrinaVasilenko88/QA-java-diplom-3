package ru.yandex.praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.model.UserGenerator;
import ru.yandex.praktikum.model.UserLogin;
import ru.yandex.praktikum.model.UserProfile;
import ru.yandex.praktikum.pageObjects.LoginPage;
import ru.yandex.praktikum.pageObjects.MainPage;
import ru.yandex.praktikum.rest.UserRestAuth;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;

public class ConstructorTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private UserLogin userLogin;
    private String accessToken;
    private UserRestAuth userRestAuth;
    private UserProfile profile;

    @Before
    public void setUp() {
        userRestAuth = new UserRestAuth();
        profile = UserGenerator.getRandomUser();
        userRestAuth.registerUser(profile)
                .assertThat()
                .statusCode(SC_OK)
                .body("success", is(true)).extract().path("accessToken");
        mainPage = open(MainPage.URL, MainPage.class);
        loginPage = page(LoginPage.class);
        userLogin = UserGenerator.from(profile);
        mainPage.moveToLoginFromMainPageByClickOnEnterAccount()
                .enterEmail(userLogin.getEmail())
                .enterPassword(userLogin.getPassword())
                .clickLoginButton()
                .checkMakeOrderButtonVisibility();
    }

    @After
    public void tearDown() {

        if (accessToken != null) {
            userRestAuth.deleteUser(accessToken);
        }
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Check buns tab by click on it-Переход к разделу «Булки»")
    public void checkBunsTabByClickItTest()  {
        mainPage.clickOnFillingsTab();
        mainPage.clickOnSaucesTab();
        mainPage.clickOnBunsTab();
        mainPage.isBunsSectionVisible();
    }

    @Test
    @DisplayName("Check fillings tab by click on it-Переход к разделу «Начинки»")
    public void checkFillingsTabByClickItTest() {
        mainPage.clickOnFillingsTab();
        mainPage.isFillingsSectionVisible();
    }

    @Test
    @DisplayName("Check sauces tab by click on it-Переход к разделу «Соусы»")
    public void checkSaucesTabByClickItTest() {
        mainPage.clickOnSaucesTab();
        mainPage.isSaucesSectionVisible();
    }
}
