package ru.yandex.praktikum.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    public static final String URI = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.CSS, using = ".Auth_login__3hAey > h2")
    private SelenideElement loginHeader;

    @FindBy(how = How.XPATH, using = ".//*[text()='Email']/../input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = ".//*[text()='Пароль']/../input")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement registerLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement resetPasswordLink;


    @Step("Enter email")
    public LoginPage enterEmail(String email) {
        emailInput.shouldBe(empty).click();
        emailInput.setValue(email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        passwordInput.click();
        passwordInput.setValue(password);
        return this;
    }

    @Step("Click Login button")
    public MainPage clickLoginButton() {
        loginButton.click();
        return page(MainPage.class);
    }

    @Step("Check Login header")
    public boolean checkLoginHeader() {
        loginHeader.shouldHave(Condition.text("Вход"));
        return true;
    }

    @Step("Click Sign Up link")
    public RegisterPage clickSignUpButton() {
        registerLink.click();
        return page(RegisterPage.class);
    }

    @Step("Click Reset Password link")
    public ResetPasswordPage resetPasswordLinkClick() {
        resetPasswordLink.scrollIntoView(true);
        resetPasswordLink.click();
        return page(ResetPasswordPage.class);
    }
}
