package test;

import models.Auth;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if (app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

//    @Test
//    public void loginSuccess() throws InterruptedException {
//        wd.findElement(By.cssSelector("[href='/login']")).click();
//        WebElement email = wd.findElement(By.id("user"));
//        email.click();
//        email.clear();
//        email.sendKeys("olgaludman@gmail.com");
//        wd.findElement(By.id("login")).click(); //click enter withAtlassian
//        //pause
//        Thread.sleep(2000);
//        WebElement password = wd.findElement(By.id("password"));
//        password.click();
//        password.clear();
//        password.sendKeys("mK#J7a#mDU6.9Gz");
//        wd.findElement(By.cssSelector("#login-submit")).click(); // submit form
//        Assert.assertTrue(wd.findElements(By.cssSelector("[aria-label='Open member menu']")).size()>0);
//        Assert.assertTrue(wd.findElement(By.cssSelector("[aria-label='Open member menu']")).isDisplayed());
//    }

    @Test
    public void LoginSuccessNew(){
        app.getUser().initLogin();
        app.getUser().fillLoginForm("olgaludman@gmail.com", "mK#J7a#mDU6.9Gz");
        app.getUser().submitLogin();

        Assert.assertTrue(app.getUser().isAvatarPresent());
    }

    @Test
    public void LoginSuccessNewModel(){

        app.getUser().initLogin();
        app.getUser().fillLoginForm(new User().withEmail("olgaludman@gmail.com").withPassword("mK#J7a#mDU6.9Gz"));
        app.getUser().submitLogin();

        Assert.assertTrue(app.getUser().isAvatarPresent());
    }

    @Test
    public void LoginSuccessNewModelLombook(){

        Auth auth = Auth.builder().email("olgaludman@gmail.com").password("mK#J7a#mDU6.9Gz").build();

        app.getUser().initLogin();
        app.getUser().fillLoginForm(new User().withEmail("olgaludman@gmail.com").withPassword("mK#J7a#mDU6.9Gz"));
        app.getUser().submitLogin();

        Assert.assertTrue(app.getUser().isAvatarPresent());
    }

    @Test
    public void loginUnsuccessfulWithWrongEmail(){
        app.getUser().initLogin();
        app.getUser().fillLoginFormWrongEmail("olgaludmangmail.com", "mK#J7a#mDU6.9Gz");
        app.getUser().submitLoginWithError();

        app.getUser().pause(1000);

        Assert.assertEquals(app.getUser().textErrorMessage(),"There isn't an account for this username");
    }

    @Test
    public void loginUnsuccessfulWithWrongPassword(){
        app.getUser().initLogin();
        app.getUser().fillLoginForm("olgaludman@gmail.com","Olya");
        app.getUser().submitLogin();
        app.getUser().pause(1000);
        Assert.assertTrue(app.getUser().textErrorWrongPasswordDisplayed().contains("Incorrect email address"));
    }

}
