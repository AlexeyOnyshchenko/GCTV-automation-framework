package Videos;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.video.VideoPage;
import page.menu.SideMenuPage;

public class AddYoutubeVideo extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(VideoPage.class);
    protected VideoPage videoPage;
    protected SideMenuPage sideMenuPage;

    @Test
    public void CreateYoutubeVideo() {

        sideMenuPage = new SideMenuPage(driver);
        videoPage = new VideoPage(driver);

        sideMenuPage.openVideosPage();
        videoPage.createNewVideoButton();
        videoPage.chooseYoutubeVideoSource();
        videoPage.fillYoutubeVideoURL();
        videoPage.chooseVideoCategory();
        videoPage.chooseVideoSharingStatus();
        videoPage.saveYoutubeVideo();
    }
}








