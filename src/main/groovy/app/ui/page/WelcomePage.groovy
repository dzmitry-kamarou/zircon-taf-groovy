package app.ui.page

import app.ui.Browser
import org.openqa.selenium.By

class WelcomePage extends BasePage {

    private static final String LOGIN_BUTTON_XPATH = '//button[text()=\'Log In\']'

    void clickLoginButton() {
        logger.debug 'Click \'Log In\' button'
        Browser.getDriver().findElement(By.xpath(LOGIN_BUTTON_XPATH)).click()
    }
}
