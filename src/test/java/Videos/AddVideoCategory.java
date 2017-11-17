package Videos;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.menu.SideMenuPage;
import page.video.VideoCategoryPage;



public class AddVideoCategory extends TestBase {

    private static final Logger LOG = LogFactory.getLogger(VideoCategoryPage.class);
    protected VideoCategoryPage videoCategoryPage;
    protected SideMenuPage sideMenuPage;




    @Test
    public void createVideoCategory() {


        sideMenuPage = new SideMenuPage(driver);
        videoCategoryPage =  new VideoCategoryPage(driver);

        sideMenuPage.openVideoCategoriesPage();
        videoCategoryPage.createVideoCategoryButton();
        videoCategoryPage.fillVideoCategoryInfo();
        videoCategoryPage.chooseCategorySharingStatus();
        videoCategoryPage.saveVideoCategory();


}

    }






