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
import ru.yandex.praktikum.pageObjects.RegisterPage;
import ru.yandex.praktikum.rest.UserRestAuth;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertEquals;

public class RegistrationTest {

    private RegisterPage register;
    private LoginPage loginPage;
    private UserRestAuth userRestAuth;
    private String accessToken;
    private UserProfile profile;
    private UserLogin userLogin;

    @Before
    public void setUp() {
        userRestAuth = new UserRestAuth();
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        register = open(RegisterPage.URL, RegisterPage.class);
        loginPage = page(LoginPage.class);
    }

    @After
    public void tearDown() {

        if (accessToken != null) {
            userRestAuth.deleteUser(accessToken);
        }
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Valid data user registration - Успешная регистрация нового пользователя")
    public void successfulRegistration() {
        profile = UserGenerator.getRandomUser();
        register.registerNewUser(profile);
        boolean result = true;
        assertEquals(result, loginPage.checkLoginHeader());
    }

    @Test
    @DisplayName("Incorrect password user registration - Регистрация нового пользователя с некорректным паролем")
    public void incorrectPasswordRegistration() {
        profile = UserGenerator.getUserWithInvalidPassword();
        register.registerNewUser(profile);
        boolean result = true;
        assertEquals(result, register.checkInvalidPasswordNotification());

    }
}
