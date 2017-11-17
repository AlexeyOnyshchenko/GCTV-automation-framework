package games;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.game.gameGenrePage;
import page.menu.SideMenuPage;


/**
 * Created by user on 6/30/17.
 */
public class AddGameGenre extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(gameGenrePage.class);

    protected SideMenuPage sideMenuPage;
    protected gameGenrePage gameGenrePage;


    @Test
    public void createGameGenre() {
        sideMenuPage = new SideMenuPage(driver);
        gameGenrePage = new gameGenrePage(driver);

        sideMenuPage.openGameGenresPage();
        gameGenrePage.createNewGameGenreButton();
        gameGenrePage.fillGameGenreFields();
        gameGenrePage.chooseGameGenreSharingOption();
        gameGenrePage.clickOnSubmitButton();

    }
}
