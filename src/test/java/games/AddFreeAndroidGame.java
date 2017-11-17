package games;


import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.game.GamePage;
import page.menu.SideMenuPage;



public class AddFreeAndroidGame extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(GamePage.class);
    protected SideMenuPage sideMenuPage;
    protected GamePage GamePage;


    @Test

    public void addFreeAndroidGame() {
        sideMenuPage = new SideMenuPage(driver);
        GamePage = new GamePage(driver);

        sideMenuPage.openGamesPage();
        GamePage.addNewGameButton();
        GamePage.mobileTypeGameButton();
        GamePage.inputAndroidGameURL();
        GamePage.chooseGameGenre();
        GamePage.chooseGameDestribution();
        GamePage.gameSaveChanges();


    }
}
