package Music;
import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.music.MusicCategoryPage;
import page.menu.SideMenuPage;


public class AddMusicVideoCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MusicCategoryPage.class);
    protected SideMenuPage sideMenuPage;
    protected MusicCategoryPage musicCategoryPage;


    @Test
    public void createCategory(){

        sideMenuPage = new SideMenuPage(driver);
        musicCategoryPage = new MusicCategoryPage(driver);

        sideMenuPage.openMusicCategoriesPage();
        musicCategoryPage.createNewMusicCategoryButton();
        musicCategoryPage.fillMusicCategoryFields();
        musicCategoryPage.chooseMusicCategorySharingOption();
        musicCategoryPage.clickOnSubmitButton();


    }


}
