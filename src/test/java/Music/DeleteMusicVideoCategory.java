package Music;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.menu.SideMenuPage;
import page.music.MusicCategoryPage;


public class DeleteMusicVideoCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MusicCategoryPage.class);
    protected SideMenuPage sideMenuPage;
    protected MusicCategoryPage musicCategoryPage;

    @Test
    public void DeleteCategory(){
        sideMenuPage = new SideMenuPage(driver);
        musicCategoryPage = new MusicCategoryPage(driver);

        sideMenuPage.openMusicCategoriesPage();
        musicCategoryPage.deleteCategoryButton();
        musicCategoryPage.confirmDeleteCategoryButton();

    }

}
