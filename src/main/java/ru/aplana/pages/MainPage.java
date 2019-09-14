package ru.aplana.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static ru.aplana.steps.BaseSteps.driver;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[.='Ипотека']")
    public WebElement menu;

    @FindBy(xpath = "//li[contains(@class, 'lg-menu__item_hovered')]//a[.='Ипотека на готовое жильё']")
    public WebElement ipoteka;

    @FindBy(xpath = "//a[.='Условия и требования']")
    public WebElement ipotekaNew;

    @FindBy(xpath = "//div[@class='bp-container h-accordion h-accordion_opened kit-col_xs-top_50']//span[@class='kit-button__text']")
    public WebElement ipotekaButton;

    @FindBy(xpath = "//div[@class='product-teaser-full-width__button']/a[.='Подать заявку']")
    public WebElement co;


    public void menu(){
        Actions builder = new Actions(driver);
        builder.moveToElement(menu).build().perform();
    }

    public void goToIpoteka() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(ipoteka));
        ipoteka.click();

        wait.until(ExpectedConditions.elementToBeClickable(ipotekaNew));
        ipotekaNew.click();

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,900)");


        Thread.sleep(2000);
      wait.until(ExpectedConditions.elementToBeClickable(ipotekaButton));
        ipotekaButton.click();






    }



}

