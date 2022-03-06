package app.ui.page

import app.ui.Browser
import groovy.util.logging.Slf4j
import org.openqa.selenium.By

@Slf4j
class LoginPage extends BasePage {

    private static final def EMAIL_FIELD_XPATH = '//input[@id=\'email\']'
    private static final def PASSWORD_FIELD_XPATH = '//input[@id=\'password\']'
    private static final def LOGIN_BUTTON_XPATH = '//button[@id=\'submit\']'
    private static final def REGISTRATION_LINK_XPATH = '//a[@id=\'registration\']'
    private static final def REGISTER_BUTTON_XPATH = '//button[text()=\'Register\']'

    LoginPage fillEmailField(String email) {
        log.debug "Enter '${email}' to 'email' field"
        Browser.getDriver().findElement(By.xpath(EMAIL_FIELD_XPATH)).sendKeys(email)
        return this
    }

    LoginPage fillPasswordField(String password) {
        log.debug "Enter '${password}' to 'password' field"
        Browser.getDriver().findElement(By.xpath(PASSWORD_FIELD_XPATH)).sendKeys password
        return this
    }

    void clickLoginButton() {
        log.debug 'Click \'Login\' button'
        Browser.getDriver().findElement(By.xpath(LOGIN_BUTTON_XPATH)).click()
    }

    void clickRegistrationLink() {
        log.debug 'Click \'Registration!\' link'
        Browser.getDriver().findElement(By.xpath(REGISTRATION_LINK_XPATH)).click()
    }

    void clickRegisterButton() {
        log.debug 'Click \'Register\' button'
        Browser.getDriver().findElement(By.xpath(REGISTER_BUTTON_XPATH)).click()
    }
}
