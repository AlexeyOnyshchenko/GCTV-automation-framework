package Music;

import base.TestBase;
import org.testng.annotations.Test;
import page.music.MusicSinglePage;
import page.menu.SideMenuPage;


public class AddMusicAudioSingle extends TestBase {
    protected SideMenuPage sideMenuPage;
    protected MusicSinglePage musicSinglePage;

    @Test

    public void addMusicSingle(){

        sideMenuPage = new SideMenuPage(driver);
        musicSinglePage = new MusicSinglePage(driver);

        sideMenuPage.openMusicAudioPage();
        musicSinglePage.createNewAudioButton();
        musicSinglePage.uploadAudioFile();
    }



}
