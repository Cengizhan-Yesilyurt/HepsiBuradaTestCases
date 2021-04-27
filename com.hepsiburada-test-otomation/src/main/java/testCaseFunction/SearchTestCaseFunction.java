package testCaseFunction;

import locators.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import utils.BasePage;


public class SearchTestCaseFunction extends BasePage {
    WebElement element = null;
    Actions actions;
    LoginPageLocators loginPage;
    SearchPageLocators searchPage;
    SelectCategoryLocators selectCategory;
    ProductDetailPageLocators pdLocators;
    AccountPageLocators accLocators;
    String productText;

    public static Logger logger = LogManager.getLogger(SearchTestCaseFunction.class);

    public SearchTestCaseFunction(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);

    }

    public void login() throws InterruptedException {
        loginPage = new LoginPageLocators();
        Thread.sleep(2000);
        actions.moveToElement(findElement(loginPage.myAccount)).build().perform();
        Thread.sleep(2000);
        click(loginPage.login);
        sendKeys(loginPage.username, Constants.userName);
        logger.info("kullanıcı adı girildi");
        sendKeys(loginPage.password, Constants.userPassword);
        logger.info("kullanıcı şifresi girildi");
        click(loginPage.btnLogin);
        logger.info("giriş butonuna tıklandı");

    }

    public void search() {

        searchPage = new SearchPageLocators();
        sendKeys(searchPage.inputLabel, Constants.searchByText);
        logger.info("arama lable ına "+Constants.searchByText+" yazıldı");
        click(searchPage.searchButton);
        logger.info("arama butonuna tıklandı");

    }

    public void searchNavBar() throws InterruptedException {
        selectCategory = new SelectCategoryLocators();
        Thread.sleep(2000);
        click(selectCategory.mainCategory);
        logger.info("ana kategori seçildi");
        Thread.sleep(2000);
        click(selectCategory.subCategory);
        logger.info("alt kategor, seçildi");
        String numberOfProducts = getText(selectCategory.productQuantity);
        int sayi = Integer.parseInt(numberOfProducts);
        Assert.assertTrue(sayi > 0, "Ürün Bulunamadı");
        logger.info("Ürün bulunamadı kontolü yapıldı");
    }

    public void nextPage() {

        selectCategory = new SelectCategoryLocators();
        element = findElement(selectCategory.secondPage);
        moveDown(element);
        element.click();
        element = null;
        logger.info("Bir sonraki sayfaya geçildi");
    }

    public void getNthProduct() {

        searchPage = new SearchPageLocators();
        element = findElementOnPage(searchPage.products, (Constants.nthProduct - 1));
        productText = element.findElement(searchPage.getProductHeaderText).getText();
        moveDown(element);
        element.click();
        logger.info(Constants.nthProduct+". eleman seçildi");
        element = null;
    }


    public void like() {
        pdLocators = new ProductDetailPageLocators();
        click(pdLocators.like);
        Assert.assertEquals(getText(pdLocators.popup), "Ürün listenize eklendi.");
        logger.info("ürün beğenildi");
    }

    public void goToLiked() throws InterruptedException {
        accLocators = new AccountPageLocators();
        Thread.sleep(2000);
        actions.moveToElement(findElement(loginPage.myAccount)).build().perform();
        Thread.sleep(2000);
        click(accLocators.likedProducts);
        Assert.assertEquals(getText(accLocators.productTextAfterCart), productText);
        logger.info("Beğendiklerim alanına gidildi");
    }

    public void addBasket() throws InterruptedException {
        accLocators = new AccountPageLocators();
        Thread.sleep(2000);
        actions.moveToElement(findElement(accLocators.goProduct)).build().perform();
        Thread.sleep(1000);
        click(accLocators.selectProduct);
        Assert.assertEquals(getText(accLocators.popupText), "Ürün sepete eklendi");
        logger.info("Ürün sepete eklendi");
    }

    public void gotoBasket() throws InterruptedException {
        accLocators = new AccountPageLocators();
        Thread.sleep(2000);
        click(accLocators.closePopup);
        click(accLocators.cart);
        logger.info("Sepete gidildi");
    }

    public void removeProduct() {
        accLocators = new AccountPageLocators();
        actions.moveToElement(findElement(accLocators.moveToContainer)).build().perform();
        click(accLocators.btnDelete);
        logger.info("ürün sepetten silindi");
    }

    public void isEmptyBasket() {
        Assert.assertEquals(getText(accLocators.isEmpty), "Sepetin şu an boş");
        logger.info("Sepet kontrol edildi");
    }

}


