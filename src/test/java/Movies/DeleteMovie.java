package Movies;

import base.TestBase;
import org.testng.annotations.Test;
import page.movies.MoviePageAlexey;
import page.menu.SideMenuPage;

public class DeleteMovie extends TestBase  {
    protected SideMenuPage sideMenuPage;
    protected MoviePageAlexey moviePage;

    @Test
    public void deleteMovie(){

        sideMenuPage = new SideMenuPage(driver);
        moviePage = new MoviePageAlexey(driver);

        sideMenuPage.openMoviesPage();
        moviePage.deleteMovieButton();
        moviePage.confirmDeleteMovieButton();

    }
}
