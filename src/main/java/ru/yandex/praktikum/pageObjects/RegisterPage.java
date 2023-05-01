package ru.yandex.praktikum.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import ru.yandex.praktikum.model.UserProfile;

import static com.codeborne.selenide.Selenide.page;

public class RegisterPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoButton;

    @FindBy(how = How.XPATH, using = ".//*[text()='Имя']/../input")
    private SelenideElement nameInput;

    @FindBy(how = How.CSS, using = "[name=\"name\"]")
    private SelenideElement emailField;

    @FindBy(how = How.CSS, using = "[name=\"Пароль\"]")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//*[text()='Email']/../input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = ".//*[text()='Пароль']/../input")
    private SelenideElement passwordInput;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//*[text()='Некорректный пароль']")
    private SelenideElement invalidPasswordNotification;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Fulfill Name")
    public void fulfillName(String name) {
        nameInput.setValue(name);
    }

    @Step("Fulfill Password")
    public void fulfillPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Fulfill email")
    public void fulfillEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Register new User")
    public void registerNewUser(UserProfile profile) {
        fulfillName(profile.getName());
        fulfillEmail(profile.getEmail());
        fulfillPassword(profile.getPassword());
        registerButton.click();
    }

    @Step("Move to Login Page from Registration Page")
    public LoginPage moveToLoginFromRegistration() {
        loginLink.click();
        return page(LoginPage.class);
    }

    @Step("Check Invalid Password Notification")
    public boolean checkInvalidPasswordNotification() {
        return invalidPasswordNotification.isDisplayed();
    }
}
