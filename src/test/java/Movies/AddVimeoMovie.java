package Movies;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.movies.MoviePageAlexey;
import page.menu.SideMenuPage;

public class AddVimeoMovie extends TestBase{
    private static final Logger LOG = LogFactory.getLogger(MoviePageAlexey.class);
    protected SideMenuPage sideMenuPage;
    protected MoviePageAlexey moviePage;

    @Test
    public void addVimeoMovie(){

        sideMenuPage = new SideMenuPage(driver);
        moviePage = new MoviePageAlexey(driver);

        sideMenuPage.openMoviesPage();
        moviePage.createNewMovieVideoButton();
        moviePage.chooseVimeoMovieSource();
        moviePage.fillVimeoURL();
        moviePage.chooseMovieCategory();
        moviePage.chooseSharingStatus();
        moviePage.saveMovieButton();

    }


}
