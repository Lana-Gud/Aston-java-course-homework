package com.mts.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

//  Класс который представляет страницу с блоком оплаты
public class OnlinePaymentPage {
    private WebDriver driver;

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Заголовок блока
    @FindBy(xpath = "//h2[contains(text(), 'Онлайн пополнение')]")
    private WebElement blockTitle;

    // Логотипы
    @FindBy(css = ".pay__partners img")
    private List<WebElement> paymentLogos;

    // Ссылка "Подробнее о сервисе"
    @FindBy(linkText = "Подробнее о сервисе")
    private WebElement detailsLink;

    // Выпадающий список услуг
    @FindBy(id = "pay")
    private WebElement serviceSelect;

    // Формы для каждой услуги
    @FindBy(id = "pay-connection")
    private WebElement connectionForm;

    @FindBy(id = "pay-internet")
    private WebElement internetForm;

    @FindBy(id = "pay-instalment")
    private WebElement instalmentForm;

    @FindBy(id = "pay-arrears")
    private WebElement arrearsForm;

    // новые интернет
    @FindBy(id = "connection-phone")
    private WebElement phoneInput;

    @FindBy(id = "connection-sum")
    private WebElement sumInput;

    @FindBy(id = "connection-email")
    private WebElement emailInput;

    @FindBy(xpath = "//form[@id='pay-connection']//button[text()='Продолжить']")
    private WebElement continueButton;
    // Поля для домашнего интернета
    @FindBy(id = "internet-phone")
    private WebElement internetPhoneInput;

    @FindBy(id = "internet-sum")
    private WebElement internetSumInput;

    @FindBy(id = "internet-email")
    private WebElement internetEmailInput;

    // Поля для рассрочки
    @FindBy(id = "score-instalment")
    private WebElement instalmentAccountInput;

    @FindBy(id = "instalment-sum")
    private WebElement instalmentSumInput;

    @FindBy(id = "instalment-email")
    private WebElement instalmentEmailInput;

    // Поля для задолженности
    @FindBy(id = "score-arrears")
    private WebElement arrearsAccountInput;

    @FindBy(id = "arrears-sum")
    private WebElement arrearsSumInput;

    @FindBy(id = "arrears-email")
    private WebElement arrearsEmailInput;


    public String getBlockTitle() {
        return blockTitle.getText();
    }
    // Кликнуть по ссылке "Подробнее о сервисе"
    public void clickDetailsLink() {
        detailsLink.click();
    }
    // Выбрать услугу из выпадающего списка по названию
    public void selectService(String serviceName) {
        Select select = new Select(serviceSelect);
        select.selectByVisibleText(serviceName);
    }
    public WebElement getPhoneInput() {
        return phoneInput; // поле с аннотацией @FindBy(id = "connection-phone")
    }
    // Получить выбранную услугу
    public String getSelectedService() {
        Select select = new Select(serviceSelect);
        return select.getFirstSelectedOption().getText();
    }
    // Заполнить форму услуги связи
    public void fillConnectionForm(String phone, String sum, String email) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        sumInput.clear();
        sumInput.sendKeys(sum);

        if (email != null && !email.isEmpty()) {
            emailInput.clear();
            emailInput.sendKeys(email);
        }
    }
    // Кликнуть кнопку Продолжить
    public void clickContinueButton() {
        continueButton.click();
    }
    // Проверить, активна ли кнопка "Продолжить"
    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }
    // Методы для проверки плейсхолдеров

    // Для услуги связи
    public List<String> getConnectionFormPlaceholders() {
        List<String> placeholders = new java.util.ArrayList<>();
        placeholders.add(phoneInput.getAttribute("placeholder"));
        placeholders.add(sumInput.getAttribute("placeholder"));
        placeholders.add(emailInput.getAttribute("placeholder"));
        return placeholders;
    }

    // Для домашнего интернета
    public List<String> getInternetFormPlaceholders() {
        List<String> placeholders = new java.util.ArrayList<>();
        placeholders.add(internetPhoneInput.getAttribute("placeholder"));
        placeholders.add(internetSumInput.getAttribute("placeholder"));
        placeholders.add(internetEmailInput.getAttribute("placeholder"));
        return placeholders;
    }

    // Для рассрочки
    public List<String> getInstalmentFormPlaceholders() {
        List<String> placeholders = new java.util.ArrayList<>();
        placeholders.add(instalmentAccountInput.getAttribute("placeholder"));
        placeholders.add(instalmentSumInput.getAttribute("placeholder"));
        placeholders.add(instalmentEmailInput.getAttribute("placeholder"));
        return placeholders;
    }

    // Для задолженности
    public List<String> getArrearsFormPlaceholders() {
        List<String> placeholders = new java.util.ArrayList<>();
        placeholders.add(arrearsAccountInput.getAttribute("placeholder"));
        placeholders.add(arrearsSumInput.getAttribute("placeholder"));
        placeholders.add(arrearsEmailInput.getAttribute("placeholder"));
        return placeholders;
    }

    public boolean isConnectionFormActive() {
        return phoneInput.isDisplayed();
    }

    public boolean isInternetFormActive() {
        return internetPhoneInput.isDisplayed();
    }

    public boolean isInstalmentFormActive() {
        return instalmentAccountInput.isDisplayed();
    }

    public boolean isArrearsFormActive() {
        return arrearsAccountInput.isDisplayed();
    }
}
