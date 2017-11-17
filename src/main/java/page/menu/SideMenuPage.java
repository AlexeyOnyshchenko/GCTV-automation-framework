package page.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by user on 4/11/17.
 */
public class SideMenuPage {


    public WebDriver driver;
    private WebDriverWait wait;

    //List of videos
    private By videoMenuDropdown = By.xpath(".//*[@id='side-menu']/li[2]/a");
    private By listOfVideos = By.xpath(".//*[@id='side-menu']/li[2]/ul/li[2]/a");
    //Video categories
    private By listOfVideoCategories = By.xpath(".//*[@id='side-menu']/li[2]/ul/li[1]/a");

    //List of movies
    private By movieMenuDropdown = By.xpath(".//*[@id='side-menu']/li[1]/a");
    private By listOfMovies = By.xpath(".//*[@id='side-menu']/li[1]/ul/li[2]/a");
    //Movie categories
    private By listOfMovieCategories = By.xpath(".//*[@id='side-menu']/li[1]/ul/li[1]/a");

    //List of channels
    private By channelMenuDropdown = By.xpath(".//*[@id='side-menu']/li[3]/a");
    private By listOfChannels = By.xpath(".//*[@id='side-menu']/li[3]/ul/li[2]/a");
    //Channel categories
    private By listOfChannelCategories = By.xpath(".//*[@id='side-menu']/li[3]/ul/li[1]/a");

    //List of games
    private By gamesMenuDropdown = new By.ByLinkText("Games");
    private By listOfGames = new By.ByLinkText("Games");
    //Game Genres
    private By listOfGameGenres = new By.ByLinkText("Game Genres");

    //List of music videos
    private By musicMenuDropdown = new By.ByLinkText("Music");
    private By listOfMusicVideos = new By.ByLinkText("Music Video");
    //List of music audio
    private By listOfMusicAudio = new By.ByLinkText("Music Audio");
    //List of music video categories
    private By listOfMusicVideoCategories = new By.ByLinkText("Music Video Categories");

    //List of Offline Events
    private By eventsDropdown = new By.ByLinkText("Events calendar");
    private By listOfOfflineEvents = new By.ByLinkText("In Person/Offline Event");
    //List of Schedule broadcast events
    private By listOfBroadcastEvents = new By.ByLinkText("Schedule broadcast event");

    //List of education videos
    private By educationMenuDropdown = new By.ByLinkText("Education");
    //List of education video categories
    private By listOfEducationVideoCategories = new By.ByLinkText("Education Video Categories");
    //List of education videos
    private By listOfEducationVideos = new By.ByLinkText("Education Video");



    public SideMenuPage(WebDriver driver) {
        this.driver = (driver);
        this.wait = new WebDriverWait(driver, 5);
    }

    private void click (final By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();

    }

    //Videos
    public void openVideosPage(){
        click(videoMenuDropdown);
        click(listOfVideos);

    }

    //Video categories
    public void openVideoCategoriesPage(){
        click(videoMenuDropdown);
        click(listOfVideoCategories);
    }

    //Movies
    public void openMoviesPage(){
        click(movieMenuDropdown);
        click(listOfMovies);
    }

    //Movie categories
    public void openMovieCategoriesPage(){
        click(movieMenuDropdown);
        click(listOfMovieCategories);
    }

    //Channels
    public void openChannelsPage(){
        click(channelMenuDropdown);
        click(listOfChannels);
    }

    //Channel categories
    public void openChannelCategoriesPage(){
       click(channelMenuDropdown);
        click(listOfChannelCategories);
    }

    //Games
    public void openGamesPage(){
        click(gamesMenuDropdown);
        click(listOfGames);
    }
    //Game Genres
    public void openGameGenresPage(){
        click(gamesMenuDropdown);
        click(listOfGameGenres);
    }

    //Music videos
    public void openMusicVideoPage(){
     click(musicMenuDropdown);
     click(listOfMusicVideos);
    }

    //Music audio
    public void openMusicAudioPage(){
        click(musicMenuDropdown);
        click(listOfMusicAudio);
    }

    //Music video categories
    public void openMusicCategoriesPage(){
        click(musicMenuDropdown);
        click(listOfMusicVideoCategories);
    }

    //Offline events
    public void openOfflineEventsPage(){
        click(eventsDropdown);
        click(listOfOfflineEvents);
    }

    //Broadcast events
    public void openBroadcastEventsPage(){
        click(eventsDropdown);
        click(listOfBroadcastEvents);
    }

    //Education video categories
    public void openEducationVideoCategoriesPage(){
        click(educationMenuDropdown);
        click(listOfEducationVideoCategories);
    }

    //Education video
    public void openEducationVideoPage() {
        click(educationMenuDropdown);
        click(listOfEducationVideos);
    }


















}
