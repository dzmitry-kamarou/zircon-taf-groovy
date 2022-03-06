package api.user

import app.api.flow.UserFlow
import business.account.AccountFactory
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.Matchers.is
import static org.hamcrest.text.MatchesPattern.matchesPattern

import static org.hamcrest.MatcherAssert.assertThat

class AuthTest {

    @Test
    @Tag('C11')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void loggedInUserRetrievesToken() {
        def account = AccountFactory.registeredUser()
        def userFlow = new UserFlow()
        userFlow.loginAccount account
        def token = userFlow.authAccount account
        def reason = "Token generated for logged in '${account.getEmail()}' account"
        def jwtPattern = '[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*'
        assertThat reason, token, matchesPattern(jwtPattern)
    }

    @Test
    @Tag('C12')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void notLoggedInUserNotRetrievesToken() {
        def account = AccountFactory.registeredUser()
        def message = new UserFlow().authAccount account
        def reason = "Token not generated for not logged in '${account.getEmail()}' account"
        assertThat reason, message, is('Not authorized')
    }
}
