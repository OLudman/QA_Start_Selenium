package manager;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import models.Board;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public int getBoardCountUpList(){
        return wd.findElements(By.cssSelector(".content-all-boards>div>div:nth-child(2) li")).size();
    }

    public int getBoardCountDownList(){
        return wd.findElements(By.cssSelector(".content-all-boards>div>div:nth-child(3) li")).size()-1;
    }

    public void providerBoards(){
        int count = getBoardCountUpList();
        System.out.println(count);
        if(count<9){
            int index = (int) (System.currentTimeMillis()/1000)%3600;
            Board board = Board.builder().title("My board - " +index).color("Green").build();
            initCreationBoard();
            fillBoardCreationForm(board);
            submitBoardCreation();
            pause(2000);
            returnToHome();
        }
    }

    public void selectBoard() {
        click(By.cssSelector(".board-title-details"));
    }

    public void openSideBoardMenu() {
        click(By.cssSelector(".js-show-sidebar"));
    }

    public void openMore() {
        click(By.cssSelector(".js-open-more"));
    }

    public void closeBoard() {
        click(By.cssSelector(".js-close-board"));
        click(By.cssSelector(".js-confirm"));
        new WebDriverWait(wd,10)
                .until(ExpectedConditions.visibilityOf
                        (wd.findElement(By.cssSelector("[data-test-id='close-board-big-message']"))));
        click(By.cssSelector("[data-test-id='close-board-delete-board-button']"));
        click(By.cssSelector("[data-test-id='close-board-delete-board-confirm-button']"));
        new WebDriverWait(wd,10)
                .until(ExpectedConditions.visibilityOf(wd.findElement(By.xpath("//h3text() YOUR WORKSPACES"))));//to write
    }
}
