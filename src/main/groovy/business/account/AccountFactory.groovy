package business.account

import business.BusinessConfig
import business.role.Role
import com.github.javafaker.Faker

class AccountFactory {

    private static final BusinessConfig CONFIG = BusinessConfig.config

    private AccountFactory() {
    }

    static Account registeredUser() {
        def account = new Account()
        account.id = Long.valueOf CONFIG.registeredUserId()
        account.email = CONFIG.registeredUserEmail()
        account.password = CONFIG.registeredUserPassword()
        account.role = Role.USER
        account
    }

    static Account randomAccount() {
        def account = new Account()
        def faker = new Faker()
        account.email = faker.internet().emailAddress()
        account.password = faker.internet().password()
        account
    }
}
