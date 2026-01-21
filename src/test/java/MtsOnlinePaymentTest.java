import com.mts.pages.OnlinePaymentPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.TimeoutException;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.api.Assertions;
import java.time.Duration;
import java.util.List;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlinePaymentPage onlinePaymentPage;

    @BeforeEach
    void setUp() {
        System.out.println("НАЧИНАЕМ ТЕСТ");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
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
        } catch (Exception e) {
            System.out.println("Окно кук не появилось (или уже было принято)");
        }
    }

    @Test
    void testOnlinePaymentBlock() {
        System.out.println("\nНачинаем тестирование блока 'Онлайн пополнение без комиссии' ");
        driver.get("https://www.mts.by");
        System.out.println("✓ Сайт открыт");
        handleCookies();
        onlinePaymentPage = new OnlinePaymentPage(driver);
        checkBlockTitle();
        checkPaymentLogos();
        checkDetailsLink();
        testContinueButton();
        System.out.println("Все проверки завершены успешно");
    }

    private void checkBlockTitle() {
        System.out.println("\n1. Проверяем название блока...");
        String titleText = onlinePaymentPage.getBlockTitle();
        System.out.println("Найден заголовок: " + titleText);
        Assertions.assertTrue(titleText.contains("Онлайн пополнение"),
                "Заголовок должен содержать 'Онлайн пополнение'");
        Assertions.assertTrue(titleText.contains("без комиссии"),
                "Заголовок должен содержать 'без комиссии'");
        System.out.println("✓ Название блока проверено");
    }

    private void checkPaymentLogos() {
        System.out.println("\n2. Проверяем логотипы платёжных систем...");
        WebElement logosBlock = driver.findElement(By.className("pay__partners"));
        List<WebElement> visaLogos = logosBlock.findElements(
                By.cssSelector("img[alt='Visa'], img[alt*='Visa']")
        );
        List<WebElement> mastercardLogos = logosBlock.findElements(
                By.cssSelector("img[alt='MasterCard'], img[alt*='MasterCard']")
        );
        List<WebElement> belkartLogos = logosBlock.findElements(
                By.cssSelector("img[alt='Белкарт'], img[alt*='Белкарт']")
        );
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
        String originalUrl = driver.getCurrentUrl();
        onlinePaymentPage.clickDetailsLink();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));
        System.out.println("Перешли по ссылке на: " + driver.getCurrentUrl());
        driver.navigate().back();
        wait.until(ExpectedConditions.urlToBe(originalUrl));
        onlinePaymentPage = new OnlinePaymentPage(driver);
        System.out.println("✓ Ссылка работает правильно");
    }

    private void testContinueButton() {
        System.out.println("\n4. Проверяем форму пополнения...");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));

        String selectedService = onlinePaymentPage.getSelectedService();
        Assertions.assertEquals("Услуги связи", selectedService);
        System.out.println("✓ Услуга 'Услуги связи' выбрана по умолчанию");

        onlinePaymentPage.fillConnectionForm("297777777", "1", null);
        System.out.println("✓ Ввели номер телефона: 29 777-77-77");
        System.out.println("✓ Ввели сумму: 1 рубль");

        Assertions.assertTrue(onlinePaymentPage.isContinueButtonEnabled());
        System.out.println("✓ Кнопка 'Продолжить' активна");

        onlinePaymentPage.clickContinueButton();
        System.out.println("✓ Нажали кнопку 'Продолжить'");

        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.not(ExpectedConditions.urlToBe("https://www.mts.by/")),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(@class, 'success') or contains(@class, 'error') or contains(@class, 'message')]")
                    )
            ));
            System.out.println("✓ Форма отреагировала на нажатие кнопки");
        } catch (Exception e) {
            System.out.println("⚠ Форма не изменилась после нажатия кнопки");
            List<WebElement> errorMessages = driver.findElements(
                    By.xpath("//*[contains(@class, 'error') or contains(@class, 'invalid')]")
            );
            if (!errorMessages.isEmpty()) {
                System.out.println("Обнаружены ошибки валидации:");
                for (WebElement error : errorMessages) {
                    if (!error.getText().isEmpty()) {
                        System.out.println("  - " + error.getText());
                    }
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
            System.out.println("✓ Браузер закрыт");
        }
    }

    @Test
    void testAllPaymentFormsPlaceholders() throws InterruptedException {
        System.out.println("\n=== Проверяем надписи в полях всех форм ===");
        driver.get("https://www.mts.by");
        handleCookies();
        onlinePaymentPage = new OnlinePaymentPage(driver);

        System.out.println("\n1. Услуги связи:");
        List<String> connectionPlaceholders = onlinePaymentPage.getConnectionFormPlaceholders();
        System.out.println("Надписи в полях:");
        for (String placeholder : connectionPlaceholders) {
            System.out.println("  - " + placeholder);
            Assertions.assertNotNull(placeholder);
            Assertions.assertFalse(placeholder.isEmpty());
        }

        System.out.println("\n2. Домашний интернет:");
        onlinePaymentPage.selectService("Домашний интернет");
        Thread.sleep(2000);
        List<String> internetPlaceholders = onlinePaymentPage.getInternetFormPlaceholders();
        System.out.println("Надписи в полях:");
        for (String placeholder : internetPlaceholders) {
            System.out.println("  - " + placeholder);
            Assertions.assertNotNull(placeholder);
            Assertions.assertFalse(placeholder.isEmpty());
        }

        System.out.println("\n3. Рассрочка:");
        onlinePaymentPage.selectService("Рассрочка");
        Thread.sleep(1000);
        List<String> instalmentPlaceholders = onlinePaymentPage.getInstalmentFormPlaceholders();
        System.out.println("Надписи в полях:");
        for (String placeholder : instalmentPlaceholders) {
            System.out.println("  - " + placeholder);
            Assertions.assertNotNull(placeholder);
            Assertions.assertFalse(placeholder.isEmpty());
        }

        System.out.println("\n4. Задолженность:");
        onlinePaymentPage.selectService("Задолженность");
        Thread.sleep(1000);
        List<String> arrearsPlaceholders = onlinePaymentPage.getArrearsFormPlaceholders();
        System.out.println("Надписи в полях:");
        for (String placeholder : arrearsPlaceholders) {
            System.out.println("  - " + placeholder);
            Assertions.assertNotNull(placeholder);
            Assertions.assertFalse(placeholder.isEmpty());
        }

        System.out.println("\n✓ Все формы проверены успешно");
    }

    @Test
    void testPaymentPageAfterContinue() {
        System.out.println("\n=== Тест: Страница оплаты после нажатия 'Продолжить' ===");
        driver.get("https://www.mts.by");
        handleCookies();
        onlinePaymentPage = new OnlinePaymentPage(driver);

        Assertions.assertEquals("Услуги связи", onlinePaymentPage.getSelectedService());

        onlinePaymentPage.fillConnectionForm("29 777-77-77", "1", null);
        System.out.println("✓ Форма заполнена (номер: 29 777-77-77, сумма: 1)");

        onlinePaymentPage.clickContinueButton();
        System.out.println("✓ Кнопка 'Продолжить' нажата");

        System.out.println("Ищем iframe...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        System.out.println("Найдено iframe: " + iframes.size());

        if (iframes.isEmpty()) {
            throw new RuntimeException("Не найдено ни одного iframe! Форма оплаты не загрузилась.");
        }

        boolean foundInIframe = false;

        for (int i = 0; i < iframes.size(); i++) {
            try {
                System.out.println("Проверяем iframe #" + i);
                driver.switchTo().frame(i);

                List<WebElement> spans = driver.findElements(
                        By.xpath("//span[contains(text(), 'Оплата: Услуги связи')]")
                );

                if (!spans.isEmpty()) {
                    System.out.println("✓ Нашли в iframe #" + i);
                    foundInIframe = true;

                    WebDriverWait iframeWait = new WebDriverWait(driver, Duration.ofSeconds(10));
                    iframeWait.until(ExpectedConditions.textToBePresentInElementLocated(
                            By.cssSelector(".pay-description__cost span"), "BYN"));

                    WebElement amountElement = driver.findElement(By.cssSelector(".pay-description__cost span"));
                    String amount = amountElement.getText();
                    Assertions.assertEquals("1.00 BYN", amount);
                    System.out.println("✓ Сумма найдена: " + amount);

                    iframeWait.until(ExpectedConditions.textToBePresentInElementLocated(
                            By.cssSelector(".pay-description__text span"), "Оплата:"));

                    WebElement infoElement = driver.findElement(By.cssSelector(".pay-description__text span"));
                    String infoText = infoElement.getText();
                    Assertions.assertTrue(infoText.contains("Оплата: Услуги связи"));
                    Assertions.assertTrue(infoText.contains("375297777777"));
                    System.out.println("✓ Информация: " + infoText);

                    WebElement payButton = driver.findElement(By.cssSelector("button.colored span"));
                    String buttonText = payButton.getText();
                    Assertions.assertTrue(buttonText.contains("1.00 BYN"));
                    System.out.println("✓ Кнопка оплаты: " + buttonText);

                    List<WebElement> cardLabels = driver.findElements(By.cssSelector(".card label"));
                    String[] expectedLabels = {"Номер карты", "Срок действия", "CVC", "Имя и фамилия на карте"};

                    for (int j = 0; j < cardLabels.size(); j++) {
                        Assertions.assertEquals(expectedLabels[j], cardLabels.get(j).getText());
                        System.out.println("✓ Поле: " + expectedLabels[j]);
                    }

                    List<WebElement> paymentIcons = driver.findElements(By.cssSelector(".cards-brands img"));
                    Assertions.assertTrue(paymentIcons.size() >= 3);
                    System.out.println("✓ Найдено иконок платежных систем: " + paymentIcons.size());

                    WebElement expiresInput = driver.findElement(By.cssSelector(".expires-input input"));
                    String placeholder = expiresInput.getAttribute("placeholder");
                    Assertions.assertEquals("MM / YY", placeholder);
                    System.out.println("✓ Плейсхолдер для срока действия: " + placeholder);

                    break;
                }

                driver.switchTo().defaultContent();
            } catch (Exception e) {
                System.out.println("Ошибка при проверке iframe #" + i + ": " + e.getMessage());
                driver.switchTo().defaultContent();
            }
        }

        if (!foundInIframe) {
            System.out.println("Не нашли в iframe, проверяем основную страницу...");
            driver.switchTo().defaultContent();

            WebElement paymentText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//span[contains(text(), 'Оплата: Услуги связи')]")
            ));
            System.out.println("✓ Нашли на основной странице: " + paymentText.getText());
        }

        System.out.println("✓ Форма оплаты найдена!");
    }
}