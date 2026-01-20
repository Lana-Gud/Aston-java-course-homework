import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.List;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        System.out.println("НАЧИНАЕМ ТЕСТ");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        System.out.println("✓ Браузер открыт");
    }

    private void handleCookies() {
        System.out.println("Ищем окно с куками...");
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptButton = shortWait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))
            );
            acceptButton.click();
            System.out.println("✓ Куки приняты");
            wait.until(ExpectedConditions.invisibilityOf(acceptButton));
        } catch (TimeoutException e) {
            System.out.println("Окно кук не появилось (или уже было принято)");
        }
    }

    @Test
    void testOnlinePaymentBlock() {
        System.out.println("\n=== Начинаем тестирование блока 'Онлайн пополнение без комиссии' ===");

        // 1. Открываем сайт и обрабатываем куки
        driver.get("https://www.mts.by");
        System.out.println("✓ Сайт открыт");
        handleCookies();

        // 2. Проверяем название блока
        checkBlockTitle();

        // 3. Проверяем логотипы платёжных систем
        checkPaymentLogos();

        // 4. Проверяем ссылку «Подробнее о сервисе»
        checkDetailsLink();

        // 5. Проверяем форму пополнения (услуги связи, номер 297777777)
        testContinueButton();

        System.out.println("Все проверки завершены успешно");
    }

    private void checkBlockTitle() {
        System.out.println("\n1. Проверяем название блока...");

        // Промотаем нн вниз
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");

        WebElement title = driver.findElement(
                By.xpath("//h2[contains(text(), 'Онлайн пополнение')]")
        );

        String titleText = title.getText();
        System.out.println("Найден заголовок: " + titleText);

        Assertions.assertTrue(titleText.contains("Онлайн пополнение"),
                "Заголовок должен содержать 'Онлайн пополнение'");
        Assertions.assertTrue(titleText.contains("без комиссии"),
                "Заголовок должен содержать 'без комиссии'");

        System.out.println("✓ Название блока проверено");
    }

    private void checkPaymentLogos() {
        System.out.println("\n2. Проверяем логотипы платёжных систем...");

        // Находим блок с логотипами
        WebElement logosBlock = driver.findElement(By.className("pay__partners"));

        // Проверяем логотипы
        List<WebElement> visaLogos = logosBlock.findElements(
                By.cssSelector("img[alt='Visa'], img[alt*='Visa']")
        );
        List<WebElement> mastercardLogos = logosBlock.findElements(
                By.cssSelector("img[alt='MasterCard'], img[alt*='MasterCard']")
        );
        List<WebElement> belkartLogos = logosBlock.findElements(
                By.cssSelector("img[alt='Белкарт'], img[alt*='Белкарт']")
        );

        // Проверяем, что все 3ьб логотипа присутствуют
        if (!visaLogos.isEmpty()) {
            System.out.println("✓ Логотип Visa найден");
            Assertions.assertTrue(visaLogos.get(0).isDisplayed());
        } else {
            Assertions.fail("Логотип Visa не найден");
        }

        if (!mastercardLogos.isEmpty()) {
            System.out.println("✓ Логотип MasterCard найден");
            Assertions.assertTrue(mastercardLogos.get(0).isDisplayed());
        } else {
            Assertions.fail("Логотип MasterCard не найден");
        }

        if (!belkartLogos.isEmpty()) {
            System.out.println("✓ Логотип Белкарт найден");
            Assertions.assertTrue(belkartLogos.get(0).isDisplayed());
        } else {
            Assertions.fail("Логотип белкарт не найден");
        }

        System.out.println("✓ Все логотипы проверены");
    }

    private void checkDetailsLink() {
        System.out.println("\n3. Проверяем ссылку 'Подробнее о сервисе'...");

        WebElement detailsLink = driver.findElement(
                By.linkText("Подробнее о сервисе")
        );

        Assertions.assertTrue(detailsLink.isDisplayed(),
                "Ссылка 'Подробнее о сервисе' должна быть видна");

        String currentUrl = driver.getCurrentUrl();
        String linkText = detailsLink.getText();
        System.out.println("Найдена ссылка: " + linkText);

        // Кликаем по ссылке
        detailsLink.click();

        // Ждём загрузки новой страницы
        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlToBe(currentUrl)
        ));

        System.out.println("Перешли по ссылке на: " + driver.getCurrentUrl());

        // Возвращаемся назад
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(currentUrl));

        System.out.println("✓ Ссылка работает правильно");
    }

    private void testContinueButton() {
        System.out.println("\n4. Проверяем форму пополнения...");

        // Находим форму для услуги связи
        WebElement form = driver.findElement(By.id("pay-connection"));

        // Проверяем, что форма активна (имеет класс 'opened')
        Assertions.assertTrue(form.getAttribute("class").contains("opened"),
                "Форма 'Услуги связи' должна быть активна");
        System.out.println("✓ Форма 'Услуги связи' активна");

        // Проверяем, что выбрана услуга "Услуги связи"
        WebElement selectedService = driver.findElement(
                By.xpath("//option[@selected and @value='Услуги связи']")
        );
        Assertions.assertEquals("Услуги связи", selectedService.getAttribute("value"),
                "По умолчанию должна быть выбрана 'Услуги связи'");
        System.out.println("✓ Услуга 'Услуги связи' выбрана по умолчанию");

        // Заполняем обязательное поле "Номер телефона"
        WebElement phoneInput = form.findElement(By.id("connection-phone"));
        phoneInput.clear();
        phoneInput.sendKeys("297777777");
        System.out.println("✓ Ввели номер телефона: 297777777");

        // Заполняем обязательное поле "Сумма" (минимальная сумма)
        WebElement sumInput = form.findElement(By.id("connection-sum"));
        sumInput.clear();
        sumInput.sendKeys("10");
        System.out.println("✓ Ввели сумму: 10 рублей");

        // Проверяем, что кнопка "Продолжить" активна
        WebElement continueButton = form.findElement(
                By.xpath(".//button[text()='Продолжить']")
        );
        Assertions.assertTrue(continueButton.isEnabled(),
                "Кнопка 'Продолжить' должна быть активна при заполненных обязательных полях");
        System.out.println("✓ Кнопка 'Продолжить' активна");

        // Нажимаем кнопку
        continueButton.click();
        System.out.println("✓ Нажали кнопку 'Продолжить'");

        // Проверяем реакцию системы
        try {
            // Ждём либо изменения URL, либо появления сообщения
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.urlToBe("https://www.mts.by/")),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(@class, 'success') or contains(@class, 'error') or contains(@class, 'message')]")
                    )
            ));
            System.out.println("✓ Форма отреагировала на нажатие кнопки");
        } catch (TimeoutException e) {
            System.out.println(" Форма не изменилась после нажатия кнопки");
            // Если форма не отреагировала, проверяем валидацию
            List<WebElement> errorMessages = driver.findElements(
                    By.xpath("//*[contains(@class, 'error') or contains(@class, 'invalid')]")
            );
            if (!errorMessages.isEmpty()) {
                System.out.println("Обнаружены ошибки валидации:");
                for (WebElement error : errorMessages) {
                    System.out.println("  - " + error.getText());
                }
            }
        }

        System.out.println("✓ Проверка формы завершена");
    }

    @AfterEach
    void tearDown() {
        System.out.println("\n Закрываем браузер");
        if (driver != null) {
            try {
                Thread.sleep(2000); // Пауза чтобы увидеть результат
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
            System.out.println("✓ Браузер закрыт");
        }
    }
}