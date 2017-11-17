package Channels;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.channel.ChannelCategoryPage;
import page.menu.SideMenuPage;

public class DeleteChannelCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(ChannelCategoryPage.class);
    protected SideMenuPage sideMenuPage;
    protected ChannelCategoryPage channelCategoryPage;



    @Test
    public void deleteChannelCategory(){
        channelCategoryPage = new ChannelCategoryPage(driver);
        sideMenuPage = new SideMenuPage(driver);

        sideMenuPage.openChannelCategoriesPage();
        channelCategoryPage.deleteChannelCategory();
        channelCategoryPage.confirmDeleteChannelCategory();
    }


}
