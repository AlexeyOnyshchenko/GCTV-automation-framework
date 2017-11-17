package Movies;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.movies.MovieCategoryPageAlexey;
import page.menu.SideMenuPage;

public class DeleteMovieCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MovieCategoryPageAlexey.class);
    protected SideMenuPage sideMenuPage;
    protected MovieCategoryPageAlexey movieCategoryPageAlexey;

    @Test
    public void deleteMovieCategory() {

        sideMenuPage = new SideMenuPage(driver);
        movieCategoryPageAlexey = new MovieCategoryPageAlexey(driver);

        sideMenuPage.openMovieCategoriesPage();
        movieCategoryPageAlexey.deleteCategoryButton();
        movieCategoryPageAlexey.confirmDeleteCategoryButton();


    }
}
