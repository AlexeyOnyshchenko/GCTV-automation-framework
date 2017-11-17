package games;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.game.GamePage;
import page.menu.SideMenuPage;

public class AddSteamGame extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(GamePage.class);
    protected SideMenuPage sideMenuPage;
    protected GamePage gamePage;

    @Test

    public void addSteamGame(){
        sideMenuPage = new SideMenuPage(driver);
        gamePage = new GamePage(driver);

        sideMenuPage.openGamesPage();
        gamePage.addNewGameButton();
        gamePage.steamGameTypeButton();
        gamePage.inputSteamURL();
        gamePage.inputGameTitle();
        gamePage.inputGameDescription();
        gamePage.chooseSteamGameGenre();
        gamePage.chooseSteamGameDisctribution();
        gamePage.saveChangesButton();

    }

}
