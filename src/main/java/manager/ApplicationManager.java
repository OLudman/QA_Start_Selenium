package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
//    WebDriver wd;
    UserHelper user;
    BoardHelper board;
    EventFiringWebDriver wd;
    String browser;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init(){
        if (browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd= new EventFiringWebDriver(new FirefoxDriver());
        }        wd.navigate().to("https://trello.com/");
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wd.manage().window().maximize();
        user = new UserHelper(wd);
        board = new BoardHelper(wd);
        wd.register(new MyListener());
    }

    public BoardHelper getBoard() {
        return board;
    }

    public UserHelper getUser() {
        return user;
    }

    public void stop(){
        wd.quit();
    }
}
