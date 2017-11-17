package Channels;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.channel.ChannelPage;
import page.menu.SideMenuPage;

public class DeleteChannel extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(ChannelPage.class);
    protected SideMenuPage sideMenuPage;
    protected ChannelPage channelPage;

    @Test
    public void DeleteChannel() {
        sideMenuPage = new SideMenuPage(driver);
        channelPage =  new ChannelPage(driver);

        sideMenuPage.openChannelsPage();
        channelPage.deleteChannel();
        channelPage.confirmDeleteChannel();
    }
}
