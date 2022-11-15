package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DigitalSimulationPage extends Base {

    By txtRequestedAmount = By.xpath("//div[@class='ant-spin-container']//input[@class='ant-input sml-input']");
    By txtDues = By.cssSelector("input[class='ant-input-number-input']");
    By selExpandedMonthOfGrace = By.xpath("//div[@class='ant-row sml-credit']//div[@class='sml-select ant-select ant-select-open ant-select-focused ant-select-enabled']");
    By selMonthOfGrace = By.xpath("//div[@class='ant-row sml-credit']//div[@class='sml-select ant-select ant-select-enabled']");
    By btnSimulate = By.xpath("//div[@class='ant-spin-container']//button[@type='button']");
    By lblSimulationResults = By.xpath("//div[@class='ant-row sml-credit-result']/h3");
    By btnRequestCredit = By.xpath("//main[@class='ant-layout-content']//button[@class='ant-btn next-btn buttonSimulation2']");

    public DigitalSimulationPage(WebDriver driver) {
        super(driver);
    }

    public void simulateCredit() throws InterruptedException {
        Thread.sleep(20000);
        explicitWaitElementToBeClickable(txtRequestedAmount);
        clearTextField(txtRequestedAmount);
        typeWithEnter("2.000.000", txtRequestedAmount);
        type("50", txtDues);
        selectDropDown(selMonthOfGrace);
        selectDropDown(selExpandedMonthOfGrace);
        performScrollDown();
        explicitWaitElementToBeClickable(btnSimulate).click();
    }

    public Boolean validateSimulationResults(String text) {
        return explicitWaitTextToBePresentInElement(lblSimulationResults, text);
    }

    public void requestCredit() {
        performScrollDown();
        explicitWaitElementToBeClickable(btnRequestCredit);
        click(btnRequestCredit);
    }
}
