package Channels;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.channel.ChannelCategoryPage;
import page.menu.SideMenuPage;

public class AddChannelCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(AddChannelCategory.class);
    protected ChannelCategoryPage channelCategoryPage;
    protected SideMenuPage sideMenuPage;

    @Test
    public void addChannel(){
        channelCategoryPage = new ChannelCategoryPage(driver);
        sideMenuPage = new SideMenuPage(driver);


        sideMenuPage.openChannelCategoriesPage();
        channelCategoryPage.createNewChannelCategory();
        channelCategoryPage.fillChannelCategoryTitle();
        channelCategoryPage.fillChannelCategoryDescription();
        channelCategoryPage.fillChannelCategoryOrder();
        channelCategoryPage.chooseCategorySharingStatus();
        channelCategoryPage.saveChannelCategory();
    }


}
