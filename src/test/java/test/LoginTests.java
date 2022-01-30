package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (isLogged()){
            logout();
        }
    }

    @Test
    public void loginSuccess() throws InterruptedException {
        wd.findElement(By.cssSelector("[href='/login']")).click();
        WebElement email = wd.findElement(By.id("user"));
        email.click();
        email.clear();
        email.sendKeys("olgaludman@gmail.com");
        wd.findElement(By.id("login")).click(); //click enter withAtlassian
        //pause
        Thread.sleep(2000);
        WebElement password = wd.findElement(By.id("password"));
        password.click();
        password.clear();
        password.sendKeys("mK#J7a#mDU6.9Gz");
        wd.findElement(By.cssSelector("#login-submit")).click(); // submit form
        Assert.assertTrue(wd.findElements(By.cssSelector("[aria-label='Open member menu']")).size()>0);
        Assert.assertTrue(wd.findElement(By.cssSelector("[aria-label='Open member menu']")).isDisplayed());
    }

    @Test
    public void LoginSuccessNew(){
        initLogin();
        fillLoginForm("olgaludman@gmail.com", "mK#J7a#mDU6.9Gz");
        submitLogin();

        Assert.assertTrue(isAvatarPresent());
    }

    @Test
    public void loginUnsuccessfulWithWrongEmail(){
        initLogin();
        fillLoginFormWrongEmail("olgaludmangmail.com", "mK#J7a#mDU6.9Gz");
        submitLoginWithError();

        pause(1000);

        Assert.assertEquals(textErrorMessage(),"There isn't an account for this username");
    }

}
