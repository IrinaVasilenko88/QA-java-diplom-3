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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

public class LoginTest {

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
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userRestAuth.deleteUser(accessToken);
        }
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Login Through 'Sign In' Button on Main Page-Вход по кнопке «Войти в аккаунт» на главной")
    public void loginFromMainPageByClickOnEnterAccountTest() {
        userLogin = UserGenerator.from(profile);
        mainPage.moveToLoginFromMainPageByClickOnEnterAccount()
                .enterEmail(userLogin.getEmail())
                .enterPassword(userLogin.getPassword())
                .clickLoginButton();
        boolean result = true;
        assertEquals(result, mainPage.checkMakeOrderButtonVisibility());
    }

    @Test
    @DisplayName("Login Through 'Profile' Button on Main Page-Вход через кнопку «Личный кабинет»")
    public void loginFromMainPageByClickOnProfileButtonTest() {
        userLogin = UserGenerator.from(profile);
        mainPage.moveToLoginFromMainPageByClickOnProfile()
                .enterEmail(userLogin.getEmail())
                .enterPassword(userLogin.getPassword())
                .clickLoginButton();
        boolean result = true;
        assertEquals(result, mainPage.checkMakeOrderButtonVisibility());
    }

    @Test
    @DisplayName("Login Through 'Sign In' Button on Register Page-Вход через кнопку в форме регистрации")
    public void loginFromRegisterPageByClickOnSignInButtonTest() {
        userLogin = UserGenerator.from(profile);
        mainPage.moveToLoginFromMainPageByClickOnEnterAccount()
                .clickSignUpButton()
                .moveToLoginFromRegistration()
                .enterEmail(userLogin.getEmail())
                .enterPassword(userLogin.getPassword())
                .clickLoginButton();
        boolean result = true;
        assertEquals(result, mainPage.checkMakeOrderButtonVisibility());
    }

    @Test
    @DisplayName("Login Through 'Sign In' Button on Reset Password Page-Вход через кнопку в форме восстановления пароля")
    public void loginResetPasswordPageByClickOnLoginButtonTest() {
        userLogin = UserGenerator.from(profile);
        mainPage.moveToLoginFromMainPageByClickOnEnterAccount()
                .resetPasswordLinkClick()
                .clickLoginButtonInResetPassword()
                .enterEmail(userLogin.getEmail())
                .enterPassword(userLogin.getPassword())
                .clickLoginButton();
        boolean result = true;
        assertEquals(result, mainPage.checkMakeOrderButtonVisibility());
    }
}
