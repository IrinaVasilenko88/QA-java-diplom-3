package ru.yandex.praktikum.pageObjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement enterAccountButton;

    @FindBy(how = How.XPATH, using = ".//a[contains(@href,'/account')]")
    private SelenideElement profileButton;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-default'][text()='Булки']")
    private SelenideElement bunsTab;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-medium mb-6 mt-10'][text()='Булки']")
    private SelenideElement bunsSection;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-default'][text()='Соусы']")
    private SelenideElement saucesTab;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-medium mb-6 mt-10'][text()='Соусы']")
    private SelenideElement saucesSection;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-default'][text()='Начинки']")
    private SelenideElement fillingTab;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-medium mb-6 mt-10'][text()='Начинки']")
    private SelenideElement fillingSection;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement makeOrderButton;

    @FindBy(how = How.XPATH, using = ".//*[@class='text text_type_main-large mb-5 mt-10'][text()='Соберите бургер']")
    private SelenideElement constructorText;


    @Step("Move to Login Page from Main Page by click on enter account")
    public LoginPage moveToLoginFromMainPageByClickOnEnterAccount() {
        enterAccountButton.click();
        return page(LoginPage.class);
    }

    @Step("Move to Profile Page from Main Page by click on enter account")
    public ProfilePage moveToProfilePageFromMainPageByClickOnEnterAccount() {
        profileButton.click();
        return page(ProfilePage.class);
    }

    @Step("Move to Login Page from Main Page by click on profile")
    public LoginPage moveToLoginFromMainPageByClickOnProfile() {
        profileButton.click();
        return page(LoginPage.class);
    }

    @Step("Click on buns tab")
    public void clickOnBunsTab() {
        bunsTab.shouldBe(Condition.visible);
        bunsTab.click();

    }

    @Step("Check the buns section visibility")
    public boolean isBunsSectionVisible() {
        return bunsSection.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Click on fillings tab")
    public void clickOnFillingsTab() {
        fillingTab.click();
    }

    @Step("Check the fillings section visibility")
    public boolean isFillingsSectionVisible() {
        return fillingSection.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Click on sauces tab")
    public void clickOnSaucesTab() {
        saucesTab.click();
    }

    @Step("Check the sauces section")
    public void isSaucesSectionVisible() {
        saucesSection.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Check make order button visibility")
    public void checkMakeOrderButtonVisibility() {
        makeOrderButton.shouldBe(Condition.visible);
    }

    @Step("Check Constructor text visibility")
    public boolean checkConstructorTextVisibility() {
        constructorText.shouldBe(Condition.visible);
        return true;
    }
}
