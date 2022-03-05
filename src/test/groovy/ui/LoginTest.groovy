package ui

import app.ui.Browser
import app.ui.flow.DashboardPageFlow
import app.ui.flow.WelcomePageFlow
import business.account.AccountFactory
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.junit.jupiter.api.Assertions.assertEquals

class LoginTest {

    @BeforeEach
    void setUp() {
        Browser.up()
        Browser.loadApp()
    }

    @Test
    @Tag('C1')
    @Tag('smoke')
    @Tag('regression')
    void registeredUserCanLogIn() {
        new WelcomePageFlow().login().login AccountFactory.registeredUser()
        assertEquals true, new DashboardPageFlow().exitButtonVisible(), 'Registered account has logged in'
    }

    @Test
    @Tag('C2')
    @Tag('smoke')
    @Tag('regression')
    void registeredUserCantLoginWithWrongPassword() {
        def account = AccountFactory.registeredUser()
        account.setPassword('wrong')
        new WelcomePageFlow().login().login(account)
        def text = Browser.getAlertText()
        Browser.acceptAlert()
        def reason = 'Registered account has not logged in with wrong password'
        assertThat(reason, text, is('Wrong password'))
    }

    @AfterEach
    void tearDown() {
        Browser.down()
    }
}
