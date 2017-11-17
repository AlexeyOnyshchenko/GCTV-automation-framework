package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Created by olga on 11/10/16.
 */
public class CustomDefaultFieldDecorator extends DefaultFieldDecorator {

    public CustomDefaultFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    protected WebElement proxyForLocator(ClassLoader loader,
                                         ElementLocator locator) {
        InvocationHandler handler = new CustomLocatingElementHandler(locator);

        WebElement proxy;
        proxy = (WebElement) Proxy.newProxyInstance(loader, new Class[] {
                        WebElement.class, WrapsElement.class, Locatable.class },
                handler);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected List<WebElement> proxyForListLocator(ClassLoader loader,
                                                   ElementLocator locator) {
        InvocationHandler handler = new CustomLocatingElementListHandler(
                locator);

        List<WebElement> proxy;
        proxy = (List<WebElement>) Proxy.newProxyInstance(loader,
                new Class[] { List.class }, handler);
        return proxy;
    }

}
