package app.ui

import app.ZirconConfig
import groovy.util.logging.Slf4j
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver

import java.util.concurrent.TimeUnit
import org.openqa.selenium.WebDriver

@Slf4j
class Browser {

    private static final ZirconConfig ZIRCON_CONFIG = ZirconConfig.config
    private static final String ZIRCON_URL = ZIRCON_CONFIG.uiUri()
    private static final int IMPLICIT_WAIT_TIMEOUT = 10
    private static WebDriver driver

    static void up() {
        getDriver().manage().window().maximize()
    }

    static void down() {
        getDriver().close()
        getDriver().quit()
        driver = null
    }

    static boolean isDriverNull() {
        return driver == null
    }

    static WebDriver getDriver() {
        def browser = System.getProperty 'browser'
        if (isDriverNull()) {
            switch (browser) {
                case ('firefox'):
                    driver = initFireFox()
                    break
                case ('chrome'):
                    driver = initChrome()
                    break
                default:
                    break
            }
            driver.manage().timeouts().implicitlyWait IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS
        }
        return driver
    }

    private static WebDriver initFireFox() {
        System.setProperty('webdriver.gecko.driver', System.getenv('geckodriver'))
        return new FirefoxDriver()
    }

    private static WebDriver initChrome() {
        System.setProperty('webdriver.chrome.driver', System.getenv('chromedriver'))
        ChromeOptions chromeOptions = new ChromeOptions()
        // chromeOptions.setBinary(System.getenv('chromebinary'))
        return new ChromeDriver(chromeOptions)
    }

    static void loadApp() {
        log.debug "Load ${ZIRCON_URL}"
        getDriver().get ZIRCON_URL
    }
}
