package games;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.game.gameGenrePage;
import page.menu.SideMenuPage;

public class DeleteGameGenre extends TestBase{
    private static final Logger LOG = LogFactory.getLogger(gameGenrePage.class);
    protected SideMenuPage sideMenuPage;
    protected gameGenrePage gameGenrePage;

    @Test
    public void DeleteGameGenre(){

        sideMenuPage = new SideMenuPage(driver);
        gameGenrePage = new gameGenrePage(driver);

        sideMenuPage.openGameGenresPage();
        gameGenrePage.deleteCategoryButton();
        gameGenrePage.confirmDeleteCategoryButton();
    }
}
