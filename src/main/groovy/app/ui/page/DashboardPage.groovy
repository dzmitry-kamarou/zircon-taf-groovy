package app.ui.page

import app.ui.Browser
import groovy.util.logging.Slf4j
import org.openqa.selenium.By

@Slf4j
class DashboardPage extends BasePage {

    private static final def ADMIN_PANEL_BUTTON_XPATH = '//button[@id=\'admin\']'
    private static final def EXIT_BUTTON_XPATH = '//button[@id=\'exit\']'

    boolean isExitButtonVisible() {
        Browser.getDriver().findElement(By.xpath(EXIT_BUTTON_XPATH)).isDisplayed()
    }

    void clickExitButton() {
        Browser.getDriver().findElement(By.xpath(EXIT_BUTTON_XPATH)).click()
    }

    boolean isAdminPanelButtonVisible() {
        try {
            Browser.getDriver().findElement(By.xpath(ADMIN_PANEL_BUTTON_XPATH)).isDisplayed()
        } catch (Exception e) {
            e.printStackTrace()
            false
        }
    }
}
