package com.project.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import static org.testng.AssertJUnit.fail;

public class Base {

    private WebDriver driver;

    Base(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeDriverConection() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/USUARIO/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public WebDriver remoteHubTest() {
        try {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setPlatform(Platform.WINDOWS);
            URL hubURL = new URL("http://localhost:4444/");
            driver = new RemoteWebDriver(hubURL, desiredCapabilities);
            driver.manage().window().maximize();
            return driver;
        } catch (MalformedURLException e) {
            fail(e.getMessage());
            return driver;
        }
    }

    public void visit(String url) {
        driver.get(url);
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    public String getText(WebElement webElement) {
        return webElement.getText();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    public void typeWithEnter(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
        driver.findElement(locator).sendKeys(Keys.ENTER);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public Boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clearTextField(By locator) {
        driver.findElement(locator).clear();
    }

    public WebElement fluentWait(final By locator) {
        // Waiting 30 seconds for an element to be present on the page, checking
        // for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
        WebElement webElement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return webElement;
    }

    public WebElement explicitWaitVisibilityOfElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement explicitWaitElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public Boolean explicitWaitTextToBePresentInElement(By locator, String text) {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    public void performScrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    public void selectDropDown(By locator) {
        WebElement webElement = driver.findElement(locator);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", webElement);
    }
}
