package business.account

import business.BusinessConfig
import business.role.Role
import com.github.javafaker.Faker

class AccountFactory {

    private static final BusinessConfig CONFIG = BusinessConfig.config

    private AccountFactory() {
    }

    static Account registeredUser() {
        new Account(
                id: Long.valueOf(CONFIG.registeredUserId()),
                email: CONFIG.registeredUserEmail(),
                password: CONFIG.registeredUserPassword(),
                role: Role.USER
        )
    }

    static Account randomAccount() {
        def faker = new Faker()
        new Account(email: faker.internet().emailAddress(), password: faker.internet().password())
    }

    static Account registeredAdmin() {
        new Account(
                id: Long.parseLong(CONFIG.registeredAdminId()),
                email: CONFIG.registeredAdminEmail(),
                password: CONFIG.registeredAdminPassword(),
                role: Role.USER
        )
    }
}
