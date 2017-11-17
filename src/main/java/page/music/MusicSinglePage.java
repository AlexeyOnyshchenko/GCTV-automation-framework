package page.music;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MusicSinglePage {

    //private By uploadAudioFile = By.xpath("//*[@ng-repeat='field in ::formController.fields track by $index']//*[text()='Select an audio']");

    public WebDriver driver;

    public MusicSinglePage (WebDriver driver){
        this.driver = driver;
    }

    public void createNewAudioButton(){
        WebElement createNewAudioButton = driver.findElement(By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a"));
        createNewAudioButton.click();
    }

    public void uploadAudioFile(){
WebElement uploadFile = driver.findElement(By.xpath("//*[@ng-repeat='field in ::formController.fields track by $index']//*[text()='Select an audio']"));
        //uploadFile.click();
        uploadFile.sendKeys("/Users/user/Downloads/childLifeTV/src/main/resources/test-file-upload-data/alkalino–lucky_fellow_(original_mix).mp3");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }








    //to be continued after file uploading investigation


            //"//*[@ng-repeat='field in ::formController.fields track by $index']//*[text()='Select an audio']"










    //field in ::formController.fields track by $index


}
