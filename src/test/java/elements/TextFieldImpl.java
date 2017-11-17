package elements;

import org.openqa.selenium.WebElement;

/**
 * Created by user on 7/16/17.

public class TextFieldImpl extends Browser implements ITextField {

    private WebElement element;

    public TextField(String xpath) {
        element = By.xpath(xpath);
    }

    private typeText(String text){
        driver.sendKeys(text);
    }
}
 */