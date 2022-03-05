package ui

import app.api.flow.UserFlow
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

class RegisterTest {

    @BeforeEach
    void setUp() {
        Browser.up()
        Browser.loadApp()
    }

    @Test
    @Tag('C7')
    @Tag('smoke')
    @Tag('regression')
    void newAccountCanBeRegistered() {
        def account = AccountFactory.randomAccount()
        def reason = 'Registered account has logged in'
        try {
            new WelcomePageFlow().login().registration().register account
            assertThat reason, new DashboardPageFlow().exitButtonVisible(), is(true)
        } finally {
            def userFlow = new UserFlow()
            userFlow.deleteAccount userFlow.findAccount(account)
        }
    }

    @AfterEach
    void tearDown() {
        Browser.down()
    }
}
