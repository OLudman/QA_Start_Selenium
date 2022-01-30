package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

public class TestBase {
    static WebDriver wd;

    @BeforeSuite
    public void setUp(){
        wd = new ChromeDriver();
        wd.navigate().to("https://trello.com/");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
    }

    @AfterSuite
    public void tearDown(){
        wd.quit();
    }

    //***************************************************************

    public void type(By locator, String text){
        if(text!=null && text.isEmpty()){
            WebElement element = wd.findElement(locator);
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public void initLogin(){
        click(By.cssSelector("[href='/login']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.id("user"),email);
        click(By.id("login"));
        pause(2000);
        type(By.id("password"), password);
    }

    public void fillLoginFormWrongEmail(String email, String password){
        type(By.id("user"),email);
        type(By.id("password"), password);
    }

    public void pause(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submitLogin(){
        click(By.cssSelector("#login-submit"));
    }

    public void submitLoginWithError(){
        click(By.id("login"));
    }

    public boolean isAvatarPresent(){
        return isElementPresent(By.cssSelector("[aria-label='Open member menu']"));
    }

    public boolean isElementPresent(By locator){
        return wd.findElements(locator).size()>0;
    }

    public boolean isLogged() {
        return isAvatarPresent();
    }

    public void logout(){
        click(By.cssSelector("[aria-label='Open member menu']"));
        click(By.xpath("//*[text()='Log out']"));
        click(By.id("logout-submit"));
    }

    public String textErrorMessage(){
        return elementGetText(By.cssSelector("#error p"));
    }

    public String elementGetText(By locator){
        return wd.findElement(locator).getText();
    }

}
