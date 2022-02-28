package test;

import models.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangeAvatarTest extends TestBase{
    @BeforeMethod
    public void preCondition(){
        app.getUser().initLogin();
        app.getUser().fillLoginForm(Auth.builder().email("olgaludman@gmail.com").password("mK#J7a#mDU6.9Gz").build());
        app.getUser().submitLogin();
    }

    @Test
    public void changeAvatarTest(){
        app.getUser().clickAvatarImg();
        app.getUser().openProfileVisibility();
        app.getUser().navigateToAtlassianProfile();
        app.getUser().pause(2000);
        Assert.assertTrue(app.getUser().getURL().contains("https://id.atlassian.com/manage-profile"));
        app.getUser().initChangePhoto();
        app.getUser().uploadPhoto("C:\\Users\\vanan\\Desktop\\qa telran myproject\\QA_Start_Selenium");
//        app.getUser().returnToTrelloFromAtlassian();
    }
}
