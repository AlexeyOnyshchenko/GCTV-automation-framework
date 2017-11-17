package Music;

import base.TestBase;
import org.testng.annotations.Test;
import page.music.MusicVideoPage;
import page.menu.SideMenuPage;

public class DeleteMusicVideo extends TestBase{

    protected SideMenuPage sideMenuPage;
    protected MusicVideoPage musicVideoPage;


    @Test
    public void deleteMusicVideo() {

        sideMenuPage = new SideMenuPage(driver);
        musicVideoPage = new MusicVideoPage(driver);

        sideMenuPage.openMusicVideoPage();
        musicVideoPage.deleteVideoButton();
        musicVideoPage.confirmDeleteButton();

    }


}
