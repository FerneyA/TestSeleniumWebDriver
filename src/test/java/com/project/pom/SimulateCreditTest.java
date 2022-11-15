package com.project.pom;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

import static org.testng.AssertJUnit.assertTrue;

public class SimulateCreditTest {

    WebDriver driver;
    LoginSvpPage loginSvpPage;
    HomeBranchPage homeBranchPage;
    DigitalSimulationPage digitalSimulationPage;

    @BeforeTest
    public void setUp() {
        loginSvpPage = new LoginSvpPage(driver);
        //driver = loginSvpPage.chromeDriverConection();
        driver = loginSvpPage.remoteHubTest();
        loginSvpPage.visit("https://pwpwebqaohs.cajalosandes.cl/mi-sucursal/inicio");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test(dataProvider = "login_data")
    public void test(String user, String password) throws InterruptedException {
        homeBranchPage = new HomeBranchPage(driver);
        digitalSimulationPage = new DigitalSimulationPage(driver);
        loginSvpPage.loginUser(user, password);
        assertTrue("Object on screen was not loaded!!", homeBranchPage.selectDirectAccess("SIMULA TU CRÉDITO"));
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        digitalSimulationPage.simulateCredit();
        assertTrue("Message was not found", digitalSimulationPage.validateSimulationResults("Resultados Simulaci"));
        digitalSimulationPage.requestCredit();
    }

    @DataProvider(name = "login_data")
    public Object[][] getData() {
        return new Object[][] {
                {"116767619", "12345678"}
        };
    }
}
