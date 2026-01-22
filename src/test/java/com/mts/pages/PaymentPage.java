package com.mts.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Сумма: <div class="pay-description__cost"><span>1.00 BYN</span></div>
    @FindBy(css = "div.pay-description__cost span")
    private WebElement amountDisplay;

    @FindBy(css = "div.pay-description__text span")
    private WebElement phoneNumberDisplay;

    @FindBy(css = "button.colored")
    private WebElement payButton;

    @FindBy(xpath = "//label[contains(text(), 'Номер карты')]")
    private WebElement cardNumberLabel;

    @FindBy(css = "input[placeholder='MM / YY']")
    private WebElement expiryInput;


    @FindBy(xpath = "//label[contains(text(), 'CVC')]")
    private WebElement cvcLabel;


    @FindBy(xpath = "//label[contains(text(), 'Имя и фамилия на карте')]")
    private WebElement cardHolderLabel;


    @FindBy(css = "div.cards-brands__container img")
    private List<WebElement> paymentSystemIcons;

    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(amountDisplay));
        wait.until(ExpectedConditions.visibilityOf(phoneNumberDisplay));
    }

    public String getAmountText() {
        return amountDisplay.getText();
    }

    public String getPhoneNumberText() {
        return phoneNumberDisplay.getText();
    }

    public String getPayButtonText() {
        return payButton.getText();
    }

    public String getCardNumberLabel() {
        return cardNumberLabel.getText();
    }

    public String getExpiryPlaceholder() {
        return expiryInput.getAttribute("placeholder");
    }

    public String getCvcLabel() {
        return cvcLabel.getText();
    }

    public String getCardHolderLabel() {
        return cardHolderLabel.getText();
    }

    public boolean arePaymentSystemIconsDisplayed() {
        return !paymentSystemIcons.isEmpty() &&
                paymentSystemIcons.stream().allMatch(WebElement::isDisplayed);
    }
}
