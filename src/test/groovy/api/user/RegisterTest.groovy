package api.user

import app.api.flow.UserFlow
import business.account.Account
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

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

    private static Stream<Account> wrongAccountDataProvider() {
        Stream.of(new Account(email: ''), new Account(password: ''))
    }
}
