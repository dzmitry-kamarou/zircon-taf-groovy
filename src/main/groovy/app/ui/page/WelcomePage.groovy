package app.ui.page

import app.ui.Browser
import groovy.util.logging.Slf4j
import org.openqa.selenium.By

@Slf4j
class WelcomePage extends BasePage {

    private static final String LOGIN_BUTTON_XPATH = '//button[text()=\'Log In\']'

    void clickLoginButton() {
        log.debug 'Click \'Log In\' button'
        Browser.getDriver().findElement(By.xpath(LOGIN_BUTTON_XPATH)).click()
    }

    boolean isLoginButtonVisible() {
        Browser.getDriver().findElement(By.xpath(LOGIN_BUTTON_XPATH)).isDisplayed()
    }
}
