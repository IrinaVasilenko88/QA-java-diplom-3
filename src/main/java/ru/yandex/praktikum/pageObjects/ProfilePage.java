package ru.yandex.praktikum.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement signOutButton;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'/account/profile')]")
    private SelenideElement profileHeader;

    @FindBy(how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement logoButton;

    @FindBy(how = How.XPATH, using = ".//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    @Step("Sign Out")
    public LoginPage clickSignOutButton() {
        signOutButton.click();
        return page(LoginPage.class);
    }

    @Step("Move to Main Page by click on logo")
    public MainPage clickLogoButton() {
        logoButton.click();
        return page(MainPage.class);
    }

    @Step("Move to Main Page by click on constructor")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        return page(MainPage.class);
    }

    @Step("Check Profile Header visibility")
    public boolean checkProfileHeaderVisibility() {
        profileHeader.shouldBe(Condition.visible);
        return true;
    }
}
