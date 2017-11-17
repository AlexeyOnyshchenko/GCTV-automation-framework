package driver;

import org.openqa.selenium.WebDriver;

public interface IWebDriverProvidable {

    WebDriver initBrowserDriver(WebBrowser webBrowser);

    void killDriverInstance();
}
