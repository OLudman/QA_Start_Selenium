package manager;

import models.Auth;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.util.ArrayList;

public class UserHelper extends HelperBase {
    public UserHelper(WebDriver wd) {
        super(wd);
    }

    public void initLogin(){
        if(wd.findElement(By.cssSelector("[href='/login']")).isDisplayed())
            click(By.cssSelector("[href='/login']"));
    }

    public void fillLoginForm(String email, String password){
        type(By.id("user"),email);
        click(By.id("login"));
        pause(2000);
        type(By.id("password"), password);
    }

    public void fillLoginForm(User user){
        type(By.id("user"), user.getEmail());
        click(By.id("login"));
        pause(2000);
        type(By.id("password"), user.getPassword());
    }

    public void fillLoginForm(Auth auth){
        type(By.id("user"), auth.getEmail());
        click(By.id("login"));
        pause(2000);
        type(By.id("password"), auth.getPassword());
    }

    public void fillLoginFormWrongEmail(String email, String password){
        type(By.id("user"),email);
        type(By.id("password"), password);
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


    public String textErrorWrongPasswordDisplayed() {
        return elementGetText(By.cssSelector("#login-error span"));
    }

    public void clickAvatarImg() {
        click(By.cssSelector("[data-test-id='header-member-menu-button']"));
    }

    public void openProfileVisibility() {
        click(By.cssSelector("[data-test-id='header-member-menu-profile']"));
    }

    public void navigateToAtlassianProfile() {
        click(By.xpath("//a[text()='Atlassian profile']"));
        ArrayList<String> tabs = new ArrayList<>(wd.getWindowHandles());
        wd.switchTo().window(tabs.get(1));
    }

    public String getURL() {
        return wd.getCurrentUrl();
    }

    public void initChangePhoto() {
//        click(By.cssSelector("[data-test-selector='profile-avatar']"));
        Actions actions = new Actions(wd);
        actions.moveToElement(wd.findElement(By.cssSelector("[data-test-selector='profile-avatar']")))
                .click().perform();
        pause(3000);
        click(By.xpath("//*[text()='Change profile photo']"));
    }

    public void uploadPhoto(String url) {
        wd.findElement(By.id("image-input")).sendKeys(url);
        click(By.xpath("//button[.='Upload']"));
    }
}
