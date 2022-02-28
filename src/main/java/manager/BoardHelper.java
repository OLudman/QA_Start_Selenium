package manager;

import models.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BoardHelper extends HelperBase{
    public BoardHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreationBoard() {
        click(By.cssSelector("[data-test-id = 'create-board-tile']"));
    }

    public void fillBoardCreationForm(Board board) {
        type(By.cssSelector("[data-test-id='create-board-title-input'']"), board.getTitle());
        String locator = String.format("button[title='%s']",board.getColor());
        click(By.cssSelector(locator));
    }

    public void submitBoardCreation() {
        click(By.cssSelector("[data-test-id='create-board-submit-button']"));
    }

    public void returnToHome() {
        click(By.cssSelector("[aria-label='Back to home']"));
    }

    public boolean isBoardCreatedByTitle(Board board) {
        List<WebElement> list = wd.findElements(By.cssSelector("div.content-all-boards>dive>div:nth-child(2) ul div[title]"));
        //div.content-all-boards>dive>div:nth-child(2) ul div[title]
        for (WebElement el:list){
            el.getAttribute("title").equals(board.getTitle());
            return true;
        }
        return false;
    }
}
