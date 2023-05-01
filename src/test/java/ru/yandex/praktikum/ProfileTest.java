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
import ru.yandex.praktikum.pageObjects.ProfilePage;
import ru.yandex.praktikum.rest.UserRestAuth;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class ProfileTest {

    private MainPage mainPage;
    private ProfilePage profilePage;
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
        profilePage = page(ProfilePage.class);
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
    @DisplayName("Move to Profile by click on Profile button-Переход по клику на «Личный кабинет»")
    public void moveToProfileByClickOnProfileButtonTest() {
        mainPage.moveToProfilePageFromMainPageByClickOnEnterAccount();
        boolean result = true;
        assertEquals(result, profilePage.checkProfileHeaderVisibility());
    }

    @Test
    @DisplayName("Move to Constructor by click on Constructor button-Переход по клику на «Конструктор»")
    public void moveToConstructorByClickOnConstructorButtonTest() {
        mainPage.moveToProfilePageFromMainPageByClickOnEnterAccount();
        profilePage.checkProfileHeaderVisibility();
        profilePage.clickConstructorButton();
        boolean result = true;
        assertEquals(result, mainPage.checkConstructorTextVisibility());
    }

    @Test
    @DisplayName("Move to Constructor by click on Constructor button-Переход по клику на логотип Stellar Burgers")
    public void moveToConstructorByClickOnLogoButtonTest() {
        mainPage.moveToProfilePageFromMainPageByClickOnEnterAccount();
        profilePage.checkProfileHeaderVisibility();
        profilePage.clickLogoButton();
        boolean result = true;
        assertEquals(result, mainPage.checkConstructorTextVisibility());
    }

    @Test
    @DisplayName("Sign out from Profile by click on Sign out button-Выход по кнопке «Выйти» в личном кабинете")
    public void signOutFromProfileByClickOnSignOutButtonTest() {
        mainPage.moveToProfilePageFromMainPageByClickOnEnterAccount();
        profilePage.clickSignOutButton();
        boolean result = true;
        assertEquals(result, loginPage.checkLoginHeader());
    }
}
