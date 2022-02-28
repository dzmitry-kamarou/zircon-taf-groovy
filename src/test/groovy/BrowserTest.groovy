import app.ui.Browser
import app.ui.flow.DashboardPageFlow
import app.ui.flow.WelcomePageFlow
import business.account.AccountFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class BrowserTest {

    @BeforeEach
    void setUp() {
        Browser.up()
        Browser.loadApp()
    }

    @Test
    @Tag('smoke')
    @Tag('regression')
    void registeredUserCanLogIn() {
        new WelcomePageFlow().login().login AccountFactory.registeredUser()
        assertEquals true, new DashboardPageFlow().exitButtonVisible(), 'Registered account has logged in'
    }

    @AfterEach
    void tearDown() {
        Browser.down()
    }
}
