package Music;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.music.MusicVideoPage;
import page.menu.SideMenuPage;

public class AddVimeoMusicVideo extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MusicVideoPage.class);

    protected SideMenuPage sideMenuPage;
    protected MusicVideoPage musicVideoPage;

    @Test
    public void addVimeoMusicVideo(){

        sideMenuPage = new SideMenuPage(driver);
        musicVideoPage = new MusicVideoPage(driver);

        sideMenuPage.openMusicVideoPage();
        musicVideoPage.createNewMusicVideoButton();
        musicVideoPage.chooseVimeoVideoSource();
        musicVideoPage.fillVimeoURL();
        musicVideoPage.chooseVideoCategory();
        musicVideoPage.chooseSharingStatus();
        musicVideoPage.setVideoOrder();
        musicVideoPage.saveVideoButton();



    }

}
