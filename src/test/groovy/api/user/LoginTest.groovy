package api.user

import app.api.flow.UserFlow
import business.account.AccountFactory
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is
import static org.hamcrest.text.MatchesPattern.matchesPattern

class LoginTest {

    @Test
    @Tag('C15')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void registeredUserRetrievesToken() {
        def account = AccountFactory.registeredUser()
        def reason = "Token generated for '${account.email}' account"
        def token = new UserFlow().loginAccount account
        def jwtPattern = '[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.?[A-Za-z0-9-_.+/=]*'
        assertThat reason, token, matchesPattern(jwtPattern)
    }

    @Test
    @Tag('C16')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void unregisteredUserCantLogin() {
        def account = AccountFactory.randomAccount()
        def reason = "Unregistered '${account.email}' account can't get token"
        def message = new UserFlow().loginAccount account
        assertThat reason, message, is('The user was not found')
    }

    @Test
    @Tag('C17')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void registeredUserWithWrongPasswordCantLogin() {
        def account = AccountFactory.registeredUser()
        account.password = 'wrong'
        def reason = "Registered '${account.email}' account with wrong password can't get token"
        def message = new UserFlow().loginAccount account
        assertThat reason, message, is('Wrong password')
    }
}
