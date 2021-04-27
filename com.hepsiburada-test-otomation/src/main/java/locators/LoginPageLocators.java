package locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public By myAccount = By.id("myAccount");
    public By login = By.cssSelector("a[title='Giriş yap']");
    public By username = By.id("txtUserName");
    public By password = By.id("txtPassword");
    public By btnLogin = By.id("btnLogin");
}
