package Movies;

import base.TestBase;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.annotations.Test;
import page.movies.MovieCategoryPageAlexey;
import page.menu.SideMenuPage;

public class AddMovieCategory extends TestBase {
    private static final Logger LOG = LogFactory.getLogger(MovieCategoryPageAlexey.class);

    protected SideMenuPage sideMenuPage;
    protected MovieCategoryPageAlexey movieCategoryPageAlexey;

    @Test
    public void createMovieCategory(){

        sideMenuPage = new SideMenuPage(driver);
        movieCategoryPageAlexey = new MovieCategoryPageAlexey(driver);

        sideMenuPage.openMovieCategoriesPage();
        movieCategoryPageAlexey.createMovieCategory();
        movieCategoryPageAlexey.fillMovieCategoryFields();
        movieCategoryPageAlexey.chooseMovieCategorySharingOption();
        movieCategoryPageAlexey.clickOnSubmitButton();
    }
}
