package Videos;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.video.VideoPage;
import page.menu.SideMenuPage;

public class DeleteVideo extends TestBase {

    private static final Logger LOG = LogFactory.getLogger(VideoPage.class);
    protected VideoPage videoPage;
    protected SideMenuPage sideMenuPage;

    @Test
    public void deleteVideoAsSuperAdmin(){

        videoPage = new VideoPage(driver);
        sideMenuPage = new SideMenuPage(driver);

        sideMenuPage.openVideosPage();
        videoPage.deleteVideoButton();
        videoPage.confirmDeleteVideoButton();

    }


}
