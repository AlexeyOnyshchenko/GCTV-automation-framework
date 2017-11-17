package Channels;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.channel.ChannelPage;
//import page.login.MainPage;
import page.menu.SideMenuPage;


public class AddChannel extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(ChannelPage.class);
    protected SideMenuPage sideMenuPage;
    protected ChannelPage channelPage;

    @Test
    public void AddChannel(){

        sideMenuPage = new SideMenuPage(driver);
        channelPage =  new ChannelPage(driver);

        sideMenuPage.openChannelsPage();
        channelPage.createNewChannel();
        channelPage.fillChannelURL();
        channelPage.fillChannelTitle();
        channelPage.fillChannelDescription();
        channelPage.chooseChannelCategory();
        channelPage.chooseChannelSharing();
        channelPage.saveChannel();
        //new LoginPage().
           //     authenticate("user","pass").
              //  openSettings().
              //  openFaqs().
              //  findUsreById().
              //  asserTitle("tile");

       // LoginPage loginPage = new LogginPage();
       // loginPage.authenticate();
       // MainPage mainPage = new MainPage();
        //mainPage.doSomething();
    }

}
