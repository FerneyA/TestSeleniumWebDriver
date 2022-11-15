package com.project.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginSvpPage extends Base {

    By txtRutUser = By.name("username");
    By txtPassword = By.name("password");
    By btnEnter = By.id("botonLogin");

    public LoginSvpPage(WebDriver driver) {
        super(driver);
    }

    public void loginUser(String user, String password) throws InterruptedException {
        type(user, txtRutUser);
        type(password, txtPassword);
        click(btnEnter);
    }
}
