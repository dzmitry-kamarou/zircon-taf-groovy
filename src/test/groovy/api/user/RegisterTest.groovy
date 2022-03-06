package api.user

import app.api.flow.UserFlow
import business.account.Account
import business.account.AccountFactory
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

class RegisterTest {

    @Tag('C18')
    @Tag('api')
    @Tag('regression')
    @ParameterizedTest
    @MethodSource("wrongAccountDataProvider")
    void wrongAccountCantBeRegistered(Account account) {
        def message = new UserFlow().registerAccount account
        def reason = 'Account with blank email not registered'
        assertThat reason, message, is('Incorrect email or password')
    }

    @Test
    @Tag('C19')
    @Tag('api')
    @Tag('regression')
    void theSameAccountCantBeRegisteredTwice() {
        def account = AccountFactory.registeredUser()
        def userFlow = new UserFlow()
        def message = userFlow.registerAccount account
        def reason = 'Account with the same email can\'t be registered'
        assertThat reason, message, is('There is already user with the same email')
    }

    private static List<Account> wrongAccountDataProvider() {
        [new Account(email: ''), new Account(password: '')]
    }
}
