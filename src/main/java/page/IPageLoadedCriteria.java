package page;

import com.google.common.base.Function;
import org.openqa.selenium.WebDriver;


public interface IPageLoadedCriteria {
    /*
	 * loaded criteria that will be implemented by each page
	 */
    public Function<WebDriver, ?> loadedCriteria();
}
