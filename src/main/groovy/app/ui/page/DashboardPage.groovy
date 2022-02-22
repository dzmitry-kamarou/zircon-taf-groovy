package app.ui.page

import app.ui.Browser
import org.openqa.selenium.By

class DashboardPage extends BasePage {

    private static final def EXIT_BUTTON_XPATH = '//button[@id=\'exit\']'

    boolean isExitButtonVisible() {
        return Browser.getDriver().findElement(By.xpath(EXIT_BUTTON_XPATH)).isDisplayed()
    }
}
