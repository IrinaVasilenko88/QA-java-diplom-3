package ru.yandex.praktikum.pageObjects;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ResetPasswordPage {

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Click Login link")
    public LoginPage clickLoginButtonInResetPassword() {
        loginLink.click();
        return page(LoginPage.class);
    }
}
