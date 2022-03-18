package test;

import models.Auth;
import models.Board;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewBoardTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        //login
        app.getUser().initLogin();
        app.getUser().fillLoginForm(Auth.builder().email("olgaludman@gmail.com").password("mK#J7a#mDU6.9Gz").build());
        app.getUser().submitLogin();
    }

    @Test
    public void newBoardSuccess(){
        Board board = Board.builder().title("Blue1").color("Blue").build();
        app.getBoard().initCreationBoard();
        app.getBoard().fillBoardCreationForm(board);
        app.getBoard().submitBoardCreation();
        app.getBoard().pause(3000);
        app.getBoard().returnToHome();
        app.getBoard().pause(4000);
        Assert.assertTrue(app.getBoard().isBoardCreatedByTitle(board));

    }
}
