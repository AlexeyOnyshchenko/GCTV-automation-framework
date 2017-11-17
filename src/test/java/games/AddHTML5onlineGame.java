package games;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.game.GamePage;
import page.menu.SideMenuPage;

public class AddHTML5onlineGame extends TestBase{
    private static final Logger LOG = LogFactory.getLogger(GamePage.class);
    protected SideMenuPage sideMenuPage;
    protected GamePage gamePage;

    @Test

    public void addHTML5onlineGame(){
        sideMenuPage = new SideMenuPage(driver);
        gamePage = new GamePage(driver);

        sideMenuPage.openGamesPage();
        gamePage.addNewGameButton();
        gamePage.onlineGameTypeButton();
        gamePage.inputOnlineGameURL();
        gamePage.inputOnlineGameTitle();
        gamePage.inputOnlineGameDescription();
        gamePage.chooseOnlineGameGenre();
        gamePage.onlineSaveChangesButton();

    }
}
