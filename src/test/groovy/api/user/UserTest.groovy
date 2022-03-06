package api.user

import app.api.flow.UserFlow
import business.account.Account
import business.account.AccountFactory
import org.hamcrest.Matchers
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.allOf
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.is

class UserTest {

    @Test
    @Tag('C20')
    @Tag('api')
    @Tag('regression')
    void accountsCanBeRetrieved() {
        def account = AccountFactory.registeredUser()
        def accounts = new UserFlow().getAccounts()
        def reason = "Exists '${account.email}' account is in accounts list"
        assertThat reason, accounts, hasItem(allOf(
                Matchers.<Account> hasProperty('email', is(account.email)),
                Matchers.<Account> hasProperty('id', is(account.id))
        ))
    }
}
