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
        account.setPassword 'wrong'
        new WelcomePageFlow().login().login account
        def text = Browser.getAlertText()
        Browser.acceptAlert()
        def reason = 'Registered account has not logged in with wrong password'
        assertThat reason, text, is('Wrong password')
    }

    @Test
    @Tag('C3')
    @Tag('smoke')
    @Tag('regression')
    void unregisteredUserCantLogin() {
        def account = AccountFactory.randomAccount()
        new WelcomePageFlow().login().login account
        def text = Browser.getAlertText()
        Browser.acceptAlert()
        def reason = 'Unregistered account has not logged in'
        assertThat reason, text, is('The user was not found')
    }

    @Test
    @Tag('C4')
    @Tag('smoke')
    @Tag('regression')
    void loggedInUserCanLogout() {
        def account = AccountFactory.registeredUser()
        new WelcomePageFlow().login().login(account).exit()
        def reason = 'Logged in account has logged out'
        assertThat reason, new WelcomePageFlow().loginButtonVisible(), is(true)
    }

    @Test
    @Tag('C5')
    @Tag('smoke')
    @Tag('regression')
    void loggedInAdminSeeAdminPanelButton() {
        def account = AccountFactory.registeredAdmin()
        def dashboardPageFlow = new WelcomePageFlow().login().login account
        def reason = 'Logged in admin saw \'Admin Panel\' button'
        assertThat reason, dashboardPageFlow.adminPanelButtonVisible(), is(true)
    }

    @AfterEach
    void tearDown() {
        Browser.down()
    }
}
