package listeners;


import driver.WebBrowser;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class TestListenerUtil {
    private static final Logger LOG = LogFactory
            .getLogger(TestListenerUtil.class);

    /**
     * get full test name
     *
     * @param result
     *            result
     * @return full test name
     */
    public static String getFullTestName(ITestResult result) {
        String testName = result.getTestClass().getRealClass().getName() + "."
                + getTestName(result);
        if (result.getParameters() != null && result.getParameters().length > 0) {
            testName += result.getParameters()[0];
        }

        return testName;
    }

    /**
     * get test name
     *
     * @param result
     *            result
     * @return test name
     */
    public static String getTestName(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        return testName;
    }

    public static String getTestMethod(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        return testName;
    }

    /**
     * takes browser screenshot
     *
     * @param driver
     *            driver
     * @return buffered image
     */
    static BufferedImage takeWebBrowserScreenshot(final WebDriver driver) {
        AShot ashot = new AShot().coordsProvider(new WebDriverCoordsProvider());
        Screenshot screenImage = ashot.takeScreenshot(driver);
        return screenImage.getImage();

		/*
		 * if (isRetinaScreen()) { screenImage =
		 * ashot.dpr(2).takeScreenshot(driver); return screenImage.getImage(); }
		 */
    }

    // TODO handle FF also in future, now should work for Chrome only,
    // because there are issues with Chrome Driver on Retina, FF works fine fow
    // now
    @SuppressWarnings("unused")
    private static boolean isRetinaScreen() {
        if (WebBrowser.getCreatedWebBrowser().getBrowserName().equals("chrome")) {
            GraphicsEnvironment env = GraphicsEnvironment
                    .getLocalGraphicsEnvironment();
            final GraphicsDevice device = env.getDefaultScreenDevice();

            try {
                Field field = device.getClass().getDeclaredField("scale");

                if (field != null) {
                    field.setAccessible(true);
                    Object scale = field.get(device);

                    if (scale instanceof Integer
                            && ((Integer) scale).intValue() == 2) {
                        return true;
                    }
                }
            } catch (Exception ignore) {
            }

            return false;
        } else
            return false;
    }

    /**
     * get byte array from Buffered image
     *
     * @param image
     *            buffered image
     * @param format
     *            image format
     * @return byte array from image
     */
    public static byte[] getByteArrayFromImage(BufferedImage image,
                                               String format) {
        LOG.debug("Converting screenshot to byte array for report attachment");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, format, baos);
            LOG.info("Successfully wrote screenshot to byte array output stream");
        } catch (IOException e) {
            LOG.error("Cannot write screenshot to ByteArrayOutputStream");
        }
        byte[] imageBytes = baos.toByteArray();

        if (imageBytes.length == 0) {
            String errorMessage = "Converted byte array for screenshot is empty.";
            LOG.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }
        LOG.info("Converted image screenshot to byte array. Byte array size is: "
                + imageBytes.length);
        return imageBytes;
    }
}
