package api.user

import app.api.flow.UserFlow
import business.account.AccountFactory
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

class FindTest {

    @Test
    @Tag('C13')
    @Tag('api')
    @Tag('regression')
    @Tag('smoke')
    void registeredUserCanBeFound() {
        def account = AccountFactory.registeredUser()
        def found = new UserFlow().findAccount account
        def reason = "Registered '${account.email}' account was found"
        assertThat reason, found.email, is(account.email)
    }
}
