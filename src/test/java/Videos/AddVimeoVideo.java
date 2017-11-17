package Videos;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.video.VideoPage;
import page.menu.SideMenuPage;

public class AddVimeoVideo extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(VideoPage.class);
    protected VideoPage videoPage;
    protected SideMenuPage sideMenuPage;

    @Test
    public void createVimeoVideo() {

        sideMenuPage = new SideMenuPage(driver);
        videoPage = new VideoPage(driver);

        sideMenuPage.openVideosPage();
        videoPage.createNewVideoButton();
        videoPage.chooseVimeoVideoSource();
        videoPage.fillVimeoVideoURL();
        videoPage.chooseVideoCategory();
        videoPage.chooseVideoSharingStatus();
        videoPage.saveYoutubeVideo();

    }
}