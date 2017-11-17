package Movies;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.movies.MoviePageAlexey;
import page.menu.SideMenuPage;

public class AddYoutubeMovie extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MoviePageAlexey.class);
    protected SideMenuPage sideMenuPage;
    protected MoviePageAlexey moviePage;

    @Test
    public void addYoutubeMovie(){

        sideMenuPage = new SideMenuPage(driver);
        moviePage = new MoviePageAlexey(driver);

        sideMenuPage.openMoviesPage();
        moviePage.createNewMovieVideoButton();
        moviePage.chooseYoutubeMovieSource();
        moviePage.fillYoutubeURL();
        moviePage.chooseMovieCategory();
        moviePage.chooseSharingStatus();
        moviePage.saveMovieButton();
    }

}
