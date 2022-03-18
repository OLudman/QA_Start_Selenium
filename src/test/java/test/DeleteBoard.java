package test;

import models.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteBoard extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getUser().isLoginButtonPresent()){
            app.getUser().initLogin();
            app.getUser().fillLoginForm(Auth.builder().email("olgaludman@gmail.com").password("mK#J7a#mDU6.9Gz").build());
            app.getUser().submitLogin();
        }

        app.getBoard().providerBoards();
    }

    @Test
    public void deleteOneBoard(){
        int countBoardsBefore= app.getBoard().getBoardCountDownList();
        logger.info("Count of boards before test was: " + countBoardsBefore);
        app.getBoard().pause(5000);
        app.getBoard().selectBoard();
        app.getBoard().openSideBoardMenu();
        app.getBoard().openMore();
        app.getBoard().closeBoard();
        int countBoardsAfter= app.getBoard().getBoardCountUpList();
        logger.info("Count of boards after test: " + countBoardsAfter);
        Assert.assertEquals(countBoardsBefore, countBoardsAfter +1);
    }
}
